package com.team.happysending.views.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team.happysending.presenter.BasePresenter;
import com.team.happysending.views.interfaces.BaseView;

import butterknife.ButterKnife;

/**
 * Created by zhaoshihao on 2017/2/23.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    //上下文
    protected Context mContext;
    //fragment所加载的视图
    protected View mView;
    //对应的presenter对象
    protected T mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //获取上下文
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = initView();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //注册接口
        BaseView baseInterface = initCallBack();

        mPresenter = initPresenter();

        if (mPresenter != null) {
            mPresenter.setThisView(baseInterface);
        }
        //注册butterknife
        ButterKnife.bind(getActivity());
        //初始化UI
        initUi();
        //初始化数据
        initData();
    }

    //返回Fragment所加载的视图
    protected abstract View initView();

    //初始化数据
    protected abstract void initData();

    //初始化UI
    protected abstract void initUi();

    //返回所需要的presenter
    protected abstract T initPresenter();

    //返回接口的实现类对象 (子类中可直接返回this)
    protected abstract BaseView initCallBack();


}
