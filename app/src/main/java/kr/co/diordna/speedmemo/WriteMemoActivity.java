package kr.co.diordna.speedmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import kr.co.diordna.speedmemo.database.DBProvider;
import kr.co.diordna.speedmemo.model.Memo;

/**
 * Created by ryans on 2018-01-21.
 */

public class WriteMemoActivity extends AppCompatActivity implements View.OnClickListener{

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

        Memo memo = null;

        try {
            Intent i = getIntent();
            memo = (Memo) i.getSerializableExtra("memo");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (memo != null) {
            et_content.setText(memo.getContent());
            et_content.setCursorVisible(false);
            tv_update_time.setText(memo.getUpdateAt().toString("yyyy-MM-dd"));
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
                showMemo();
                break;
            case R.id.iv_save_btn:
                saveMemo();
                break;
        }
    }

    private void saveMemo() {
        mDBProvider.insertMemo(et_content.getText().toString());
    }

    private void showMemo() {
        mDBProvider.selectAllMemo();
    }
}
