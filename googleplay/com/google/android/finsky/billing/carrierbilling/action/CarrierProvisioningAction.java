package com.google.android.finsky.billing.carrierbilling.action;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.billing.BillingEventRecorder;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingPreferences;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.api.DcbApi;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;

public class CarrierProvisioningAction {
    private final DcbApi mDcbApi;
    private final CarrierBillingStorage mDcbStorage;

    public CarrierProvisioningAction() {
        this(BillingLocator.getCarrierBillingStorage(), BillingLocator.createDcbApi());
    }

    public CarrierProvisioningAction(CarrierBillingStorage storage, DcbApi dcbApi) {
        this.mDcbStorage = storage;
        this.mDcbApi = dcbApi;
    }

    public void runIfNotOnWifi(Context context) {
        if (shouldRunIfNotOnWifi(context)) {
            run(null);
        }
    }

    public boolean shouldRunIfNotOnWifi(Context context) {
        NetworkInfo ni = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
        if (ni == null || ni.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }

    public void run(Runnable finishRunnable) {
        if (shouldFetchProvisioning(this.mDcbStorage)) {
            fetchProvisioning((String) BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.get(), finishRunnable, finishRunnable);
            return;
        }
        if (finishRunnable != null) {
            finishRunnable.run();
        }
        FinskyLog.d("No need to fetch provisioning from carrier.", new Object[0]);
    }

    public void forceRun(Runnable successRunnable, Runnable errorRunnable) {
        fetchProvisioning(null, successRunnable, errorRunnable);
    }

    public void forceRun(Runnable successRunnable, Runnable errorRunnable, String acceptedTosVersion) {
        fetchProvisioning(acceptedTosVersion, successRunnable, errorRunnable);
    }

    private void fetchProvisioning(String acceptedTosVersion, Listener<CarrierBillingProvisioning> listener, ErrorListener errorListener) {
        this.mDcbApi.getProvisioning(acceptedTosVersion, listener, errorListener);
    }

    private void fetchProvisioning(String acceptedTosVersion, final Runnable successRunnable, final Runnable errorRunnable) {
        CarrierBillingParameters params = this.mDcbStorage.getParams();
        if (params != null) {
            final String carrierId = params.getId();
            fetchProvisioning(acceptedTosVersion, new Listener<CarrierBillingProvisioning>() {
                public void onResponse(CarrierBillingProvisioning provisioning) {
                    long now = System.currentTimeMillis();
                    if (provisioning == null) {
                        FinskyLog.w("Fetching provisioning returned null.", new Object[0]);
                        BillingPreferences.EARLIEST_PROVISIONING_CHECK_TIME_MILLIS.put(Long.valueOf(now + ((Long) G.vendingCarrierProvisioningRetryMs.get()).longValue()));
                        BillingEventRecorder.recordError(carrierId, 0, "SERVER");
                    } else {
                        BillingPreferences.EARLIEST_PROVISIONING_CHECK_TIME_MILLIS.put(Long.valueOf(now + ((Long) G.vendingCarrierProvisioningRefreshFrequencyMs.get()).longValue()));
                        CarrierProvisioningAction.this.mDcbStorage.setProvisioning(provisioning);
                        BillingEventRecorder.recordSuccess(carrierId, 0, provisioning.isProvisioned());
                    }
                    if (successRunnable != null) {
                        successRunnable.run();
                    }
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    FinskyLog.d("CarrierProvisioningAction encountered an error: %s", error);
                    String legacyMessage = DfeUtils.getLegacyErrorCode(error);
                    String exceptionMessage = error.getMessage();
                    String exceptionType = error.getClass().getCanonicalName();
                    String message = String.format("%s/%s/%s", new Object[]{legacyMessage, exceptionMessage, exceptionType});
                    BillingPreferences.EARLIEST_PROVISIONING_CHECK_TIME_MILLIS.put(Long.valueOf(System.currentTimeMillis() + ((Long) G.vendingCarrierProvisioningRetryMs.get()).longValue()));
                    BillingEventRecorder.recordError(carrierId, 0, message);
                    if (errorRunnable != null) {
                        errorRunnable.run();
                    }
                }
            });
            updateBillingPreferences(System.currentTimeMillis());
        } else if (errorRunnable != null) {
            errorRunnable.run();
        }
    }

    public static boolean shouldFetchProvisioning(CarrierBillingStorage carrierBillingStorage) {
        return shouldFetchProvisioning(carrierBillingStorage, System.currentTimeMillis(), SystemClock.elapsedRealtime(), ((Long) BillingPreferences.LAST_PROVISIONING_TIME_MILLIS.get()).longValue(), ((Long) BillingPreferences.EARLIEST_PROVISIONING_CHECK_TIME_MILLIS.get()).longValue());
    }

    static boolean shouldFetchProvisioning(CarrierBillingStorage carrierBillingStorage, long now, long awakeTime, long lastCheck, long earliestCheck) {
        CarrierBillingParameters params = carrierBillingStorage.getParams();
        if (params == null || params.getGetProvisioningUrl() == null) {
            FinskyLog.d("Required CarrierBillingParams missing. Shouldn't fetch provisioning.", new Object[0]);
            return false;
        }
        boolean hasBootedSinceLastCheck = now - lastCheck > awakeTime;
        if (now > earliestCheck) {
            return true;
        }
        if (CarrierBillingUtils.isProvisioned(carrierBillingStorage) || !hasBootedSinceLastCheck) {
            return false;
        }
        return true;
    }

    private void updateBillingPreferences(long now) {
        BillingPreferences.EARLIEST_PROVISIONING_CHECK_TIME_MILLIS.put(Long.valueOf(now + Math.min(((Long) G.vendingCarrierProvisioningRefreshFrequencyMs.get()).longValue(), ((Long) G.vendingCarrierProvisioningRetryMs.get()).longValue())));
        BillingPreferences.LAST_PROVISIONING_TIME_MILLIS.put(Long.valueOf(now));
    }
}
