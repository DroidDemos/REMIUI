package com.google.android.finsky.services;

import android.accounts.Account;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.android.vending.licensing.ILicenseResultListener;
import com.android.vending.licensing.ILicensingService.Stub;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.protos.VendingProtos.CheckLicenseRequestProto;
import com.google.android.finsky.protos.VendingProtos.CheckLicenseResponseProto;
import com.google.android.finsky.utils.FinskyLog;
import java.util.List;

public class LicensingService extends Service {
    private final Stub mBinder;

    public LicensingService() {
        this.mBinder = new Stub() {
            public void checkLicense(long nonce, String packageName, ILicenseResultListener listener) {
                try {
                    PackageInfo packageInfo = LicensingService.this.getPackageManager().getPackageInfo(packageName, 0);
                    if (packageInfo.applicationInfo.uid != AnonymousClass1.getCallingUid()) {
                        LicensingService.notifyListener(listener, 259, null, null);
                        return;
                    }
                    int versionCode = packageInfo.versionCode;
                    AppStates appStates = FinskyApp.get().getAppStates();
                    appStates.blockingLoad();
                    AppState appState = appStates.getApp(packageName);
                    if (appState == null) {
                        FinskyLog.wtf("Unexpected null appState for %s", packageName);
                        LicensingService.notifyListener(listener, 258, null, null);
                        return;
                    }
                    if (appState.installerData != null) {
                        String accountName = appState.installerData.getAccountName();
                        if (!TextUtils.isEmpty(accountName)) {
                            checkLicense(nonce, packageName, listener, versionCode, accountName);
                            return;
                        }
                    }
                    Libraries libraries = FinskyApp.get().getLibraries();
                    libraries.blockingLoad();
                    List<Account> ownerAccounts = libraries.getAppOwners(packageName, appState.packageManagerState.certificateHashes);
                    long j;
                    String str;
                    ILicenseResultListener iLicenseResultListener;
                    int i;
                    if (ownerAccounts.isEmpty()) {
                        Account firstAccount = AccountHandler.getFirstAccount(LicensingService.this);
                        if (firstAccount != null) {
                            j = nonce;
                            str = packageName;
                            iLicenseResultListener = listener;
                            i = versionCode;
                            checkLicense(j, str, iLicenseResultListener, i, firstAccount.name);
                            return;
                        }
                        LicensingService.notifyListener(listener, 1, null, null);
                        return;
                    }
                    j = nonce;
                    str = packageName;
                    iLicenseResultListener = listener;
                    i = versionCode;
                    checkLicense(j, str, iLicenseResultListener, i, ((Account) ownerAccounts.get(0)).name);
                } catch (NameNotFoundException e) {
                    LicensingService.notifyListener(listener, 258, null, null);
                }
            }

            private void checkLicense(long nonce, String packageName, final ILicenseResultListener listener, int versionCode, String acountName) {
                CheckLicenseRequestProto licenseRequest = new CheckLicenseRequestProto();
                licenseRequest.packageName = packageName;
                licenseRequest.hasPackageName = true;
                licenseRequest.versionCode = versionCode;
                licenseRequest.hasVersionCode = true;
                licenseRequest.nonce = nonce;
                licenseRequest.hasNonce = true;
                FinskyApp.get().getVendingApi(acountName).checkLicense(licenseRequest, new Listener<CheckLicenseResponseProto>() {
                    public void onResponse(CheckLicenseResponseProto response) {
                        LicensingService.notifyListener(listener, response.responseCode, response.signedData, response.signature);
                    }
                }, new ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        LicensingService.notifyListener(listener, 257, null, null);
                    }
                });
            }
        };
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    private static void notifyListener(ILicenseResultListener listener, int responseCode, String signedData, String signature) {
        try {
            listener.verifyLicense(responseCode, signedData, signature);
        } catch (RemoteException e) {
            FinskyLog.e("Unable to send license information", new Object[0]);
        }
    }
}
