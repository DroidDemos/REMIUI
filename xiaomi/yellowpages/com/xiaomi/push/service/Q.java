package com.xiaomi.push.service;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.xiaomi.f.a.a.c;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Q {
    private static Object a;
    private static Map iJ;

    static {
        a = new Object();
        iJ = new HashMap();
    }

    public static boolean a(XMPushService xMPushService, String str, String str2) {
        synchronized (a) {
            SharedPreferences sharedPreferences = xMPushService.getSharedPreferences("push_message_ids", 0);
            Collection collection = (Queue) iJ.get(str);
            if (collection == null) {
                String[] split = sharedPreferences.getString(str, ConfigConstant.WIRELESS_FILENAME).split(",");
                collection = new LinkedList();
                for (Object add : split) {
                    collection.add(add);
                }
                iJ.put(str, collection);
            }
            if (collection.contains(str2)) {
                return true;
            }
            collection.add(str2);
            if (collection.size() > 10) {
                collection.poll();
            }
            String a = c.a(collection, ",");
            Editor edit = sharedPreferences.edit();
            edit.putString(str, a);
            edit.commit();
            return false;
        }
    }
}
