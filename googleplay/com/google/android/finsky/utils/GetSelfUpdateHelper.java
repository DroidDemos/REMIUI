package com.google.android.finsky.utils;

import android.text.TextUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.protos.SelfUpdate.SelfUpdateResponse;

public class GetSelfUpdateHelper {

    public interface Listener {
        void onErrorResponse(VolleyError volleyError);

        void onResponse(SelfUpdateResponse selfUpdateResponse);
    }

    public static void getSelfUpdate(DfeApi dfeApi, Listener listener) {
        if (TextUtils.isEmpty(DeviceConfigurationHelper.getToken())) {
            doRequestToken(true, dfeApi, listener);
        } else {
            doGetSelfUpdate(true, dfeApi, listener);
        }
    }

    private static void doRequestToken(final boolean allowRetry, final DfeApi dfeApi, final Listener listener) {
        DeviceConfigurationHelper.requestToken(dfeApi, new com.google.android.finsky.utils.DeviceConfigurationHelper.Listener() {
            public void onSuccess() {
                GetSelfUpdateHelper.doGetSelfUpdate(allowRetry, dfeApi, listener);
            }

            public void onError(VolleyError error) {
                FinskyLog.w("Upload device configuration failed - try selfupdate anyway", new Object[0]);
                GetSelfUpdateHelper.doGetSelfUpdate(allowRetry, dfeApi, listener);
            }
        });
    }

    private static void doGetSelfUpdate(final boolean allowRetry, final DfeApi dfeApi, final Listener listener) {
        dfeApi.getSelfUpdate(DeviceConfigurationHelper.getToken(), new com.android.volley.Response.Listener<SelfUpdateResponse>() {
            public void onResponse(SelfUpdateResponse response) {
                if (response.requiresUploadDeviceConfig) {
                    FinskyLog.d("Server requests device config token", new Object[0]);
                    DeviceConfigurationHelper.invalidateToken();
                    GetSelfUpdateHelper.changedDeviceConfigToken(dfeApi);
                    if (allowRetry) {
                        GetSelfUpdateHelper.doRequestToken(false, dfeApi, listener);
                        return;
                    }
                    FinskyLog.w("Failed to converge on device config for selfUpdate", new Object[0]);
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
        dfeApi.invalidateSelfUpdateCache();
    }
}
