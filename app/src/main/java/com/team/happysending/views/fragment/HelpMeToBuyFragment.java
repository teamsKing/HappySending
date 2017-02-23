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
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_helpmebuy, null);
        return view;

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initUi() {

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
