package com.google.android.finsky.billing;

import android.accounts.Account;
import android.content.Intent;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.activities.SetupWizardAvailablePromoOfferActivity;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.protos.CheckPromoOffer.CheckPromoOfferResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;

public class CheckPromoOffersAction {
    private static boolean sRunning;
    private final Account mAccount;
    private final MainActivity mActivity;
    private Runnable mCallback;
    private final DfeApi mDfeApi;

    static {
        sRunning = false;
    }

    public CheckPromoOffersAction(MainActivity activity, DfeApi dfeApi) {
        this.mDfeApi = dfeApi;
        this.mAccount = dfeApi.getApiContext().getAccount();
        this.mActivity = activity;
    }

    public void run(Runnable callback) {
        this.mCallback = callback;
        if (!((Boolean) FinskyPreferences.checkPromoOffers.get(this.mAccount.name).get()).booleanValue() || sRunning) {
            this.mCallback.run();
            return;
        }
        sRunning = true;
        checkPromoOffers();
    }

    private void checkPromoOffers() {
        this.mDfeApi.checkPromoOffers(new Listener<CheckPromoOfferResponse>() {
            public void onResponse(CheckPromoOfferResponse checkPromoOfferResponse) {
                Intent intent = null;
                if (checkPromoOfferResponse.checkoutTokenRequired) {
                    FinskyLog.wtf("Unexpected checkout_token_required. Skipping CheckPromoOffersAction.", new Object[0]);
                } else if (checkPromoOfferResponse.availableOffer.length > 0) {
                    intent = SetupWizardAvailablePromoOfferActivity.createIntent(CheckPromoOffersAction.this.mAccount, checkPromoOfferResponse.availableOffer[0]);
                } else if (checkPromoOfferResponse.redeemedOffer != null) {
                }
                FinskyPreferences.checkPromoOffers.get(CheckPromoOffersAction.this.mAccount.name).remove();
                if (intent != null) {
                    CheckPromoOffersAction.this.mActivity.startActivity(intent);
                }
                CheckPromoOffersAction.sRunning = false;
                CheckPromoOffersAction.this.mCallback.run();
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                FinskyLog.e("Error while checking for offers: %s", volleyError);
                CheckPromoOffersAction.sRunning = false;
                CheckPromoOffersAction.this.mCallback.run();
            }
        });
    }
}
