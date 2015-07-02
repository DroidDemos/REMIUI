package com.xiaomi.xmsf.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.push.service.MIPushAccountUtils;
import com.xiaomi.xmsf.push.service.data.BusinessMessage;
import com.xiaomi.xmsf.push.service.data.BusinessNotification;
import com.xiaomi.xmsf.push.service.notification.BigViewImageDownloader;
import com.xiaomi.xmsf.push.service.notification.BigViewImageDownloader.Callback;
import com.xiaomi.xmsf.push.service.notification.NotificationBaseRemoteView;
import com.xiaomi.xmsf.push.service.notification.NotificationBigRemoteView;
import com.xiaomi.xmsf.push.service.trace.AdsLogSender;
import com.xiaomi.xmsf.push.service.trace.MiPushRelayTraceService;
import com.xiaomi.xmsf.push.service.trace.TraceLog;
import java.util.Map;
import miui.os.Build;
import org.apache.thrift.transport.TTransportException;

public class BusinessApp {
    private static final String[] S_TOPICS;
    private static BusinessApp mSelf;
    private final Context mAppCtx;
    private final String mAppId;
    private final String mAppKey;
    private boolean mIsInit;
    private String mRegId;
    private final int[] mScreenWidth;
    private final AdsLogSender mTracer;
    private String mUid;

    static {
        S_TOPICS = new String[]{Constants.XMSF_TOPIC_PREFIX + Build.PRODUCT, Constants.XMSF_TOPIC_PREFIX + Build.MODEL, Constants.XMSF_TOPIC_PREFIX + VERSION.RELEASE + " " + Build.ID, "MIUI_XMSF_271828_TOPIC_MIUI-" + VERSION.INCREMENTAL, "MIUI_XMSF_271828_TOPIC_ALL"};
    }

    public static synchronized BusinessApp getInstance(Context appContext) {
        BusinessApp businessApp;
        synchronized (BusinessApp.class) {
            if (mSelf == null) {
                mSelf = new BusinessApp(appContext);
            }
            businessApp = mSelf;
        }
        return businessApp;
    }

    private BusinessApp(Context appContext) {
        this.mIsInit = false;
        this.mAppId = MIPushAccountUtils.MIPUSH_MIUI_APPID;
        this.mAppKey = MIPushAccountUtils.MIPUSH_MIUI_APP_TOKEN;
        this.mAppCtx = appContext;
        String str = "";
        this.mUid = str;
        this.mRegId = str;
        this.mTracer = AdsLogSender.getInstance(this.mAppCtx, this.mAppId, this.mAppKey);
        this.mScreenWidth = new int[]{320, 360};
    }

    public void setAccountAsAlias() {
        if (this.mIsInit) {
            String uid = Utils.getXiaomiUserId(this.mAppCtx);
            if ((TextUtils.isEmpty(this.mUid) && !TextUtils.isEmpty(uid)) || (!TextUtils.isEmpty(this.mUid) && !this.mUid.equals(uid))) {
                if (TextUtils.isEmpty(this.mUid)) {
                    MiPushClient.setAlias(this.mAppCtx, uid, null);
                    MiPushClient.setAlias(this.mAppCtx, Constants.XMSF_ALIAS_PREFIX + uid, null);
                } else {
                    MiPushClient.unsetAlias(this.mAppCtx, this.mUid, null);
                    MiPushClient.unsetAlias(this.mAppCtx, Constants.XMSF_ALIAS_PREFIX + this.mUid, null);
                    resetAdsLimits();
                    this.mTracer.onAccountChanged();
                }
                this.mUid = uid;
            }
        }
    }

    public void onPushRegisterSuccess(String regId) {
        if (!TextUtils.isEmpty(regId)) {
            this.mRegId = regId;
            this.mIsInit = true;
            setCommonAlias();
            setCommonTopics();
        }
    }

    private void resetAdsLimits() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this.mAppCtx);
        pref.edit().putLong(Constants.PREFER_KEY_STARTTIME, 0).commit();
        pref.edit().putInt(Constants.PREFER_KEY_NOTIFY_SUCCESS_COUNT, 0).commit();
        pref.edit().putInt(Constants.PREFER_KEY_BUBBLE_SUCCESS_COUNT, 0).commit();
    }

    private void setCommonAlias() {
        String imei = Utils.getIMEI(this.mAppCtx);
        if (imei != null) {
            MiPushClient.setAlias(this.mAppCtx, imei, null);
            MiPushClient.setAlias(this.mAppCtx, Constants.XMSF_ALIAS_PREFIX + imei, null);
        }
        setAccountAsAlias();
    }

    private void setCommonTopics() {
        for (String topic : S_TOPICS) {
            MiPushClient.subscribe(this.mAppCtx, topic, null);
        }
    }

    public void init() {
        if (!this.mIsInit) {
            MiPushClient.registerPush(this.mAppCtx, this.mAppId, this.mAppKey);
        }
    }

    public void release() {
        if (this.mIsInit && this.mTracer != null) {
            this.mTracer.release();
        }
    }

    private void increaseSuccessAds(int showType) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this.mAppCtx);
        switch (showType) {
            case TTransportException.NOT_OPEN /*1*/:
                pref.edit().putInt(Constants.PREFER_KEY_BUBBLE_SUCCESS_COUNT, pref.getInt(Constants.PREFER_KEY_BUBBLE_SUCCESS_COUNT, 0) + 1).commit();
                return;
            case TTransportException.ALREADY_OPEN /*2*/:
                pref.edit().putInt(Constants.PREFER_KEY_NOTIFY_SUCCESS_COUNT, pref.getInt(Constants.PREFER_KEY_NOTIFY_SUCCESS_COUNT, 0) + 1).commit();
                return;
            default:
                return;
        }
    }

    private Intent constructPassThroughIntentToApp(String pkgName, MiPushMessage message) {
        Intent ret = new Intent(Constants.INTENT_ACTION_MIPUSH_MIUI_RECEIVE_MESSAGE);
        ret.setPackage(pkgName);
        ret.putExtras(message.toBundle());
        return ret;
    }

    private void handleAdsCell(BusinessMessage cell, MiPushMessage message) {
        this.mTracer.receiveTrace(new TraceLog(cell));
        String pkgName = (String) message.getExtra().get(Constants.EXTRA_KEY_PKGNAME);
        if (pkgName != null && pkgName.trim().length() > 0) {
            switch (cell.showType) {
                case TTransportException.NOT_OPEN /*1*/:
                    this.mAppCtx.sendBroadcast(constructPassThroughIntentToApp(pkgName, message));
                    return;
                case TTransportException.ALREADY_OPEN /*2*/:
                    popNotification(pkgName, (BusinessNotification) cell, message);
                    return;
                default:
                    return;
            }
        }
    }

    @TargetApi(16)
    private void popNotification(String pkgName, BusinessNotification cell, MiPushMessage message) {
        String title = cell.priText;
        String subTitle = cell.secText;
        if (title != null) {
            title = title.trim();
        }
        if (subTitle != null) {
            subTitle = subTitle.trim();
        }
        if (!TextUtils.isEmpty(title) || !TextUtils.isEmpty(subTitle)) {
            if (TextUtils.isEmpty(title)) {
                title = subTitle;
                subTitle = "";
            }
            try {
                final int smallIcon = this.mAppCtx.getPackageManager().getApplicationInfo(pkgName, 0).icon;
                if (smallIcon > 0) {
                    int widthPixels;
                    int width;
                    final int notifyId = Long.valueOf(cell.id).hashCode();
                    final Notification notification = new Notification();
                    notification.icon = smallIcon;
                    Map<String, String> extra = message.getExtra();
                    if (extra != null) {
                        widthPixels = this.mAppCtx.getResources().getDisplayMetrics().widthPixels;
                        width = Float.valueOf((((float) widthPixels) / this.mAppCtx.getResources().getDisplayMetrics().density) + 0.5f).intValue();
                        if (width <= this.mScreenWidth[0]) {
                            title = (String) extra.get("title_short");
                            subTitle = (String) extra.get("description_short");
                        } else if (width > this.mScreenWidth[1]) {
                            title = (String) extra.get("title_long");
                            subTitle = (String) extra.get("description_long");
                        }
                    }
                    NotificationBaseRemoteView remoteViews = new NotificationBaseRemoteView(this.mAppCtx);
                    remoteViews.setTitles(title, subTitle);
                    setIcon(remoteViews, pkgName, smallIcon);
                    notification.contentView = remoteViews;
                    notification.tickerText = cell.titText;
                    notification.flags |= 16;
                    notification.contentIntent = getPendingIntentByType(cell, 2, message);
                    notification.deleteIntent = getPendingIntentByType(cell, 1, null);
                    notification.extraNotification.setTargetPkg(pkgName);
                    final NotificationManager manager = (NotificationManager) this.mAppCtx.getSystemService("notification");
                    if (VERSION.SDK_INT < 16 || TextUtils.isEmpty(cell.imgUrl)) {
                        manager.notify(notifyId, notification);
                        return;
                    }
                    final String t1 = title;
                    final String t2 = subTitle;
                    String imagePath = cell.imgUrl;
                    if (extra != null) {
                        widthPixels = this.mAppCtx.getResources().getDisplayMetrics().widthPixels;
                        width = Float.valueOf((((float) widthPixels) / this.mAppCtx.getResources().getDisplayMetrics().density) + 0.5f).intValue();
                        if (width <= this.mScreenWidth[0]) {
                            imagePath = (String) extra.get("downloadImagePath_short");
                        } else if (width > this.mScreenWidth[1]) {
                            imagePath = (String) extra.get("downloadImagePath_long");
                        }
                    }
                    final String str = pkgName;
                    BigViewImageDownloader bigViewImageDownloader = new BigViewImageDownloader(imagePath, new Callback() {
                        public void onFinish(Bitmap showPicture) {
                            NotificationBigRemoteView bigRemoteViews = new NotificationBigRemoteView(BusinessApp.this.mAppCtx);
                            bigRemoteViews.setTitles(t1, t2);
                            BusinessApp.this.setIcon(bigRemoteViews, str, smallIcon);
                            bigRemoteViews.setBigPicture(showPicture);
                            notification.bigContentView = bigRemoteViews;
                            manager.notify(notifyId, notification);
                        }
                    });
                    bigViewImageDownloader.execute(new Object[0]);
                }
            } catch (NameNotFoundException e) {
                MyLog.e("Package Not Found: " + e.getMessage());
            }
        }
    }

    private void setIcon(NotificationBaseRemoteView remoteView, String packageName, int icon) {
        try {
            Drawable d = this.mAppCtx.getPackageManager().getResourcesForApplication(packageName).getDrawable(icon);
            if (d instanceof BitmapDrawable) {
                remoteView.setImageViewBitmap(16908294, ((BitmapDrawable) d).getBitmap());
            }
        } catch (Throwable e) {
            MyLog.e(e);
        }
    }

    private void setActionButton(BusinessNotification cell, int notifyId, NotificationBaseRemoteView view, MiPushMessage message) {
        if (cell.actionText != null && !TextUtils.isEmpty(cell.actionText.trim()) && cell.actionUrl != null && !TextUtils.isEmpty(cell.actionUrl.trim())) {
            Intent traceIntent = new Intent(this.mAppCtx, MiPushRelayTraceService.class);
            Bundle bundle = cell.toBundle();
            bundle.putInt(Constants.INTENT_FLAG_TYPE, 3);
            bundle.putInt(Constants.INTENT_FLAG_NOTIFYID, notifyId);
            bundle.putBundle(Constants.INTENT_EXTRA_MIPUSHMESSAGE, message.toBundle());
            traceIntent.putExtras(bundle);
            traceIntent.setType(cell.id + "");
            view.setActionButton(cell.actionText, PendingIntent.getService(this.mAppCtx, 0, traceIntent, 134217728));
        }
    }

    private PendingIntent getPendingIntentByType(BusinessNotification cell, int intentType, MiPushMessage message) {
        Intent traceIntent = new Intent(this.mAppCtx, MiPushRelayTraceService.class);
        Bundle bundle = cell.toBundle();
        bundle.putInt(Constants.INTENT_FLAG_TYPE, intentType);
        if (intentType == 2) {
            bundle.putBundle(Constants.INTENT_EXTRA_MIPUSHMESSAGE, message.toBundle());
            bundle.putString(Constants.EXTRA_KEY_PKGNAME, (String) message.getExtra().get(Constants.EXTRA_KEY_PKGNAME));
            traceIntent.setType("" + cell.id);
        } else if (intentType == 1) {
            traceIntent.setType("delete_" + cell.id);
        }
        traceIntent.putExtras(bundle);
        return PendingIntent.getService(this.mAppCtx, 0, traceIntent, 134217728);
    }

    public void handlePushMessage(final MiPushMessage message) {
        if (this.mIsInit) {
            String topic = message.getTopic();
            if (topic != null && topic.startsWith(Constants.XMSF_TOPIC_PREFIX)) {
                boolean isMyTopic = false;
                for (String t : S_TOPICS) {
                    if (t.equals(topic)) {
                        isMyTopic = true;
                        break;
                    }
                }
                if (!isMyTopic) {
                    MiPushClient.unsubscribe(this.mAppCtx, topic, null);
                    return;
                }
            }
            String content = message.getContent();
            if (content != null) {
                new BusinessMessageParser(this.mAppCtx, content, new BusinessMessageParser.Callback() {
                    public void onAdsReceived(int statusCode, BusinessMessage cell) {
                        if (statusCode == 0) {
                            if (cell.receiveUpperBound > 0) {
                                BusinessApp.this.increaseSuccessAds(cell.showType);
                            }
                            BusinessApp.this.handleAdsCell(cell, message);
                            return;
                        }
                        MyLog.i("\u5e7f\u544a\u65e0\u6548\u6216\u8005\u8d85\u8fc7\u9650\u5236 " + statusCode);
                    }
                }).execute(new String[0]);
            }
        }
    }
}
