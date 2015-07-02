package com.google.android.gms.location.reporting;

import com.google.android.wallet.instrumentmanager.R;

public class OptInResult {
    public static int sanitize(int serviceValue) {
        switch (serviceValue) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                return serviceValue;
            default:
                return 1;
        }
    }
}
