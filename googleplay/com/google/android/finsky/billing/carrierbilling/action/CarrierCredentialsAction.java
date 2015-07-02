package com.google.android.finsky.billing.carrierbilling.action;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.billing.BillingEventRecorder;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.api.DcbApi;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingCredentials;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.utils.FinskyLog;

public class CarrierCredentialsAction {
    private final CarrierBillingStorage mDcbStorage;

    public CarrierCredentialsAction() {
        this(BillingLocator.getCarrierBillingStorage());
    }

    public CarrierCredentialsAction(CarrierBillingStorage dcbStorage) {
        this.mDcbStorage = dcbStorage;
    }

    public void run(String provisioningId, String password, final Runnable successRunnable, final Runnable errorRunnable) {
        DcbApi dcbApi = BillingLocator.createDcbApi();
        CarrierBillingParameters params = this.mDcbStorage.getParams();
        if (params == null) {
            errorRunnable.run();
            return;
        }
        final String carrierId = params.getId();
        dcbApi.getCredentials(provisioningId, password, new Listener<CarrierBillingCredentials>() {
            public void onResponse(CarrierBillingCredentials credentials) {
                if (credentials != null) {
                    CarrierCredentialsAction.this.mDcbStorage.setCredentials(credentials);
                    BillingEventRecorder.recordSuccess(carrierId, 1, CarrierBillingUtils.areCredentialsValid(CarrierCredentialsAction.this.mDcbStorage));
                } else {
                    FinskyLog.d("Fetching credentials returned null.", new Object[0]);
                    BillingEventRecorder.recordError(carrierId, 1, "SERVER");
                }
                if (successRunnable != null) {
                    successRunnable.run();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FinskyLog.d("Fetching credentials returned an error: %s", error);
                String legacyMessage = DfeUtils.getLegacyErrorCode(error);
                String exceptionMessage = error.getMessage();
                String exceptionType = error.getClass().getCanonicalName();
                BillingEventRecorder.recordError(carrierId, 1, String.format("%s/%s/%s", new Object[]{legacyMessage, exceptionMessage, exceptionType}));
                if (errorRunnable != null) {
                    errorRunnable.run();
                }
            }
        });
    }
}
