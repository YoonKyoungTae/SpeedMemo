package kr.co.diordna.speedmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kr.co.diordna.speedmemo.utils.AppConstant;
import kr.co.diordna.speedmemo.utils.MyPref;

/**
 * Created by ryans on 2018-01-30.
 */

public class SelectFunctionActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_function);

        findViewById(R.id.li_write_btn).setOnClickListener(this);
        findViewById(R.id.li_read_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.li_write_btn:
                MyPref.getInstance().putBoolean(AppConstant.APP_MODE, true);
                Intent i = new Intent(this, WriteMemoActivity.class);
                i.putExtra(WriteMemoActivity.IS_FIRST_ACTIVITY_KEY, true);
                startActivity(i);
                break;
            case R.id.li_read_btn:
                MyPref.getInstance().putBoolean(AppConstant.APP_MODE, false);
                startActivity(new Intent(this, MemoMainActivity.class));
                break;
        }

        MyPref.getInstance().putBoolean(AppConstant.IS_FIRST, false);
        finish();
    }
}
