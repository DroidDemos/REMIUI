package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.d.c.f;
import com.xiaomi.d.c.g;
import com.xiaomi.d.c.h;
import com.xiaomi.d.c.i;
import com.xiaomi.f.a.c.b;
import java.util.Collection;

public class w {
    private M vv;

    public w() {
        this.vv = new M();
    }

    public static String a(String str) {
        return str + ".permission.MIPUSH_RECEIVE";
    }

    public void a(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.service_started");
        context.sendBroadcast(intent);
    }

    public void a(Context context, m mVar, int i) {
        if (!"5".equalsIgnoreCase(mVar.h)) {
            Intent intent = new Intent();
            intent.setAction("com.xiaomi.push.channel_closed");
            intent.setPackage(mVar.a);
            intent.putExtra(F.m, mVar.h);
            intent.putExtra("ext_reason", i);
            intent.putExtra(F.l, mVar.b);
            intent.putExtra(F.v, mVar.j);
            context.sendBroadcast(intent, a(mVar.a));
        }
    }

    public void a(Context context, m mVar, String str, String str2) {
        if ("5".equalsIgnoreCase(mVar.h)) {
            b.c("mipush kicked by server");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.kicked");
        intent.setPackage(mVar.a);
        intent.putExtra("ext_kick_type", str);
        intent.putExtra("ext_kick_reason", str2);
        intent.putExtra("ext_chid", mVar.h);
        intent.putExtra(F.l, mVar.b);
        intent.putExtra(F.v, mVar.j);
        context.sendBroadcast(intent, a(mVar.a));
    }

    public void a(Context context, m mVar, boolean z, int i, String str) {
        if ("5".equalsIgnoreCase(mVar.h)) {
            this.vv.a(context, mVar, z, i, str);
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.channel_opened");
        intent.setPackage(mVar.a);
        intent.putExtra("ext_succeeded", z);
        if (!z) {
            intent.putExtra("ext_reason", i);
        }
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("ext_reason_msg", str);
        }
        intent.putExtra("ext_chid", mVar.h);
        intent.putExtra(F.l, mVar.b);
        intent.putExtra(F.v, mVar.j);
        context.sendBroadcast(intent, a(mVar.a));
    }

    public void a(XMPushService xMPushService, String str, g gVar) {
        Collection bo = q.fp().bo(str);
        if (bo == null || bo.isEmpty()) {
            b.c("error while notify channel closed! channel " + str + " not registered");
            return;
        }
        m mVar = (m) bo.iterator().next();
        if ("5".equalsIgnoreCase(str)) {
            this.vv.a(xMPushService, gVar, mVar);
            return;
        }
        String str2;
        String str3 = mVar.a;
        if (gVar instanceof h) {
            str2 = "com.xiaomi.push.new_msg";
        } else if (gVar instanceof i) {
            str2 = "com.xiaomi.push.new_iq";
        } else if (gVar instanceof f) {
            str2 = "com.xiaomi.push.new_pres";
        } else {
            b.c("unknown packet type, drop it");
            return;
        }
        Intent intent = new Intent();
        intent.setAction(str2);
        intent.setPackage(str3);
        intent.putExtra("ext_chid", str);
        intent.putExtra("ext_packet", gVar.iK());
        intent.putExtra(F.v, mVar.j);
        xMPushService.sendBroadcast(intent, a(str3));
    }
}
