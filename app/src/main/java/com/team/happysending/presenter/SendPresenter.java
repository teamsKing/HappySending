package com.team.happysending.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.team.happysending.R;
import com.team.happysending.model.bean.BannerItem;
import com.team.happysending.views.interfaces.SendView;

import java.util.ArrayList;

/**
 * Created by 樊、先生 on 2017/2/23.
 */

public class SendPresenter extends BasePresenter<SendView> {
    /**
     * 存放图片的url的集合
     */
    private ArrayList<BannerItem> mBannerItems;
    /**
     * 设置viewpager滑动
     * @param mConvenientBanner
     */
    public void setConvenientBannerView(ConvenientBanner mConvenientBanner){


        mBannerItems = new ArrayList<>();
        mBannerItems.add(new BannerItem("http://img2.imgtn.bdimg.com/it/u=115095478,556151950&fm=21&gp=0.jpg"));
        mBannerItems.add(new BannerItem("http://img2.imgtn.bdimg.com/it/u=3440997812,1142459374&fm=23&gp=0.jpg"));
        mBannerItems.add(new BannerItem("http://img1.imgtn.bdimg.com/it/u=2004503322,3375141491&fm=23&gp=0.jpg"));
        mBannerItems.add(new BannerItem("http://img0.imgtn.bdimg.com/it/u=2254867758,3578667568&fm=23&gp=0.jpg"));
        mBannerItems.add(new BannerItem("http://img3.imgtn.bdimg.com/it/u=161604145,2128129646&fm=23&gp=0.jpg"));
        //开始自动翻页

        mConvenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, mBannerItems)    //设置需要切换的View
                .setPointViewVisible(true)    //设置指示器是否可见
                //.setPageIndicator(new int[]{R.drawable.u49, R.drawable.u55})   //设置指示器圆点
                .startTurning(3000)     //设置自动切换（同时设置了切换时间间隔）
                //关闭自动切换
                .setManualPageable(true);  //设置手动影响（设置了该项无法手动切换）

    }

    public class NetworkImageHolderView implements Holder<BannerItem> {
        private View view;

        @Override
        public View createView(Context context) {
            view = LayoutInflater.from(context).inflate(R.layout.banner_item, null, false);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerItem data) {
            //接口回调显示UI
            getThisView().onSucceess(view,data);
        }
    }
}
