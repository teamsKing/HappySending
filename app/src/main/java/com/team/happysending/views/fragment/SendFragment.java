package com.team.happysending.views.fragment;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.facebook.drawee.view.SimpleDraweeView;
import com.team.happysending.R;
import com.team.happysending.model.bean.BannerItem;
import com.team.happysending.presenter.SendPresenter;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.SendView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 樊、先生 on 2017/2/23.
 * 帮我送的界面，实现头部viewpager无线轮播等功能
 */

public class SendFragment extends BaseFragment<SendPresenter> implements SendView {


    @BindView(R.id.cblViewPager)
    ConvenientBanner mConvenientBanner;
    @BindView(R.id.send_start)
    RelativeLayout mSendStart;
    @BindView(R.id.stop_start)
    RelativeLayout mStopStart;
    @BindView(R.id.get_time)
    RelativeLayout mGetTime;
    @BindView(R.id.send_btn)
    Button mSendBtn;



    @Override
    protected void initView(View view) {
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
        return new SendPresenter();
    }

    @Override
    protected BaseView initCallBack() {
        return this;
    }



    @OnClick(R.id.send_btn)
    public void onClick() {

    }

    @Override
    public void onSucceess(View view, BannerItem data) {
        ((SimpleDraweeView)view.findViewById(R.id.sdv_background)).setImageURI(Uri.parse(data.getImageUrl()));

    }
}
