package com.team.happysending.views.fragment;

import android.view.View;

import com.team.happysending.R;
import com.team.happysending.presenter.TaskHistoryPresenter;
import com.team.happysending.views.interfaces.BaseView;
import com.team.happysending.views.interfaces.TaskHistoryView;

/**
 * Created by zhang_shuai on 2017/2/24
 *
 * 任务的历史记录
 */

public class TaskHistoryFragment extends BaseFragment<TaskHistoryPresenter> implements TaskHistoryView {
    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_task_history;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected TaskHistoryPresenter initPresenter() {
        return new TaskHistoryPresenter();
    }

    @Override
    protected BaseView initCallBack() {
        return this;
    }
}
