package com.team.happysending.presenter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.team.happysending.R;
import com.team.happysending.model.bean.BannerItem;
import com.team.happysending.utils.ToastUtils;
import com.team.happysending.views.activity.PlaceAnOrderActivity;
import com.team.happysending.views.interfaces.SendView;
import com.team.happysending.views.widget.wheelview.DateUtils;
import com.team.happysending.views.widget.wheelview.JudgeDate;
import com.team.happysending.views.widget.wheelview.ScreenInfo;
import com.team.happysending.views.widget.wheelview.WheelWeekMain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 樊、先生 on 2017/2/23.
 */

public class SendPresenter extends BasePresenter<SendView> {
    private Activity mContext;
    public SendPresenter(Activity context){
        mContext = context;
    }
    private WheelWeekMain wheelWeekMainDate;
    private String beginTime;
    /**
     * 存放图片的url的集合
     */
    private ArrayList<BannerItem> mBannerItems;


    /**
     * 设置viewpager滑动
     * @param mConvenientBanner
     */
    public void setConvenientBannerView(ConvenientBanner mConvenientBanner){
    /*    String url1 = "res://com.team.happysending/" + R.drawable.lunbo1;
        String url2 = "res://com.team.happysending/" + R.drawable.lunbo2;
        String url3 = "res://com.team.happysending/" + R.drawable.lunbo1;
        String url4 = "res://com.team.happysending/" + R.drawable.lunbotu;
        */

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

    private java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 时间滚动的popwindow
     */
    public void showWeekBottoPopupWindow(LinearLayout sendLinearLayout) {

        WindowManager manager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        View menuView = LayoutInflater.from(mContext).inflate(R.layout.show_week_popup_window,null);
        final PopupWindow mPopupWindow = new PopupWindow(menuView, (int)(width*0.8),
                ActionBar.LayoutParams.WRAP_CONTENT);
        ScreenInfo screenInfoDate = new ScreenInfo(mContext);
        wheelWeekMainDate = new WheelWeekMain(menuView, true);
        wheelWeekMainDate.screenheight = screenInfoDate.getHeight();
        String time = DateUtils.currentMonth().toString();
        Calendar calendar = Calendar.getInstance();
        if (JudgeDate.isDate(time, "yyyy-MM-DD")) {
            try {
                calendar.setTime(new Date(time));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelWeekMainDate.initDateTimePicker(year, month, day, hours,minute);
        final String currentTime = wheelWeekMainDate.getTime().toString();
        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        mPopupWindow.showAtLocation(sendLinearLayout, Gravity.CENTER, 0, 0);
        mPopupWindow.setOnDismissListener(new poponDismissListener());
        backgroundAlpha(0.6f);
        TextView tv_cancle = (TextView) menuView.findViewById(R.id.tv_cancle);
        TextView tv_ensure = (TextView) menuView.findViewById(R.id.tv_ensure);
        TextView tv_pop_title = (TextView) menuView.findViewById(R.id.tv_pop_title);
        tv_pop_title.setText("选择起始时间");
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });
        tv_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                beginTime = wheelWeekMainDate.getTime().toString();
                //回调接口返回时间
                getThisView().getQuHuoTime(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm),true);


                mPopupWindow.dismiss();
                //设置背景回复正常
                backgroundAlpha(1f);
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        mContext.getWindow().setAttributes(lp);
    }

    class poponDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }

    /**
     * 判断三个boolea值，都满足情况后跳转
     * @param SETTIME
     * @param SETSTARTADD
     * @param SETSTOPADD
     */
    public void checkMsg(boolean SETTIME, boolean SETSTARTADD, boolean SETSTOPADD){
        if (SETTIME && SETSTARTADD && SETSTOPADD){
            //跳转
            Intent intent = new Intent(mContext,PlaceAnOrderActivity.class);
            mContext.startActivity(intent);
            mContext.finish();
        }else{
            ToastUtils.showToast(mContext,"请完善信息！！");
        }
    }
}
