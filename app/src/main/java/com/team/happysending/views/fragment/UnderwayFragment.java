package com.team.happysending.views.fragment;

import android.view.View;

import com.team.happysending.R;
import com.team.happysending.presenter.UnderwayPresenter;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.UnderwayView;

/**
 * Created by zhang_shuai on 2017/2/24
 *
 * 正在进行的任务
 */

public class UnderwayFragment  extends BaseFragment<UnderwayPresenter> implements UnderwayView{
    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_underway;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected UnderwayPresenter initPresenter() {
        return new UnderwayPresenter();
    }

    @Override
    protected BaseView initCallBack() {
        return this;
    }
}
