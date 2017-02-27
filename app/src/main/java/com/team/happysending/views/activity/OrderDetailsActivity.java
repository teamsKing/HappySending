package com.team.happysending.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.happysending.R;
import com.team.happysending.presenter.OrderDetailsPresenter;
import com.team.happysending.utils.ToastUtil;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.OrderDetailsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhang_shuai on 2017/2/24
 * <p>
 * 订单详情界面
 */

public class OrderDetailsActivity extends BaseActivity<OrderDetailsPresenter> implements OrderDetailsView {
    @BindView(R.id.detaild_shuqi)
    TextView detaildShuqi;
    @BindView(R.id.detaild_shushi)
    TextView detaildShushi;
    @BindView(R.id.details_wo_fa)
    TextView detailsWoFa;
    @BindView(R.id.detail_fa_shou)
    TextView detailFaShou;
    @BindView(R.id.detail_shuigou)
    TextView detailShuigou;
    @BindView(R.id.detail_shui)
    TextView detailShui;
    @BindView(R.id.detail_feiyong)
    TextView detailFeiyong;
    @BindView(R.id.detail_homebtn)
    Button detailHomebtn;
    @BindView(R.id.detail_home_jiedan)
    ImageView detailHomeJiedan;
    @BindView(R.id.detail_home_renwu)
    Button detailHomeRenwu;
    @BindView(R.id.detail_jiedan)
    Button detail_jiedan;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.detaild_qi, R.id.detaild_shuqi, R.id.detaild_shushi, R.id.details_wo_fa, R.id.detail_fa_shou, R.id.detail_shuigou, R.id.detail_shui, R.id.detail_feiyong, R.id.detail_homebtn, R.id.detail_home_jiedan, R.id.detail_home_renwu,R.id.detail_jiedan})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.detaild_qi:
                break;
            /**
             * 发货地址
             */
            case R.id.detaild_shuqi:
                break;
            /**
             * 收货地址
             */
            case R.id.detaild_shushi:
                break;
            /**
             * 我的位置到发货的位置
             */
            case R.id.details_wo_fa:
                break;
            /**
             * 发货的位置到收货位置的距离
             */
            case R.id.detail_fa_shou:
                break;
            /**
             *要捎带的话  比如：水果
             */
            case R.id.detail_shuigou:
                break;
            /**
             * 物品的类别
             */
            case R.id.detail_shui:
                break;
            /**
             * 我要挣去的费用
             */
            case R.id.detail_feiyong:
                break;
            /**
             * 回到主界面
             */
            case R.id.detail_homebtn:
                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
            /**
             * 接单的界面
             */
            case R.id.detail_home_jiedan:
                startActivity(new Intent(this,OrderDetailsActivity.class));
                finish();
                break;
            /**
             * 历史任务的界面
             */
            case R.id.detail_home_renwu:
                startActivity(new Intent(this,HistoryActivity.class));
                finish();
                break;
            /**
             * 本界面的接单
             */
            case R.id.detail_jiedan:
                ToastUtil.show(this,"等待处理");
                break;
        }
    }
}
