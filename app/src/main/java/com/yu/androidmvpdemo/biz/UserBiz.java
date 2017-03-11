package com.yu.androidmvpdemo.biz;

import android.text.TextUtils;

import com.yu.androidmvpdemo.bean.User;

/**
 * Created by Administrator on 2017-2-28.
 * Model 模拟访问网络 登录操作
 */

public class UserBiz implements IUserBiz {
    @Override
    public void login(final String username, final String password, final OnLoginListener listener) {

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return;
        }

        //模拟耗时操作
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //模拟登录成功
                if ("yu".equals(username) && "123".equals(password)) {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    if (listener != null) {
                        listener.loginSuccess(user);
                    }
                }else {
                    //登录失败
                    listener.loginFailed();
                }
            }
        }.start();
    }
}
