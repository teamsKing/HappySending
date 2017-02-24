package com.team.happysending.model.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 樊、先生 on 2017/2/19.
 * 网络请求工具类
 */

public class NetClient {
    //主网址
    public static final String HOST = "http://169.254.0.89:8080/MyPaoT/";
    private static ApiService apiService;
    protected static final Object monitor = new Object();
    private static Retrofit retrofit;

    /**
     * 请求超时时间
     */
    private static final int DEFAULT_TIMEOUT = 10000;

    static {


        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        /**
         *  拦截器
         */
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

//                    Request.Builder requestBuilder = request.newBuilder();
//                    RequestBody formBody = new FormBody.Builder()
//                            .add("1","2")
//                            .build();

                HttpUrl.Builder authorizedUrlBuilder = request.url()
                        .newBuilder()
                        //添加统一参数 如手机唯一标识符,token等
                        .addQueryParameter("key1", "value1")
                        .addQueryParameter("key2", "value2");

                Request newRequest = request.newBuilder()
                        //对所有请求添加请求头
                        .header("mobileFlag", "adfsaeefe").addHeader("type", "4")
                        .method(request.method(), request.body())
                        .url(authorizedUrlBuilder.build())
                        .build();

//                    okhttp3.Response originalResponse = chain.proceed(request);
//                    return originalResponse.newBuilder().header("mobileFlag", "adfsaeefe").addHeader("type", "4").build();
                return chain.proceed(newRequest);
            }
        });

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .client(httpClientBuilder.build())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static ApiService getApiRetrofitInstance() {

        if (apiService == null) {
            synchronized (monitor) {
                if (apiService == null) {
                    apiService = retrofit.create(ApiService.class);
                }
            }
        }
        return apiService;
    }


}
