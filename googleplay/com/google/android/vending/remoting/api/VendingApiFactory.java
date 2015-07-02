package com.google.android.vending.remoting.api;

import android.accounts.Account;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.android.volley.RequestQueue;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.utils.Maps;
import java.util.Locale;
import java.util.Map;

public class VendingApiFactory {
    private Context mContext;
    private RequestQueue mQueue;
    private final Map<String, VendingApi> mVendingApiMap;

    public VendingApiFactory(Context context, RequestQueue queue) {
        this.mVendingApiMap = Maps.newHashMap();
        this.mContext = context;
        this.mQueue = queue;
    }

    public VendingApi getApi(String account) {
        VendingApi api;
        synchronized (this.mVendingApiMap) {
            api = (VendingApi) this.mVendingApiMap.get(account);
            if (api == null) {
                api = new VendingApi(this.mQueue, getApiContext(new Account(account, "com.google")));
                this.mVendingApiMap.put(account, api);
            }
        }
        return api;
    }

    private VendingApiContext getApiContext(Account account) {
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
            return new VendingApiContext(this.mContext, account, Locale.getDefault(), Long.toHexString(((Long) DfeApiConfig.androidId.get()).longValue()), pm.getPackageInfo(this.mContext.getPackageName(), 0).versionCode, telephonyManager.getNetworkOperatorName(), telephonyManager.getSimOperatorName(), telephonyManager.getNetworkOperator(), telephonyManager.getSimOperator(), Build.DEVICE, VERSION.SDK, (String) DfeApiConfig.clientId.get(), (String) DfeApiConfig.loggingId.get());
        } catch (Throwable e) {
            throw new RuntimeException("Can't find our own package", e);
        }
    }
}
