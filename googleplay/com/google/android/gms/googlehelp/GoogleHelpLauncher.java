package com.google.android.gms.googlehelp;

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class GoogleHelpLauncher {
    protected final Activity mActivity;

    public GoogleHelpLauncher(Activity activity) {
        this.mActivity = activity;
    }

    private boolean p(Intent intent) {
        return this.mActivity.getPackageManager().queryIntentActivities(intent, 0).size() > 0;
    }

    void c(int i, Intent intent) {
        Intent data = new Intent("android.intent.action.VIEW").setData(((GoogleHelp) intent.getParcelableExtra("EXTRA_GOOGLE_HELP")).getFallbackSupportUri());
        if (i == 7 || !p(data)) {
            GooglePlayServicesUtil.showErrorDialogFragment(i, this.mActivity, 0);
        } else {
            this.mActivity.startActivity(data);
        }
    }

    protected int isGooglePlayServicesAvailable() {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mActivity);
    }

    public void launch(Intent helpIntent) {
        o(helpIntent);
        int isGooglePlayServicesAvailable = isGooglePlayServicesAvailable();
        if (isGooglePlayServicesAvailable == 0) {
            this.mActivity.startActivityForResult(helpIntent, 123);
        } else {
            c(isGooglePlayServicesAvailable, helpIntent);
        }
    }

    void o(Intent intent) {
        if (!intent.getAction().equals("com.google.android.gms.googlehelp.HELP") || !intent.hasExtra("EXTRA_GOOGLE_HELP")) {
            throw new IllegalArgumentException("The intent you are trying to launch is not GoogleHelp intent! This class only supports GoogleHelp intents.");
        }
    }
}
