package com.mediatek.encapsulation;

import com.mediatek.xlog.Xlog;

public class MmsLog {
    public static void v(String tag, String msg) {
        Xlog.v(tag, msg);
    }

    public static void e(String tag, String msg) {
        Xlog.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        Xlog.e(tag, msg, tr);
    }

    public static void i(String tag, String msg) {
        Xlog.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        Xlog.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable tr) {
        Xlog.d(tag, msg, tr);
    }

    public static void w(String tag, String msg) {
        Xlog.w(tag, msg);
    }
}
