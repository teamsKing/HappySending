package com.team.happysending.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.team.happysending.R;
import com.team.happysending.presenter.HistoryPresenter;
import com.team.happysending.views.adapter.Main_tab_Adapter;
import com.team.happysending.views.fragment.TaskHistoryFragment;
import com.team.happysending.views.fragment.UnderwayFragment;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.HistoryView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhang_shuai on 2017/2/24
 * <p>
 * 任务历史
 */

public class HistoryActivity extends BaseActivity<HistoryPresenter> implements HistoryView {

    @BindView(R.id.history_homebtn)
    Button historyHomebtn;
    @BindView(R.id.history_home_jiedan)
    ImageView historyHomeJiedan;
    @BindView(R.id.history_home_renwu)
    Button historyHomeRenwu;
    private TabLayout mTitle; //定义TabLayout
    private ViewPager mViewPager; //定义viewPager
    private Main_tab_Adapter fAdapter; //定义adapter
    private List<Fragment> list_fragment; //定义要装fragment的列表
    private List<String> list_title; //tab名称列表

    private UnderwayFragment mUnderwayFragment;//正在进行的任务
    private TaskHistoryFragment mTaskHistoryFragment; //任务的历史记录

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        mTitle = (TabLayout) findViewById(R.id.tab_history_title);
        mViewPager = (ViewPager) findViewById(R.id.vp_historyFragment_pager);
        mUnderwayFragment = new UnderwayFragment();
        mTaskHistoryFragment = new TaskHistoryFragment();

        list_fragment = new ArrayList<>();
        list_fragment.add(mUnderwayFragment);
        list_fragment.add(mTaskHistoryFragment);

        list_title = new ArrayList<>();
        list_title.add("正在进行");
        list_title.add("历史记录");

        //设置TabLayout的模式
        mTitle.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        mTitle.addTab(mTitle.newTab().setText(list_title.get(0)));
        mTitle.addTab(mTitle.newTab().setText(list_title.get(1)));
        fAdapter = new Main_tab_Adapter(getSupportFragmentManager(), list_fragment, list_title);
        //viewpager加载adapter
        mViewPager.setAdapter(fAdapter);
        //TabLayout加载viewpager
        mTitle.setupWithViewPager(mViewPager);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_history;
    }

    @Override
    protected HistoryPresenter initPresenter() {
        return new HistoryPresenter();
    }

    @Override
    protected void addActivity() {

    }

    @Override
    protected BaseView initCallBack() {
        return this;
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.history_homebtn, R.id.history_home_jiedan, R.id.history_home_renwu})
    public void onClick(View view) {
        switch (view.getId()) {
            /**
             * 回到主界面
             */
            case R.id.history_homebtn:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
            /**
             * 接单界面
             */
            case R.id.history_home_jiedan:
                startActivity(new Intent(this, OrdersActivity.class));
                finish();
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
            /**
             * 历史任务的界面即本类
             */
            case R.id.history_home_renwu:
                startActivity(new Intent(this, HistoryActivity.class));
                finish();
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
        }
    }
}
