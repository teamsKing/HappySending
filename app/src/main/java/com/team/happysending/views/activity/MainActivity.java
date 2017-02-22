package com.team.happysending.views.activity;

import android.widget.TextView;

import com.team.happysending.R;
import com.team.happysending.model.bean.ResultsBean;
import com.team.happysending.presenter.MainPresenter;
import com.team.happysending.utils.L;
import com.team.happysending.utils.ToastUtils;
import com.team.happysending.views.interfaces.MainView;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView {
    private final String TAG = L.createTag(this);
    @BindView(R.id.txt)
    TextView mTxt;
    //chushihua
    private MainPresenter mPresenter;


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
    protected void initPresenter() {
        mPresenter = new MainPresenter(this);
        mPresenter.setThisView(this);


    }

    @Override
    protected void initData() {
        //网络请求
        mPresenter.getDatasFromNet();
    }

    //回调P层网络请求接口
    @Override
    public void onSuccess(List<ResultsBean> beans) {
     mTxt.setText(beans.toString());
        ToastUtils.showToast(this,beans.toString());
    }
}
