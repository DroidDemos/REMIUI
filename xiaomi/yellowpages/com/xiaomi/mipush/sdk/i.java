package com.xiaomi.mipush.sdk;

import android.content.Context;

public class i {
    private static l vk;

    static {
        vk = null;
    }

    public static void a(Context context, f fVar) {
        synchronized (i.class) {
            try {
                if (vk == null) {
                    vk = new l(context);
                    vk.a(fVar);
                    vk.start();
                } else {
                    vk.a(fVar);
                }
            } catch (Throwable th) {
                Class cls = i.class;
            }
        }
    }
}
