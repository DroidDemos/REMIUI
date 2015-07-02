package com.google.android.finsky.billing.creditcard;

import android.accounts.Account;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.vending.billing.IBillingAccountService.Stub;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SetupWizardAvailablePromoOfferActivity;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.billing.promptforfop.SetupWizardPromptForFopActivity;
import com.google.android.finsky.protos.BuyInstruments.CheckInstrumentResponse;
import com.google.android.finsky.protos.CheckPromoOffer.CheckPromoOfferResponse;
import com.google.android.finsky.protos.Toc.TocResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.GetTocHelper;
import com.google.android.finsky.utils.GetTocHelper.Listener;
import com.google.android.finsky.utils.Utils;
import com.google.android.wallet.instrumentmanager.R;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BillingAccountService extends Service {
    public IBinder onBind(Intent intent) {
        return new Stub() {
            public int hasValidCreditCard(String accountName) throws RemoteException {
                Utils.ensureNotOnMainThread();
                int[] resultCode = new int[1];
                Semaphore semaphore = new Semaphore(0);
                Account account = AccountHandler.findAccount(accountName, BillingAccountService.this);
                if (account == null) {
                    FinskyLog.e("Received invalid account name: " + accountName, new Object[0]);
                    return -5;
                }
                try {
                    BillingAccountService.this.checkValidInstrument(FinskyApp.get().getDfeApi(account.name), resultCode, semaphore);
                    if (!semaphore.tryAcquire(45, TimeUnit.SECONDS)) {
                        resultCode[0] = -4;
                    }
                    return resultCode[0];
                } catch (InterruptedException e) {
                    FinskyLog.d("Timed out while waiting for response.", new Object[0]);
                    return -4;
                }
            }

            public Bundle getOffers(String accountName) {
                Utils.ensureNotOnMainThread();
                Account account = AccountHandler.findAccount(accountName, BillingAccountService.this);
                if (account == null) {
                    FinskyLog.e("Received invalid account name: %s", FinskyLog.scrubPii(accountName));
                    Bundle result = new Bundle();
                    result.putInt("result_code", -5);
                    return result;
                }
                try {
                    return BillingAccountService.this.checkPromoOffers(account);
                } catch (AuthFailureError e) {
                    try {
                        return BillingAccountService.this.checkPromoOffers(account);
                    } catch (AuthFailureError e2) {
                        result = new Bundle();
                        result.putInt("result_code", -3);
                        return result;
                    }
                }
            }
        };
    }

    private Bundle checkPromoOffers(Account account) throws AuthFailureError {
        int resultCode;
        Bundle result = new Bundle();
        DfeApi dfeApi = FinskyApp.get().getDfeApi(account.name);
        RequestFuture<CheckPromoOfferResponse> future = RequestFuture.newFuture();
        dfeApi.checkPromoOffers(future, future);
        try {
            CheckPromoOfferResponse checkPromoOfferResponse = (CheckPromoOfferResponse) future.get();
            if (checkPromoOfferResponse.checkoutTokenRequired) {
                FinskyLog.wtf("Unexpected checkout_token_required.", new Object[0]);
                resultCode = -3;
            } else if (checkPromoOfferResponse.hasAvailablePromoOfferStatus) {
                resultCode = setResultFromOfferStatus(checkPromoOfferResponse, account, result);
            } else {
                FinskyLog.wtf("No available promo offer status returned.", new Object[0]);
                resultCode = -1;
            }
        } catch (InterruptedException e) {
            FinskyLog.e("Interrupted while requesting /checkPromoOffers: %s", e.getMessage());
            resultCode = -4;
        } catch (ExecutionException e2) {
            FinskyLog.e("Error while requesting /checkPromoOffers: %s", e2.getCause());
            resultCode = convertErrorCode(error);
        }
        if (resultCode == 1) {
            resultCode = fetchToc(dfeApi, resultCode);
        }
        result.putInt("result_code", resultCode);
        logOfferResultCode(resultCode);
        FinskyLog.d("CheckPromoOffers result: %d", Integer.valueOf(resultCode));
        return result;
    }

    private int fetchToc(DfeApi dfeApi, int resultCode) {
        final int[] resultCodeWrapper = new int[]{resultCode};
        final Semaphore semaphore = new Semaphore(0);
        GetTocHelper.getToc(dfeApi, false, new Listener() {
            public void onResponse(TocResponse response) {
                semaphore.release();
            }

            public void onErrorResponse(VolleyError error) {
                FinskyLog.e("Error while loading toc: %s", error);
                resultCodeWrapper[0] = BillingAccountService.this.convertErrorCode(error);
                semaphore.release();
            }
        });
        try {
            if (!semaphore.tryAcquire(45, TimeUnit.SECONDS)) {
                resultCodeWrapper[0] = -4;
            }
        } catch (InterruptedException e) {
            resultCodeWrapper[0] = -4;
        }
        return resultCodeWrapper[0];
    }

    private int setResultFromOfferStatus(CheckPromoOfferResponse checkPromoOfferResponse, Account account, Bundle result) {
        switch (checkPromoOfferResponse.availablePromoOfferStatus) {
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                if (checkPromoOfferResponse.availableOffer.length == 0) {
                    FinskyLog.wtf("No offer returned while offer status is %d.", Integer.valueOf(checkPromoOfferResponse.availablePromoOfferStatus));
                    return -1;
                }
                result.putParcelable("available_offer_redemption_intent", SetupWizardAvailablePromoOfferActivity.createIntent(account, checkPromoOfferResponse.availableOffer[0]));
                return 1;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                result.putParcelable("available_offer_redemption_intent", SetupWizardPromptForFopActivity.createExternalSetupWizardIntent(account));
                return 1;
            default:
                if (checkPromoOfferResponse.redeemedOffer == null) {
                    return 3;
                }
                result.putString("redeemed_offer_message_html", checkPromoOfferResponse.redeemedOffer.descriptionHtml);
                return 2;
        }
    }

    private static void logOfferResultCode(int resultCode) {
    }

    private void checkValidInstrument(final DfeApi dfeApi, final int[] resultCodeOut, final Semaphore semaphore) {
        dfeApi.checkInstrument(new Response.Listener<CheckInstrumentResponse>() {
            public void onResponse(CheckInstrumentResponse response) {
                BillingAccountService.returnResult(dfeApi, response.userHasValidInstrument ? 1 : 2, resultCodeOut, semaphore);
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FinskyLog.w("Received error: %s", error);
                BillingAccountService.returnResult(dfeApi, BillingAccountService.this.convertErrorCode(error), resultCodeOut, semaphore);
            }
        });
    }

    private int convertErrorCode(Throwable error) {
        if (error instanceof ServerError) {
            return -1;
        }
        if (error instanceof NetworkError) {
            return -2;
        }
        if (error instanceof AuthFailureError) {
            return -3;
        }
        if (error instanceof TimeoutError) {
            return -4;
        }
        return 0;
    }

    private static void returnResult(DfeApi dfeApi, int result, int[] resultOut, Semaphore semaphore) {
        resultOut[0] = result;
        semaphore.release();
    }
}
