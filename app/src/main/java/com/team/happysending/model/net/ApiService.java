package com.team.happysending.model.net;


import com.team.happysending.model.bean.BaseBean;
import com.team.happysending.model.bean.ResultsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 樊、先生 on 2017/2/19.
 */

public interface ApiService {
    @GET("api/data/Android/10/1")
    Observable<BaseBean<List<ResultsBean>>> getAndroidInfo();
}
