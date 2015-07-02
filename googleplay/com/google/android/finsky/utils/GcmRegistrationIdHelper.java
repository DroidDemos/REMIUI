package com.google.android.finsky.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.gcm.GCMRegistrar;

public class GcmRegistrationIdHelper {
    public static String getRegistrationId(Context context) {
        GCMRegistrar.checkDevice(context);
        String registrationId = GCMRegistrar.getRegistrationId(context);
        if (!TextUtils.isEmpty(registrationId)) {
            return registrationId;
        }
        FinskyLog.d("Start requesting GCM Reg Id", new Object[0]);
        GCMRegistrar.register(context, "932144863878");
        return null;
    }

    public static void uploadIfNotRegistered(Context context, DfeApi dfeApi) {
        String registrationId = GCMRegistrar.getRegistrationId(context);
        if (!TextUtils.isEmpty(registrationId) && !registrationId.equals(FinskyPreferences.gcmRegistrationIdOnServer.get())) {
            FinskyLog.d("Uploading GcmRegistration Id because not registered yet", new Object[0]);
            DeviceConfigurationHelper.uploadGcmRegistrationId(dfeApi);
        }
    }

    public static void setRegisteredOnServer(String registeredId) {
        FinskyPreferences.gcmRegistrationIdOnServer.put(registeredId);
    }

    public static void onGcmError(Context context, String errId) {
        FinskyLog.d("%s", errId);
    }

    public static void onGcmMessage(Context context, Intent intent) {
        if (FinskyLog.DEBUG) {
            FinskyLog.v("%s", intent.toString());
        }
    }

    public static void onGcmRegistered(Context context, String regId) {
        if (FinskyLog.DEBUG) {
            FinskyLog.v("%s", regId);
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                GcmRegistrationIdHelper.uploadIfNotRegistered(FinskyApp.get(), FinskyApp.get().getDfeApi());
            }
        });
    }

    public static void onGcmUnregistered(final Context context, String regId) {
        FinskyLog.d("GcmUnregistered - invalidating and requesting new id.", new Object[0]);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                GcmRegistrationIdHelper.setRegisteredOnServer(null);
                GcmRegistrationIdHelper.getRegistrationId(context);
            }
        });
    }
}
