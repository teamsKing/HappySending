package com.team.happysending.views.app;

import android.app.Activity;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.hawk.Hawk;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;

/**
 * Created by 樊、先生 on 2017/2/20.
 */

public class MyApplication extends Application {

    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    private ArrayList<Activity> mActivityList;


    @Override
    public void onCreate() {
        super.onCreate();
        if (mActivityList == null) {
            mActivityList = new ArrayList<Activity>();
        }
        UMShareAPI.get(this);
        Hawk.init(this).build();
        Fresco.initialize(this);
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
