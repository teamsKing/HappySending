package com.team.happysending.views.app;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

/**
 * Created by 樊、先生 on 2017/2/20.
 */

public class MyApplication extends Application {

    private ArrayList<Activity> mActivityList;

    public static  SharedPreferences mSp;
    @Override
    public void onCreate() {
        super.onCreate();
        if (mActivityList == null) {
            mActivityList = new ArrayList<Activity>();
        }
        Hawk.init(this).build();
        Fresco.initialize(this);
        mSp =  getSharedPreferences("config", MODE_PRIVATE);
    }

    //将activity添加进集合
    public void addActivity(Activity activity) {
        mActivityList.add(activity);
    }

    //关闭activity
    public void finishActivity() {
        for (Activity activity : mActivityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }


}
