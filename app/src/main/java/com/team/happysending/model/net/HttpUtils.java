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
    public static <T> void toSubscibers(Observable<BaseBean<T>> observable, ProgressObserver<T> ober){

        observable. subscribeOn(Schedulers.io())
                .map(new Function<BaseBean<T>, T>() {
                    @Override
                    public T apply(BaseBean<T> tBaseBean) throws Exception {
                        return tBaseBean.getResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ober);
        //RetrofitCache.load(cacheKey,observable,isSave);
    }
}
