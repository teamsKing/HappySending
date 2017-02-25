package com.team.happysending.presenter;

import android.content.Context;

import com.team.happysending.model.bean.BaseBean;
import com.team.happysending.model.bean.LoginBean;
import com.team.happysending.model.net.NetClient;
import com.team.happysending.model.net.ProgressObserver;
import com.team.happysending.views.interfaces.LoginView;

import io.reactivex.Observable;

/**
 * 登陆
 * Created by zhaoshihao on 2017/2/23.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    private final Context mContext;

    public LoginPresenter(Context context) {
        mContext = context;
    }

    //登陆
    public void getDatasFromNet(String loginphone, String loginnum) {
        Observable<BaseBean<LoginBean>> androidInfo = NetClient.getApiRetrofitInstance().getLoginData(loginphone, loginnum);
        mHttpRequest.toSubscibers(androidInfo, new ProgressObserver<LoginBean>(mContext) {
            @Override
            public void onSuccess(LoginBean resultsBeen) {
                someView.OnSuccess(resultsBeen);
            }
        });
    }
}
