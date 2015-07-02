package com.google.android.finsky.utils;

import android.accounts.Account;
import android.text.TextUtils;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.protos.Buy.BuyResponse;
import com.google.android.finsky.protos.Buy.PurchaseNotificationResponse;
import com.google.android.finsky.protos.Buy.PurchaseStatusResponse;

public class PurchaseInitiator {

    public interface SuccessListener {
        void onFreePurchaseSuccess(Account account, Document document);
    }

    public static void makeFreePurchase(Account account, Document doc, int offerType, String continueUrl, SuccessListener successListener, boolean initiateAppDownload, boolean showErrors) {
        FinskyApp.get().getEventLogger(account).logPurchaseBackgroundEvent(300, doc.getDocId(), offerType, null, 0, null, -1, -1);
        FinskyApp.get().getDfeApi(account.name).makePurchase(doc, offerType, null, createFreePurchaseListener(account, doc, offerType, continueUrl, successListener, initiateAppDownload, showErrors), createFreePurchaseErrorListener(account, doc, offerType, showErrors));
    }

    private static Listener<BuyResponse> createFreePurchaseListener(Account account, Document doc, int offerType, String continueUrl, SuccessListener successListener, boolean initiateAppDownload, boolean showErrors) {
        final Account account2 = account;
        final Document document = doc;
        final int i = offerType;
        final String str = continueUrl;
        final boolean z = initiateAppDownload;
        final SuccessListener successListener2 = successListener;
        final boolean z2 = showErrors;
        return new Listener<BuyResponse>() {
            public void onResponse(BuyResponse buyResponse) {
                FinskyEventLog eventLog = FinskyApp.get().getEventLogger(account2);
                if (buyResponse.purchaseResponse != null) {
                    PurchaseNotificationResponse purchaseResponse = buyResponse.purchaseResponse;
                    if (purchaseResponse.status != 0) {
                        eventLog.logPurchaseBackgroundEvent(301, document.getDocId(), i, null, purchaseResponse.status, null, -1, -1);
                        if (z2) {
                            String error = FinskyApp.get().getString(R.string.error);
                            String errorMessage = purchaseResponse.localizedErrorMessage;
                            FinskyApp.get().getNotifier().showPurchaseErrorMessage(error, errorMessage, errorMessage, document.getDocId(), document.getDetailsUrl());
                            return;
                        }
                        return;
                    } else if (buyResponse.purchaseStatusResponse != null) {
                        eventLog.logPurchaseBackgroundEvent(301, document.getDocId(), i, null, 0, buyResponse.serverLogsCookie, -1, -1);
                        PurchaseInitiator.processPurchaseStatusResponse(account2, document, str, buyResponse.purchaseStatusResponse, z, "free_purchase", buyResponse.encodedDeliveryToken);
                        if (successListener2 != null) {
                            successListener2.onFreePurchaseSuccess(account2, document);
                            return;
                        }
                        return;
                    } else {
                        FinskyLog.w("Expected PurchaseStatusResponse.", new Object[0]);
                        return;
                    }
                }
                FinskyLog.w("Expected PurchaseResponse.", new Object[0]);
            }
        };
    }

    private static void processPurchaseStatusResponse(Account account, Document doc, String continueUrl, PurchaseStatusResponse response, boolean initiateAppDownload, String debugEventName, String deliveryToken) {
        final PurchaseStatusResponse purchaseStatusResponse = response;
        final boolean z = initiateAppDownload;
        final Document document = doc;
        final String str = continueUrl;
        final String str2 = deliveryToken;
        final Account account2 = account;
        Runnable postLibraryUpdateCallback = new Runnable() {
            public void run() {
                if (purchaseStatusResponse.status == 1 && z && document.getDocumentType() == 1) {
                    FinskyApp.get().getAppStates().getInstallerDataStore().setContinueUrl(document.getBackendDocId(), str);
                    if (purchaseStatusResponse.appDeliveryData != null) {
                        if (!TextUtils.isEmpty(str2)) {
                            FinskyApp.get().getInstaller().setDeliveryToken(document.getDocId(), str2);
                        }
                        PurchaseInitiator.initiateDownload(account2, document);
                        return;
                    }
                    FinskyLog.w("missing delivery data for %s", document.getDocId());
                }
            }
        };
        FinskyApp.get().getLibraryReplicators().applyLibraryUpdates(account, debugEventName, postLibraryUpdateCallback, response.libraryUpdate);
    }

    public static void initiateDownload(Account account, Document doc) {
        if (doc.getAppDetails() == null) {
            FinskyLog.wtf("Document does not contain AppDetails, cannot download: %s", doc.getDocId());
        }
        FinskyApp.get().getInstaller().requestInstall(doc.getAppDetails().packageName, doc.getAppDetails().versionCode, account.name, doc.getTitle(), false, "single_install", 2);
    }

    private static ErrorListener createFreePurchaseErrorListener(final Account account, final Document doc, final int offerType, final boolean showErrors) {
        return new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FinskyApp.get().getEventLogger(account).logPurchaseBackgroundEvent(301, doc.getDocId(), offerType, error.getClass().getSimpleName(), 0, null, -1, -1);
                if (showErrors) {
                    String title = FinskyApp.get().getString(R.string.error);
                    String errorMessage = ErrorStrings.get(FinskyApp.get(), error);
                    FinskyApp.get().getNotifier().showPurchaseErrorMessage(title, errorMessage, errorMessage, doc.getDocId(), doc.getDetailsUrl());
                }
            }
        };
    }
}
