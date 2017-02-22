package com.team.happysending.model.net;

import android.content.Context;

import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 樊、先生 on 2017/2/22.
 * 自定义观察者
 */

public abstract class ProgressObserver<T> implements Observer<T> ,ProgressCancelListener{
    /**
     * 声明AlertDialog对象
     */
    private SweetAlertDialog mAlertDialog;
    private Context mContext;
    private SweetAlertDialog mErrorDialog;

    /**
     * 在构造函数中实例化AlertDialog
     * @param context
     */
    public ProgressObserver(Context context) {
        mContext = context;
        mAlertDialog = new SweetAlertDialog(context,SweetAlertDialog.PROGRESS_TYPE);
        mAlertDialog.setTitleText("Loading...");

    }

    /**
     * 开始时显示Dialog
     * @param d
     */
    @Override
    public void onSubscribe(Disposable d) {
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {

        String error= "";
        if(e instanceof ApiException){
            error = e.getMessage();
        } else if ((e instanceof UnknownHostException)) {
            error = "网络异常";
        } else if (e instanceof JsonSyntaxException) {
            error = "数据异常";
        } else if (e instanceof SocketTimeoutException) {
            error = "连接超时";
        } else if (e instanceof ConnectException) {
            error = "连接服务器失败";
        }else{
            error = "未知异常";
        }
        mErrorDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE);
        mErrorDialog.setTitleText("出问题了....")
                .setContentText(error)
                .show();

        if(mAlertDialog != null){
            mAlertDialog.cancel();
        }
    }

    /**
     * 完成时取消提示窗
     */
    @Override
    public void onComplete() {
        dismissProgressDialog();
        if (mErrorDialog != null ) {
            mErrorDialog.cancel();
        }
    }

    /**
     * 显示Dialog
     */
    public void showProgressDialog(){
        if (mAlertDialog != null) {
            mAlertDialog.show();
        }
    }
    /**
     * 取消提示窗
     */
    protected void dismissProgressDialog(){
        if (mAlertDialog != null) {
//            dialogHandler.obtainMessage(SimpleLoadDialog.DISMISS_PROGRESS_DIALOG).sendToTarget();
           mAlertDialog.dismiss();
          //  mAlertDialog=null;
        }
    };

    public abstract void onSuccess(T t);

    @Override
    public void onCancelProgress() {

    }
}
