package com.yu.androidmvpdemo.application;

import android.app.Application;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2017-3-11.
 *
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initLeakCanary();
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        Log.e("application" , "init leak canary");
        LeakCanary.install(this);
        // Normal app init code...
    }

}
