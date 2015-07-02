package com.google.android.finsky.utils;

import com.android.volley.Response.ErrorListener;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.protos.Toc.TocResponse;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class GetTocHelper {

    public interface Listener {
        void onErrorResponse(VolleyError volleyError);

        void onResponse(TocResponse tocResponse);
    }

    public static void getToc(DfeApi dfeApi, boolean allowDouble, Listener listener) {
        String deviceConfigToken = DeviceConfigurationHelper.getToken();
        GcmRegistrationIdHelper.uploadIfNotRegistered(FinskyApp.get(), dfeApi);
        doGetToc(true, dfeApi, allowDouble, listener);
    }

    public static TocResponse getTocBlocking(DfeApi dfeApi) {
        return getTocBlocking(dfeApi, 999);
    }

    public static TocResponse getTocBlocking(DfeApi dfeApi, int secondsToWait) {
        Utils.ensureNotOnMainThread();
        final Semaphore semaphore = new Semaphore(0);
        final TocResponse[] result = new TocResponse[1];
        getToc(dfeApi, false, new Listener() {
            public void onResponse(TocResponse response) {
                result[0] = response;
                semaphore.release();
            }

            public void onErrorResponse(VolleyError error) {
                result[0] = null;
                semaphore.release();
            }
        });
        try {
            if (semaphore.tryAcquire((long) secondsToWait, TimeUnit.SECONDS)) {
                return result[0];
            }
            return null;
        } catch (InterruptedException e) {
            return null;
        }
    }

    private static void doRequestToken(final boolean allowRetry, final DfeApi dfeApi, final boolean allowDouble, final Listener listener) {
        DeviceConfigurationHelper.requestToken(dfeApi, new com.google.android.finsky.utils.DeviceConfigurationHelper.Listener() {
            public void onSuccess() {
                GetTocHelper.doGetToc(allowRetry, dfeApi, allowDouble, listener);
            }

            public void onError(VolleyError error) {
                FinskyLog.w("Upload device configuration failed", new Object[0]);
                listener.onErrorResponse(error);
            }
        });
    }

    private static void doGetToc(final boolean allowRetry, final DfeApi dfeApi, final boolean allowDouble, final Listener listener) {
        dfeApi.getToc(allowDouble, DeviceConfigurationHelper.getToken(), new com.android.volley.Response.Listener<TocResponse>() {
            public void onResponse(TocResponse response) {
                if (response.requiresUploadDeviceConfig) {
                    FinskyLog.d("Server requests device config token", new Object[0]);
                    DeviceConfigurationHelper.invalidateToken();
                    GetTocHelper.changedDeviceConfigToken(dfeApi);
                    if (allowRetry) {
                        GetTocHelper.doRequestToken(false, dfeApi, allowDouble, listener);
                        return;
                    }
                    FinskyLog.w("Failed to converge on device config for TOC", new Object[0]);
                    listener.onErrorResponse(new ServerError());
                    return;
                }
                listener.onResponse(response);
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponse(error);
            }
        });
    }

    public static void changedDeviceConfigToken(DfeApi dfeApi) {
        dfeApi.invalidateTocCache();
    }
}
