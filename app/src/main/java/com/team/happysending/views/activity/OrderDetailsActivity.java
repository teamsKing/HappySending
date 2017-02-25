package com.team.happysending.views.activity;

import com.team.happysending.R;
import com.team.happysending.presenter.OrderDetailsPresenter;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.OrderDetailsView;

/**
 * Created by zhang_shuai on 2017/2/24
 *
 * 订单详情界面
 */

public class OrderDetailsActivity extends BaseActivity<OrderDetailsPresenter> implements OrderDetailsView {
    @Override
    protected int getLayout() {
        return R.layout.fragment_order_details;
    }

    @Override
    protected OrderDetailsPresenter initPresenter() {
        return new OrderDetailsPresenter();
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
