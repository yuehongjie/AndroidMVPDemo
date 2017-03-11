package com.yu.androidmvpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yu.androidmvpdemo.presenter.UserLoginPresenter;
import com.yu.androidmvpdemo.view.IUserLoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements IUserLoginView {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_passwd)
    TextView tvPasswd;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_passwd)
    EditText etPasswd;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private UserLoginPresenter mUserLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mUserLoginPresenter = new UserLoginPresenter(this);
    }

    @OnClick({R.id.btn_clear, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_clear:
                //toast("清除");
                mUserLoginPresenter.clear();
                break;
            case R.id.btn_login:
                //toast("登录");
                mUserLoginPresenter.login();
                break;
        }
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    //------------------------ 接口中的方法 -----------------------------

    @Override
    public String getUserName() {
        return etName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPasswd.getText().toString();
    }

    @Override
    public void clearUserName() {
        etName.setText("");
    }

    @Override
    public void clearPassword() {
        etPasswd.setText("");
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void go2MainActivity() {
        toast("登录成功");
        startActivity(new Intent(this , MainActivity.class));
    }

    @Override
    public void showFailedError() {
        toast("用户名或密码不正确");
    }
}
