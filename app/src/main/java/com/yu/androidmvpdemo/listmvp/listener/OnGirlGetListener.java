package com.yu.androidmvpdemo.listmvp.listener;

import com.yu.androidmvpdemo.listmvp.bean.GirlsDatas;

/**
 * Created by Administrator on 2017-3-10.
 * 获取数据的回调
 */

public interface OnGirlGetListener {

    void onStart();
    void onSuccess(GirlsDatas girlsDatas);
    void onFailed(Throwable e);
}
