package com.yu.androidmvpdemo.listmvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yu.androidmvpdemo.R;
import com.yu.androidmvpdemo.listmvp.bean.GirlBean;
import com.yu.androidmvpdemo.listmvp.listener.OnGirlItemClickListener;

import java.util.List;

/**
 * Created by Administrator on 2017-3-10.
 * 展示 girls 信息的 adapter
 */

public class GirlDataAdapter extends RecyclerView.Adapter<GirlDataAdapter.MyHolder>{

    private List<GirlBean> mGirlList;

    public GirlDataAdapter(List<GirlBean> girlList) {
        mGirlList = girlList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.item_girls_list, null);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        GirlBean girl = mGirlList.get(position);
        Picasso.with(holder.itemView.getContext()).load(girl.url).error(R.mipmap.girl1).into(holder.ivGirl);
        holder.tvDesc.setText(TextUtils.isEmpty(girl.desc) ? "none" : girl.desc);

        setClickListener(holder , position);
    }

    private void setClickListener(MyHolder holder, final int position) {
        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked(position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mListener.onItemLongClicked(position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mGirlList == null ? 0 : mGirlList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private ImageView ivGirl;
        private TextView tvDesc;

        public MyHolder(View itemView) {
            super(itemView);

            ivGirl = (ImageView) itemView.findViewById(R.id.iv_girl);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
        }

    }

    //点击事件监听
    private OnGirlItemClickListener mListener;
    public void setOnItemClickListener(OnGirlItemClickListener listener){
        mListener = listener;
    }
}
