package com.team.happysending.views.activity;

import com.team.happysending.R;
import com.team.happysending.presenter.AddressChosePresenter;
import com.team.happysending.views.interfaces.AddressChoseView;
import com.team.happysending.views.interfaces.BaseView;

/**
 * 选择城市
 * Created by zhaoshihao on 2017/2/23.
 */

public class AddressChoseActivity extends BaseActivity<AddressChosePresenter> implements AddressChoseView {


    @Override
    protected int getLayout() {
        return R.layout.activity_city_selecter;
    }

    @Override
    protected AddressChosePresenter initPresenter() {
        return new AddressChosePresenter();
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
