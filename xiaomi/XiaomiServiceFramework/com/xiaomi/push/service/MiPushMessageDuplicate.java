package com.xiaomi.push.service;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MiPushMessageDuplicate {
    private static final int MAX_MSG_CACHE_COUNT = 10;
    private static Object lock;
    private static Map<String, Queue<String>> mCachedMsgIds;

    static {
        lock = new Object();
        mCachedMsgIds = new HashMap();
    }

    public static boolean isDuplicateMessage(XMPushService pushService, String packageName, String key) {
        boolean z;
        synchronized (lock) {
            SharedPreferences sp = pushService.getSharedPreferences("push_message_ids", 0);
            Collection packageCachedMsgIds = (Queue) mCachedMsgIds.get(packageName);
            if (packageCachedMsgIds == null) {
                String[] ids = sp.getString(packageName, "").split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                packageCachedMsgIds = new LinkedList();
                for (String id : ids) {
                    packageCachedMsgIds.add(id);
                }
                mCachedMsgIds.put(packageName, packageCachedMsgIds);
            }
            if (packageCachedMsgIds.contains(key)) {
                z = true;
            } else {
                packageCachedMsgIds.add(key);
                if (packageCachedMsgIds.size() > MAX_MSG_CACHE_COUNT) {
                    packageCachedMsgIds.poll();
                }
                String newMsgIds = XMStringUtils.join(packageCachedMsgIds, MiPushClient.ACCEPT_TIME_SEPARATOR);
                Editor ed = sp.edit();
                ed.putString(packageName, newMsgIds);
                ed.commit();
                z = false;
            }
        }
        return z;
    }
}
