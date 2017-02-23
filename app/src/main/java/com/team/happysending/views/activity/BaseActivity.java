package com.team.happysending.views.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.team.happysending.model.net.ActivityLifeCycleEvent;
import com.team.happysending.presenter.BasePresenter;
import com.team.happysending.views.app.MyApplication;
import com.team.happysending.views.interfaces.BaseView;

import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by 樊、先生 on 2017/2/19.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    //得到生命周期，用于联网时，判断activity的生命状态
    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();

    protected MyApplication application;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
        super.onCreate(savedInstanceState);
        //注册接口
        BaseView baseInterface = initCallBack();
        if (application == null) {
            application = (MyApplication) getApplicationContext();
        }
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.setThisView(baseInterface);
        }
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

    protected abstract T initPresenter();

    protected abstract void addActivity();

    protected abstract BaseView initCallBack();


    //初始化数据
    protected abstract void initData();
}
