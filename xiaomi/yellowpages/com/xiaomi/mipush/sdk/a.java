package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.e.a.c;
import com.xiaomi.e.a.n;
import java.util.List;

public class a {
    private static int dH;

    static {
        dH = 0;
    }

    public static MiPushCommandMessage a(String str, List list, long j, String str2, String str3) {
        MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
        miPushCommandMessage.setCommand(str);
        miPushCommandMessage.c(list);
        miPushCommandMessage.f(j);
        miPushCommandMessage.setReason(str2);
        miPushCommandMessage.setCategory(str3);
        return miPushCommandMessage;
    }

    public static MiPushMessage a(n nVar, c cVar, boolean z) {
        MiPushMessage miPushMessage = new MiPushMessage();
        miPushMessage.setMessageId(nVar.c());
        if (!TextUtils.isEmpty(nVar.j())) {
            miPushMessage.setMessageType(MiPushMessage.MESSAGE_TYPE_ALIAS);
            miPushMessage.setAlias(nVar.j());
        } else if (TextUtils.isEmpty(nVar.h())) {
            miPushMessage.setMessageType(MiPushMessage.MESSAGE_TYPE_REG);
        } else {
            miPushMessage.setMessageType(MiPushMessage.MESSAGE_TYPE_TOPIC);
            miPushMessage.setTopic(nVar.h());
        }
        miPushMessage.setCategory(nVar.gz());
        if (nVar.gy() != null) {
            miPushMessage.setContent(nVar.gy().e());
        }
        if (cVar != null) {
            miPushMessage.setDescription(cVar.i());
            miPushMessage.setTitle(cVar.g());
            miPushMessage.setNotifyType(cVar.k());
            miPushMessage.setNotifyId(cVar.gt());
            miPushMessage.setPassThrough(cVar.gr());
        }
        miPushMessage.setNotified(z);
        return miPushMessage;
    }

    public static void a(Context context, MiPushCommandMessage miPushCommandMessage) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        intent.putExtra("message_type", 3);
        intent.putExtra("key_command", miPushCommandMessage);
        context.sendBroadcast(intent);
    }

    private static boolean a(Context context, Intent intent) {
        try {
            List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            return (queryBroadcastReceivers == null || queryBroadcastReceivers.isEmpty()) ? false : true;
        } catch (Exception e) {
            return true;
        }
    }

    private static void d(int i) {
        dH = i;
    }

    public static int p(Context context) {
        if (dH == 0) {
            if (q(context)) {
                d(1);
            } else {
                d(2);
            }
        }
        return dH;
    }

    public static boolean q(Context context) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setClassName(context.getPackageName(), "com.xiaomi.mipush.sdk.PushServiceReceiver");
        return a(context, intent);
    }
}
