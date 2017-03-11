package com.yu.androidmvpdemo.listmvp.presenter;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.yu.androidmvpdemo.listmvp.adapter.GirlDataAdapter;
import com.yu.androidmvpdemo.listmvp.bean.GirlBean;
import com.yu.androidmvpdemo.listmvp.bean.GirlsDatas;
import com.yu.androidmvpdemo.listmvp.listener.OnGirlGetListener;
import com.yu.androidmvpdemo.listmvp.listener.OnGirlItemClickListener;
import com.yu.androidmvpdemo.listmvp.model.GirlModel;
import com.yu.androidmvpdemo.listmvp.view.IMVPView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-3-9.
 * presenter 层，使 view 和 model 间接交互
 */

public class GirlPresenter {

    private static final String TAG = "GirlPresenter";

    private IMVPView mView;
    private RecyclerView mRecyclerView;
    private Activity mActivity;

    private final GirlModel mModel;

    private Handler mHandler;
    private boolean isLoading;//是否正在加载

    private List<GirlBean> mGirlList;
    private GirlDataAdapter mAdapter;


    public GirlPresenter(IMVPView view) {
        this.mView = view;
        this.mRecyclerView = mView.getRecyclerView();
        mActivity = mView.getActivity();
        mModel = new GirlModel();
        mHandler = new Handler();
    }

    public void initData() {

        mModel.getGirlData(false, new OnGirlGetListener() {
            @Override
            public void onStart() {
                Log.e(TAG , "开始请求数据");
                mView.showLoading();
            }

            @Override
            public void onSuccess(GirlsDatas girlsDatas) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.hidLoading();
                        Log.e(TAG , "成功请求数据");
                    }
                });

            }

            @Override
            public void onFailed(Throwable e) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.hidLoading();
                        Log.e(TAG , "请求数据失败");
                    }
                });

            }
        });
    }

    /**
     * 加载网络数据
     * @param isMore 是加载更多还是刷新
     */
    public void getDataWithRetrofit(final boolean isMore){
        if (isLoading) {
            return;
        }
        isLoading = true;

        mModel.getGirlDataWithRetrofit(isMore, new OnGirlGetListener() {
            @Override
            public void onStart() {
                mView.showLoading();
            }

            @Override
            public void onSuccess(GirlsDatas girlsDatas) {

                mView.hidLoading();
                if (mRecyclerView != null) {
                    if (mAdapter == null) {//首次加载
                        Log.e(TAG , "加载数据");
                        mGirlList = new ArrayList<>();
                        mGirlList.addAll(girlsDatas.results);

                        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                        mAdapter = new GirlDataAdapter(mGirlList);
                        initAdapterListener();
                        mRecyclerView.setAdapter(mAdapter);
                    }else {//加载更多或者
                        if (isMore) {//加载更多
                            Log.e(TAG , "加载更多数据 " + girlsDatas.results);

                            mGirlList.addAll(girlsDatas.results);
                        }else {//刷新
                            Log.e(TAG , "刷新数据 " + girlsDatas.results);

                            mGirlList.clear();
                            mGirlList.addAll(girlsDatas.results);
                        }
                        mAdapter.notifyDataSetChanged();
                    }

                }

                isLoading = false;
            }

            @Override
            public void onFailed(Throwable e) {
                mView.hidLoading();
                isLoading = false;
                Log.e(TAG , e.getMessage());
            }
        });
    }

    private void initAdapterListener() {
        mAdapter.setOnItemClickListener(new OnGirlItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Toast.makeText(mRecyclerView.getContext(), "点击了图 " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClicked(int position) {
                Toast.makeText(mRecyclerView.getContext(), "长按了图 " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
