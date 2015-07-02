package com.google.android.gms.maps.internal;

import com.google.android.wallet.instrumentmanager.R;

public final class a {
    public static Boolean a(byte b) {
        switch (b) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return Boolean.FALSE;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return Boolean.TRUE;
            default:
                return null;
        }
    }

    public static byte c(Boolean bool) {
        return bool != null ? bool.booleanValue() ? (byte) 1 : (byte) 0 : (byte) -1;
    }
}
