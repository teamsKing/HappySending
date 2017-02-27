package com.team.happysending.views.interfaces;

/**
 * Created by 樊、先生 on 2017/2/24.
 */

public interface PlaceAnOrderView extends BaseView {
    /**
     * 文字备注返回edittext内容
     */
   void onGetTextBeiZhu(String str);
    /**
     * 得到seekbar钱数，回调给textview
     */
    void onGetMoney(int num);
    /**
     * 得到物品种类
     */
    void onGetKind(String str);
}
