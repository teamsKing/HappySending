package com.team.happysending.views.activity;

import com.team.happysending.R;
import com.team.happysending.presenter.LoginPresenter;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.LoginView;

/**
 * 登陆
 * Created by zhaoshihao on 2017/2/23.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
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
}
