package com.google.android.wallet.instrumentmanager.common.address;

import android.text.TextUtils;

public final class SourceUtils {
    public static boolean startsWithOrContainsWordPrefix(String value, CharSequence prefix) {
        if (TextUtils.isEmpty(value)) {
            return false;
        }
        if (TextUtils.isEmpty(prefix)) {
            return true;
        }
        return (" " + value.toLowerCase()).contains((" " + prefix).toLowerCase());
    }
}
