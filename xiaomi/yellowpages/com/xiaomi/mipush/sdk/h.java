package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import com.xiaomi.e.a.a;
import com.xiaomi.e.a.j;
import com.xiaomi.e.a.q;
import com.xiaomi.e.a.u;
import com.xiaomi.f.a.a.c;
import com.xiaomi.push.service.XMPushService;
import org.apache.thrift.TBase;

public class h {
    private static h vh;
    private boolean a;
    private String d;
    private Context vi;
    private Intent vj;

    private h(Context context) {
        this.a = false;
        this.vj = null;
        this.vi = context.getApplicationContext();
        this.d = c.a(6);
        this.a = d();
    }

    public static h T(Context context) {
        if (vh == null) {
            vh = new h(context);
        }
        return vh;
    }

    private boolean d() {
        try {
            PackageInfo packageInfo = this.vi.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
            return packageInfo != null && packageInfo.versionCode >= 105;
        } catch (Exception e) {
            return false;
        }
    }

    private Intent eY() {
        if (b()) {
            Intent intent = new Intent();
            intent.setPackage("com.xiaomi.xmsf");
            intent.setClassName("com.xiaomi.xmsf", "com.xiaomi.xmsf.push.service.XMPushService");
            intent.putExtra("mipush_app_package", this.vi.getPackageName());
            f();
            return intent;
        }
        g();
        intent = new Intent(this.vi, XMPushService.class);
        intent.putExtra("mipush_app_package", this.vi.getPackageName());
        return intent;
    }

    private void f() {
        this.vi.getPackageManager().setComponentEnabledSetting(new ComponentName(this.vi, XMPushService.class), 2, 1);
    }

    private void g() {
        this.vi.getPackageManager().setComponentEnabledSetting(new ComponentName(this.vi, XMPushService.class), 1, 1);
    }

    public void a() {
        this.vi.startService(eY());
    }

    public final void a(j jVar) {
        this.vj = null;
        Intent eY = eY();
        byte[] a = u.a(j.a(this.vi, jVar, a.Registration));
        eY.setAction("com.xiaomi.mipush.REGISTER_APP");
        eY.putExtra("mipush_app_id", k.U(this.vi).b());
        eY.putExtra("mipush_payload", a);
        eY.putExtra("mipush_session", this.d);
        if (com.xiaomi.f.a.d.a.Z(this.vi)) {
            this.vi.startService(eY);
        } else {
            this.vj = eY;
        }
    }

    public final void a(q qVar) {
        Intent eY = eY();
        byte[] a = u.a(j.a(this.vi, qVar, a.UnRegistration));
        eY.setAction("com.xiaomi.mipush.UNREGISTER_APP");
        eY.putExtra("mipush_app_id", k.U(this.vi).b());
        eY.putExtra("mipush_payload", a);
        this.vi.startService(eY);
    }

    public final void a(TBase tBase, a aVar) {
        Intent eY = eY();
        byte[] a = u.a(j.a(this.vi, tBase, aVar));
        eY.setAction("com.xiaomi.mipush.SEND_MESSAGE");
        eY.putExtra("mipush_payload", a);
        this.vi.startService(eY);
    }

    public final void a(TBase tBase, a aVar, boolean z) {
        Intent eY = eY();
        byte[] a = u.a(j.a(this.vi, tBase, aVar, z));
        eY.setAction("com.xiaomi.mipush.SEND_MESSAGE");
        eY.putExtra("mipush_payload", a);
        this.vi.startService(eY);
    }

    public boolean b() {
        return (!this.a || com.xiaomi.f.a.b.a.a() || com.xiaomi.f.a.b.a.b()) ? false : true;
    }
}
