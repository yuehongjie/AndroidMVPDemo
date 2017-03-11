package com.yu.androidmvpdemo.listmvp.model;

import com.yu.androidmvpdemo.listmvp.listener.OnGirlGetListener;

/**
 * Created by Administrator on 2017-3-9.
 * model 层获取数据的接口
 */

public interface IGirlModel {
    //获取数据
    void getGirlData(boolean isMore, OnGirlGetListener listener);

    void getGirlDataWithRetrofit(boolean isMore , OnGirlGetListener listener);
}
