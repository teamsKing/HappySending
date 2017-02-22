package com.team.happysending.utils;

import android.content.Context;
import android.widget.Toast;
/**
 * @作者 : 杨玉安
 * @日期 : 2017/2/20 0020 11:25
 *  Toast工具类
 */
public class ToastUtils {
    private static Toast toast = null; //Toast的对象！

    public static void showToast(Context mContext, String id) {
        if (toast == null) {
            toast = Toast.makeText(mContext, id, Toast.LENGTH_SHORT);
        }
        else {
            toast.setText(id);
        }
        toast.show();
    }
}
