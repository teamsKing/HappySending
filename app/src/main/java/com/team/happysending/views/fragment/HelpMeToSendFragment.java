package com.team.happysending.views.fragment;

import android.view.View;

import com.team.happysending.R;
import com.team.happysending.presenter.HelpMeToSendPresenter;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.HelpMeToSendInterface;

/**
 * Created by zhaoshihao on 2017/2/23.
 */

public class HelpMeToSendFragment extends BaseFragment<HelpMeToSendPresenter> implements HelpMeToSendInterface {

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_helpmesend, null);
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initUi() {

    }

    @Override
    protected HelpMeToSendPresenter initPresenter() {
        return new HelpMeToSendPresenter();
    }

    @Override
    protected BaseView initCallBack() {
        return this;
    }
}
