package com.yu.androidmvpdemo.listmvp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017-3-9.
 * 福利数据
 */

public class GirlsDatas {
   
    public boolean error; // false 无错
    public List<GirlBean> results;//列表


    @Override
    public String toString() {
        return "hasError: " + error + "  results size: " + results.size();
    }

}
