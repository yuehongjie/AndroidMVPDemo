package com.yu.androidmvpdemo.listmvp.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2017-3-9.
 * 需要实现的接口
 */

public interface IMVPView {
    //友好交互，提示用户正在加载或者隐藏加载
    void showLoading();
    void hidLoading();

    //获取 RecyclerView 对象
    RecyclerView getRecyclerView();

    Activity getActivity();
}
