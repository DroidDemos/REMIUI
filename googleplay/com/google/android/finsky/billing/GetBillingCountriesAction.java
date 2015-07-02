package com.google.android.finsky.billing;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import com.google.android.finsky.utils.FinskyLog;

public class GetBillingCountriesAction {
    boolean enoughTimePassed(long now, long lastRefresh) {
        return now > ((Long) G.vendingBillingCountriesRefreshMs.get()).longValue() + lastRefresh;
    }

    private boolean canSkip() {
        return !enoughTimePassed(System.currentTimeMillis(), ((Long) BillingPreferences.LAST_BILLING_COUNTRIES_REFRESH_MILLIS.get()).longValue());
    }

    public void run(String account, final Runnable finishRunnable) {
        if (canSkip()) {
            if (finishRunnable != null) {
                finishRunnable.run();
            }
            FinskyLog.d("Skip getting fresh list of billing countries.", new Object[0]);
            return;
        }
        FinskyApp.get().getVendingApi(account).getBillingCountries(new Listener<Country[]>() {
            public void onResponse(Country[] response) {
                BillingLocator.setBillingCountries(response);
                FinskyLog.d("Received and stored %d billing countries.", Integer.valueOf(response.length));
                if (finishRunnable != null) {
                    finishRunnable.run();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FinskyLog.w("PurchaseMetadataRequest failed: %s", error);
                if (finishRunnable != null) {
                    finishRunnable.run();
                }
            }
        });
    }
}
