package com.yu.androidmvpdemo.view;

/**
 * Created by Administrator on 2017-2-28.
 * Presenter 与 View 交互的接口
 */

public interface IUserLoginView {

    //登录操作需要 获取用户名 密码
    String getUserName();
    String getPassword();
    //清除操作需要 清空用户名 密码
    void clearUserName();
    void clearPassword();

    //耗时操作友好提示/隐藏进度条
    void showLoading();
    void hideLoading();

    //登录成功，执行跳转到主界面的操作
    void go2MainActivity();
    //登录失败，弹出错误提示
    void showFailedError();
}
