package com.google.android.finsky.billing.lightpurchase;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.iab.InAppBillingUtils.ResponseCode;

public class IabV3Activity extends IabActivity {
    public static Intent createIntent(Account account, PurchaseParams params) {
        Intent intent = PurchaseActivity.createIntent(account, params, null, null);
        intent.setClass(FinskyApp.get(), IabV3Activity.class);
        return intent;
    }

    protected ResponseCode getResponseCodeForAlreadyOwned() {
        return ResponseCode.RESULT_ITEM_ALREADY_OWNED;
    }

    protected void onFinish(PurchaseFragment purchaseFragment) {
        Intent data = new Intent();
        ResponseCode responseCode = getResponseCode(purchaseFragment);
        Bundle extraPurchaseData = purchaseFragment.getExtraPurchaseData();
        if (extraPurchaseData != null) {
            String purchaseData = extraPurchaseData.getString("inapp_signed_purchase_data");
            String signature = extraPurchaseData.getString("inapp_purchase_data_signature");
            if (!(purchaseData == null || signature == null)) {
                data.putExtra("INAPP_PURCHASE_DATA", purchaseData);
                data.putExtra("INAPP_DATA_SIGNATURE", signature);
            }
        }
        data.putExtra("RESPONSE_CODE", responseCode.ordinal());
        setResult(responseCode == ResponseCode.RESULT_OK ? -1 : 0, data);
    }

    protected void handleAccessRestriction() {
        setResult(0, new Intent().putExtra("RESPONSE_CODE", ResponseCode.RESULT_USER_CANCELED.ordinal()));
    }
}
