package com.google.android.finsky.billing;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.VendingProtos.BillingEventRequestProto;
import com.google.android.finsky.protos.VendingProtos.BillingEventResponseProto;
import com.google.android.finsky.utils.FinskyLog;

public class BillingEventRecorder {
    private static final ErrorListener LOGGING_ERROR_LISTENER;
    private static final Listener<BillingEventResponseProto> NOP_LISTENER;

    static {
        NOP_LISTENER = new Listener<BillingEventResponseProto>() {
            public void onResponse(BillingEventResponseProto response) {
            }
        };
        LOGGING_ERROR_LISTENER = new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FinskyLog.w("BillingEventRecorder got error: %s", error);
            }
        };
    }

    public static void enableBillingEventLogging(boolean enable) {
        G.logBillingEventsEnabled.override(Boolean.valueOf(enable));
    }

    public static void recordSuccess(String carrierId, int eventType, boolean resultSuccess) {
        if (((Boolean) G.logBillingEventsEnabled.get()).booleanValue()) {
            BillingEventRequestProto request = new BillingEventRequestProto();
            request.billingParametersId = carrierId;
            request.hasBillingParametersId = true;
            request.eventType = eventType;
            request.hasEventType = true;
            request.resultSuccess = resultSuccess;
            request.hasResultSuccess = true;
            FinskyApp.get().getVendingApi().recordBillingEvent(request, NOP_LISTENER, LOGGING_ERROR_LISTENER);
        }
    }

    public static void recordError(String carrierId, int eventType, String errorCode) {
        if (((Boolean) G.logBillingEventsEnabled.get()).booleanValue()) {
            BillingEventRequestProto request = new BillingEventRequestProto();
            request.billingParametersId = carrierId;
            request.hasBillingParametersId = true;
            request.eventType = eventType;
            request.hasEventType = true;
            request.resultSuccess = false;
            request.hasResultSuccess = true;
            request.clientMessage = errorCode;
            request.hasClientMessage = true;
            FinskyApp.get().getVendingApi().recordBillingEvent(request, NOP_LISTENER, LOGGING_ERROR_LISTENER);
        }
    }
}
