package com.team.happysending.views.interfaces;

import com.team.happysending.model.bean.ResultsBean;

import java.util.List;

/**
 * Created by 樊、先生 on 2017/2/19.
 */

public interface MainView extends BaseView {

   void onSuccess(List<ResultsBean> beans);
}
