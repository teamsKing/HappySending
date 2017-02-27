package com.team.happysending.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team.happysending.R;
import com.team.happysending.presenter.OrdersPresenter;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.OrdersView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhang_shuai on 2017/2/24
 * <p>
 * 接单界面
 */

public class OrdersActivity extends BaseActivity<OrdersPresenter> implements OrdersView {


    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.orders_yuying)
    TextView ordersYuying;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.orders_leibian)
    TextView ordersLeibian;
    @BindView(R.id.jiage)
    TextView jiage;
    @BindView(R.id.orders_jiage)
    TextView ordersJiage;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.orders_wo_fa)
    TextView ordersWoFa;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.orders_fa_shou)
    TextView ordersFaShou;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv8)
    TextView tv8;
    @BindView(R.id.qi)
    TextView qi;
    @BindView(R.id.shi)
    TextView shi;
    @BindView(R.id.tv9)
    TextView tv9;
    @BindView(R.id.tv10)
    TextView tv10;
    @BindView(R.id.orders_shuigou)
    TextView ordersShuigou;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.orders_jiedan)
    Button ordersJiedan;
    @BindView(R.id.orders_homebtn)
    Button ordersHomebtn;
    @BindView(R.id.orders_home_jiedan)
    ImageView ordersHomeJiedan;
    @BindView(R.id.orders_home_renwu)
    Button ordersHomeRenwu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ordersHomeRenwu.setBackgroundResource(R.drawable.bcj);
        ordersHomebtn.setBackgroundResource(R.drawable.bcp);
    }

    private void initUI() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_orders;
    }

    @Override
    protected OrdersPresenter initPresenter() {
        return new OrdersPresenter();
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


    @OnClick({R.id.orders_jiedan, R.id.orders_home_renwu, R.id.orders_homebtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.orders_jiedan:

                break;
            case R.id.orders_home_renwu:
                ordersHomeRenwu.setBackgroundResource(R.drawable.bck);
                startActivity(new Intent(this, HistoryActivity.class));
                break;
            case R.id.orders_homebtn:
                ordersHomebtn.setBackgroundResource(R.drawable.bcq);
                finish();
                break;
        }
    }
}
