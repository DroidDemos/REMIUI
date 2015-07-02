package com.miui.yellowpage.utils;

import android.net.Uri;
import android.provider.Telephony.Sms;
import com.alipay.sdk.cons.MiniDefine;

/* compiled from: Contact */
public interface m {
    public static final String[] COLUMNS;
    public static final Uri URI;

    static {
        URI = Sms.CONTENT_URI;
        COLUMNS = new String[]{"_id", MiniDefine.m, "date", "address"};
    }
}
