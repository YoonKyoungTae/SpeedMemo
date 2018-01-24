package kr.co.diordna.speedmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import kr.co.diordna.speedmemo.adapter.MemoListAdapter;
import kr.co.diordna.speedmemo.database.DBProvider;
import kr.co.diordna.speedmemo.model.Memo;

public class MemoMainActivity extends AppCompatActivity implements View.OnClickListener, MemoListAdapter.OnItemClickListener{

    private ImageView iv_menu_btn;
    private ImageView iv_add_btn;
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

        rv_memo_list = findViewById(R.id.rv_memo_list);
        rv_memo_list.setLayoutManager(new LinearLayoutManager(this));

        drawer_layout = findViewById(R.id.drawer_layout);

        iv_menu_btn.setOnClickListener(this);
        iv_add_btn.setOnClickListener(this);
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
        mMemoListAdapter.setList(mDBProvider.selectAllMemo());
    }

    @Override
    public void onClickItem(Memo memo) {
        Intent i = new Intent(this, WriteMemoActivity.class);
        i.putExtra("memo", memo);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_menu_btn:
                drawer_layout.openDrawer(Gravity.START);
                break;
            case R.id.iv_add_btn:
                startActivity(new Intent(this, WriteMemoActivity.class));
                break;
        }
    }
}
