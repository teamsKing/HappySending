package com.team.happysending.presenter;

import android.content.Context;

import com.team.happysending.model.bean.BaseBean;
import com.team.happysending.model.bean.ResultsBean;
import com.team.happysending.model.net.NetClient;
import com.team.happysending.model.net.ProgressObserver;
import com.team.happysending.views.interfaces.MainView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 樊、先生 on 2017/2/19.
 * mainActivity的P层
 */

public class MainPresenter extends BasePresenter<MainView> {

    private final Context mContext;

    public MainPresenter(Context context) {
        mContext = context;
    }

    //测试
    public void getDatasFromNet() {
        Observable<BaseBean<List<ResultsBean>>> androidInfo = NetClient.getApiRetrofitInstance().getAndroidInfo();
        mHttpRequest.toSubscibers(androidInfo, new ProgressObserver<List<ResultsBean>>(mContext) {
            @Override
            public void onSuccess(List<ResultsBean> resultsBeen) {

            }
        });
    }


}
