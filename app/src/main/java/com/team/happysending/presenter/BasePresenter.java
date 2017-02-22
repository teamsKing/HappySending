package com.team.happysending.presenter;


import com.team.happysending.views.interfaces.BaseView;

/**
 * Created by 樊、先生 on 2017/2/19.
 * P层基类
 */

public class BasePresenter<T extends BaseView> {
    private T someView;
    public T getThisView(){
        return someView;
    }
    public void setThisView(T thisView){
        someView = thisView;
        someView.initView();
    }

}
