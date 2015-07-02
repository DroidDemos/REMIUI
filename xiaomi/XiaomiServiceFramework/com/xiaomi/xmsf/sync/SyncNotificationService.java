package com.xiaomi.xmsf.sync;

import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.app.MiuiNotification;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SyncAdapterType;
import android.content.SyncInfo;
import android.content.SyncStatusObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.xmsf.R;
import java.util.Arrays;
import java.util.List;
import miui.content.res.IconCustomizer;

public class SyncNotificationService extends Service {
    private static final List<String> AOSP_AUTHORITIES;
    private static final int SYNC_STATE_NOTIFICATION_ID = 8;
    private static final String TAG = "SyncNotificationService";
    private static volatile SyncNotificationService sInstance;
    private final Handler mHandler;
    private String mLastNotifiedAuthority;
    private Object mStatusChangeListenerHandle;
    private SyncStatusObserver mSyncStatusObserver;

    public SyncNotificationService() {
        this.mHandler = new Handler();
        this.mSyncStatusObserver = new SyncStatusObserver() {
            public void onStatusChanged(int which) {
                SyncNotificationService.this.mHandler.post(new Runnable() {
                    public void run() {
                        SyncNotificationService.this.updateSyncNotification();
                    }
                });
            }
        };
    }

    static {
        sInstance = null;
        AOSP_AUTHORITIES = Arrays.asList(new String[]{"com.android.contacts", "contacts;com.android.contacts", "com.android.calendar", "com.android.browser"});
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            Log.e(TAG, "nothing for SyncStateNotificationService");
            stopSelf();
        } else {
            updateSyncNotification();
        }
        return 2;
    }

    private void updateSyncNotification() {
        List<SyncInfo> currentSyncs = ContentResolver.getCurrentSyncs();
        if (currentSyncs.isEmpty()) {
            log("currentSyncs is empty");
            cancelNotification();
            return;
        }
        SyncInfo syncInfo = (SyncInfo) currentSyncs.get(0);
        String authority = syncInfo.authority;
        if (!updateNotification(getPackageManager().resolveContentProvider(authority, 0), syncInfo.account.type)) {
            log("updateNotification returns false");
            cancelNotification();
        }
    }

    private static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private boolean updateNotification(ProviderInfo providerInfo, String accountType) {
        if (providerInfo == null) {
            log("providerInfo is null");
            return false;
        } else if (accountType == null) {
            log("accountType is null");
            return false;
        } else {
            if (TextUtils.equals(providerInfo.authority, this.mLastNotifiedAuthority)) {
                log("same as mLastNotifiedAuthority");
                return true;
            }
            if (checkBlacklistedAuthorities(providerInfo.authority)) {
                return false;
            }
            for (SyncAdapterType type : ContentResolver.getSyncAdapterTypes()) {
                if (providerInfo.authority.equals(type.authority) && !type.isUserVisible()) {
                    log("user invisible authority: " + type.authority);
                }
            }
            this.mLastNotifiedAuthority = providerInfo.authority;
            AuthenticatorDescription authenticatorInfo = getAuthenticatorByAccountType(accountType);
            if (authenticatorInfo == null) {
                log("authenticatorInfo is null");
                return false;
            }
            PackageManager pm = getPackageManager();
            CharSequence providerLabel = providerInfo.loadLabel(pm);
            if (TextUtils.isEmpty(providerLabel)) {
                providerLabel = providerInfo.authority;
            }
            Resources authenticatorAppResources = getAppResources(pm, authenticatorInfo.packageName);
            if (authenticatorAppResources == null) {
                log("authenticatorAppResources is null");
                return false;
            }
            StringBuilder append = new StringBuilder().append("sync notification will show: accountType: ");
            String str = providerInfo.authority;
            log(r24.append(accountType).append(", authority: ").append(r0).append(", authenticatorPackage: ").append(authenticatorInfo.packageName).append(", providerPackage: ").append(providerInfo.packageName).toString());
            Bitmap largeIcon = null;
            String accountLabel;
            Intent intent;
            if ("com.xiaomi".equals(accountType)) {
                BitmapDrawable icon = IconCustomizer.getCustomizedIcon(this, "com.miui.cloudservice.png");
                if (icon != null) {
                    largeIcon = icon.getBitmap();
                } else {
                    log("cannot get cloudservice icon");
                }
                accountLabel = getString(R.string.xiaomi_cloud_service);
                intent = new Intent("com.xiaomi.action.MICLOUD_MAIN");
                intent.setPackage("com.miui.cloudservice");
                showNotification(largeIcon, accountLabel, providerLabel, PendingIntent.getActivity(this, 0, intent, 134217728));
            } else {
                if (AOSP_AUTHORITIES.contains(providerInfo.authority)) {
                    largeIcon = drawableToBitmap(authenticatorAppResources.getDrawable(authenticatorInfo.iconId));
                } else if (!(providerInfo == null || providerInfo.applicationInfo == null)) {
                    String GOOGLE_SERVICE_FRAMEWORK_PACKAGE_NAME = "com.google.android.gsf";
                    String GOOGLE_PLAY_SERVICE_PACKAGE_NAME = "com.google.android.gms";
                    if (!"com.google.android.gsf".equals(providerInfo.packageName)) {
                        if (!"com.google.android.gms".equals(providerInfo.packageName)) {
                            largeIcon = drawableToBitmap(pm.getApplicationIcon(providerInfo.applicationInfo));
                        }
                    }
                    largeIcon = drawableToBitmap(authenticatorAppResources.getDrawable(authenticatorInfo.iconId));
                    log("use google account icon instead of google service app icon");
                }
                accountLabel = authenticatorAppResources.getString(authenticatorInfo.labelId);
                if (TextUtils.isEmpty(accountLabel)) {
                    accountLabel = accountType;
                }
                if (largeIcon == null) {
                    log("appIcon is null when showing notification 3rd party app");
                }
                intent = new Intent("android.intent.action.MAIN");
                intent.addFlags(268435456);
                intent.setClassName("com.android.settings", "com.android.settings.Settings");
                Bundle args = new Bundle();
                args.putString("account_label", accountLabel);
                args.putString("account_type", accountType);
                intent.putExtra(":android:show_fragment", "com.android.settings.accounts.ManageAccountsSettings");
                intent.putExtra(":android:show_fragment_args", args);
                intent.putExtra(":android:show_fragment_title", 0);
                intent.putExtra(":android:show_fragment_short_title", 0);
                intent.putExtra(":android:no_headers", true);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 134217728);
                String contentText = accountLabel;
                String AOSP_EMAIL_PACKAGE_NAME = "com.android.email";
                if ("com.android.email".equals(authenticatorInfo.packageName)) {
                    contentText = null;
                }
                showNotification(largeIcon, contentText, providerLabel, pendingIntent);
            }
            return true;
        }
    }

    private boolean checkBlacklistedAuthorities(String authority) {
        if ("miui.yellowpage".equals(authority)) {
            return true;
        }
        return false;
    }

    private Bitmap getProviderAppIcon(PackageManager pm, ProviderInfo providerInfo) {
        if (providerInfo == null || providerInfo.applicationInfo == null) {
            return null;
        }
        return BitmapFactory.decodeResource(getAppResources(pm, providerInfo.applicationInfo.packageName), providerInfo.applicationInfo.icon);
    }

    private void showNotification(Bitmap largeIcon, String accountLabel, CharSequence providerLabel, PendingIntent pendingIntent) {
        Builder builder = new Builder(this);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(-1);
        builder.setLargeIcon(largeIcon);
        builder.setContentText(accountLabel);
        String title = getString(R.string.sync_notification_title_format, new Object[]{providerLabel});
        builder.setContentTitle(title);
        builder.setAutoCancel(false);
        builder.setWhen(0);
        Notification notification = builder.build();
        MiuiNotification extraNotification = new MiuiNotification();
        extraNotification.setCustomizedIcon(true);
        notification.extraNotification = extraNotification;
        ((NotificationManager) getSystemService("notification")).notify(SYNC_STATE_NOTIFICATION_ID, notification);
        log("notification sent, title: " + title + ", content: " + accountLabel);
    }

    private void log(String message) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, message);
        }
    }

    private Resources getAppResources(PackageManager pm, String packageName) {
        try {
            return pm.getResourcesForApplication(packageName);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private AuthenticatorDescription getAuthenticatorByAccountType(String accountType) {
        for (AuthenticatorDescription authenticator : AccountManager.get(this).getAuthenticatorTypes()) {
            if (TextUtils.equals(accountType, authenticator.type)) {
                return authenticator;
            }
        }
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.mStatusChangeListenerHandle = ContentResolver.addStatusChangeListener(4, this.mSyncStatusObserver);
        sInstance = this;
    }

    public void onDestroy() {
        super.onDestroy();
        ContentResolver.removeStatusChangeListener(this.mStatusChangeListenerHandle);
        sInstance = null;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    private void cancelNotification() {
        ((NotificationManager) getSystemService("notification")).cancel(SYNC_STATE_NOTIFICATION_ID);
    }

    public static void begin(Context context) {
        context.startService(new Intent(context, SyncNotificationService.class));
    }

    public static void end(Context context) {
        if (sInstance != null) {
            sInstance.cancelNotification();
            sInstance.stopSelf();
        }
    }
}
