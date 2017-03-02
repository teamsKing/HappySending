package com.team.happysending.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.team.happysending.R;
import com.team.happysending.model.bean.LoginBean;
import com.team.happysending.presenter.LoginPresenter;
import com.team.happysending.utils.Constant;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.LoginView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * 登陆
 * Created by zhaoshihao on 2017/2/23.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView, View.OnClickListener {
    private UMShareAPI mShareAPI;
    private EditText mLoginPhone;
    private EditText mLoginPwd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShareAPI = UMShareAPI.get(this);
        initUI();
    }

    private void initUI() {
        findViewById(R.id.qqlogin).setOnClickListener(this);
        findViewById(R.id.weixinlogin).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.regist).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
        mLoginPhone = (EditText) findViewById(R.id.loginphone);
        mLoginPwd = (EditText) findViewById(R.id.loginpwd);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void addActivity() {
        application.addActivity(this);
    }

    @Override
    protected BaseView initCallBack() {
        return this;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qqlogin:
                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.weixinlogin:
                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.regist:
                Intent intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                String loginphone = mLoginPhone.getText().toString().trim();
                String loginpwd = mLoginPwd.getText().toString().trim();
                mPresenter.getDatasFromNet(loginphone, loginpwd);
                break;

        }
    }

    //数据回调的方法
    @Override
    public void OnSuccess(LoginBean loginBean) {
        if (mLoginPhone.getText().toString().trim().equals(loginBean.getPhone_num())) {
            editor.putString(Constant.LOGINTOKEN, loginBean.getToken());
            editor.commit();
            finish();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            editor.putString(Constant.LOGINTOKEN, "accessToken ");
            editor.commit();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "取消登录", Toast.LENGTH_SHORT).show();
        }
    };

}
