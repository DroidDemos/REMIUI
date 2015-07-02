package com.google.android.finsky.activities;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class GaiaRecoveryHelper {
    private static String sCurrentAccountName;
    private static PendingIntent sGaiaAuthIntent;

    static {
        sGaiaAuthIntent = null;
        sCurrentAccountName = null;
    }

    public static void prefetchAndCacheGaiaAuthRecoveryIntent(Context context, String currentAccount) {
        if (((Boolean) G.enableGaiaRecovery.get()).booleanValue()) {
            if (sGaiaAuthIntent != null) {
                if (!TextUtils.equals(sCurrentAccountName, currentAccount)) {
                    sCurrentAccountName = null;
                    sGaiaAuthIntent = null;
                } else {
                    return;
                }
            }
            sCurrentAccountName = currentAccount;
            if (sCurrentAccountName == null) {
                FinskyLog.d("Skipping fetching recovery intent -- no user set.", new Object[0]);
                return;
            }
            new AsyncTask<Context, Void, PendingIntent>() {
                protected PendingIntent doInBackground(Context... arg0) {
                    PendingIntent recoveryIntent = null;
                    try {
                        Context context = arg0[0];
                        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) != 0) {
                            FinskyLog.e("GooglePlayServices is not available.", new Object[0]);
                            return null;
                        }
                        recoveryIntent = GoogleAuthUtil.getRecoveryIfSuggested(context, GaiaRecoveryHelper.sCurrentAccountName, null, true);
                        return recoveryIntent;
                    } catch (Exception e) {
                    }
                }

                protected void onPostExecute(PendingIntent result) {
                    GaiaRecoveryHelper.sGaiaAuthIntent = result;
                }
            }.execute(new Context[]{context});
            return;
        }
        FinskyLog.d("Skipping fetching recovery intent -- gaia recovery disabled", new Object[0]);
    }

    public static void launchGaiaRecoveryDialog(Resources res, FragmentManager fragmentManager, int requestCodeForResponse, String tagToUse) {
        if (shouldShowGaiaRecoveryDialog()) {
            String message = res.getString(R.string.gaia_recovery_dialog_text, new Object[]{res.getString(R.string.continue_text)});
            Builder builder = new Builder();
            builder.setMessage(message).setPositiveId(R.string.continue_text).setCanceledOnTouchOutside(false).setEventLog(307, null, 247, 248, null).setCallback(null, requestCodeForResponse, null);
            builder.build().show(fragmentManager, tagToUse);
        }
    }

    public static boolean shouldShowGaiaRecoveryDialog() {
        return getRecoveryIntentIfExists() != null;
    }

    public static void onPositiveGaiaRecoveryDialogResponse() {
        PendingIntent intent = getRecoveryIntentIfExists();
        if (intent == null) {
            FinskyLog.wtf("Called Gaia recovery flow but PendingIntent didn't exist.", new Object[0]);
            return;
        }
        try {
            intent.send();
        } catch (CanceledException e) {
            FinskyLog.e(e, "PendingIntent for GAIA auth was canceled", new Object[0]);
        } finally {
            sGaiaAuthIntent = null;
        }
    }

    private static PendingIntent getRecoveryIntentIfExists() {
        return TextUtils.equals(FinskyApp.get().getCurrentAccountName(), sCurrentAccountName) ? sGaiaAuthIntent : null;
    }
}
