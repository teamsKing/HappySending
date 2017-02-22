package com.team.happysending.views.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.team.happysending.model.net.ActivityLifeCycleEvent;

import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by 樊、先生 on 2017/2/19.
 */

public abstract class BaseActivity extends AppCompatActivity {
    //得到生命周期，用于联网时，判断activity的生命状态
    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
        super.onCreate(savedInstanceState);
        //添加布局
        setContentView(getLayout());
        //注册butterknife
        ButterKnife.bind(this);
        //实例化P
        initPresenter();
        //初始化数据
        initData();
    }

    @Override
    protected void onPause() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);
    }


    protected abstract int getLayout();
    protected abstract void initPresenter();
    protected abstract void initData();
}
