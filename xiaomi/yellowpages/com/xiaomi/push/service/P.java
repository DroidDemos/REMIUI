package com.xiaomi.push.service;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.xiaomi.e.a.c;
import com.xiaomi.e.a.h;
import java.util.List;

public class P {
    public static long a;

    static {
        a = 0;
    }

    private static Notification a(Context context, h hVar, byte[] bArr) {
        c gp = hVar.gp();
        Builder builder = new Builder(context);
        builder.setContentText(gp.i());
        builder.setContentTitle(gp.g());
        builder.setWhen(System.currentTimeMillis());
        builder.setContentIntent(a(context, hVar, gp, bArr));
        int c = c(context, hVar.j(), "mipush_notification");
        int c2 = c(context, hVar.j(), "mipush_small_notification");
        if (c <= 0 || c2 <= 0) {
            builder.setSmallIcon(v(context, hVar.j()));
        } else {
            builder.setLargeIcon(f(context, c));
            builder.setSmallIcon(c2);
        }
        builder.setAutoCancel(true);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - a > 10000) {
            a = currentTimeMillis;
            builder.setDefaults(gp.f);
        }
        return builder.getNotification();
    }

    private static PendingIntent a(Context context, h hVar, c cVar, byte[] bArr) {
        if (cVar == null || TextUtils.isEmpty(cVar.g)) {
            Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
            intent.setComponent(new ComponentName(hVar.f, "com.xiaomi.mipush.sdk.PushMessageHandler"));
            intent.putExtra("mipush_payload", bArr);
            intent.putExtra("mipush_notified", true);
            intent.addCategory(String.valueOf(cVar.gt()));
            return PendingIntent.getService(context, 0, intent, 134217728);
        }
        intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(cVar.g));
        intent.addFlags(268435456);
        return PendingIntent.getActivity(context, 0, intent, 134217728);
    }

    static void a(XMPushService xMPushService, h hVar, byte[] bArr) {
        Notification a;
        NotificationManager notificationManager = (NotificationManager) xMPushService.getSystemService("notification");
        if (VERSION.SDK_INT >= 11) {
            a = a((Context) xMPushService, hVar, bArr);
        } else {
            a = new Notification(v(xMPushService, hVar.j()), null, System.currentTimeMillis());
            c gp = hVar.gp();
            a.setLatestEventInfo(xMPushService, gp.g(), gp.i(), a(xMPushService, hVar, gp, bArr));
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - a > 10000) {
                a = currentTimeMillis;
                a.defaults = gp.f;
            }
            a.flags |= 16;
        }
        notificationManager.notify(hVar.gp().gt() + ((hVar.j().hashCode() / 10) * 10), a);
    }

    private static int c(Context context, String str, String str2) {
        return str.equals(context.getPackageName()) ? context.getResources().getIdentifier(str2, "drawable", str) : 0;
    }

    public static Bitmap c(Drawable drawable) {
        int i = 1;
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicHeight > 0) {
            i = intrinsicHeight;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    private static Bitmap f(Context context, int i) {
        return c(context.getResources().getDrawable(i));
    }

    public static boolean t(Context context, String str) {
        List runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        return runningTasks.isEmpty() ? false : ((RunningTaskInfo) runningTasks.get(0)).topActivity.getPackageName().equals(str);
    }

    private static int v(Context context, String str) {
        int c = c(context, str, "mipush_notification");
        int c2 = c(context, str, "mipush_small_notification");
        if (c > 0) {
            c2 = c;
        } else if (c2 <= 0) {
            c2 = context.getApplicationInfo().icon;
        }
        return (c2 != 0 || VERSION.SDK_INT < 9) ? c2 : context.getApplicationInfo().logo;
    }
}
