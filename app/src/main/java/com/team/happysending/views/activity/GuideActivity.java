package com.team.happysending.views.activity;

import com.airbnb.lottie.LottieAnimationView;
import com.team.happysending.R;

/**
 * Created by 樊、先生 on 2017/2/22.
 */

public class GuideActivity extends BaseActivity {
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
        animationView.setAnimation("PinJump.json");
        animationView.playAnimation();
    }
}
