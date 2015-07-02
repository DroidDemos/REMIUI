package com.weibo.sdk.android;

import android.os.Handler;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;

/* compiled from: Weibo */
public class a {
    private static String sP;
    private static a sQ;
    private static String sR;
    private static String sS;
    private static String sT;
    private static String sU;
    private static boolean sW;
    private e sV;
    private b sX;
    private Handler sY;

    public a() {
        this.sV = null;
        this.sY = new c(this);
    }

    static {
        sP = "https://open.weibo.cn/oauth2";
        sQ = null;
        sR = ConfigConstant.WIRELESS_FILENAME;
        sS = ConfigConstant.WIRELESS_FILENAME;
        sT = ConfigConstant.WIRELESS_FILENAME;
        sU = ConfigConstant.WIRELESS_FILENAME;
        sW = false;
    }

    public static boolean dX() {
        return sW;
    }

    public static void u(boolean z) {
        sW = z;
    }
}
