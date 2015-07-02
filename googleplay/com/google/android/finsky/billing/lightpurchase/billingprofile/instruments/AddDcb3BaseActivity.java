package com.google.android.finsky.billing.lightpurchase.billingprofile.instruments;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowFragment;
import com.google.android.finsky.billing.carrierbilling.flow.CreateDcb3Flow;
import com.google.android.finsky.protos.CommonDevice.CarrierBillingInstrumentStatus;
import com.google.android.finsky.utils.ParcelableProto;

public abstract class AddDcb3BaseActivity extends InstrumentActivity {
    protected abstract int getPlayStoreUiElementType();

    protected static void putIntentExtras(String accountName, CarrierBillingInstrumentStatus instrumentStatus, Intent intent) {
        intent.putExtra("authAccount", accountName);
        intent.putExtra("AddDcb3Instrument.instrument_status", ParcelableProto.forProto(instrumentStatus));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startOrResumeFlow(this.mSavedFlowState);
    }

    protected BillingFlow getBillingFlow() {
        return new CreateDcb3Flow(this, this, FinskyApp.get().getDfeApi(this.mAccountName), (CarrierBillingInstrumentStatus) ParcelableProto.getProtoFromIntent(getIntent(), "AddDcb3Instrument.instrument_status"), getBillingUiMode());
    }

    protected BillingFlowFragment getBillingFlowFragment() {
        return null;
    }
}
