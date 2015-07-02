package com.android.vending;

import android.content.Context;
import android.content.Intent;
import com.google.android.finsky.utils.GcmRegistrationIdHelper;
import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
    public GCMIntentService() {
        super("932144863878");
    }

    protected void onError(Context context, String errId) {
        GcmRegistrationIdHelper.onGcmError(context, errId);
    }

    protected void onMessage(Context context, Intent intent) {
        GcmRegistrationIdHelper.onGcmMessage(context, intent);
    }

    protected void onRegistered(Context context, String regId) {
        GcmRegistrationIdHelper.onGcmRegistered(context, regId);
    }

    protected void onUnregistered(Context context, String regId) {
        GcmRegistrationIdHelper.onGcmUnregistered(context, regId);
    }
}
