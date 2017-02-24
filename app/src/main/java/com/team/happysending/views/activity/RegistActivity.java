package com.team.happysending.views.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team.happysending.R;
import com.team.happysending.model.bean.RegistBean;
import com.team.happysending.presenter.RegistPresenter;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.RegistView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhaoshihao on 2017/2/24.
 */

public class RegistActivity extends BaseActivity<RegistPresenter> implements RegistView {


    @BindView(R.id.back)
    Button back;
    @BindView(R.id.regist_nicheng)
    EditText registNicheng;
    @BindView(R.id.regist_phone)
    EditText registPhone;
    @BindView(R.id.regist_pwd)
    EditText registPwd;
    @BindView(R.id.regist_repetitionpwd)
    EditText registRepetitionpwd;
    @BindView(R.id.regist_agree)
    Button registAgree;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected int getLayout() {
        return R.layout.activity_regist;
    }


    @Override
    protected RegistPresenter initPresenter() {
        return new RegistPresenter(this);
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


    @OnClick({R.id.back, R.id.regist_nicheng, R.id.regist_phone, R.id.regist_pwd, R.id.regist_repetitionpwd, R.id.regist_agree})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.regist_agree:
                String nicheng = registNicheng.getText().toString().trim();
                String phone = registPhone.getText().toString().trim();
                String password = registPwd.getText().toString().trim();
                String repetitionpwd = registRepetitionpwd.getText().toString().trim();
                mPresenter.getDatasFromNet(nicheng, phone, password, repetitionpwd);
                break;
        }
    }

    //数据回调的方法
    @Override
    public void OnRequestSuccess(RegistBean registBean) {
        if (registNicheng.getText().toString().trim().equals(registBean.getNick_name())) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
            finish();
        }


    }
}
