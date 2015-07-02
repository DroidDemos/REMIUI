package com.google.android.finsky.utils;

import android.content.Context;
import android.os.AsyncTask;
import com.google.android.finsky.config.G;
import com.google.android.gms.checkin.CheckinServiceClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

public class ConsistencyTokenHelper {

    public interface Listener {
        void onTokenReceived(String str);
    }

    public static void get(final Context context, final Listener listener) {
        Utils.executeMultiThreaded(new AsyncTask<Void, Void, String>() {
            protected String doInBackground(Void... voids) {
                return ConsistencyTokenHelper.getBlocking(context);
            }

            protected void onPostExecute(String checkinConsistencyToken) {
                listener.onTokenReceived(checkinConsistencyToken);
            }
        }, new Void[0]);
    }

    public static String getBlocking(Context context) {
        if (!((Boolean) G.consistencyTokenEnabled.get()).booleanValue()) {
            return null;
        }
        String str = null;
        try {
            return CheckinServiceClient.getDeviceDataVersionInfo(context);
        } catch (GooglePlayServicesRepairableException e) {
            FinskyLog.e("Unable to fetch token, GooglePlayServicesRepairableException: %s", e.getMessage());
            return str;
        } catch (GooglePlayServicesNotAvailableException e2) {
            FinskyLog.e("Unable to fetch token, GooglePlayServicesNotAvailableException: %s", e2.getMessage());
            return str;
        } catch (IOException e3) {
            FinskyLog.e("Unable to fetch token, IOException: %s", e3.getMessage());
            return str;
        }
    }
}
