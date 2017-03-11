package com.yu.androidmvpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yu.androidmvpdemo.databinding.DataBindingDemoActivity;
import com.yu.androidmvpdemo.listmvp.view.ListMVPActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_use_data_binding)
    Button btnUseDataBinding;
    @BindView(R.id.btn_use_mvp_list)
    Button btnUseMvpList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_use_data_binding, R.id.btn_use_mvp_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_use_data_binding:
                startActivity(new Intent(this, DataBindingDemoActivity.class));
                break;
            case R.id.btn_use_mvp_list:
                startActivity(new Intent(this , ListMVPActivity.class));
                break;
        }
    }

}
