package com.xiaomi.push.service.a;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.xiaomi.d.z;
import com.xiaomi.f.a.c.b;
import com.xiaomi.push.service.F;

public class a {
    private PendingIntent JL;
    private Context b;

    public a(Context context) {
        this.JL = null;
        this.b = null;
        this.b = context;
    }

    private long q(long j) {
        return (1 + (SystemClock.elapsedRealtime() / j)) * j;
    }

    public void a() {
        synchronized (this) {
            if (this.JL != null) {
                ((AlarmManager) this.b.getSystemService("alarm")).cancel(this.JL);
                this.JL = null;
                b.b("unregister timer");
            }
        }
    }

    public void a(Intent intent, long j) {
        synchronized (this) {
            if (this.JL == null) {
                AlarmManager alarmManager = (AlarmManager) this.b.getSystemService("alarm");
                this.JL = PendingIntent.getBroadcast(this.b, 0, intent, 0);
                if (VERSION.SDK_INT < 19) {
                    alarmManager.set(2, j, this.JL);
                }
                b.b("register timer");
            }
        }
    }

    public void a(boolean z) {
        synchronized (this) {
            Intent intent = new Intent(F.k);
            intent.setPackage(this.b.getPackageName());
            a(intent, z ? SystemClock.elapsedRealtime() : q((long) z.ig()));
        }
    }

    public boolean b() {
        boolean z;
        synchronized (this) {
            z = this.JL != null;
        }
        return z;
    }
}
