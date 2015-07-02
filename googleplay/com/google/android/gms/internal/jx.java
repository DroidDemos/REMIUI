package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

public class jx implements OnClickListener {
    private final Fragment VB;
    private final int VC;
    private final Activity mActivity;
    private final Intent mIntent;

    public jx(Activity activity, Intent intent, int i) {
        this.mActivity = activity;
        this.VB = null;
        this.mIntent = intent;
        this.VC = i;
    }

    public jx(Fragment fragment, Intent intent, int i) {
        this.mActivity = null;
        this.VB = fragment;
        this.mIntent = intent;
        this.VC = i;
    }

    public void onClick(DialogInterface dialog, int which) {
        try {
            if (this.mIntent != null && this.VB != null) {
                this.VB.startActivityForResult(this.mIntent, this.VC);
            } else if (this.mIntent != null) {
                this.mActivity.startActivityForResult(this.mIntent, this.VC);
            }
            dialog.dismiss();
        } catch (ActivityNotFoundException e) {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }
}
