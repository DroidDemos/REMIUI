package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.xiaomi.f.a.c.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class L {
    private static final Map fe;
    private static final ArrayList wf;

    static {
        fe = new HashMap();
        wf = new ArrayList();
    }

    public static void a(Context context, int i, String str) {
        synchronized (fe) {
            for (String str2 : fe.keySet()) {
                a(context, str2, (byte[]) fe.get(str2), i, str);
            }
            fe.clear();
        }
    }

    public static void a(Context context, String str, byte[] bArr, int i, String str2) {
        Intent intent = new Intent("com.xiaomi.mipush.ERROR");
        intent.setPackage(str);
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mipush_error_code", i);
        intent.putExtra("mipush_error_msg", str2);
        context.sendBroadcast(intent, w.a(str));
    }

    public static void a(String str, byte[] bArr) {
        synchronized (fe) {
            fe.put(str, bArr);
        }
    }

    public static void b(XMPushService xMPushService) {
        try {
            synchronized (fe) {
                for (String str : fe.keySet()) {
                    xMPushService.a(str, (byte[]) fe.get(str));
                }
                fe.clear();
            }
        } catch (Exception e) {
            b.a((Throwable) e);
            xMPushService.b(10, e);
        }
    }

    public static void b(String str, byte[] bArr) {
        synchronized (wf) {
            wf.add(new Pair(str, bArr));
        }
    }

    public static void c(XMPushService xMPushService) {
        try {
            synchronized (wf) {
                Iterator it = wf.iterator();
                while (it.hasNext()) {
                    Pair pair = (Pair) it.next();
                    xMPushService.a((String) pair.first, (byte[]) pair.second);
                }
                wf.clear();
            }
        } catch (Exception e) {
            b.a((Throwable) e);
            xMPushService.b(10, e);
        }
    }
}
