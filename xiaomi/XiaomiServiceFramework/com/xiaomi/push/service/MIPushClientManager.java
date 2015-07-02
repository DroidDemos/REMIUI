package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MIPushClientManager {
    private static final ArrayList<Pair<String, byte[]>> pendingMessages;
    private static final Map<String, byte[]> pendingRegisterationRequests;

    static {
        pendingRegisterationRequests = new HashMap();
        pendingMessages = new ArrayList();
    }

    public static void registerApp(String packageName, byte[] payload) {
        synchronized (pendingRegisterationRequests) {
            pendingRegisterationRequests.put(packageName, payload);
        }
    }

    public static void processPendingRegistrationRequest(XMPushService pushService) {
        try {
            synchronized (pendingRegisterationRequests) {
                for (String packageName : pendingRegisterationRequests.keySet()) {
                    pushService.sendMIPushPacket(packageName, (byte[]) pendingRegisterationRequests.get(packageName));
                }
                pendingRegisterationRequests.clear();
            }
        } catch (Throwable e) {
            MyLog.e(e);
            pushService.disconnect(10, e);
        }
    }

    public static void notifyRegisterError(Context context, int errorCode, String msg) {
        synchronized (pendingRegisterationRequests) {
            for (String packageName : pendingRegisterationRequests.keySet()) {
                notifyError(context, packageName, (byte[]) pendingRegisterationRequests.get(packageName), errorCode, msg);
            }
            pendingRegisterationRequests.clear();
        }
    }

    public static void processPendingMessages(XMPushService pushService) {
        try {
            synchronized (pendingMessages) {
                Iterator i$ = pendingMessages.iterator();
                while (i$.hasNext()) {
                    Pair<String, byte[]> m = (Pair) i$.next();
                    pushService.sendMIPushPacket((String) m.first, (byte[]) m.second);
                }
                pendingMessages.clear();
            }
        } catch (Throwable e) {
            MyLog.e(e);
            pushService.disconnect(10, e);
        }
    }

    public static void addPendingMessages(String packageName, byte[] message) {
        synchronized (pendingMessages) {
            pendingMessages.add(new Pair(packageName, message));
        }
    }

    public static void notifyError(Context context, String packageName, byte[] payload, int errorCode, String msg) {
        Intent intent = new Intent(PushConstants.MIPUSH_ACTION_ERROR);
        intent.setPackage(packageName);
        intent.putExtra(PushConstants.MIPUSH_EXTRA_PAYLOAD, payload);
        intent.putExtra(PushConstants.MIPUSH_EXTRA_ERROR_CODE, errorCode);
        intent.putExtra(PushConstants.MIPUSH_EXTRA_ERROR_MSG, msg);
        context.sendBroadcast(intent, ClientEventDispatcher.getReceiverPermission(packageName));
    }
}
