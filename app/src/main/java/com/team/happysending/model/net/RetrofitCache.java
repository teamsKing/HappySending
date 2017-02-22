package com.team.happysending.model.net;


import com.orhanobut.hawk.Hawk;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by helin on 2016/11/10 10:41.
 */

public class RetrofitCache {
    /**
     * @param cacheKey       缓存的Key
     * @param fromNetwork
     * @param isSave         是否缓存
     * @param //forceRefresh 是否强制刷新
     * @param <T>
     * @return
     */                                         /*boolean forceRefresh*/
    public static <T> Observable<T> load(final String cacheKey,
                                         Observable<T> fromNetwork,
                                         boolean isSave
    ) {
        Observable<T> fromCache = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {

                T cache = (T) Hawk.get(cacheKey);
                if (cache != null) {
                    e.onNext(cache);
                } else {
                    e.onComplete();
                }
            }
        })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        //是否缓存
        if (isSave) {
            /**
             * 这里的fromNetwork 不需要指定Schedule,在handleRequest中已经变换了
             */
            fromNetwork = fromNetwork.map(new Function<T, T>() {
                @Override
                public T apply(T t) throws Exception {
                    Hawk.put(cacheKey, t);
                    return t;
                }
            });
        }
        //强制刷新
 /*       if (forceRefresh) {
            return fromNetwork;
        } else {
//            return Observable.concat(fromCache, fromNetwork).first();
            return null;
            Observable.concat(fromCache, fromNetwork).take(new Function<T, Boolean>() {
                @Override
                public Boolean apply(T t) throws Exception {
                    return t!=null;
                }
            });


        }*/
        return fromNetwork;
    }

}
