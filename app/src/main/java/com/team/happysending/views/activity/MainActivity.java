package com.team.happysending.views.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.team.happysending.R;
import com.team.happysending.presenter.MainPresenter;
import com.team.happysending.utils.Constant;
import com.team.happysending.views.adapter.Main_tab_Adapter;
import com.team.happysending.views.fragment.HelpMeToBuyFragment;
import com.team.happysending.views.fragment.HelpMeToHandFragment;
import com.team.happysending.views.fragment.SendFragment;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.MainView;
import com.zaaach.citypicker.CityPickerActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView, View.OnClickListener {

    private TabLayout tab_FindFragment_title; //定义TabLayout
    private ViewPager vp_FindFragment_pager; //定义viewPager
    private Main_tab_Adapter fAdapter; //定义adapter
    private List<Fragment> list_fragment; //定义要装fragment的列表
    private List<String> list_title; //tab名称列表
    private HelpMeToBuyFragment mHelpMeToBuy; //帮我买fragment
    private HelpMeToHandFragment mHelpMeToHand; //帮我忙fragment
    private SendFragment mSendFragment;//帮我送fragment
    private ImageView image;
    private TextView logintext;
    private RelativeLayout mRelative;
    private static final int REQUEST_CODE_PICK_CITY = 0;
    private TextView mCityName;
    private ImageView slidingimageView;
    private TextView slidingname;
    private TextView slidingsex;
    private TextView slidingcity;
    private TextView slidingprovince;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();

        //实例化菜单控件
        SlidingMenu slidingMenu = new SlidingMenu(this);
        //设置相关属性
        slidingMenu.setMode(SlidingMenu.RIGHT);//菜单靠左
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu.setBehindOffset(200);//SlidingMenu划出时主页面显示的剩余宽度
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);//不包含ActionBar
        slidingMenu.setMenu(R.layout.right_menu);
        //设置透明度
        slidingMenu.setOffsetFadeDegree(0.4f);
        slidingimageView = (ImageView) findViewById(R.id.sliding_image);
        slidingname = (TextView) findViewById(R.id.sliding_name);
        slidingsex = (TextView) findViewById(R.id.sliding_sex);
        slidingcity = (TextView) findViewById(R.id.sliding_city);
        slidingprovince = (TextView) findViewById(R.id.sliding_province);


    }

    @Override
    protected void onResume() {
        super.onResume();
        String token = mSp.getString(Constant.LOGINTOKEN, "");
        if (TextUtils.isEmpty(token)) {
            image.setVisibility(View.GONE);
            logintext.setVisibility(View.VISIBLE);
        } else {
            Uri uri = Uri.parse(getIntent().getStringExtra("iconurl"));
            slidingimageView.setImageURI(uri);
            slidingname.setText(getIntent().getStringExtra("name"));
            slidingsex.setText(getIntent().getStringExtra("gender"));
            slidingcity.setText(getIntent().getStringExtra("city"));
            slidingprovince.setText(getIntent().getStringExtra("province"));
            image.setVisibility(View.VISIBLE);
            logintext.setVisibility(View.GONE);
        }
    }

    private void initUI() {
        mCityName = (TextView) findViewById(R.id.cityname);
        mCityName.setOnClickListener(this);
        mRelative = (RelativeLayout) findViewById(R.id.relative);
        findViewById(R.id.btn_right).setOnClickListener(this);
        logintext = (TextView) findViewById(R.id.text_btn);
        logintext.setOnClickListener(this);
        image = (ImageView) findViewById(R.id.btn_right);
        tab_FindFragment_title = (TabLayout) findViewById(R.id.tab_FindFragment_title);
        vp_FindFragment_pager = (ViewPager) findViewById(R.id.vp_FindFragment_pager);
        findViewById(R.id.renwu).setOnClickListener(this);
        findViewById(R.id.jiedan).setOnClickListener(this);
        //初始化各fragment
        mSendFragment = new SendFragment();
        mHelpMeToBuy = new HelpMeToBuyFragment();
        mHelpMeToHand = new HelpMeToHandFragment();
        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(mSendFragment);
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

        vp_FindFragment_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mRelative.setVisibility(View.VISIBLE);
                }
                if (i == 1) {
                    mRelative.setVisibility(View.GONE);
                }
                if (i == 2) {
                    mRelative.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

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
        return new MainPresenter();
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

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cityname:
                //启动
                startActivityForResult(new Intent(MainActivity.this, CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
            case R.id.text_btn:
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
            case R.id.jiedan:
                startActivity(new Intent(this, OrdersActivity.class));
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
            case R.id.renwu:
                startActivity(new Intent(this, HistoryActivity.class));
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
            case R.id.btn_right:

                break;
        }
    }

    //重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                mCityName.setText(city);
            }
        }
    }
}
