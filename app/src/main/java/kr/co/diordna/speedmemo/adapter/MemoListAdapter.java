package kr.co.diordna.speedmemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import kr.co.diordna.speedmemo.R;
import kr.co.diordna.speedmemo.model.Memo;
import kr.co.diordna.speedmemo.utils.AppConstant;

/**
 * Created by ryans on 2018-01-21.
 */

public class MemoListAdapter extends RecyclerView.Adapter<MemoListAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Memo> mList;

    public MemoListAdapter() {
    }

    public void setList(ArrayList<Memo> list) {
        mList = list;

        Collections.sort(mList, new Comparator<Memo>() {
            @Override
            public int compare(Memo m1, Memo m2) {
                if (m1.getUpdateAt().getMillis() > m2.getUpdateAt().getMillis()) {
                    return -1;
                } else if (m1.getUpdateAt().getMillis() < m2.getUpdateAt().getMillis()) {
                    return 1;
                }
                return 0;
            }
        });

        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo_vertical, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_content.setText(mList.get(position).getContent());
        holder.tv_update_time.setText(mList.get(position).getUpdateAt().toString(AppConstant.DEFAULT_DATE_FORMAT));
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onClick(View view) {
        Memo clickItem = mList.get((int) view.getTag());
        mOnItemClickListener.onClickItem(clickItem);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_content;
        private TextView tv_update_time;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_update_time = itemView.findViewById(R.id.tv_update_time);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClickItem(Memo memo);
    }

}
