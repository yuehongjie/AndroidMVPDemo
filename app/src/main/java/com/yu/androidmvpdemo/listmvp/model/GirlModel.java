package com.yu.androidmvpdemo.listmvp.model;

import android.util.Log;

import com.yu.androidmvpdemo.listmvp.bean.GirlBean;
import com.yu.androidmvpdemo.listmvp.bean.GirlsDatas;
import com.yu.androidmvpdemo.listmvp.listener.OnGirlGetListener;
import com.yu.androidmvpdemo.listmvp.net.GirlService;
import com.yu.androidmvpdemo.listmvp.net.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017-3-9.
 * 获取数据的实现类
 */

public class GirlModel implements IGirlModel {

    private static final String TAG = "GirlModel";

    private OnGirlGetListener mListener;

    private int mPage;

    @Override
    public void getGirlData(boolean isMore , OnGirlGetListener listener) {
        mListener = listener;
        if (mListener != null) {
            mListener.onStart();
        }

        //模拟加载数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    if (mListener != null) {
                        if (Math.random() * 10 > 5) {
                            mListener.onFailed(new Throwable("bad"));
                        }else {
                            mListener.onSuccess(null);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //使用 Retrofit 加载数据
    @Override
    public void getGirlDataWithRetrofit(boolean isMore , OnGirlGetListener listener) {

        if (isMore) {
            mPage++;
        }else {
            mPage = 1;
        }

        mListener = listener;
        if (mListener != null) {
            mListener.onStart();

            //使用 Retrofit 获取数据
            Retrofit retrofit = RetrofitUtils.getRetrofit();
            GirlService girlService = retrofit.create(GirlService.class);
            Call<GirlsDatas> girlsDatasCall = girlService.getGirls("福利", 10, mPage);

            girlsDatasCall.enqueue(new Callback<GirlsDatas>() {
                @Override
                public void onResponse(Call<GirlsDatas> call, Response<GirlsDatas> response) {
                    GirlsDatas girlsDatas = response.body();
                    if (girlsDatas != null) {

                        //获取成功
                        mListener.onSuccess(girlsDatas);

                    }else {
                        Log.e(TAG , "response body is null");
                        mListener.onFailed(new Throwable("girl data is null"));
                    }
                }

                @Override
                public void onFailure(Call<GirlsDatas> call, Throwable t) {

                    mListener.onFailed(t);

                }
            });
        }
    }
}
