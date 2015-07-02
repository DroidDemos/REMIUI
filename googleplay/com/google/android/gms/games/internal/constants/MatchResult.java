package com.google.android.gms.games.internal.constants;

import com.google.android.wallet.instrumentmanager.R;

public final class MatchResult {
    public static boolean isValid(int result) {
        switch (result) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return true;
            default:
                return false;
        }
    }
}
