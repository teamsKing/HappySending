package com.team.happysending.model.bean;

/**
 * Created by 樊、先生 on 2017/2/21.
 * 基类
 */

public class BaseBean<T> {


    private String code;
    private String message;
    //显示数据（用户需要关心的数据）
    private T userinfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(T userinfo) {
        this.userinfo = userinfo;
    }
}
