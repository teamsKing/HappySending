package com.team.happysending.presenter;

import android.content.Context;

import com.team.happysending.model.bean.BaseBean;
import com.team.happysending.model.bean.RegistBean;
import com.team.happysending.model.net.NetClient;
import com.team.happysending.model.net.ProgressObserver;
import com.team.happysending.views.interfaces.RegistView;

import io.reactivex.Observable;

/**
 * Created by zhaoshihao on 2017/2/24.
 */

public class RegistPresenter extends BasePresenter<RegistView> {
    private final Context mContext;

    public RegistPresenter(Context context) {
        mContext = context;
    }

    //注册
    public void getDatasFromNet(String nicheng, String phone, String password, String repetitionpwd) {
        Observable<BaseBean<RegistBean>> androidInfo = NetClient.getApiRetrofitInstance().getRegistData(nicheng, phone, password, repetitionpwd);
        mHttpRequest.toSubscibers(androidInfo, new ProgressObserver<RegistBean>(mContext) {
            @Override
            public void onSuccess(RegistBean resultsBeen) {
                someView.OnRequestSuccess(resultsBeen);
            }
        });
    }

}
