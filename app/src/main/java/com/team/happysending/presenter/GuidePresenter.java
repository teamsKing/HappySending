package com.team.happysending.presenter;

import android.content.Context;

import com.team.happysending.views.interfaces.GuideView;

/**
 * Created by zhaoshihao on 2017/2/23.
 */

public class GuidePresenter extends BasePresenter<GuideView> {

    private final Context mContext;

    public GuidePresenter(Context mContext) {
        this.mContext = mContext;
    }
}
