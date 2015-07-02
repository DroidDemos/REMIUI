package com.google.android.finsky.billing.iab;

import android.accounts.Account;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.SystemClock;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.VendingProtos.AppDataProto;
import com.google.android.finsky.protos.VendingProtos.DataMessageProto;
import com.google.android.finsky.protos.VendingProtos.GetMarketMetadataResponseProto;
import com.google.android.finsky.protos.VendingProtos.PendingNotificationsProto;
import com.google.android.finsky.utils.BackgroundThreadFactory;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.VendingPreferences;

public class PendingNotificationsService extends Service {
    private static String ACTION_ALARM;
    public static String ACTION_RESTART_ALARM;

    static {
        ACTION_ALARM = "action_alarm";
        ACTION_RESTART_ALARM = "action_restart_alarm";
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            stopSelf();
        } else {
            String action = intent.getAction();
            if (action.equals(ACTION_ALARM)) {
                handleAlarm(intent.getStringExtra("account"));
            } else if (action.equals(ACTION_RESTART_ALARM)) {
                restartAlarmsAfterBoot();
            } else {
                FinskyLog.e("unexpected action: %s", action);
                stopSelf();
            }
        }
        return 2;
    }

    private void handleAlarm(final String account) {
        FinskyApp.get().getVendingApi(account).checkForPendingNotifications(new Listener<GetMarketMetadataResponseProto>() {
            public void onResponse(GetMarketMetadataResponseProto response) {
                PendingNotificationsService.this.stopSelf();
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FinskyLog.d("CheckForPendingNotifications failed: error=%s", error);
                PendingNotificationsService.rescheduleMarketAlarm(FinskyApp.get(), account);
                PendingNotificationsService.this.stopSelf();
            }
        });
    }

    private void restartAlarmsAfterBoot() {
        BackgroundThreadFactory.createThread(new Runnable() {
            public void run() {
                for (Account account : AccountHandler.getAccounts(PendingNotificationsService.this)) {
                    String accountName = account.name;
                    if (FinskyLog.DEBUG) {
                        FinskyLog.v("Checking for pending alarms for account=%s", accountName);
                    }
                    long startTime = ((Long) VendingPreferences.getMarketAlarmStartTime(accountName).get()).longValue();
                    if (startTime != 0) {
                        long interval = ((Long) VendingPreferences.getMarketAlarmTimeout(accountName).get()).longValue();
                        long now = System.currentTimeMillis();
                        long elapsed = now - startTime;
                        if (elapsed < 0) {
                            FinskyLog.e("Current time is wrong? current time=%d, alarm start time=%d", Long.valueOf(now), Long.valueOf(startTime));
                            elapsed = 0;
                        }
                        long remaining = interval - elapsed;
                        if (remaining < 20000) {
                            if (FinskyLog.DEBUG) {
                                FinskyLog.v("remaining=%d, delaying alarm for a while.", Long.valueOf(remaining));
                            }
                            remaining = 20000;
                        }
                        PendingNotificationsService.setMarketAlarm(PendingNotificationsService.this, accountName, remaining, now);
                    } else if (FinskyLog.DEBUG) {
                        FinskyLog.v("No pending alarm.", new Object[0]);
                    }
                }
                PendingNotificationsService.this.stopSelf();
            }
        }).start();
    }

    private static PendingIntent createPendingIntentForMarketAlarm(Context context, String account) {
        Intent intent = new Intent(ACTION_ALARM);
        intent.setClass(context, PendingNotificationsService.class);
        intent.putExtra("account", account);
        intent.setData(Uri.fromParts("vendingpending", account, null));
        return PendingIntent.getService(context, 0, intent, 1073741824);
    }

    public static void setMarketAlarm(Context context, String account, long duration) {
        setMarketAlarm(context, account, duration, System.currentTimeMillis());
    }

    public static void setMarketAlarm(Context context, String account, long duration, long startTime) {
        FinskyLog.d("Setting alarm for account=%s, duration=%d", account, Long.valueOf(duration));
        ((AlarmManager) context.getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + duration, createPendingIntentForMarketAlarm(context, account));
        VendingPreferences.getMarketAlarmStartTime(account).put(Long.valueOf(startTime));
        VendingPreferences.getMarketAlarmTimeout(account).put(Long.valueOf(duration));
    }

    public static void cancelMarketAlarm(Context context, String account) {
        FinskyLog.d("Canceling alarm for account=%s", account);
        VendingPreferences.getMarketAlarmStartTime(account).put(Long.valueOf(0));
        ((AlarmManager) context.getSystemService("alarm")).cancel(createPendingIntentForMarketAlarm(context, account));
    }

    private static void rescheduleMarketAlarm(final Context context, final String account) {
        BackgroundThreadFactory.createThread(new Runnable() {
            public void run() {
                long interval = ((Long) VendingPreferences.getMarketAlarmTimeout(account).get()).longValue() * 2;
                if (interval <= ((Long) G.vendingAlarmExpirationTimeoutMs.get()).longValue()) {
                    PendingNotificationsService.setMarketAlarm(context, account, Math.max(Math.min(interval, ((Long) G.vendingAlarmMaxTimeoutMs.get()).longValue()), ((Long) G.vendingAlarmMinTimeoutMs.get()).longValue()));
                }
            }
        }).start();
    }

    public static boolean handlePendingNotifications(Context context, String account, PendingNotificationsProto pendingNotifications, boolean allowCancellation) {
        boolean handledNotifications = false;
        if (pendingNotifications.hasNextCheckMillis) {
            long nextCheckMillis = pendingNotifications.nextCheckMillis;
            if (FinskyLog.DEBUG) {
                FinskyLog.v("Got next_check_millis=%d", Long.valueOf(nextCheckMillis));
            }
            if (nextCheckMillis > 0) {
                setMarketAlarm(FinskyApp.get(), account, nextCheckMillis);
                handledNotifications = true;
            } else if (allowCancellation) {
                cancelMarketAlarm(FinskyApp.get(), account);
                handledNotifications = true;
            }
        }
        int numNotifications = 0;
        for (DataMessageProto notification : pendingNotifications.notification) {
            String category = notification.category;
            if (FinskyLog.DEBUG) {
                FinskyLog.v("Processing pending notification with category=%s", category);
            }
            Intent intent = new Intent("com.google.android.c2dm.intent.RECEIVE");
            intent.addCategory(category);
            intent = IntentUtils.createIntentForReceiver(context.getPackageManager(), FinskyApp.get().getPackageName(), intent);
            if (intent == null) {
                FinskyLog.w("Cannot find receiver for intent category: %s", category);
            } else {
                for (AppDataProto appData : notification.appData) {
                    intent.putExtra(appData.key, appData.value);
                }
                context.sendOrderedBroadcast(intent, null);
                handledNotifications = true;
                numNotifications++;
            }
        }
        if (FinskyLog.DEBUG) {
            FinskyLog.v("Handled %d notifications.", Integer.valueOf(numNotifications));
        }
        return handledNotifications;
    }
}
