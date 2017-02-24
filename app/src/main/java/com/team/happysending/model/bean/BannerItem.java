package com.team.happysending.model.bean;

/**
 * Created by 樊、先生 on 2017/2/23.
 * ConvenientBanner 设置适配器的地址bean类，在SendFragment中引用
 */

public class BannerItem {
    private String imageUrl;

    public BannerItem(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
