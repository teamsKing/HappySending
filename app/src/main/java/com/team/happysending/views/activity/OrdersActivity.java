package com.team.happysending.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.happysending.R;
import com.team.happysending.presenter.OrdersPresenter;
import com.team.happysending.utils.ToastUtil;
import com.team.happysending.views.app.MyApplication;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.OrdersView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.team.happysending.R.id.orders_fa_shou;
import static com.team.happysending.R.id.orders_yuying;

/**
 * Created by zhang_shuai on 2017/2/24
 * <p>
 * 接单界面
 */

public class OrdersActivity extends BaseActivity<OrdersPresenter> implements OrdersView ,View.OnClickListener{


    @BindView(orders_yuying)
    TextView mOrdersYuying;
    /**
     * 物品类别
     */
    @BindView(R.id.orders_leibian)
    TextView mOrdersLeibian;
    /**
     * 送货的价格
     */
    @BindView(R.id.orders_jiage)
    TextView mOrdersJiage;
    /**
     * 我倒发货的距离
     */
    @BindView(R.id.orders_wo_fa)
    TextView mOrdersWoFa;
    @BindView(R.id.tv4)
    TextView tv4;
    /**
     * 发货到收货的距离
     */
    @BindView(orders_fa_shou)
    TextView mOrdersFaShou;
    /**
     * 备注信息
     */
    @BindView(R.id.orders_shuigou)
    TextView mOrdersShuigou;
    /**
     * 接单
     */
    @BindView(R.id.orders_jiedan)
    Button mOrdersJiedan;
    /**
     * 回到主界面
     */
    @BindView(R.id.orders_homebtn)
    Button mOrdersHomebtn;
    /**
     * 本页面
     */
    @BindView(R.id.orders_home_jiedan)
    ImageView mOrdersHomeJiedan;
    /**
     * 历史任务
     */
    @BindView(R.id.orders_home_renwu)
    Button mOrdersHomeRenwu;
    /**
     * 开始的地址
     */
    @BindView(R.id.orders_qishi_Address)
    TextView mOrdersQishi;
    /**
     * 结束的地址
     */
    @BindView(R.id.orders_jieshu_Address)
    TextView mOrdersJieShu;
    private String startAdderss;
    private String endAdderss;
    private String beiZhu;
    private String money;
    private String xuanZe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initUI();
    }



    private void initUI() {
        findViewById(R.id.orders_jiedan).setOnClickListener(this);
        findViewById(R.id.orders_yuying).setOnClickListener(this);
        findViewById(R.id.orders_leibian).setOnClickListener(this);
        findViewById(R.id.orders_jiage).setOnClickListener(this);
        findViewById(R.id.orders_wo_fa).setOnClickListener(this);
        findViewById(R.id.orders_shuigou).setOnClickListener(this);
        findViewById(R.id.orders_fa_shou).setOnClickListener(this);
        findViewById(R.id.orders_homebtn).setOnClickListener(this);
        findViewById(R.id.orders_home_jiedan).setOnClickListener(this);
        findViewById(R.id.orders_home_renwu).setOnClickListener(this);
        findViewById(R.id.orders_qishi_Address).setOnClickListener(this);
        findViewById(R.id.orders_jieshu_Address).setOnClickListener(this);
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

        Intent intent = getIntent();
        xuanZe = intent.getStringExtra("XuanZe");
        beiZhu = intent.getStringExtra("BeiZhu");
        money = intent.getStringExtra("Money");
        mOrdersLeibian.setText("物品类别:"+ xuanZe);
        mOrdersShuigou.setText(beiZhu);
        mOrdersJiage.setText(money);

        //取值
        startAdderss = MyApplication.mSp.getString("startAdderss", null);
        endAdderss = MyApplication.mSp.getString("endAdderss", null);
        mOrdersQishi.setText(startAdderss);
        mOrdersJieShu.setText(endAdderss);

    }


    @OnClick(orders_yuying)
    public void onClick() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /**
             * 接单详情界面
             */
            case R.id.orders_jiedan:
                Intent intent = new Intent(this,OrderDetailsActivity.class);
                intent.putExtra("startAdderss",startAdderss);
                intent.putExtra("endAdderss",endAdderss);
                intent.putExtra("beiZhu",beiZhu);
                intent.putExtra("money",money);
                intent.putExtra("xuanZe",xuanZe);
                startActivity(intent);
                finish();
                break;
            /**
             * 录取语音
             */
            case R.id.orders_yuying:
                ToastUtil.show(this,"语音");
                break;
            /**
             * 物品类别
             */
            case R.id.orders_leibian:
//                ToastUtil.show(this,"物品类别");
                break;
            /**
             * 已加价格
             */
            case R.id.orders_jiage:
                ToastUtil.show(this,"价格");
                break;
            /**
             * 我到取货的距离
             */
            case R.id.orders_wo_fa:
//                ToastUtil.show(this,"我fa的距离");
                break;
            /**
             * 水果有多少斤
             */
            case R.id.orders_shuigou:
//                ToastUtil.show(this,"水果");
                break;
            /**
             * 我从取货到收货人的距离
             */
            case R.id.orders_fa_shou:
//                ToastUtil.show(this,"我shou的距离");
                break;

            /**
             * 回到主页
             */
            case R.id.orders_homebtn:
                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
            /**
             * 留在本界面的接单
             */
            case R.id.orders_home_jiedan:
//                startActivity(new Intent(this,OrdersActivity.class));
//                finish();
                break;
            /**
             * 跳转到历史任务界面
             */
            case R.id.orders_home_renwu:
                startActivity(new Intent(this,HistoryActivity.class));
                finish();
                break;
        }
    }
}
