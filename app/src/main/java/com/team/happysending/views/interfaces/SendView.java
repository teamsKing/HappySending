package com.team.happysending.views.interfaces;

import android.view.View;

import com.team.happysending.model.bean.BannerItem;

/**
 * Created by 樊、先生 on 2017/2/23.
 * 帮我送的view层接口
 */

public interface SendView extends BaseView {

    /**
     * 设置viewpager返回URL
     */
    void onSucceess(View view,BannerItem data);

}
