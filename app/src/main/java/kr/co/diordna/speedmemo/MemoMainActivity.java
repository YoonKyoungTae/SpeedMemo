package kr.co.diordna.speedmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.diordna.speedmemo.adapter.MemoListAdapter;
import kr.co.diordna.speedmemo.database.DBProvider;
import kr.co.diordna.speedmemo.model.Memo;
import kr.co.diordna.speedmemo.utils.Utils;

public class MemoMainActivity extends AppCompatActivity implements View.OnClickListener, MemoListAdapter.OnItemClickListener{

    private ImageView iv_menu_btn;
    private ImageView iv_add_btn;
    private TextView tv_empty_msg;
    private RecyclerView rv_memo_list;
    private DrawerLayout drawer_layout;

    private MemoListAdapter mMemoListAdapter;
    private DBProvider mDBProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_main);
        initView();
        initData();
    }

    private void initView() {
        iv_menu_btn = findViewById(R.id.iv_menu_btn);
        iv_add_btn = findViewById(R.id.iv_add_btn);
        tv_empty_msg = findViewById(R.id.tv_empty_msg);

        rv_memo_list = findViewById(R.id.rv_memo_list);
        rv_memo_list.setLayoutManager(new LinearLayoutManager(this));

        drawer_layout = findViewById(R.id.drawer_layout);


        iv_menu_btn.setOnClickListener(this);
        iv_add_btn.setOnClickListener(this);

        findViewById(R.id.tv_menu_mail_to).setOnClickListener(this);
        findViewById(R.id.tv_menu_go_review).setOnClickListener(this);
        findViewById(R.id.tv_menu_share_to).setOnClickListener(this);
        findViewById(R.id.tv_menu_setting).setOnClickListener(this);
    }

    private void initData() {
        mDBProvider = new DBProvider(this, DBProvider.DB_VERSION);
        mMemoListAdapter = new MemoListAdapter();
        mMemoListAdapter.setOnItemClickListener(this);
        rv_memo_list.setAdapter(mMemoListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mDBProvider != null) {
            ArrayList<Memo> memos = mDBProvider.selectAllMemo();
            if (memos.size() == 0) {
                tv_empty_msg.setVisibility(View.VISIBLE);
                rv_memo_list.setVisibility(View.GONE);
            } else {
                tv_empty_msg.setVisibility(View.GONE);
                rv_memo_list.setVisibility(View.VISIBLE);
                mMemoListAdapter.setList(memos);
            }
        } else {
            tv_empty_msg.setVisibility(View.VISIBLE);
            rv_memo_list.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClickItem(Memo memo) {
        Intent i = new Intent(this, WriteMemoActivity.class);
        i.putExtra(WriteMemoActivity.MEMO_ITEM_KEY, memo);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_menu_btn:
                drawer_layout.openDrawer(Gravity.START);
                break;
            case R.id.iv_add_btn:
                startActivity(new Intent(this, WriteMemoActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;

            // -- Navi Menus -- //

            case R.id.tv_menu_mail_to:
                Utils.sendMailToDeveloper(this);
                break;

            case R.id.tv_menu_go_review:
                Utils.goToReview(this);
                break;

            case R.id.tv_menu_share_to:
                Utils.shareToApp(this);
                break;

            case R.id.tv_menu_setting:
                startActivity(new Intent(this, SettingActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
