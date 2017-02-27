package com.team.happysending.views.activity;

import android.content.Intent;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team.happysending.R;
import com.team.happysending.presenter.PlaceAnOrderPresenter;
import com.team.happysending.utils.ToastUtils;
import com.team.happysending.views.interfaces.PlaceAnOrderView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 樊、先生 on 2017/2/24.
 * 立即下单页面
 */

public class PlaceAnOrderActivity extends BaseActivity<PlaceAnOrderPresenter> implements PlaceAnOrderView, View.OnTouchListener {
    @BindView(R.id.placeonorder_backBtn)
    ImageView mPlaceonorderBackBtn;
    @BindView(R.id.placeonorder_etFaName)
    EditText mPlaceonorderEtFaName;
    @BindView(R.id.placeonorder_etFaPhone)
    EditText mPlaceonorderEtFaPhone;
    @BindView(R.id.placeonorder_imgTXL1)
    ImageView mPlaceonorderImgTXL1;
    @BindView(R.id.placeonorder_etShouName)
    EditText mPlaceonorderEtShouName;
    @BindView(R.id.placeonorder_etShouPhone)
    EditText mPlaceonorderEtShouPhone;
    @BindView(R.id.placeonorder_imgTXL2)
    ImageView mPlaceonorderImgTXL2;
    @BindView(R.id.placeonorder_renzheng)
    TextView mPlaceonorderRenzheng;
    @BindView(R.id.placeonorder_xuanZe)
    TextView mPlaceonorderXuanZe;
    @BindView(R.id.placeonorder_txtBeiZhu)
    TextView mPlaceonorderTextBeiZhu;
    @BindView(R.id.placeonorder_imgYuYin)
    ImageView mPlaceonorderImgYuYin;
    @BindView(R.id.placeonorder_money)
    TextView mPlaceonorderMoney;
    @BindView(R.id.placeonorder_tiJiaoBtn)
    Button mPlaceonorderTiJiaoBtn;
    @BindView(R.id.placeanorder_llayout)
    LinearLayout mPlaceanorderLlayout;
    @BindView(R.id.placeonorder_imgYuYinLong)
    ImageView mPlaceonorderImgYuYinLong;

    //语音文件保存路径
    private String FileName = null;

    @Override
    protected int getLayout() {
        return R.layout.activity_placeanorder;
    }

    @Override
    protected PlaceAnOrderPresenter initPresenter() {
        return new PlaceAnOrderPresenter(this);
    }

    /**
     * 将当前activity加入栈中
     */
    @Override
    protected void addActivity() {
        application.addActivity(this);
    }

    @Override
    protected PlaceAnOrderView initCallBack() {
        return this;
    }

    @Override
    protected void initData() {
        // long timestamp = System.currentTimeMillis();
        long time = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
        String str = String.valueOf(time);
        //设置sdcard的路径
        FileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        FileName += "/beizhu+" + str + ".3gp";
        mPlaceonorderImgYuYin.setOnTouchListener(this);
    }


    //长按监听
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //长按开始录音，显示popow动画
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            /**
             * 显示录音中动画
             */
            mPresenter.showPop(mPlaceonorderEtFaName);
            /**
             * 开始录音
             */
            mPresenter.startRecord(FileName);
            /**
             * 显示语音条
             */
            mPlaceonorderImgYuYinLong.setVisibility(View.VISIBLE);

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            /**
             * popup消失
             */
            mPresenter.dismiss();

        }
        return false;
    }


    // 回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String name = data.getStringExtra("name");
        String phone = data.getStringExtra("phone");
        // 根据上面发送过去的请求吗来区别
        switch (requestCode) {
            case 0:
                mPlaceonorderEtFaName.setText(name);
                mPlaceonorderEtFaPhone.setText(phone);
                break;
            case 1:
                mPlaceonorderEtShouName.setText(name);
                mPlaceonorderEtShouPhone.setText(phone);
                break;
            default:
                break;
        }
    }

    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.placeonorder_backBtn, R.id.placeonorder_imgTXL1, R.id.placeonorder_imgTXL2, R.id.placeonorder_renzheng, R.id.placeonorder_xuanZe, R.id.placeonorder_imgYuYinLong, R.id.placeonorder_imgYuYin, R.id.placeonorder_tiJiaoBtn,R.id.placeonorder_txtBeiZhu,R.id.placeonorder_money})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.placeonorder_backBtn:
                //返回
                finish();
                break;
            case R.id.placeonorder_imgTXL1:
                //调用通讯录
                mPresenter.toStartActivityForResult(0);
                break;
            case R.id.placeonorder_imgTXL2:
                //跳转到通讯录界面
                mPresenter.toStartActivityForResult(1);
                break;
            case R.id.placeonorder_renzheng:
                Intent intent = new Intent(this,RenZhengActivity.class);
                startActivity(intent);
                break;
            case R.id.placeonorder_xuanZe:
                /**
                 * 弹出pop设置物品种类
                 */
                mPresenter.showKindPopup(mPlaceonorderTiJiaoBtn);
                break;
            case R.id.placeonorder_imgYuYin:
                ToastUtils.showToast(this, "时间太短，请坚持2秒噢！！！");
                break;
            case R.id.placeonorder_money:
                /**
                 * 弹出seekbar设置钱数
                 */
                mPresenter.showMoneyPopup(mPlaceonorderTiJiaoBtn);
                break;
            case R.id.placeonorder_tiJiaoBtn:
                break;
            case R.id.placeonorder_imgYuYinLong:
                /**
                 * 播放录音
                 */
                mPresenter.startPlayRecord(mPlaceonorderImgYuYinLong);
                break;
            case R.id.placeonorder_txtBeiZhu:
                mPresenter.showWenZiBeiZhuPopup(mPlaceonorderMoney);
                break;
        }

    }

    /**
     * 文字备注
     * @param str
     */
    @Override
    public void onGetTextBeiZhu(String str) {
        mPlaceonorderTextBeiZhu.setText(str);
    }

    /**
     * 配送费
     * @param num
     */
    @Override
    public void onGetMoney(int num) {
        mPlaceonorderMoney.setText("配送费：\r\r"+num+"元");
    }

    /**
     * 回调显示物品种类
     * @param str
     */
    @Override
    public void onGetKind(String str) {
        mPlaceonorderXuanZe.setText(str);
    }
}
