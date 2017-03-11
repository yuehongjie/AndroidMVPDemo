package com.yu.androidmvpdemo.listmvp.net;

import com.yu.androidmvpdemo.listmvp.bean.GirlsDatas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017-3-10.
 * 使用 Retrofit 获取数据的接口
 */

public interface GirlService {

    //http://gank.io/api/data/福利/10/1

    @GET("data/{type}/{count}/{page}")
    Call<GirlsDatas> getGirls(@Path("type") String type, @Path("count") int count , @Path("page") int page);
}
