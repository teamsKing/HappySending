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
    protected void initView(View view) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_helpmesend;
    }

    @Override
    protected void initData() {

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
