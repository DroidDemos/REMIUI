package com.google.android.wallet.instrumentmanager.common.util;

import android.os.Bundle;
import android.text.TextUtils;

public final class ErrorUtils {
    public static Bundle addErrorDetailsToBundle(Bundle bundle, int errorType, String errorTitle, String errorMessage, String errorCode, String buttonText) {
        if (TextUtils.isEmpty(errorTitle) || TextUtils.isEmpty(errorMessage) || TextUtils.isEmpty(buttonText)) {
            throw new IllegalArgumentException(String.format("Error title, message, and button text are required. Received values: title: %s  message: %s  buttonText: %s", new Object[]{errorTitle, errorMessage, buttonText}));
        }
        bundle.putInt("ErrorUtils.KEY_TYPE", errorType);
        bundle.putString("ErrorUtils.KEY_TITLE", errorTitle);
        bundle.putString("ErrorUtils.KEY_ERROR_MESSAGE", errorMessage);
        if (!TextUtils.isEmpty(errorCode)) {
            bundle.putString("ErrorUtils.KEY_ERROR_CODE", errorCode);
        }
        bundle.putString("ErrorUtils.KEY_ERROR_BUTTON_TEXT", buttonText);
        return bundle;
    }
}
