package com.yu.androidmvpdemo.listmvp.net;

import common.GlobalConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017-3-10.
 * 获取 Retrofit 实例
 */

public class RetrofitUtils {

    private static Retrofit mRetrofit;

    public static Retrofit getRetrofit(){

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(GlobalConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return mRetrofit;
    }
}
