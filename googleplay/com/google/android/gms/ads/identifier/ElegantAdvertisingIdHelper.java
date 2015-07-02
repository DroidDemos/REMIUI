package com.google.android.gms.ads.identifier;

import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings.Secure;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;

public class ElegantAdvertisingIdHelper implements AdIdProvider {
    private static String sCachedAdId;
    private static Boolean sIsLimitAdTrackingEnabled;
    private static String sPublicAndroidId;
    private final ContentResolver mContentResolver;
    private final Context mContext;

    static {
        sCachedAdId = null;
        sPublicAndroidId = null;
        sIsLimitAdTrackingEnabled = null;
    }

    public ElegantAdvertisingIdHelper(ContentResolver contentResolver, Context context) {
        this.mContentResolver = contentResolver;
        this.mContext = context;
        sPublicAndroidId = Secure.getString(contentResolver, "android_id");
        refreshCachedData();
    }

    public void refreshCachedData() {
        new AsyncTask<Void, Object, Info>() {
            protected Info doInBackground(Void... objects) {
                try {
                    return AdvertisingIdClient.getAdvertisingIdInfo(ElegantAdvertisingIdHelper.this.mContext);
                } catch (Exception exception) {
                    FinskyLog.d("Wasn't able to fetch the adId: %s", exception.getClass().getSimpleName());
                    return null;
                }
            }

            protected void onPostExecute(Info result) {
                if (result == null) {
                    FinskyLog.d("AdId result returned null.", new Object[0]);
                    return;
                }
                synchronized (ElegantAdvertisingIdHelper.this) {
                    ElegantAdvertisingIdHelper.sCachedAdId = result.getId();
                    ElegantAdvertisingIdHelper.sIsLimitAdTrackingEnabled = Boolean.valueOf(result.isLimitAdTrackingEnabled());
                }
            }
        }.execute(new Void[0]);
    }

    public synchronized String getAdId() {
        return sCachedAdId;
    }

    public synchronized Boolean isLimitAdTrackingEnabled() {
        return sIsLimitAdTrackingEnabled;
    }

    public String getPublicAndroidId() {
        return sPublicAndroidId;
    }
}
