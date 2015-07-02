package com.google.android.finsky.billing.lightpurchase.billingprofile.instruments;

import android.content.Intent;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.protos.Common.Docid;

public class RedeemCodeActivity extends RedeemCodeBaseActivity {
    public static Intent createIntent(String accountName, int redemptionContext) {
        return createIntent(accountName, redemptionContext, null, null);
    }

    public static Intent createIntent(String accountName, int redemptionContext, String prefillCode, String partnerPayload) {
        Intent intent = new Intent(FinskyApp.get(), RedeemCodeActivity.class);
        RedeemCodeBaseActivity.putIntentExtras(intent, accountName, redemptionContext, 3, null, 0, prefillCode, partnerPayload);
        return intent;
    }

    public static Intent createBuyFlowIntent(String accountName, int backend, Docid docid, int offerType) {
        Intent intent = new Intent(FinskyApp.get(), RedeemCodeActivity.class);
        RedeemCodeBaseActivity.putIntentExtras(intent, accountName, 1, backend, docid, offerType, null, null);
        return intent;
    }

    protected int getActivityLayout() {
        return R.layout.redeem_activity;
    }

    protected int getBillingUiMode() {
        return 0;
    }

    protected void handleMessageAndFinish(String redeemedOfferHtml) {
        finish();
    }

    protected int getPlayStoreUiElementType() {
        return 880;
    }
}
