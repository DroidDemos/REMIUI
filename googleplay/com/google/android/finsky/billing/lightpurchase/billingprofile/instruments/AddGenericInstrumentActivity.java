package com.google.android.finsky.billing.lightpurchase.billingprofile.instruments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.genericinstrument.GenericInstrumentHostFragment;
import com.google.android.finsky.billing.genericinstrument.GenericInstrumentHostFragment.Listener;
import com.google.android.finsky.protos.CommonDevice.GenericInstrument;
import com.google.android.finsky.utils.ParcelableProto;

public class AddGenericInstrumentActivity extends LoggingFragmentActivity implements Listener {
    public static Intent createIntent(String accountName, GenericInstrument genericInstrument) {
        Intent intent = new Intent(FinskyApp.get(), AddGenericInstrumentActivity.class);
        intent.putExtra("authAccount", accountName);
        intent.putExtra("AddGenericInstrumentActivity.generic_instrument", ParcelableProto.forProto(genericInstrument));
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(R.layout.billing_generic_instrument_activity, null));
        if (getSupportFragmentManager().findFragmentByTag("generic_instrument_host_fragment") == null) {
            Fragment fragment = GenericInstrumentHostFragment.newInstance(this.mAccountName, (GenericInstrument) ParcelableProto.getProtoFromIntent(getIntent(), "AddGenericInstrumentActivity.generic_instrument"));
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.generic_instrument_container, fragment, "generic_instrument_host_fragment");
            transaction.commit();
        }
    }

    public void onFinished(String instrumentId) {
        if (!TextUtils.isEmpty(instrumentId)) {
            Intent data = new Intent();
            data.putExtra("instrument_id", instrumentId);
            setResult(-1, data);
        }
        finish();
    }

    protected int getPlayStoreUiElementType() {
        return 1500;
    }
}
