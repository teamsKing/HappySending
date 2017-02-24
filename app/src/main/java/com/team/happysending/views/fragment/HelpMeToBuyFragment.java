package com.team.happysending.views.fragment;

import android.view.View;

import com.team.happysending.R;
import com.team.happysending.presenter.HelpMeToBuyPresenter;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.HelpMeToBuyInterface;

/**
 * Created by zhaoshihao on 2017/2/23.
 */

public class HelpMeToBuyFragment extends BaseFragment<HelpMeToBuyPresenter> implements HelpMeToBuyInterface {


    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_helpmebuy;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected HelpMeToBuyPresenter initPresenter() {
        return new HelpMeToBuyPresenter();
    }

    @Override
    protected BaseView initCallBack() {
        return this;
    }
}
