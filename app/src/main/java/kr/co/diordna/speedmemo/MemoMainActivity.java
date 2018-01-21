package kr.co.diordna.speedmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import kr.co.diordna.speedmemo.adapter.MemoListAdapter;
import kr.co.diordna.speedmemo.database.DBProvider;
import kr.co.diordna.speedmemo.model.Memo;

public class MemoMainActivity extends AppCompatActivity implements MemoListAdapter.OnItemClickListener{

    private ImageView iv_back_btn;
    private ImageView iv_add_btn;
    private RecyclerView rv_memo_list;

    private MemoListAdapter mMemoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_main);
        initView();
        initData();
    }

    private void initView() {
        iv_back_btn = findViewById(R.id.iv_back_btn);
        iv_add_btn = findViewById(R.id.iv_add_btn);

        rv_memo_list = findViewById(R.id.rv_memo_list);
        rv_memo_list.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initData() {
        DBProvider dbProvider = new DBProvider(this, DBProvider.DB_VERSION);
        mMemoListAdapter = new MemoListAdapter(dbProvider.selectAllMemo());
        mMemoListAdapter.setOnItemClickListener(this);
        rv_memo_list.setAdapter(mMemoListAdapter);
    }

    @Override
    public void onClickItem(Memo memo) {
        Intent i = new Intent(this, WriteMemoActivity.class);
        i.putExtra("memo", memo);
        startActivity(i);
    }
}
