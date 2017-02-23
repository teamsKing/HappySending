package com.team.happysending.views.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.team.happysending.R;
import com.team.happysending.presenter.MainPresenter;
import com.team.happysending.views.adapter.Main_tab_Adapter;
import com.team.happysending.views.fragment.HelpMeToBuyFragment;
import com.team.happysending.views.fragment.HelpMeToHandFragment;
import com.team.happysending.views.fragment.HelpMeToSendFragment;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    private TabLayout tab_FindFragment_title; //定义TabLayout
    private ViewPager vp_FindFragment_pager; //定义viewPager
    private Main_tab_Adapter fAdapter; //定义adapter
    private List<Fragment> list_fragment; //定义要装fragment的列表
    private List<String> list_title; //tab名称列表
    private HelpMeToSendFragment mHelpMeToSend; //帮我送fragment
    private HelpMeToBuyFragment mHelpMeToBuy; //帮我买fragment
    private HelpMeToHandFragment mHelpMeToHand; //帮我忙fragment


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initFragment();
        initUI();

    }

    private void initUI() {
        tab_FindFragment_title = (TabLayout) findViewById(R.id.tab_FindFragment_title);
        vp_FindFragment_pager = (ViewPager) findViewById(R.id.vp_FindFragment_pager);
        //初始化各fragment
        mHelpMeToSend = new HelpMeToSendFragment();
        mHelpMeToBuy = new HelpMeToBuyFragment();
        mHelpMeToHand = new HelpMeToHandFragment();
        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(mHelpMeToSend);
        list_fragment.add(mHelpMeToBuy);
        list_fragment.add(mHelpMeToHand);
        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("帮我送");
        list_title.add("帮我买");
        list_title.add("帮我忙");
        //设置TabLayout的模式
        tab_FindFragment_title.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(0)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(1)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(2)));
        fAdapter = new Main_tab_Adapter(getSupportFragmentManager(), list_fragment, list_title);
        //viewpager加载adapter
        vp_FindFragment_pager.setAdapter(fAdapter);
        //TabLayout加载viewpager
        tab_FindFragment_title.setupWithViewPager(vp_FindFragment_pager);
    }

    private void initFragment() {


    }

    /**
     * 绑定xml布局
     *
     * @return
     */
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    /**
     * 初始化P
     */
    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void addActivity() {
        application.addActivity(this);
    }

    @Override
    protected BaseView initCallBack() {
        return this;
    }

    @Override
    protected void initData() {
        //网络请求
        mPresenter.getDatasFromNet();
    }


}
