package com.team.happysending.views.app;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

/**
 * Created by 樊、先生 on 2017/2/20.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();

    }


}
