package com.google.android.finsky.billing.lightpurchase.billingprofile.instruments;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowFragment;
import com.google.android.finsky.billing.creditcard.AddCreditCardFlowFragment;

public class AddCreditCardActivity extends InstrumentActivity {
    private String mAccountName;

    public static Intent createIntent(String accountName) {
        Intent intent = new Intent(FinskyApp.get(), AddCreditCardActivity.class);
        intent.putExtra("authAccount", accountName);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAccountName = getIntent().getStringExtra("authAccount");
        startOrResumeFlow(this.mSavedFlowState);
    }

    protected BillingFlow getBillingFlow() {
        return null;
    }

    protected BillingFlowFragment getBillingFlowFragment() {
        return AddCreditCardFlowFragment.newInstance(this.mAccountName, null, 0, true);
    }

    protected int getPlayStoreUiElementType() {
        return 860;
    }
}
