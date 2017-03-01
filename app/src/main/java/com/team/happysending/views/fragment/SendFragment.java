package com.team.happysending.views.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.facebook.drawee.view.SimpleDraweeView;
import com.team.happysending.R;
import com.team.happysending.model.bean.BannerItem;
import com.team.happysending.model.net.FirstEvent;
import com.team.happysending.presenter.SendPresenter;
import com.team.happysending.views.activity.ReGeocoderActivity;
import com.team.happysending.views.app.MyApplication;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.SendView;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by 樊、先生 on 2017/2/23.
 * 帮我送的界面，实现头部viewpager无线轮播等功能
 */

public class SendFragment extends BaseFragment<SendPresenter> implements SendView {

    /**
     * viewpager
     */
    @BindView(R.id.cblViewPager)
    ConvenientBanner mConvenientBanner;
    /**
     * 发货地址
     */
    @BindView(R.id.startAddress)
    TextView mStartAddress;

    @BindView(R.id.send_start)
    RelativeLayout mSendStart;

    @BindView(R.id.fragmentsend_txt2)
    TextView mFragmentsendTxt2;
    /**
     * 取货地址
     */
    @BindView(R.id.stopAddress)
    TextView mStopAddress;
    @BindView(R.id.stop_start)
    RelativeLayout mStopStart;
    @BindView(R.id.fragmentsend_txt3)
    TextView mFragmentsendTxt3;
    /**
     * 取货时间
     */
    @BindView(R.id.quhuoTime)
    TextView mQuhuoTime;
    @BindView(R.id.fragmentsend_txt32)
    TextView mFragmentsendTxt32;
    @BindView(R.id.textView4)
    TextView mTextView4;
    @BindView(R.id.fragmentsend_img3)
    ImageView mFragmentsendImg3;
    @BindView(R.id.get_time)
    RelativeLayout mGetTime;
    /**
     * 下单按钮
     */
    @BindView(R.id.send_btn)
    Button mSendBtn;
    @BindView(R.id.send_linearLayout)
    LinearLayout mSendLinearLayout;
    /**
     * 是否设置取货时间
     */
    private boolean SETTIME = false;
    /**
     * 设置取货地址
     */
    private boolean SETSTOPADD = false;
    /**
     * 发货地址
     */
    private boolean SETSTARTADD = false;
    /**
     * 开始的地址
     */
    private String startAdderss;
    /**
     * 发货的地址
     */
    private String endAdderss;


    @Override
    protected void initView(View view) {
        //注册EventBus
        EventBus.getDefault().register(this);


    }


    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEvent(FirstEvent event){
        String msg = event.getMsg();
        int falg = event.getFalg();
        if(falg == 1){
            startAdderss = msg;
            mStartAddress.setText(startAdderss);
            SETSTOPADD = true;
            MyApplication.mSp.edit().putString("startAdderss",startAdderss).commit();

        }else if(falg ==2){
            endAdderss = msg;
            mStopAddress.setText(endAdderss);
            SETSTARTADD = true;
            MyApplication.mSp.edit().putString("endAdderss",endAdderss).commit();
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_send;
    }

    @Override
    protected void initData() {
        //调用P层的Viewpager设置方法
        mPresenter.setConvenientBannerView(mConvenientBanner);
    }


    @Override
    protected SendPresenter initPresenter() {
        return new SendPresenter(getActivity());
    }

    @Override
    protected BaseView initCallBack() {
        return this;
    }

    /**
     * fresco显示图片（viewpager的回调方法）
     * @param view
     * @param data
     */
    @Override
    public void onSucceess(View view, BannerItem data) {

        ((SimpleDraweeView) view.findViewById(R.id.sdv_background)).setImageURI(Uri.parse(data.getImageUrl()));

    }


    /**
     * 获取取货时间
     * @param time
     */
    @Override
    public void getQuHuoTime(String time, boolean flag) {
        SETTIME = flag;
        mTextView4.setVisibility(View.GONE);
        mQuhuoTime.setTextSize(13);
        mQuhuoTime.setTextColor(Color.BLUE);
        mQuhuoTime.setText("取货时间"+time);
    }




    @OnClick({R.id.send_start, R.id.stop_start, R.id.get_time, R.id.send_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            /**
             * 开始地址
             */
            case R.id.send_start:
                int falg = 1;
                Intent intent =  new Intent(mContext, ReGeocoderActivity.class);
                intent.putExtra("falg",falg);
                startActivity(intent);
                break;
            /**
             * 结束地址
             */
            case R.id.stop_start:
                int tag = 2;
                Intent intent1 =  new Intent(mContext, ReGeocoderActivity.class);
                intent1.putExtra("falg",tag);
                startActivity(intent1);
                break;
            /**
             * 获取取货时间
             */
            case R.id.get_time:
                mPresenter.showWeekBottoPopupWindow(mSendLinearLayout);
                SETTIME = true;
                break;
            /**
             * 立即下单
             */
            case R.id.send_btn:
                /**
                 * 判断三个boolea值，都满足情况后跳转
                 */
                mPresenter.checkMsg(SETTIME,SETSTARTADD,SETSTOPADD);
                break;
        }
    }

}
