package com.team.happysending.model.net;


import com.team.happysending.model.bean.BaseBean;
import com.team.happysending.model.bean.LoginBean;
import com.team.happysending.model.bean.RegistBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 樊、先生 on 2017/2/19.
 */

public interface ApiService {


    @FormUrlEncoded
    @POST("login")
    Observable<BaseBean<LoginBean>> getLoginData(@Field("phone_num") String phonenum, @Field("user_password") String userpassword);

    @FormUrlEncoded
    @POST("register")
    Observable<BaseBean<RegistBean>> getRegistData(@Field("nick_name") String nicehng, @Field("phone_num") String phonenum, @Field("user_password") String userpassword, @Field("user_district") String userdistrict);


}
