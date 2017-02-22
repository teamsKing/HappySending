package com.team.happysending.model.bean;

/**
 * Created by 樊、先生 on 2017/2/21.
 * 基类
 */

public  class BaseBean<T> {
    //  判断标示
    private boolean error;

    //显示数据（用户需要关心的数据）
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
