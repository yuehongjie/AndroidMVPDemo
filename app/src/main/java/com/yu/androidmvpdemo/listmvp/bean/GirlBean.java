package com.yu.androidmvpdemo.listmvp.bean;

/**
 * Created by Administrator on 2017-3-9.
 * 女孩信息
 */

public class GirlBean {
    /**
     * _id : 58c0ac1f421aa90f03345143
     * createdAt : 2017-03-09T09:13:03.655Z
     * desc : 3-9
     * publishedAt : 2017-03-09T11:42:30.849Z
     * source : chrome
     * type : 福利
     * url : http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-09-17127109_1652837611687612_1425055271046086656_n.jpg
     * used : true
     * who : daimajia
     */

    public String desc;
    public String type;
    public String url;
    public boolean used;
    public String who;

    @Override
    public String toString() {
        return "{url: " + url + " type: " + type + " desc: " + desc + "}\n";
    }
}
