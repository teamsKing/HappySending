package com.team.happysending.model.net;


import com.team.happysending.model.bean.BaseBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 樊、先生 on 2017/2/22.
 */
//, String cacheKey, boolean isSave
public class HttpUtils {

    private static HttpUtils mHttpRequest;

    private HttpUtils() {
    }

    public static HttpUtils getInstances() {
        if (mHttpRequest == null) {
            synchronized (HttpUtils.class) {
                if (mHttpRequest == null) {
                    mHttpRequest = new HttpUtils();
                }
            }
        }
        return mHttpRequest;
    }

    public static <T> void toSubscibers(Observable<BaseBean<T>> observable, ProgressObserver<T> ober) {

        observable.subscribeOn(Schedulers.io())
                .map(new Function<BaseBean<T>, T>() {
                    @Override
                    public T apply(BaseBean<T> tBaseBean) throws Exception {
                        if (!tBaseBean.getCode().equals("07000")) {
                            throw new ApiException(tBaseBean.getMessage());  //抛出自定义异常
                        }
                        return tBaseBean.getUserinfo();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ober);
    }
}
