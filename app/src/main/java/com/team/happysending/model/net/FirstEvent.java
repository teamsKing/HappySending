package com.team.happysending.model.net;

/**
 * Created by zhang_shuai on 2017/2/28
 */

public class FirstEvent {

    private String mMsg;
    private int  mFalg;
    public FirstEvent(String msg,int falg) {
        mMsg = msg;
        mFalg = falg;
    }
    public String getMsg(){
        return mMsg;
    }
    public int getFalg(){
        return mFalg;
    }
}
