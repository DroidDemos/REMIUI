package com.xiaomi.push.service.timers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.smack.SmackConfiguration;

public class AlarmManagerTimer {
    private Context mContext;
    private PendingIntent mPi;

    public AlarmManagerTimer(Context context) {
        this.mPi = null;
        this.mContext = null;
        this.mContext = context;
    }

    public synchronized void register(Intent intent, long triggerTime) {
        if (this.mPi == null) {
            AlarmManager mgr = (AlarmManager) this.mContext.getSystemService(PushServiceConstants.EXTENSION_ELEMENT_EXT_SUB_ELE_ALARM);
            this.mPi = PendingIntent.getBroadcast(this.mContext, 0, intent, 0);
            if (VERSION.SDK_INT >= 19) {
                setExact(mgr, triggerTime, this.mPi);
            } else {
                mgr.set(2, triggerTime, this.mPi);
            }
            MyLog.v("register timer");
        }
    }

    private void setExact(AlarmManager mgr, long triggerAtMillis, PendingIntent operation) {
        try {
            AlarmManager.class.getMethod("setExact", new Class[]{Integer.TYPE, Long.TYPE, PendingIntent.class}).invoke(mgr, new Object[]{Integer.valueOf(2), Long.valueOf(triggerAtMillis), operation});
        } catch (Throwable e) {
            MyLog.e(e);
        }
    }

    public synchronized void registerPing(boolean firstPing) {
        Intent intent = new Intent(PushConstants.ACTION_PING_TIMER);
        intent.setPackage(this.mContext.getPackageName());
        register(intent, firstPing ? SystemClock.elapsedRealtime() : getAlarmTriggerTime((long) SmackConfiguration.getPingInteval()));
    }

    private long getAlarmTriggerTime(long interval) {
        return ((SystemClock.elapsedRealtime() / interval) + 1) * interval;
    }

    public synchronized void stop() {
        if (this.mPi != null) {
            ((AlarmManager) this.mContext.getSystemService(PushServiceConstants.EXTENSION_ELEMENT_EXT_SUB_ELE_ALARM)).cancel(this.mPi);
            this.mPi = null;
            MyLog.v("unregister timer");
        }
    }

    public synchronized boolean isAlive() {
        return this.mPi != null;
    }
}
