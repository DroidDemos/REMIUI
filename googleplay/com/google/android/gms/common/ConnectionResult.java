package com.google.android.gms.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import com.google.android.gms.internal.kl;
import com.google.android.wallet.instrumentmanager.R;

public final class ConnectionResult {
    public static final ConnectionResult Rj;
    private final int Rk;
    private final PendingIntent mPendingIntent;

    static {
        Rj = new ConnectionResult(0, null);
    }

    public ConnectionResult(int statusCode, PendingIntent pendingIntent) {
        this.Rk = statusCode;
        this.mPendingIntent = pendingIntent;
    }

    private String hP() {
        switch (this.Rk) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return "SUCCESS";
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return "SERVICE_MISSING";
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return "SERVICE_DISABLED";
            case R.styleable.WalletImFormEditText_required /*4*/:
                return "SIGN_IN_REQUIRED";
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return "INVALID_ACCOUNT";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return "RESOLUTION_REQUIRED";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return "NETWORK_ERROR";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                return "INTERNAL_ERROR";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                return "SERVICE_INVALID";
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                return "DEVELOPER_ERROR";
            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                return "LICENSE_CHECK_FAILED";
            case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                return "CANCELED";
            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                return "TIMEOUT";
            case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                return "INTERRUPTED";
            default:
                return "unknown status code " + this.Rk;
        }
    }

    public int getErrorCode() {
        return this.Rk;
    }

    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }

    public boolean hasResolution() {
        return (this.Rk == 0 || this.mPendingIntent == null) ? false : true;
    }

    public boolean isSuccess() {
        return this.Rk == 0;
    }

    public void startResolutionForResult(Activity activity, int requestCode) throws SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), requestCode, null, 0, 0, 0);
        }
    }

    public String toString() {
        return kl.j(this).a("statusCode", hP()).a("resolution", this.mPendingIntent).toString();
    }
}
