package kr.co.diordna.speedmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import kr.co.diordna.speedmemo.utils.AppConstant;
import kr.co.diordna.speedmemo.utils.MyPref;

/**
 * Created by ryans on 2018-01-30.
 */

public class SelectFunctionActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean mIsFirstSelect = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_function);

        mIsFirstSelect = MyPref.getInstance().getBoolean(AppConstant.IS_FIRST, true);

        findViewById(R.id.li_write_btn).setOnClickListener(this);
        findViewById(R.id.li_read_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.li_write_btn:
                MyPref.getInstance().putBoolean(AppConstant.APP_MODE, true);

                if (mIsFirstSelect) {
                    Intent i = new Intent(this, WriteMemoActivity.class);
                    i.putExtra(WriteMemoActivity.IS_FIRST_ACTIVITY_KEY, true);
                    startActivity(i);
                } else {
                    Toast.makeText(this, "쓰기 우선으로 저장되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }

                break;
            case R.id.li_read_btn:
                MyPref.getInstance().putBoolean(AppConstant.APP_MODE, false);

                if (mIsFirstSelect) {
                    startActivity(new Intent(this, MemoMainActivity.class));
                } else {
                    Toast.makeText(this, "읽기 우선으로 저장되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }

                break;
        }

        MyPref.getInstance().putBoolean(AppConstant.IS_FIRST, false);
        finish();
    }
}
