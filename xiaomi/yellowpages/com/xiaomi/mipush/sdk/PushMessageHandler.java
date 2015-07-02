package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.f.a.c.b;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PushMessageHandler extends IntentService {
    private static List rP;

    interface a extends Serializable {
    }

    static {
        rP = new ArrayList();
    }

    public PushMessageHandler() {
        super("mipush message handler");
    }

    protected static void a() {
        synchronized (rP) {
            rP.clear();
        }
    }

    public static void a(Context context, MiPushMessage miPushMessage) {
        synchronized (rP) {
            for (c cVar : rP) {
                if (TextUtils.equals(miPushMessage.getCategory(), cVar.getCategory())) {
                    cVar.a(miPushMessage.getContent(), miPushMessage.getAlias(), miPushMessage.getTopic(), miPushMessage.isNotified());
                    cVar.a(miPushMessage);
                }
            }
        }
    }

    public static void a(Context context, a aVar) {
        String str = null;
        if (aVar instanceof MiPushMessage) {
            a(context, (MiPushMessage) aVar);
        } else if (aVar instanceof MiPushCommandMessage) {
            MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) aVar;
            String command = miPushCommandMessage.getCommand();
            List aB;
            if ("register".equals(command)) {
                aB = miPushCommandMessage.aB();
                if (!(aB == null || aB.isEmpty())) {
                    str = (String) aB.get(0);
                }
                d(miPushCommandMessage.aC(), miPushCommandMessage.getReason(), str);
            } else if ("set-alias".equals(command) || "unset-alias".equals(command) || "accept-time".equals(command)) {
                a(context, miPushCommandMessage.getCategory(), command, miPushCommandMessage.aC(), miPushCommandMessage.getReason(), miPushCommandMessage.aB());
            } else if (q.LA.equals(command)) {
                aB = miPushCommandMessage.aB();
                r5 = (aB == null || aB.isEmpty()) ? null : (String) aB.get(0);
                a(context, miPushCommandMessage.getCategory(), miPushCommandMessage.aC(), miPushCommandMessage.getReason(), r5);
            } else if (q.LB.equals(command)) {
                aB = miPushCommandMessage.aB();
                r5 = (aB == null || aB.isEmpty()) ? null : (String) aB.get(0);
                b(context, miPushCommandMessage.getCategory(), miPushCommandMessage.aC(), miPushCommandMessage.getReason(), r5);
            }
        }
    }

    protected static void a(Context context, String str, long j, String str2, String str3) {
        synchronized (rP) {
            if (j == 0) {
                if (!q.Q(context, str3)) {
                    q.M(context, str3);
                }
            }
            for (c cVar : rP) {
                if (TextUtils.equals(str, cVar.getCategory())) {
                    cVar.b(j, str2, str3);
                }
            }
        }
    }

    protected static void a(Context context, String str, String str2, long j, String str3, List list) {
        synchronized (rP) {
            if (j == 0) {
                if ("set-alias".equalsIgnoreCase(str2) && !list.isEmpty() && !q.N(context, (String) list.get(0))) {
                    q.L(context, (String) list.get(0));
                } else if ("unset-alias".equalsIgnoreCase(str2) && !list.isEmpty()) {
                    q.O(context, (String) list.get(0));
                }
            }
            for (c cVar : rP) {
                if (TextUtils.equals(str, cVar.getCategory())) {
                    cVar.a(str2, j, str3, list);
                }
            }
        }
    }

    protected static void a(c cVar) {
        synchronized (rP) {
            if (!rP.contains(cVar)) {
                rP.add(cVar);
            }
        }
    }

    protected static void b(Context context, String str, long j, String str2, String str3) {
        synchronized (rP) {
            if (j == 0) {
                q.P(context, str3);
            }
            for (c cVar : rP) {
                if (TextUtils.equals(str, cVar.getCategory())) {
                    cVar.c(j, str2, str3);
                }
            }
        }
    }

    public static boolean b() {
        return rP.isEmpty();
    }

    public static void d(long j, String str, String str2) {
        synchronized (rP) {
            for (c a : rP) {
                a.a(j, str, str2);
            }
        }
    }

    protected void onHandleIntent(Intent intent) {
        if (1 != a.p(this)) {
            Intent intent2 = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
            intent2.setPackage(getPackageName());
            intent2.putExtras(intent);
            sendBroadcast(intent2);
        } else if (b()) {
            b.c("receive a message before application calling initialize");
        } else {
            a f = g.S(this).f(intent);
            if (f != null) {
                a((Context) this, f);
            }
        }
    }
}
