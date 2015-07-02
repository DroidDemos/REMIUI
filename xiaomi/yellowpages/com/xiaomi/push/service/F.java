package com.xiaomi.push.service;

import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

public abstract class F {
    public static String a;
    public static String b;
    public static String c;
    public static String d;
    public static String e;
    public static String f;
    public static String g;
    public static String h;
    public static String i;
    public static String j;
    public static String k;
    public static String l;
    public static String m;
    public static String n;
    public static String o;
    public static String p;
    public static String q;
    public static String r;
    public static String s;
    public static String t;
    public static String u;
    public static String v;

    static {
        a = "com.xiaomi.push.OPEN_CHANNEL";
        b = "com.xiaomi.push.SEND_MESSAGE";
        c = "com.xiaomi.push.SEND_IQ";
        d = "com.xiaomi.push.BATCH_SEND_MESSAGE";
        e = "com.xiaomi.push.SEND_PRES";
        f = "com.xiaomi.push.CLOSE_CHANNEL";
        g = "com.xiaomi.push.FORCE_RECONN";
        h = "com.xiaomi.push.RESET_CONN";
        i = "com.xiaomi.push.UPDATE_CHANNEL_INFO";
        j = "com.xiaomi.push.CHANGE_HOST";
        k = "com.xiaomi.push.PING_TIMER";
        l = "ext_user_id";
        m = "ext_chid";
        n = "ext_sid";
        o = "ext_token";
        p = "ext_auth_method";
        q = "ext_security";
        r = "ext_kick";
        s = "ext_client_attr";
        t = "ext_cloud_attr";
        u = "ext_pkg_name";
        v = "ext_session";
    }

    public static String a(int i) {
        switch (i) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                return "ERROR_OK";
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return "ERROR_SERVICE_NOT_INSTALLED";
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                return "ERROR_NETWORK_NOT_AVAILABLE";
            case WindowData.d /*3*/:
                return "ERROR_NETWORK_FAILED";
            case Base64.CRLF /*4*/:
                return "ERROR_ACCESS_DENIED";
            case WindowData.f /*5*/:
                return "ERROR_AUTH_FAILED";
            case WindowData.g /*6*/:
                return "ERROR_MULTI_LOGIN";
            case WindowData.h /*7*/:
                return "ERROR_SERVER_ERROR";
            case Base64.URL_SAFE /*8*/:
                return "ERROR_RECEIVE_TIMEOUT";
            case WindowData.j /*9*/:
                return "ERROR_READ_ERROR";
            case WindowData.k /*10*/:
                return "ERROR_SEND_ERROR";
            case 11:
                return "ERROR_RESET";
            case 12:
                return "ERROR_NO_CLIENT";
            case 13:
                return "ERROR_SERVER_STREAM";
            case 14:
                return "ERROR_THREAD_BLOCK";
            case 15:
                return "ERROR_SERVICE_DESTROY";
            case Base64.NO_CLOSE /*16*/:
                return "ERROR_SESSION_CHANGED";
            case 17:
                return "ERROR_READ_TIMEOUT";
            case 18:
                return "ERROR_CONNECTIING_TIMEOUT";
            case Encoder.LINE_GROUPS /*19*/:
                return "ERROR_USER_BLOCKED";
            case 20:
                return "ERROR_REDIRECT";
            case 21:
                return "ERROR_BIND_TIMEOUT";
            case 22:
                return "ERROR_PING_TIMEOUT";
            default:
                return String.valueOf(i);
        }
    }
}
