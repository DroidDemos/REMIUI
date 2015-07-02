package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import com.xiaomi.e.a.a;
import com.xiaomi.e.a.c;
import com.xiaomi.e.a.e;
import com.xiaomi.e.a.h;
import com.xiaomi.e.a.i;
import com.xiaomi.e.a.k;
import com.xiaomi.e.a.n;
import com.xiaomi.e.a.p;
import com.xiaomi.e.a.r;
import com.xiaomi.e.a.t;
import com.xiaomi.e.a.u;
import com.xiaomi.f.a.c.b;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;

public class g {
    private static Object d;
    private static g vf;
    private static Queue vg;
    private Context b;

    static {
        vf = null;
        d = new Object();
    }

    private g(Context context) {
        this.b = context.getApplicationContext();
        if (this.b == null) {
            this.b = context;
        }
    }

    public static g S(Context context) {
        if (vf == null) {
            vf = new g(context);
        }
        return vf;
    }

    private a a(h hVar, boolean z) {
        a aVar = null;
        try {
            TBase a = j.a(this.b, hVar);
            if (a == null) {
                b.c("receiving an un-recognized message. " + hVar.a);
                return null;
            }
            b.b("receive a message." + a);
            a gn = hVar.gn();
            b.a("processing a message, action=" + gn);
            List list;
            switch (e.a[gn.ordinal()]) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    k kVar = (k) a;
                    if (kVar.f == 0) {
                        k.U(this.b).c(kVar.h, kVar.i);
                    }
                    if (TextUtils.isEmpty(kVar.h)) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(kVar.h);
                    }
                    return a.a("register", list, kVar.f, kVar.g, null);
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    if (((r) a).f == 0) {
                        k.U(this.b).f();
                        q.ax(this.b);
                    }
                    PushMessageHandler.a();
                    return null;
                case WindowData.d /*3*/:
                    if (k.U(this.b).j()) {
                        b.a("receive a message in pause state. drop it");
                        return null;
                    }
                    n nVar = (n) a;
                    com.xiaomi.e.a.b gy = nVar.gy();
                    if (gy == null) {
                        b.c("receive an empty message without push content, drop it");
                        return null;
                    }
                    if (z) {
                        TBase iVar = new i();
                        iVar.bH(nVar.d);
                        iVar.bI("bar:click");
                        iVar.bG(gy.b());
                        iVar.B(false);
                        h.T(this.b).a(iVar, a.Notification, false);
                    }
                    if (t(this.b, gy.b())) {
                        b.a("drop a duplicate message, msgid=" + gy.b());
                    } else {
                        b.a("receive a message, msgid=" + gy.b());
                        aVar = a.a(nVar, hVar.gp(), z);
                    }
                    if (hVar.gp() != null || z) {
                        return aVar;
                    }
                    a(nVar);
                    return aVar;
                case Base64.CRLF /*4*/:
                    p pVar = (p) a;
                    if (TextUtils.isEmpty(pVar.h())) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(pVar.h());
                    }
                    return a.a(q.LA, list, pVar.f, pVar.g, pVar.k());
                case WindowData.f /*5*/:
                    t tVar = (t) a;
                    if (TextUtils.isEmpty(tVar.h())) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(tVar.h());
                    }
                    return a.a(q.LB, list, tVar.f, tVar.g, tVar.k());
                case WindowData.g /*6*/:
                    com.xiaomi.e.a.g gVar = (com.xiaomi.e.a.g) a;
                    Object e = gVar.e();
                    list = gVar.gk();
                    if (TextUtils.equals(e, "accept-time") && list != null && list.size() > 1) {
                        if ("00:00".equals(list.get(0)) && "00:00".equals(list.get(1))) {
                            k.U(this.b).a(true);
                        } else {
                            k.U(this.b).a(false);
                        }
                    }
                    return a.a(e, list, gVar.g, gVar.h, gVar.m());
                case WindowData.h /*7*/:
                    if (!"registration id expired".equalsIgnoreCase(((i) a).e)) {
                        return null;
                    }
                    q.ay(this.b);
                    return null;
                default:
                    return null;
            }
        } catch (Throwable e2) {
            b.a(e2);
            b.c("receive a message which action string is not valid. is the reg expired?");
            return null;
        }
    }

    private void a(h hVar) {
        c gp = hVar.gp();
        TBase eVar = new e();
        eVar.bK(hVar.h());
        eVar.bJ(gp.a());
        eVar.m(gp.c());
        if (!TextUtils.isEmpty(gp.e())) {
            eVar.bL(gp.e());
        }
        h.T(this.b).a(eVar, a.AckMessage, false);
    }

    private void a(n nVar) {
        TBase eVar = new e();
        eVar.bK(nVar.e());
        eVar.bJ(nVar.c());
        eVar.m(nVar.gy().gq());
        if (!TextUtils.isEmpty(nVar.h())) {
            eVar.bL(nVar.h());
        }
        if (!TextUtils.isEmpty(nVar.j())) {
            eVar.bM(nVar.j());
        }
        h.T(this.b).a(eVar, a.AckMessage);
    }

    private static boolean t(Context context, String str) {
        synchronized (d) {
            SharedPreferences eZ = k.U(context).eZ();
            if (vg == null) {
                String[] split = eZ.getString("pref_msg_ids", ConfigConstant.WIRELESS_FILENAME).split(",");
                vg = new LinkedList();
                for (Object add : split) {
                    vg.add(add);
                }
            }
            if (vg.contains(str)) {
                return true;
            }
            vg.add(str);
            if (vg.size() > 10) {
                vg.poll();
            }
            String a = com.xiaomi.f.a.a.c.a(vg, ",");
            Editor edit = eZ.edit();
            edit.putString("pref_msg_ids", a);
            edit.commit();
            return false;
        }
    }

    public a f(Intent intent) {
        a aVar = null;
        String action = intent.getAction();
        b.a("receive an intent from server, action=" + action);
        if ("com.xiaomi.mipush.RECEIVE_MESSAGE".equals(action)) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            boolean booleanExtra = intent.getBooleanExtra("mipush_notified", false);
            if (byteArrayExtra == null) {
                b.c("receiving an empty message, drop");
            } else {
                h hVar = new h();
                try {
                    u.a(hVar, byteArrayExtra);
                    k U = k.U(this.b);
                    if (!(hVar.gn() != a.SendMessage || hVar.gp() == null || U.j() || booleanExtra)) {
                        a(hVar);
                    }
                    if (!U.g() && hVar.a != a.Registration) {
                        b.c("receive message without registration. need unregister or re-register!");
                    } else if (!U.g() || !U.k()) {
                        aVar = a(hVar, booleanExtra);
                    } else if (hVar.a == a.UnRegistration) {
                        U.f();
                        q.ax(this.b);
                        PushMessageHandler.a();
                    } else {
                        q.az(this.b);
                    }
                } catch (Throwable e) {
                    b.a(e);
                } catch (Throwable e2) {
                    b.a(e2);
                }
            }
        } else if ("com.xiaomi.mipush.ERROR".equals(action)) {
            aVar = new MiPushCommandMessage();
            Object hVar2 = new h();
            try {
                byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
                if (byteArrayExtra2 != null) {
                    u.a(hVar2, byteArrayExtra2);
                }
            } catch (TException e3) {
            }
            aVar.setCommand(String.valueOf(hVar2.gn()));
            aVar.f((long) intent.getIntExtra("mipush_error_code", 0));
            aVar.setReason(intent.getStringExtra("mipush_error_msg"));
            b.c("receive a error message. code = " + intent.getIntExtra("mipush_error_code", 0) + ", msg= " + intent.getStringExtra("mipush_error_msg"));
        }
        return aVar;
    }
}
