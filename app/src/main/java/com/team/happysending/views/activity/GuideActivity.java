package com.team.happysending.views.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.airbnb.lottie.LottieAnimationView;
import com.team.happysending.R;

import java.lang.ref.WeakReference;

/**
 * Created by 樊、先生 on 2017/2/22.
 */

public class GuideActivity extends BaseActivity {

    /**
     * 创建静态内部类
     */
    private static class MyHandler extends Handler{
        //持有弱引用HandlerActivity,GC回收时会被回收掉.
        private final WeakReference<GuideActivity> mActivty;
        public MyHandler(GuideActivity activity){
            mActivty =new WeakReference<GuideActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            GuideActivity activity=mActivty.get();
            super.handleMessage(msg);
            if(activity!=null){
                if (msg.what == 0){
                    //跳转到主页面
                    Intent intent = new Intent(activity,MainActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                }

            }
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        //引导动画
        LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        //设置动画资源
        animationView.setAnimation("PinJump.json");
        animationView.playAnimation();
        MyHandler myHandler=new MyHandler(this);
        //延迟五秒
        myHandler.sendEmptyMessageDelayed(0,5000);
    }
}
