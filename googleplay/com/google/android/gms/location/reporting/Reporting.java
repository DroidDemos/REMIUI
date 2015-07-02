package com.google.android.gms.location.reporting;

import com.google.android.wallet.instrumentmanager.R;

public interface Reporting {

    public static final class Setting {
        public static boolean isOn(int setting) {
            return setting > 0;
        }

        public static int sanitize(int setting) {
            switch (setting) {
                case -2:
                case -1:
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    return setting;
                default:
                    return isOn(setting) ? 99 : -3;
            }
        }
    }
}
