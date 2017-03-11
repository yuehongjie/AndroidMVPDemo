package com.yu.androidmvpdemo.presenter;

import android.os.Handler;
import android.text.TextUtils;

import com.yu.androidmvpdemo.bean.User;
import com.yu.androidmvpdemo.biz.IUserBiz;
import com.yu.androidmvpdemo.biz.OnLoginListener;
import com.yu.androidmvpdemo.biz.UserBiz;
import com.yu.androidmvpdemo.view.IUserLoginView;

/**
 * Created by Administrator on 2017-2-28.
 * presenter 处理 model 和 view 的交互
 */

public class UserLoginPresenter {
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        userBiz = new UserBiz();
    }

    //登录操作
    public void login(){

        String userName = userLoginView.getUserName();
        String password = userLoginView.getPassword();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            return;
        }

        userLoginView.showLoading();
        //调用 model 的方法，处理登录的细节，回调结果
        userBiz.login(userName, password, new OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //根据回调结果操作 view
                        userLoginView.hideLoading();
                        userLoginView.go2MainActivity();
                    }
                });
            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.hideLoading();
                        userLoginView.showFailedError();
                    }
                });
            }
        });
    }

    //清除操作
    public void clear(){
        userLoginView.clearPassword();
        userLoginView.clearUserName();
    }
}
