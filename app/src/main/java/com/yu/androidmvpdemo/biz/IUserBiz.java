package com.yu.androidmvpdemo.biz;

/**
 * Created by Administrator on 2017-2-28.
 * 登录操作接口
 */

public interface IUserBiz {
    void login(String username , String password , OnLoginListener listener);
}
