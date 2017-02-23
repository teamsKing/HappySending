package com.team.happysending.views.fragment;

import android.view.View;

import com.team.happysending.R;
import com.team.happysending.presenter.HelpMeToHandPresenter;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.HelpMeToHandInterface;

/**
 * Created by zhaoshihao on 2017/2/23.
 */

public class HelpMeToHandFragment extends BaseFragment<HelpMeToHandPresenter> implements HelpMeToHandInterface {


    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_helpmehand;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected HelpMeToHandPresenter initPresenter() {
        return new HelpMeToHandPresenter();
    }

    @Override
    protected BaseView initCallBack() {
        return this;
    }
}
