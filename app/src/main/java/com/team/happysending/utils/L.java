package com.team.happysending.utils;

import android.util.Log;

/**
 * Created by 樊、先生 on 2017/2/22.
 * 自定义Log类
 */

public class L {
    //默认的TAG，建议后面加下划线
    private static final String DEFAULT_TAG = "TAG";

    //日志等级（不想打印日志，可设置为 log.ASSERT）
    public static int LOG_LEVEL = Log.VERBOSE;

    //防止实例
    private L() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //生成TAG
    public static String createTag(Object o) {
        return o == null ? createTag() : DEFAULT_TAG + o.getClass().getSimpleName();
    }

    //生成默认TAG
    public static String createTag() {
        return DEFAULT_TAG;
    }

    public static void v(String msg) {
        if (Log.VERBOSE >= LOG_LEVEL)
            Log.v(DEFAULT_TAG, msg);
    }

    public static void d(String msg) {
        if (Log.DEBUG>=LOG_LEVEL)
            Log.d(DEFAULT_TAG, msg);
    }

    public static void i(String msg) {
        if (Log.INFO>=LOG_LEVEL)
            Log.i(DEFAULT_TAG, msg);
    }

    public static void w(String msg) {
        if (Log.WARN>=LOG_LEVEL)
            Log.w(DEFAULT_TAG, msg);
    }

    public static void e(String msg) {
        if (Log.ERROR>=LOG_LEVEL) {
            e(DEFAULT_TAG, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (Log.VERBOSE>=LOG_LEVEL)
            Log.v(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (Log.DEBUG>=LOG_LEVEL)
            Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (Log.INFO>=LOG_LEVEL)
            Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (Log.WARN>=LOG_LEVEL)
            Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (Log.ERROR>=LOG_LEVEL) {
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
            Log.e(tag, "(" + targetStackTraceElement.getFileName() + ":" + targetStackTraceElement.getLineNumber() + ")");
            Log.e(tag, msg);
        }
    }

    //用于打印可以忽略的信息，比如说被忽视的catch
    public static void ignore(String tag, String msg) {
        if (Log.VERBOSE>=LOG_LEVEL)
            Log.v(tag, "ignore_" + msg);
    }

    //来自鸿洋大神的手笔
    private static StackTraceElement getTargetStackTraceElement() {
        // find the target invoked method
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(L.class.getName())||stackTraceElement.getClassName().startsWith("java.lang");
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }
}
