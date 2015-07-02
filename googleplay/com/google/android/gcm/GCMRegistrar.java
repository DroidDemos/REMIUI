package com.google.android.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.util.Log;

public final class GCMRegistrar {
    private static GCMBroadcastReceiver sRetryReceiver;
    private static String sRetryReceiverClassName;

    public static void checkDevice(Context context) {
        int version = VERSION.SDK_INT;
        if (version < 8) {
            throw new UnsupportedOperationException("Device must be at least API Level 8 (instead of " + version + ")");
        }
        try {
            context.getPackageManager().getPackageInfo("com.google.android.gsf", 0);
        } catch (NameNotFoundException e) {
            throw new UnsupportedOperationException("Device does not have package com.google.android.gsf");
        }
    }

    public static void register(Context context, String... senderIds) {
        resetBackoff(context);
        internalRegister(context, senderIds);
    }

    static void internalRegister(Context context, String... senderIds) {
        String flatSenderIds = getFlatSenderIds(senderIds);
        Log.v("GCMRegistrar", "Registering app " + context.getPackageName() + " of senders " + flatSenderIds);
        Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gsf");
        intent.putExtra("app", PendingIntent.getBroadcast(context, 0, new Intent(), 0));
        intent.putExtra("sender", flatSenderIds);
        context.startService(intent);
    }

    static String getFlatSenderIds(String... senderIds) {
        if (senderIds == null || senderIds.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        StringBuilder builder = new StringBuilder(senderIds[0]);
        for (int i = 1; i < senderIds.length; i++) {
            builder.append(',').append(senderIds[i]);
        }
        return builder.toString();
    }

    static void internalUnregister(Context context) {
        Log.v("GCMRegistrar", "Unregistering app " + context.getPackageName());
        Intent intent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
        intent.setPackage("com.google.android.gsf");
        intent.putExtra("app", PendingIntent.getBroadcast(context, 0, new Intent(), 0));
        context.startService(intent);
    }

    static synchronized void setRetryBroadcastReceiver(Context context) {
        synchronized (GCMRegistrar.class) {
            if (sRetryReceiver == null) {
                if (sRetryReceiverClassName == null) {
                    Log.e("GCMRegistrar", "internal error: retry receiver class not set yet");
                    sRetryReceiver = new GCMBroadcastReceiver();
                } else {
                    try {
                        sRetryReceiver = (GCMBroadcastReceiver) Class.forName(sRetryReceiverClassName).newInstance();
                    } catch (Exception e) {
                        Log.e("GCMRegistrar", "Could not create instance of " + sRetryReceiverClassName + ". Using " + GCMBroadcastReceiver.class.getName() + " directly.");
                        sRetryReceiver = new GCMBroadcastReceiver();
                    }
                }
                String category = context.getPackageName();
                IntentFilter filter = new IntentFilter("com.google.android.gcm.intent.RETRY");
                filter.addCategory(category);
                String permission = category + ".permission.C2D_MESSAGE";
                Log.v("GCMRegistrar", "Registering receiver");
                context.registerReceiver(sRetryReceiver, filter, permission, null);
            }
        }
    }

    static void setRetryReceiverClassName(String className) {
        Log.v("GCMRegistrar", "Setting the name of retry receiver class to " + className);
        sRetryReceiverClassName = className;
    }

    public static String getRegistrationId(Context context) {
        SharedPreferences prefs = getGCMPreferences(context);
        String registrationId = prefs.getString("regId", "");
        int oldVersion = prefs.getInt("appVersion", Integer.MIN_VALUE);
        int newVersion = getAppVersion(context);
        if (oldVersion == Integer.MIN_VALUE || oldVersion == newVersion) {
            return registrationId;
        }
        Log.v("GCMRegistrar", "App version changed from " + oldVersion + " to " + newVersion + "; resetting registration id");
        clearRegistrationId(context);
        return "";
    }

    public static boolean isRegistered(Context context) {
        return getRegistrationId(context).length() > 0;
    }

    static String clearRegistrationId(Context context) {
        return setRegistrationId(context, "");
    }

    static String setRegistrationId(Context context, String regId) {
        SharedPreferences prefs = getGCMPreferences(context);
        String oldRegistrationId = prefs.getString("regId", "");
        int appVersion = getAppVersion(context);
        Log.v("GCMRegistrar", "Saving regId on app version " + appVersion);
        Editor editor = prefs.edit();
        editor.putString("regId", regId);
        editor.putInt("appVersion", appVersion);
        editor.commit();
        return oldRegistrationId;
    }

    private static int getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Coult not get package name: " + e);
        }
    }

    static void resetBackoff(Context context) {
        Log.d("GCMRegistrar", "resetting backoff for " + context.getPackageName());
        setBackoff(context, 3000);
    }

    static int getBackoff(Context context) {
        return getGCMPreferences(context).getInt("backoff_ms", 3000);
    }

    static void setBackoff(Context context, int backoff) {
        Editor editor = getGCMPreferences(context).edit();
        editor.putInt("backoff_ms", backoff);
        editor.commit();
    }

    private static SharedPreferences getGCMPreferences(Context context) {
        return context.getSharedPreferences("com.google.android.gcm", 0);
    }
}
