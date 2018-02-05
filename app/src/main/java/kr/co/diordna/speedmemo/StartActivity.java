package kr.co.diordna.speedmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import kr.co.diordna.speedmemo.utils.AppConstant;
import kr.co.diordna.speedmemo.utils.MyPref;

/**
 * Created by ryans on 2018-01-31.
 */

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isFirst = MyPref.getInstance().getBoolean(AppConstant.IS_FIRST, true);
        boolean appMode = MyPref.getInstance().getBoolean(AppConstant.APP_MODE, true);

        if (isFirst) {
            startActivity(new Intent(this, SelectFunctionActivity.class));
        } else {

            if (appMode) {
                Intent i = new Intent(this, WriteMemoActivity.class);
                i.putExtra(WriteMemoActivity.IS_FIRST_ACTIVITY_KEY, true);
                startActivity(i);
            } else {
                startActivity(new Intent(this, MemoMainActivity.class));
            }
        }

        finish();
    }
}
