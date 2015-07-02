package com.miui.yellowpage.utils;

import android.net.Uri;
import android.provider.BaseColumns;
import com.alipay.sdk.cons.MiniDefine;

/* compiled from: Constants */
public final class v implements BaseColumns {
    public static final Uri CONTENT_URI;
    public static final String[] PROJECTION;

    static {
        CONTENT_URI = Uri.parse("content://miui.yellowpage/region");
        PROJECTION = new String[]{"_id", MiniDefine.l, MiniDefine.m, "parent", "postal_code"};
    }
}
