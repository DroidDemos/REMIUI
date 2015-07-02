package com.google.android.finsky.billing.lightpurchase.billingprofile.instruments;

import android.content.Intent;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.protos.CommonDevice.CarrierBillingInstrumentStatus;

public class AddDcb3Activity extends AddDcb3BaseActivity {
    public static Intent createIntent(String accountName, CarrierBillingInstrumentStatus instrumentStatus) {
        Intent intent = new Intent(FinskyApp.get(), AddDcb3Activity.class);
        AddDcb3BaseActivity.putIntentExtras(accountName, instrumentStatus, intent);
        return intent;
    }

    protected int getPlayStoreUiElementType() {
        return 841;
    }
}
