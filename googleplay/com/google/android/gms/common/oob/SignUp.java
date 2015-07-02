package com.google.android.gms.common.oob;

import android.content.Intent;
import com.google.android.gms.internal.kb;

public class SignUp {
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES;

    static {
        GOOGLE_PLUS_REQUIRED_FEATURES = kb.GOOGLE_PLUS_REQUIRED_FEATURES;
    }

    public static Intent newSignUpIntent(String accountName, String backButtonName, String iconPackage, String promoText) {
        Intent intent = new Intent();
        intent.setPackage("com.google.android.gms");
        intent.setAction("com.google.android.gms.common.oob.OOB_SIGN_UP");
        intent.putExtra("com.google.android.gms.common.oob.EXTRA_ACCOUNT_NAME", accountName);
        intent.putExtra("com.google.android.gms.common.oob.EXTRA_BACK_BUTTON_NAME", backButtonName);
        intent.putExtra("com.google.android.gms.common.oob.EXTRAS_PROMO_APP_PACKAGE", iconPackage);
        intent.putExtra("com.google.android.gms.common.oob.EXTRAS_PROMO_APP_TEXT", promoText);
        return intent;
    }
}
