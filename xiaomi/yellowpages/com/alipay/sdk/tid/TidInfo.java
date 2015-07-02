package com.alipay.sdk.tid;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.sys.GlobalContext;
import com.alipay.sdk.util.DeviceInfo;

public class TidInfo {
    private static TidInfo a;
    private String b;
    private String c;

    private TidInfo() {
    }

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public void a(Context context) {
        TidDbHelper tidDbHelper = new TidDbHelper(context);
        try {
            tidDbHelper.a(DeviceInfo.a(context).b(), DeviceInfo.a(context).c(), this.b, this.c);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tidDbHelper.close();
        }
    }

    public boolean c() {
        return TextUtils.isEmpty(this.b);
    }

    public static TidInfo d() {
        if (a == null) {
            a = new TidInfo();
            Context b = GlobalContext.a().b();
            TidDbHelper tidDbHelper = new TidDbHelper(b);
            String b2 = DeviceInfo.a(b).b();
            String c = DeviceInfo.a(b).c();
            a.b = tidDbHelper.b(b2, c);
            a.c = tidDbHelper.d(b2, c);
            if (TextUtils.isEmpty(a.c)) {
                a.c = f();
            }
            tidDbHelper.a(b2, c, a.b, a.c);
        }
        return a;
    }

    public static void e() {
        Context b = GlobalContext.a().b();
        String b2 = DeviceInfo.a(b).b();
        String c = DeviceInfo.a(b).c();
        TidDbHelper tidDbHelper = new TidDbHelper(b);
        tidDbHelper.a(b2, c);
        tidDbHelper.close();
    }

    public static String f() {
        String toHexString = Long.toHexString(System.currentTimeMillis());
        if (toHexString.length() > 10) {
            return toHexString.substring(toHexString.length() - 10);
        }
        return toHexString;
    }
}
