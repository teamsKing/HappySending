package com.team.happysending.presenter;


import com.team.happysending.model.net.HttpUtils;
import com.team.happysending.views.interfaces.BaseView;

/**
 * Created by 樊、先生 on 2017/2/19.
 * P层基类
 */

public abstract class BasePresenter<T extends BaseView> {
    //定义回调接口的引用
    protected T someView;

    protected HttpUtils mHttpRequest;

    {
        mHttpRequest = HttpUtils.getInstances();
    }

    //返回接口的实现类对象
    public T getThisView() {
        return someView;
    }

    //注册接口
    public void setThisView(T thisView) {
        this.someView = thisView;
    }

}
