package com.team.happysending.views.fragment;

import android.view.View;

import com.team.happysending.R;
import com.team.happysending.presenter.OrderDetailsPresenter;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.OrderDetailsInterface;


/**
 * Created by zhang_shuai on 2017/2/23
 * 订单详情界面
 */

public class OrderDetailsFragment extends BaseFragment<OrderDetailsPresenter> implements OrderDetailsInterface {

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_order_details, null);
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initUi() {

    }

    @Override
    protected OrderDetailsPresenter initPresenter() {
        return new OrderDetailsPresenter();
    }

    @Override
    protected BaseView initCallBack() {
        return this;
    }



}
