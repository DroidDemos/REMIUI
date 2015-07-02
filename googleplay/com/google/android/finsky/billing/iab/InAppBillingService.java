package com.google.android.finsky.billing.iab;

import android.accounts.Account;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.iab.InAppBillingUtils.ResponseCode;
import java.util.List;

public class InAppBillingService extends Service {
    private final Stub mBinder;

    private class Stub extends com.android.vending.billing.IInAppBillingService.Stub {
        private Stub() {
        }

        public int isBillingSupported(int apiVersion, String packageName, String type) {
            ResponseCode responseCode = InAppBillingService.this.validatePackageName(packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return responseCode.responseCode();
            }
            String accountName = InAppBillingService.this.getAccountName(packageName);
            if (accountName == null) {
                return ResponseCode.RESULT_BILLING_UNAVAILABLE.responseCode();
            }
            return InAppBillingService.this.getManager(accountName).isBillingSupported(apiVersion, packageName, type);
        }

        public Bundle getSkuDetails(int apiVersion, String packageName, String type, Bundle skusBundle) {
            ResponseCode responseCode = InAppBillingService.this.validatePackageName(packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return InAppBillingService.this.createBundleResponse(responseCode);
            }
            String accountName = InAppBillingService.this.getAccountName(packageName);
            if (accountName == null) {
                return InAppBillingService.this.createBundleResponse(ResponseCode.RESULT_BILLING_UNAVAILABLE);
            }
            return InAppBillingService.this.getManager(accountName).getSkuDetails(apiVersion, packageName, type, skusBundle);
        }

        public Bundle getBuyIntent(int apiVersion, String packageName, String sku, String type, String developerPayload) {
            ResponseCode responseCode = InAppBillingService.this.validatePackageName(packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return InAppBillingService.this.createBundleResponse(responseCode);
            }
            String accountName = InAppBillingService.this.getAccountName(packageName);
            if (accountName == null) {
                return InAppBillingService.this.createBundleResponse(ResponseCode.RESULT_BILLING_UNAVAILABLE);
            }
            return InAppBillingService.this.getManager(accountName).getBuyIntent(apiVersion, packageName, sku, type, developerPayload);
        }

        public Bundle getPurchases(int apiVersion, String packageName, String type, String continuationToken) {
            ResponseCode responseCode = InAppBillingService.this.validatePackageName(packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return InAppBillingService.this.createBundleResponse(responseCode);
            }
            String accountName = InAppBillingService.this.getAccountName(packageName);
            if (accountName == null) {
                return InAppBillingService.this.createBundleResponse(ResponseCode.RESULT_BILLING_UNAVAILABLE);
            }
            return InAppBillingService.this.getManager(accountName).getPurchases(apiVersion, packageName, type, continuationToken);
        }

        public int consumePurchase(int apiVersion, String packageName, String purchaseToken) {
            ResponseCode responseCode = InAppBillingService.this.validatePackageName(packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return responseCode.responseCode();
            }
            String accountName = InAppBillingService.this.getAccountName(packageName);
            if (accountName == null) {
                return ResponseCode.RESULT_BILLING_UNAVAILABLE.responseCode();
            }
            return InAppBillingService.this.getManager(accountName).consumePurchase(apiVersion, packageName, purchaseToken);
        }

        public int isPromoEligible(int apiVersion, String packageName, String type) throws RemoteException {
            ResponseCode responseCode = InAppBillingService.this.validatePackageName(packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return responseCode.responseCode();
            }
            String accountName = InAppBillingService.this.getAccountName(packageName);
            if (accountName == null) {
                return ResponseCode.RESULT_BILLING_UNAVAILABLE.responseCode();
            }
            return InAppBillingService.this.getManager(accountName).isPromoEligible(apiVersion, packageName, type);
        }

        public Bundle getBuyIntentToReplaceSkus(int apiVersion, String packageName, List<String> oldSkus, String newSku, String type, String developerPayload) throws RemoteException {
            ResponseCode responseCode = InAppBillingService.this.validatePackageName(packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return InAppBillingService.this.createBundleResponse(responseCode);
            }
            String accountName = InAppBillingService.this.getAccountName(packageName);
            if (accountName == null) {
                return InAppBillingService.this.createBundleResponse(ResponseCode.RESULT_BILLING_UNAVAILABLE);
            }
            return InAppBillingService.this.getManager(accountName).getBuyIntentToReplaceSkus(apiVersion, packageName, oldSkus, newSku, type, developerPayload);
        }
    }

    public InAppBillingService() {
        this.mBinder = new Stub();
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    private ResponseCode validatePackageName(String packageName) {
        return InAppBillingUtils.validatePackageName(packageName, getPackageManager(), Binder.getCallingUid());
    }

    private String getAccountName(String packageName) {
        Account account = InAppBillingUtils.getPreferredAccount(packageName, this);
        return account == null ? null : account.name;
    }

    private InAppBillingManager getManager(String accountName) {
        return new InAppBillingManager(this, FinskyApp.get().getLibraries(), FinskyApp.get().getLibraryReplicators(), FinskyApp.get().getDfeApi(accountName));
    }

    private Bundle createBundleResponse(ResponseCode responseCode) {
        Bundle response = new Bundle();
        response.putInt("RESPONSE_CODE", responseCode.responseCode());
        return response;
    }
}
