package com.google.android.gms.common.api;

import com.google.android.wallet.instrumentmanager.R;

public class CommonStatusCodes {
    public static String getStatusCodeString(int statusCode) {
        switch (statusCode) {
            case -1:
                return "SUCCESS_CACHE";
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
                return "ERROR_OPERATION_FAILED";
            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                return "INTERRUPTED";
            case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                return "TIMEOUT";
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                return "CANCELED";
            case 3000:
                return "AUTH_API_INVALID_CREDENTIALS";
            case 3001:
                return "AUTH_API_ACCESS_FORBIDDEN";
            case 3002:
                return "AUTH_API_CLIENT_ERROR";
            case 3003:
                return "AUTH_API_SERVER_ERROR";
            case 3004:
                return "AUTH_TOKEN_ERROR";
            case 3005:
                return "AUTH_URL_RESOLUTION";
            default:
                return "unknown status code: " + statusCode;
        }
    }
}
