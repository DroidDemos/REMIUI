package com.google.android.finsky.billing.iab;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.billing.iab.InAppBillingUtils.ResponseCode;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.SignatureUtils;
import java.util.List;

public class FirstPartyInAppBillingService extends Service {
    private final Stub mBinder;

    private class Stub extends com.android.vending.billing.IFirstPartyInAppBillingService.Stub {
        private Stub() {
        }

        public int isBillingSupported(String accountName, int apiVersion, String packageName, String type) {
            ResponseCode responseCode = FirstPartyInAppBillingService.this.checkAccountAndPackageName(accountName, packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return responseCode.responseCode();
            }
            return FirstPartyInAppBillingService.this.getManager(accountName).isBillingSupported(apiVersion, packageName, type);
        }

        public Bundle getSkuDetails(String accountName, int apiVersion, String packageName, String type, Bundle skusBundle) {
            ResponseCode responseCode = FirstPartyInAppBillingService.this.checkAccountAndPackageName(accountName, packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return FirstPartyInAppBillingService.this.createBundleResponse(responseCode);
            }
            return FirstPartyInAppBillingService.this.getManager(accountName).getSkuDetails(apiVersion, packageName, type, skusBundle);
        }

        public Bundle getBuyIntent(String accountName, int apiVersion, String packageName, String sku, String type, String developerPayload) {
            ResponseCode responseCode = FirstPartyInAppBillingService.this.checkAccountAndPackageName(accountName, packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return FirstPartyInAppBillingService.this.createBundleResponse(responseCode);
            }
            return FirstPartyInAppBillingService.this.getManager(accountName).getBuyIntent(apiVersion, packageName, sku, type, developerPayload);
        }

        public Bundle getPurchases(String accountName, int apiVersion, String packageName, String type, String continuationToken) {
            ResponseCode responseCode = FirstPartyInAppBillingService.this.checkAccountAndPackageName(accountName, packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return FirstPartyInAppBillingService.this.createBundleResponse(responseCode);
            }
            return FirstPartyInAppBillingService.this.getManager(accountName).getPurchases(apiVersion, packageName, type, continuationToken);
        }

        public int consumePurchase(String accountName, int apiVersion, String packageName, String purchaseToken) {
            ResponseCode responseCode = FirstPartyInAppBillingService.this.checkAccountAndPackageName(accountName, packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return responseCode.responseCode();
            }
            return FirstPartyInAppBillingService.this.getManager(accountName).consumePurchase(apiVersion, packageName, purchaseToken);
        }

        public int isPromoEligible(String accountName, int apiVersion, String packageName, String type) throws RemoteException {
            ResponseCode responseCode = FirstPartyInAppBillingService.this.checkAccountAndPackageName(accountName, packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return responseCode.responseCode();
            }
            return FirstPartyInAppBillingService.this.getManager(accountName).isPromoEligible(apiVersion, packageName, type);
        }

        public Bundle getBuyIntentToReplaceSkus(String accountName, int apiVersion, String packageName, List<String> oldSkus, String newSku, String type, String developerPayload) throws RemoteException {
            ResponseCode responseCode = FirstPartyInAppBillingService.this.checkAccountAndPackageName(accountName, packageName);
            if (responseCode != ResponseCode.RESULT_OK) {
                return FirstPartyInAppBillingService.this.createBundleResponse(responseCode);
            }
            return FirstPartyInAppBillingService.this.getManager(accountName).getBuyIntentToReplaceSkus(apiVersion, packageName, oldSkus, newSku, type, developerPayload);
        }
    }

    public FirstPartyInAppBillingService() {
        this.mBinder = new Stub();
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    private ResponseCode checkAccountAndPackageName(String accountName, String packageName) {
        if (!((Boolean) G.iabV3FirstPartyApiEnabled.get()).booleanValue()) {
            FinskyLog.w("This API is disabled.", new Object[0]);
            return ResponseCode.RESULT_ERROR;
        } else if (TextUtils.isEmpty(accountName)) {
            FinskyLog.w("Input Error: Non empty/null argument expected for accountName.", new Object[0]);
            return ResponseCode.RESULT_DEVELOPER_ERROR;
        } else if (AccountHandler.hasAccount(accountName, this)) {
            ResponseCode responseCode = InAppBillingUtils.validatePackageName(packageName, getPackageManager(), Binder.getCallingUid());
            if (responseCode != ResponseCode.RESULT_OK) {
                return responseCode;
            }
            if (((Boolean) G.iabV3FirstPartyApiUnrestrictedAccess.get()).booleanValue() || SignatureUtils.isFirstPartyPackage(this, packageName)) {
                return ResponseCode.RESULT_OK;
            }
            FinskyLog.w("The calling package is not authorized to use this API: %s", packageName);
            return ResponseCode.RESULT_DEVELOPER_ERROR;
        } else {
            FinskyLog.w("Unable to locate specified accountName: %s", FinskyLog.scrubPii(accountName));
            return ResponseCode.RESULT_DEVELOPER_ERROR;
        }
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
