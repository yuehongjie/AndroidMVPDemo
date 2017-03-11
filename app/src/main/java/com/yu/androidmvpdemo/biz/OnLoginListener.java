package com.yu.androidmvpdemo.biz;

import com.yu.androidmvpdemo.bean.User;

/**
 * Created by Administrator on 2017-2-28.
 * 登录结果回调
 */

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailed();
}
