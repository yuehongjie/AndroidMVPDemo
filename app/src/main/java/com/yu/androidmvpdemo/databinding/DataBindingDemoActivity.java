package com.yu.androidmvpdemo.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yu.androidmvpdemo.R;
import com.yu.androidmvpdemo.databinding.bean.SimpleUser;

public class DataBindingDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_data_binding_demo);
        com.yu.androidmvpdemo.databinding.ActivityDataBindingDemoBinding binding = DataBindingUtil.setContentView(this , R.layout.activity_data_binding_demo);
        SimpleUser user = new SimpleUser("yuyuyuyuyuyuyuyu" , 18);
        binding.setSimpleUser(user);
    }
}
