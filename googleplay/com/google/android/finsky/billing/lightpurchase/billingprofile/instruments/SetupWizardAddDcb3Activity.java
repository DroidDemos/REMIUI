package com.google.android.finsky.billing.lightpurchase.billingprofile.instruments;

import android.content.Intent;
import android.os.Bundle;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.protos.CommonDevice.CarrierBillingInstrumentStatus;
import com.google.android.finsky.utils.SetupWizardUtils;
import com.google.android.finsky.utils.SetupWizardUtils.SetupWizardParams;

public class SetupWizardAddDcb3Activity extends AddDcb3BaseActivity {
    public static Intent createIntent(String accountName, CarrierBillingInstrumentStatus instrumentStatus, SetupWizardParams setupWizardParams) {
        Intent intent = new Intent(FinskyApp.get(), SetupWizardAddDcb3Activity.class);
        AddDcb3BaseActivity.putIntentExtras(accountName, instrumentStatus, intent);
        intent.putExtra("InstrumentActivity.setup_wizard_params", setupWizardParams);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        SetupWizardParams params = (SetupWizardParams) getIntent().getParcelableExtra("InstrumentActivity.setup_wizard_params");
        setTheme(params.isLightTheme() ? R.style.SetupWizardTheme.Light : R.style.SetupWizardTheme);
        super.onCreate(savedInstanceState);
        SetupWizardUtils.configureBasicUi(this, params, 0, true, true, true);
    }

    public void finish() {
        super.finish();
        SetupWizardUtils.animateSliding(this, true);
    }

    protected int getActivityLayout() {
        return R.layout.setup_wizard_play_frame;
    }

    protected int getBillingUiMode() {
        return 1;
    }

    public void showProgress(int messageId) {
        findViewById(R.id.divider).setVisibility(4);
        findViewById(R.id.progress).setVisibility(0);
    }

    public void hideProgress() {
        findViewById(R.id.divider).setVisibility(0);
        findViewById(R.id.progress).setVisibility(4);
    }

    protected int getPlayStoreUiElementType() {
        return 894;
    }
}
