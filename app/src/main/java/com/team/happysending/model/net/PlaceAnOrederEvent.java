package com.team.happysending.model.net;

/**
 * Created by zhang_shuai on 2017/2/28
 */

public class PlaceAnOrederEvent {
    private String Money;
    private String BeiZhu;
    private String XuanZe;

    public PlaceAnOrederEvent(String money, String beiZhu, String xuanZe) {
        Money = money;
        BeiZhu = beiZhu;
        XuanZe = xuanZe;
    }

    public String getMoney() {
        return Money;
    }

    public String getBeiZhu() {
        return BeiZhu;
    }

    public String getXuanZe() {
        return XuanZe;
    }
}
