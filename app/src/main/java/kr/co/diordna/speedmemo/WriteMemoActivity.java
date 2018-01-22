package kr.co.diordna.speedmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;

import kr.co.diordna.speedmemo.database.DBProvider;
import kr.co.diordna.speedmemo.model.Memo;
import kr.co.diordna.speedmemo.utils.AppConstant;

/**
 * Created by ryans on 2018-01-21.
 */

public class WriteMemoActivity extends AppCompatActivity implements View.OnClickListener{

    private Memo mMemo = null;
    private DBProvider mDBProvider;

    private ImageView iv_back_btn;
    private ImageView iv_remove_btn;
    private ImageView iv_save_btn;
    private EditText et_content;
    private TextView tv_update_time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_memo);
        initView();
        initData();
    }

    private void initView() {
        iv_back_btn = findViewById(R.id.iv_back_btn);
        iv_remove_btn = findViewById(R.id.iv_remove_btn);
        iv_save_btn = findViewById(R.id.iv_save_btn);
        et_content = findViewById(R.id.et_content);
        tv_update_time = findViewById(R.id.tv_update_time);

        et_content.setOnClickListener(this);
        iv_back_btn.setOnClickListener(this);
        iv_remove_btn.setOnClickListener(this);
        iv_save_btn.setOnClickListener(this);
    }

    private void initData() {
        mDBProvider = new DBProvider(this, DBProvider.DB_VERSION);

        try {
            Intent i = getIntent();
            mMemo = (Memo) i.getSerializableExtra("memo");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mMemo != null) {
            et_content.setText(mMemo.getContent());
            et_content.setCursorVisible(false);
            tv_update_time.setText(mMemo.getUpdateAt().toString(AppConstant.DEFAULT_DATE_FORMAT));
        } else {
            tv_update_time.setText(new DateTime().toString(AppConstant.DEFAULT_DATE_FORMAT));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_content:
                et_content.setCursorVisible(true);
                break;
            case R.id.iv_back_btn:

                break;
            case R.id.iv_remove_btn:
                deleteMemo();
                break;
            case R.id.iv_save_btn:
                saveMemo();
                break;
        }
    }

    private void saveMemo() {
        if (mMemo != null) {
            Memo updateMemo = new Memo();
            updateMemo.setIndex(mMemo.getIndex());
            updateMemo.setContent(et_content.getText().toString());
            mDBProvider.updateMemo(updateMemo);
        } else {
            mDBProvider.insertMemo(et_content.getText().toString());
        }

        finish();
    }

    private void deleteMemo() {
        if (mMemo.getIndex() == -1) {
            Toast.makeText(this, "삭제할 메모가 없습니다.", Toast.LENGTH_SHORT).show();
        } else {
            mDBProvider.deleteMemo(mMemo.getIndex());
        }

        finish();
    }
}
