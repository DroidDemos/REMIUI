package com.miui.yellowpage.utils;

import android.net.Uri;
import android.provider.CallLog.Calls;
import com.alipay.sdk.cons.MiniDefine;

/* compiled from: Contact */
public interface B {
    public static final String[] COLUMNS;
    public static final Uri URI;

    static {
        URI = Calls.CONTENT_URI_WITH_VOICEMAIL;
        COLUMNS = new String[]{"date", "duration", "number", MiniDefine.m, "_id", "voicemail_uri", "forwarded_call", "normalized_number", "simid"};
    }
}
