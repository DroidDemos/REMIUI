package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmsf.push.service.Constants;
import com.xiaomi.xmsf.push.service.log.LogProvider;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class MIPushNotificationHelper {
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FROM_NOTIFICATION = "mipush_notified";
    private static final int MAX_NOTIFY_ID = 4;
    public static final String MIUI_PACKAGE_NAME = "miui_package_name";
    private static final String NOTIFICATION_ICON = "mipush_notification";
    private static final String NOTIFICATION_SMALL_ICON = "mipush_small_notification";
    public static final String NOTIFICATION_SOUND_URI = "sound_uri";
    public static final long NOTIFY_INTERVAL = 10000;
    public static long lastNotify;

    static {
        lastNotify = 0;
    }

    public static boolean isApplicationForeground(Context context, String packageName) {
        try {
            List<RunningTaskInfo> tasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
            if (tasks == null || tasks.isEmpty()) {
                return false;
            }
            return ((RunningTaskInfo) tasks.get(0)).topActivity.getPackageName().equals(packageName);
        } catch (Throwable e) {
            MyLog.e(e);
            return false;
        }
    }

    public static void notifyPushMessage(Context context, XmPushActionContainer container, byte[] decryptedContent) {
        Notification notification;
        NotificationManager nm = (NotificationManager) context.getSystemService("notification");
        PushMetaInfo metaInfo = container.getMetaInfo();
        RemoteViews rv = getNotificationForCustomLayout(context, container, decryptedContent);
        if (VERSION.SDK_INT >= 11) {
            notification = getNotificationForLargeIcons(context, container, decryptedContent, rv);
        } else {
            Context context2 = context;
            notification = new Notification(getIdForSmallIcon(context2, getTargetPackage(container)), null, System.currentTimeMillis());
            String[] ret = determineTitleAndDespByDIP(context, metaInfo);
            notification.setLatestEventInfo(context, ret[0], ret[1], getClickedPendingIntent(context, container, metaInfo, decryptedContent));
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastNotify > NOTIFY_INTERVAL) {
                lastNotify = currentTime;
                notification.defaults = metaInfo.notifyType;
                Map<String, String> extra = metaInfo.getExtra();
                if (!(extra == null || (metaInfo.notifyType & 1) == 0)) {
                    String soundUri = (String) extra.get(NOTIFICATION_SOUND_URI);
                    if (!TextUtils.isEmpty(soundUri) && soundUri.startsWith(ANDROID_RESOURCE + getTargetPackage(container))) {
                        notification.defaults = metaInfo.notifyType ^ 1;
                        notification.sound = Uri.parse(soundUri);
                    }
                }
            }
            notification.flags |= 16;
            if (rv != null) {
                notification.contentView = rv;
            }
        }
        if (LogProvider.AUTHORITY.equals(context.getPackageName())) {
            setTargetPackage(notification, getTargetPackage(container));
        }
        nm.notify(((getTargetPackage(container).hashCode() / 10) * 10) + metaInfo.getNotifyId(), notification);
    }

    private static PendingIntent getClickedPendingIntent(Context context, XmPushActionContainer container, PushMetaInfo metaInfo, byte[] decryptedContent) {
        if (metaInfo == null || TextUtils.isEmpty(metaInfo.url)) {
            Intent intent = new Intent(PushConstants.MIPUSH_ACTION_NEW_MESSAGE);
            intent.setComponent(new ComponentName(container.packageName, "com.xiaomi.mipush.sdk.PushMessageHandler"));
            intent.putExtra(PushConstants.MIPUSH_EXTRA_PAYLOAD, decryptedContent);
            intent.putExtra(FROM_NOTIFICATION, true);
            intent.addCategory(String.valueOf(metaInfo.getNotifyId()));
            return PendingIntent.getService(context, 0, intent, 134217728);
        }
        intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(metaInfo.url));
        intent.addFlags(268435456);
        return PendingIntent.getActivity(context, 0, intent, 134217728);
    }

    private static String[] determineTitleAndDespByDIP(Context context, PushMetaInfo metaInfo) {
        String title = metaInfo.getTitle();
        String desp = metaInfo.getDescription();
        Map<String, String> extra = metaInfo.getExtra();
        if (extra != null) {
            int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
            int width = Float.valueOf((((float) widthPixels) / context.getResources().getDisplayMetrics().density) + 0.5f).intValue();
            String tmp;
            if (width <= 320) {
                tmp = (String) extra.get("title_short");
                if (!TextUtils.isEmpty(tmp)) {
                    title = tmp;
                }
                tmp = (String) extra.get("description_short");
                if (!TextUtils.isEmpty(tmp)) {
                    desp = tmp;
                }
            } else if (width > 360) {
                tmp = (String) extra.get("title_long");
                if (!TextUtils.isEmpty(tmp)) {
                    title = tmp;
                }
                tmp = (String) extra.get("description_long");
                if (!TextUtils.isEmpty(tmp)) {
                    desp = tmp;
                }
            }
        }
        return new String[]{title, desp};
    }

    @SuppressLint({"NewApi"})
    private static Notification getNotificationForLargeIcons(Context context, XmPushActionContainer container, byte[] decryptedContent, RemoteViews remoteView) {
        PushMetaInfo metaInfo = container.getMetaInfo();
        Builder builder = new Builder(context);
        String[] ret = determineTitleAndDespByDIP(context, metaInfo);
        builder.setContentTitle(ret[0]);
        builder.setContentText(ret[1]);
        if (remoteView != null) {
            builder.setContent(remoteView);
        } else if (VERSION.SDK_INT >= 16) {
            builder.setStyle(new BigTextStyle().bigText(ret[1]));
        }
        builder.setWhen(System.currentTimeMillis());
        builder.setContentIntent(getClickedPendingIntent(context, container, metaInfo, decryptedContent));
        int largeIcon = getIconId(context, getTargetPackage(container), NOTIFICATION_ICON);
        int smallIcon = getIconId(context, getTargetPackage(container), NOTIFICATION_SMALL_ICON);
        if (largeIcon <= 0 || smallIcon <= 0) {
            builder.setSmallIcon(getIdForSmallIcon(context, getTargetPackage(container)));
        } else {
            builder.setLargeIcon(getBitmapFromId(context, largeIcon));
            builder.setSmallIcon(smallIcon);
        }
        builder.setAutoCancel(true);
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastNotify > NOTIFY_INTERVAL) {
            lastNotify = currentTime;
            builder.setDefaults(metaInfo.notifyType);
            Map<String, String> extra = metaInfo.getExtra();
            if (!(extra == null || (metaInfo.notifyType & 1) == 0)) {
                String soundUri = (String) extra.get(NOTIFICATION_SOUND_URI);
                if (!TextUtils.isEmpty(soundUri) && soundUri.startsWith(ANDROID_RESOURCE + getTargetPackage(container))) {
                    builder.setDefaults(metaInfo.notifyType ^ 1);
                    builder.setSound(Uri.parse(soundUri));
                }
            }
        }
        return builder.getNotification();
    }

    private static RemoteViews getNotificationForCustomLayout(Context context, XmPushActionContainer container, byte[] decryptedContent) {
        PushMetaInfo metaInfo = container.getMetaInfo();
        String pkg = getTargetPackage(container);
        Map<String, String> extra = metaInfo.getExtra();
        if (extra == null) {
            return null;
        }
        String layoutName = (String) extra.get("layout_name");
        String layoutValue = (String) extra.get("layout_value");
        if (TextUtils.isEmpty(layoutName) || TextUtils.isEmpty(layoutValue)) {
            return null;
        }
        try {
            Resources apkResources = context.getPackageManager().getResourcesForApplication(pkg);
            int resLayout = apkResources.getIdentifier(layoutName, "layout", pkg);
            if (resLayout == 0) {
                return null;
            }
            RemoteViews remoteViews = new RemoteViews(pkg, resLayout);
            try {
                String str;
                String v;
                int resLet;
                JSONObject json = new JSONObject(layoutValue);
                if (json.has("text")) {
                    JSONObject jsonText = json.getJSONObject("text");
                    Iterator iterText = jsonText.keys();
                    while (iterText.hasNext()) {
                        str = (String) iterText.next();
                        v = jsonText.getString(str);
                        resLet = apkResources.getIdentifier(str, Constants.JSON_TAG_ID, pkg);
                        if (resLet > 0) {
                            remoteViews.setTextViewText(resLet, v);
                        }
                    }
                }
                if (!json.has("image")) {
                    return remoteViews;
                }
                JSONObject jsonImage = json.getJSONObject("image");
                Iterator iterImage = jsonImage.keys();
                while (iterImage.hasNext()) {
                    str = (String) iterImage.next();
                    v = jsonImage.getString(str);
                    resLet = apkResources.getIdentifier(str, Constants.JSON_TAG_ID, pkg);
                    int resImg = apkResources.getIdentifier(v, "drawable", pkg);
                    if (resLet > 0) {
                        remoteViews.setImageViewResource(resLet, resImg);
                    }
                }
                return remoteViews;
            } catch (Throwable e) {
                MyLog.e(e);
                return null;
            }
        } catch (Throwable e2) {
            MyLog.e(e2);
            return null;
        }
    }

    private static Bitmap getBitmapFromId(Context context, int iconId) {
        return drawableToBitmap(context.getResources().getDrawable(iconId));
    }

    private static int getIdForSmallIcon(Context context, String targetPackage) {
        int icon;
        int largeIcon = getIconId(context, targetPackage, NOTIFICATION_ICON);
        int smallIcon = getIconId(context, targetPackage, NOTIFICATION_SMALL_ICON);
        if (largeIcon > 0) {
            icon = largeIcon;
        } else if (smallIcon > 0) {
            icon = smallIcon;
        } else {
            icon = context.getApplicationInfo().icon;
        }
        if (icon != 0 || VERSION.SDK_INT < 9) {
            return icon;
        }
        return context.getApplicationInfo().logo;
    }

    private static int getIconId(Context context, String targetPackage, String iconName) {
        if (targetPackage.equals(context.getPackageName())) {
            return context.getResources().getIdentifier(iconName, "drawable", targetPackage);
        }
        return 0;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int width = drawable.getIntrinsicWidth();
        if (width <= 0) {
            width = 1;
        }
        int height = drawable.getIntrinsicHeight();
        if (height <= 0) {
            height = 1;
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private static Notification setTargetPackage(Notification notification, String packageName) {
        try {
            Field field = Notification.class.getDeclaredField("extraNotification");
            field.setAccessible(true);
            Object object = field.get(notification);
            Method setMethod = object.getClass().getDeclaredMethod("setTargetPkg", new Class[]{CharSequence.class});
            setMethod.setAccessible(true);
            setMethod.invoke(object, new Object[]{packageName});
        } catch (Throwable e) {
            MyLog.e(e);
        }
        return notification;
    }

    static String getTargetPackage(XmPushActionContainer container) {
        if (LogProvider.AUTHORITY.equals(container.packageName)) {
            PushMetaInfo metaInfo = container.getMetaInfo();
            if (!(metaInfo == null || metaInfo.getExtra() == null)) {
                String packageName = (String) metaInfo.getExtra().get(MIUI_PACKAGE_NAME);
                if (!TextUtils.isEmpty(packageName)) {
                    return packageName;
                }
            }
        }
        return container.packageName;
    }

    public static void clearNotification(Context context, String packageName) {
        NotificationManager nm = (NotificationManager) context.getSystemService("notification");
        for (int i = 0; i <= MAX_NOTIFY_ID; i++) {
            nm.cancel(((packageName.hashCode() / 10) * 10) + i);
        }
    }

    public static void clearNotification(Context context, String packageName, int notificationId) {
        ((NotificationManager) context.getSystemService("notification")).cancel(((packageName.hashCode() / 10) * 10) + notificationId);
    }
}
