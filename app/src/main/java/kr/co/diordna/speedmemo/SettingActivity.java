package kr.co.diordna.speedmemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import kr.co.diordna.speedmemo.database.DBProvider;

/**
 * Created by ryans on 2018-02-19.
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_version_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        tv_version_info = findViewById(R.id.tv_version_info);

        findViewById(R.id.iv_back_btn).setOnClickListener(this);
        findViewById(R.id.rl_select_memo_type).setOnClickListener(this);
        findViewById(R.id.rl_delete_all_memo).setOnClickListener(this);
        findViewById(R.id.rl_version_info).setOnClickListener(this);

        setVersionInfo();
    }

    private void setVersionInfo() {
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (pInfo != null) {
            tv_version_info.setText(pInfo.versionName);
        }
    }

    private void checkDeleteAll() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.dialog_memo_all_remove_title));
        builder.setMessage(getString(R.string.dialog_memo_all_remove_msg));
        builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new DBProvider(SettingActivity.this, DBProvider.DB_VERSION).deleteAll();
                Toast.makeText(SettingActivity.this, getString(R.string.toast_memo_all_removed), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_btn:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;

            case R.id.rl_select_memo_type:
                startActivity(new Intent(this, SelectFunctionActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;

            case R.id.rl_delete_all_memo:
                checkDeleteAll();
                break;

            case R.id.rl_version_info:
                //TODO
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
