package com.google.android.finsky.billing.lightpurchase;

import com.google.android.finsky.billing.iab.InAppBillingUtils.ResponseCode;
import com.google.android.finsky.billing.lightpurchase.PurchaseFragment.PurchaseError;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.wallet.instrumentmanager.R;

public abstract class IabActivity extends PurchaseActivity {
    protected abstract ResponseCode getResponseCodeForAlreadyOwned();

    protected ResponseCode getResponseCodeFromError(PurchaseError error) {
        if (error.errorType == 3) {
            switch (error.errorSubtype) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    return ResponseCode.RESULT_ERROR;
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    FinskyLog.w("Unexpected INSTALL_OK response.", new Object[0]);
                    return ResponseCode.RESULT_OK;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    return getResponseCodeForAlreadyOwned();
                case R.styleable.WalletImFormEditText_required /*4*/:
                    return ResponseCode.RESULT_ITEM_UNAVAILABLE;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    return ResponseCode.RESULT_DEVELOPER_ERROR;
                default:
                    FinskyLog.wtf("Unexpected PurchasePermissionResponse: %d", Integer.valueOf(error.errorSubtype));
                    break;
            }
        } else if (error.errorType == 1) {
            return getResponseCodeForAlreadyOwned();
        } else {
            if (error.errorType == 2) {
                return ResponseCode.RESULT_SERVICE_UNAVAILABLE;
            }
        }
        return ResponseCode.RESULT_ERROR;
    }

    protected ResponseCode getResponseCode(PurchaseFragment purchaseFragment) {
        if (purchaseFragment.hasSucceeded()) {
            return ResponseCode.RESULT_OK;
        }
        if (purchaseFragment.hasFailed()) {
            return getResponseCodeFromError(purchaseFragment.getError());
        }
        return ResponseCode.RESULT_USER_CANCELED;
    }
}
