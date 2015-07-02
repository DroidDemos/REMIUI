package com.google.android.finsky.billing.lightpurchase.billingprofile.instruments;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowFragment;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.flow.CreateCarrierBillingFlow;

public class AddDcb2Activity extends InstrumentActivity {
    public static Intent createIntent(String accountName) {
        Intent intent = new Intent(FinskyApp.get(), AddDcb2Activity.class);
        intent.putExtra("authAccount", accountName);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CarrierBillingUtils.initializeCarrierBillingStorage(new Runnable() {
            public void run() {
                CarrierBillingUtils.initializeCarrierBillingParams(AddDcb2Activity.this, true, new Runnable() {
                    public void run() {
                        AddDcb2Activity.this.startOrResumeFlow(AddDcb2Activity.this.mSavedFlowState);
                    }
                });
            }
        });
    }

    protected BillingFlow getBillingFlow() {
        return new CreateCarrierBillingFlow(this, this, FinskyApp.get().getDfeApi(this.mAccountName));
    }

    protected BillingFlowFragment getBillingFlowFragment() {
        return null;
    }

    protected int getPlayStoreUiElementType() {
        return 840;
    }
}
