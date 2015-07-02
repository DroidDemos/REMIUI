package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.xiaomi.e.a.f;
import com.xiaomi.e.a.j;
import com.xiaomi.e.a.o;
import com.xiaomi.e.a.s;
import com.xiaomi.f.a.a.c;
import com.xiaomi.f.a.b.a;
import com.xiaomi.f.a.c.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.thrift.TBase;

public abstract class q {
    public static String LA;
    public static String LB;
    private static Context LC;
    private static long LD;

    static {
        LA = "subscribe-topic";
        LB = "unsubscibe-topic";
        if (a.b || a.e || a.c || a.g) {
            b.a(0);
        }
        LD = System.currentTimeMillis();
    }

    static void L(Context context, String str) {
        synchronized (q.class) {
            try {
            } finally {
                Class cls = q.class;
            }
        }
        Object putLong = context.getSharedPreferences("mipush_extra", 0).edit().putLong("alias_" + str, System.currentTimeMillis());
        putLong.commit();
    }

    static void M(Context context, String str) {
        synchronized (q.class) {
            try {
            } finally {
                Class cls = q.class;
            }
        }
        Object putLong = context.getSharedPreferences("mipush_extra", 0).edit().putLong("topic_" + str, System.currentTimeMillis());
        putLong.commit();
    }

    static boolean N(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong(new StringBuilder().append("alias_").append(str).toString(), -1) > System.currentTimeMillis() - ConfigConstant.MAIN_SWITCH_INTERVAL_UINT;
    }

    static void O(Context context, String str) {
        synchronized (q.class) {
            try {
            } finally {
                Class cls = q.class;
            }
        }
        Object remove = context.getSharedPreferences("mipush_extra", 0).edit().remove("alias_" + str);
        remove.commit();
    }

    static void P(Context context, String str) {
        synchronized (q.class) {
            try {
            } finally {
                Class cls = q.class;
            }
        }
        Object remove = context.getSharedPreferences("mipush_extra", 0).edit().remove("topic_" + str);
        remove.commit();
    }

    static boolean Q(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong(new StringBuilder().append("topic_").append(str).toString(), -1) > System.currentTimeMillis() - ConfigConstant.MAIN_SWITCH_INTERVAL_UINT;
    }

    @Deprecated
    public static void a(Context context, String str, String str2, c cVar) {
        a(context, "context");
        a(str, "appID");
        a(str2, "appToken");
        try {
            LC = context.getApplicationContext();
            if (LC == null) {
                LC = context;
            }
            if (cVar != null) {
                PushMessageHandler.a(cVar);
            }
            if (!k.U(LC).q(str, str2) || k.U(LC).k()) {
                k.U(LC).f();
                ax(LC);
                k.U(LC).b(str, str2);
                j jVar = new j();
                jVar.br(iy());
                jVar.bs(str);
                jVar.bv(str2);
                jVar.bu(context.getPackageName());
                jVar.bt(k.u(context, context.getPackageName()));
                h.T(LC).a(jVar);
                return;
            }
            if (1 == a.p(context)) {
                a(cVar, "callback");
                cVar.a(0, null, k.U(context).d());
            } else {
                List arrayList = new ArrayList();
                arrayList.add(k.U(context).d());
                a.a(LC, a.a("register", arrayList, 0, null, null));
            }
            h.T(context).a();
        } catch (Throwable th) {
            b.a(th);
        }
    }

    protected static void a(Context context, String str, ArrayList arrayList, String str2) {
        if (k.U(context).a()) {
            TBase fVar = new f();
            fVar.bx(iy());
            fVar.by(k.U(context).b());
            fVar.bz(str);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                fVar.d((String) it.next());
            }
            fVar.bB(str2);
            fVar.bA(context.getPackageName());
            h.T(context).a(fVar, com.xiaomi.e.a.a.Command);
        }
    }

    private static void a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException("param " + str + " is not nullable");
        }
    }

    protected static void ax(Context context) {
        context.getSharedPreferences("mipush_extra", 0).edit().clear().commit();
    }

    static void ay(Context context) {
        if (k.U(context).g()) {
            String b = k.U(context).b();
            String c = k.U(context).c();
            k.U(context).f();
            k.U(context).b(b, c);
            j jVar = new j();
            jVar.br(iy());
            jVar.bs(b);
            jVar.bv(c);
            jVar.bu(context.getPackageName());
            jVar.bt(k.u(context, context.getPackageName()));
            h.T(context).a(jVar);
        }
    }

    public static void az(Context context) {
        if (k.U(context).a()) {
            com.xiaomi.e.a.q qVar = new com.xiaomi.e.a.q();
            qVar.bX(iy());
            qVar.bY(k.U(context).b());
            qVar.bZ(k.U(context).d());
            qVar.cb(k.U(context).c());
            qVar.ca(context.getPackageName());
            h.T(context).a(qVar);
            k.U(context).fa();
            ax(context);
        }
    }

    protected static void c(Context context, String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        if (!"set-alias".equalsIgnoreCase(str) || !N(context, str2)) {
            a(context, str, arrayList, str3);
        } else if (1 == a.p(context)) {
            PushMessageHandler.a(context, str3, str, 0, null, arrayList);
        } else {
            a.a(context, a.a("set-alias", arrayList, 0, null, null));
        }
    }

    public static void h(Context context, String str, String str2) {
        a(context, str, str2, null);
    }

    public static void i(Context context, String str, String str2) {
        c(context, "set-alias", str, str2);
    }

    protected static String iy() {
        long j;
        synchronized (q.class) {
            try {
            } finally {
                j = q.class;
            }
        }
        String str = c.a(4) + LD;
        j = 1 + LD;
        LD = j;
        return str;
    }

    public static void j(Context context, String str, String str2) {
        if (!k.U(context).a()) {
            return;
        }
        if (!Q(context, str)) {
            TBase oVar = new o();
            oVar.bS(iy());
            oVar.bT(k.U(context).b());
            oVar.bU(str);
            oVar.bV(context.getPackageName());
            oVar.bW(str2);
            h.T(context).a(oVar, com.xiaomi.e.a.a.Subscription);
        } else if (1 == a.p(context)) {
            PushMessageHandler.a(context, str2, 0, null, str);
        } else {
            List arrayList = new ArrayList();
            arrayList.add(str);
            a.a(context, a.a(LA, arrayList, 0, null, null));
        }
    }

    public static void k(Context context, String str, String str2) {
        if (k.U(context).a()) {
            TBase sVar = new s();
            sVar.bN(iy());
            sVar.bO(k.U(context).b());
            sVar.bP(str);
            sVar.bQ(context.getPackageName());
            sVar.bR(str2);
            h.T(context).a(sVar, com.xiaomi.e.a.a.UnSubscription);
        }
    }
}
