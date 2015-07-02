package com.google.android.finsky.billing.lightpurchase;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.iab.InAppBillingUtils.ResponseCode;
import com.google.android.finsky.billing.iab.MarketBillingService;
import com.google.android.finsky.billing.iab.PendingNotificationsService;

public class IabV2Activity extends IabActivity {
    private long mRequestId;
    private ResponseCode mResponseCode;

    public IabV2Activity() {
        this.mResponseCode = ResponseCode.RESULT_ERROR;
    }

    public static Intent createIntent(Account account, PurchaseParams params, long requestId) {
        Intent intent = PurchaseActivity.createIntent(account, params, null, null);
        intent.putExtra("LightIabV2Activity.requestId", requestId);
        intent.setClass(FinskyApp.get(), IabV2Activity.class);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        this.mRequestId = getIntent().getLongExtra("LightIabV2Activity.requestId", -1);
        super.onCreate(savedInstanceState);
    }

    protected ResponseCode getResponseCodeForAlreadyOwned() {
        return ResponseCode.RESULT_DEVELOPER_ERROR;
    }

    protected void onFinish(PurchaseFragment purchaseFragment) {
        this.mResponseCode = getResponseCode(purchaseFragment);
        super.onFinish(purchaseFragment);
    }

    protected void onStop() {
        if (isFinishing()) {
            sendResponseToCaller();
        }
        super.onStop();
    }

    protected void handleAccessRestriction() {
        this.mResponseCode = ResponseCode.RESULT_USER_CANCELED;
        setResult(0);
        sendResponseToCaller();
    }

    private void sendResponseToCaller() {
        MarketBillingService.sendResponseCode(FinskyApp.get().getApplicationContext(), this.mParams.iabParameters.packageName, this.mRequestId, this.mResponseCode);
        if (this.mResponseCode == ResponseCode.RESULT_ERROR || this.mResponseCode == ResponseCode.RESULT_OK) {
            PendingNotificationsService.setMarketAlarm(FinskyApp.get(), this.mAccount.name, 120000);
        }
    }
}
