package com.google.android.finsky.download.obb;

import com.google.android.wallet.instrumentmanager.R;

public class ObbState {
    public static String toString(int obbState) {
        switch (obbState) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return "DOWNLOAD_PENDING";
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return "DOWNLOADING";
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return "DOWNLOADED";
            case R.styleable.WalletImFormEditText_required /*4*/:
                return "NOT_ON_STORAGE";
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return "NOT_APPLICABLE";
            default:
                return Integer.toString(obbState);
        }
    }
}
