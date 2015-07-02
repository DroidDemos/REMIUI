package com.google.android.finsky.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.config.G;
import com.google.android.finsky.receivers.ExpireLaunchUrlReceiver;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.RestrictedDeviceHelper;
import com.google.android.finsky.utils.RestrictedDeviceHelper.OnCompleteListener;
import com.google.android.finsky.utils.Utils;

public class LaunchUrlHandlerActivity extends Activity {
    private static final Uri BASE_DETAILS_URI;

    static {
        BASE_DETAILS_URI = Uri.parse("http://market.android.com/details");
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RestrictedDeviceHelper.isLimitedOrSchoolEduUser(new OnCompleteListener() {
            public void onComplete(boolean isLimitedOrSchoolEduUser) {
                if (isLimitedOrSchoolEduUser) {
                    AccessRestrictedActivity.showLimitedUserUI(LaunchUrlHandlerActivity.this);
                    LaunchUrlHandlerActivity.this.finish();
                    return;
                }
                LaunchUrlHandlerActivity.this.continueOnCreate();
            }
        });
    }

    private void continueOnCreate() {
        final Intent homeIntent = getGoToMarketHomeIntent(this);
        if (((Boolean) G.launchUrlsEnabled.get()).booleanValue()) {
            final AppStates appStates = FinskyApp.get().getAppStates();
            appStates.load(new Runnable() {
                public void run() {
                    Intent launchIntent = homeIntent;
                    try {
                        launchIntent = LaunchUrlHandlerActivity.handleUrl(LaunchUrlHandlerActivity.this, LaunchUrlHandlerActivity.this.getIntent(), appStates, FinskyApp.get().getEventLogger());
                    } catch (Exception e) {
                        FinskyLog.wtf(e, "Error while processing launch URL", new Object[0]);
                    } finally {
                        LaunchUrlHandlerActivity.this.startActivity(launchIntent);
                        LaunchUrlHandlerActivity.this.finish();
                    }
                }
            });
            return;
        }
        startActivity(homeIntent);
        finish();
    }

    protected static Intent handleUrl(Context context, Intent input, AppStates appStates, FinskyEventLog eventLogger) {
        Uri uri = input.getData();
        String encodedContinuation = uri.getQueryParameter("url");
        if (TextUtils.isEmpty(encodedContinuation)) {
            FinskyLog.e("Launch URL without continue URL", new Object[0]);
            String str = "details";
            Intent intent = input;
            intent.setData(uri.buildUpon().scheme("http").authority("market.android.com").path(r17).build());
            return IntentUtils.createForwardToMainActivityIntent(context, input);
        }
        String packageName = uri.getQueryParameter("id");
        if (TextUtils.isEmpty(packageName)) {
            FinskyLog.e("Launch URL without package name", new Object[0]);
            return getGoToMarketHomeIntent(context);
        }
        String version = uri.getQueryParameter("min_version");
        int versionCode = -1;
        if (!TextUtils.isEmpty(version)) {
            try {
                versionCode = Integer.parseInt(version);
            } catch (NumberFormatException e) {
            }
        }
        int localVersion = -1;
        AppState appState = appStates.getApp(packageName);
        if (!(appState == null || appState.packageManagerState == null)) {
            localVersion = appState.packageManagerState.installedVersion;
        }
        boolean newEnough = localVersion >= 0 && localVersion >= versionCode;
        String decodedUrl = Utils.urlDecode(encodedContinuation);
        boolean canResolveUrl = IntentUtils.canResolveUrl(context.getPackageManager(), packageName, decodedUrl);
        eventLogger.logDeepLinkEvent(decodedUrl, packageName, versionCode, newEnough, canResolveUrl);
        if (newEnough && canResolveUrl) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setData(Uri.parse(decodedUrl));
            if (appState == null || appState.installerData == null) {
                return intent2;
            }
            ExpireLaunchUrlReceiver.cancel(context, packageName);
            appStates.getInstallerDataStore().setContinueUrl(packageName, null);
            return intent2;
        }
        String externalReferrer = uri.getQueryParameter("referrer");
        Builder detailsUri = BASE_DETAILS_URI.buildUpon();
        detailsUri.appendQueryParameter("id", packageName);
        detailsUri.appendQueryParameter("url", decodedUrl);
        if (!TextUtils.isEmpty(externalReferrer)) {
            detailsUri.appendQueryParameter("referrer", externalReferrer);
        }
        Intent detailsIntent = new Intent("android.intent.action.VIEW");
        detailsIntent.setData(detailsUri.build());
        return IntentUtils.createForwardToMainActivityIntent(context, detailsIntent);
    }

    private static Intent getGoToMarketHomeIntent(Context context) {
        return context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
    }
}
