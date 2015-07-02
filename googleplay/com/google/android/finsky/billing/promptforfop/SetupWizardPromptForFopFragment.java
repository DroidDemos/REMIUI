package com.google.android.finsky.billing.promptforfop;

import android.accounts.Account;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.creditcard.SetupWizardAddCreditCardActivity;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.SetupWizardAddDcb3Activity;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.SetupWizardRedeemCodeActivity;
import com.google.android.finsky.experiments.FinskyExperiments;
import com.google.android.finsky.layout.SetupWizardIconButtonGroup;
import com.google.android.finsky.protos.BillingProfileProtos.BillingProfileOption;
import com.google.android.finsky.protos.CommonDevice.CarrierBillingInstrumentStatus;
import com.google.android.finsky.protos.CommonDevice.TopupInfo;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.SetupWizardUtils;
import com.google.android.finsky.utils.SetupWizardUtils.SetupWizardParams;

public class SetupWizardPromptForFopFragment extends PromptForFopFragment {
    private SetupWizardParams mSetupWizardParams;

    public static Fragment newInstance(Account account, byte[] serverLogsCookie, SetupWizardParams params) {
        SetupWizardPromptForFopFragment result = new SetupWizardPromptForFopFragment();
        Bundle args = PromptForFopFragment.buildArgumentsBundle(account, serverLogsCookie);
        args.putParcelable("setup_wizard_params", params);
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSetupWizardParams = (SetupWizardParams) getArguments().getParcelable("setup_wizard_params");
    }

    protected int getMainLayout() {
        return R.layout.setup_wizard_prompt_for_fop_fragment;
    }

    protected void configureContinueButtonStyle(View button) {
        if (button instanceof SetupWizardIconButtonGroup) {
            SetupWizardIconButtonGroup iconButton = (SetupWizardIconButtonGroup) button;
            iconButton.setIconDrawable(getResources().getDrawable(R.drawable.purchase_wallet));
            iconButton.setText(getString(R.string.continue_text));
        }
    }

    protected int getPrimerStringId() {
        return R.string.setup_wizard_setup_account_primer;
    }

    protected int getActionEntryLayout() {
        return R.layout.setup_wizard_fop_entry;
    }

    protected int getPlayStoreUiElementType() {
        return 893;
    }

    protected int determineUiMode() {
        if (SetupWizardUtils.shouldUseMaterialTheme()) {
            return 6;
        }
        FinskyExperiments experiments = FinskyApp.get().getExperiments(this.mAccount.name);
        if (experiments.isEnabled("cl:billing.setupwizard_prompt_for_fop_ui_mode_radio_button")) {
            return 1;
        }
        if (experiments.isEnabled("cl:billing.setupwizard_prompt_for_fop_ui_mode_billing_profile")) {
            return 2;
        }
        if (experiments.isEnabled("cl:billing.setupwizard_prompt_for_fop_ui_mode_billing_profile_more_details")) {
            return 4;
        }
        FinskyLog.d("No UI mode specified, defaulting to UI_MODE_RADIO_BUTTONS", new Object[0]);
        return 1;
    }

    protected void addCreditCard() {
        startActivityForResult(SetupWizardAddCreditCardActivity.createIntent(this.mAccount.name, this.mSetupWizardParams), 1);
        SetupWizardUtils.animateSliding(getActivity(), false);
    }

    protected void redeemCheckoutCode() {
        startActivityForResult(SetupWizardRedeemCodeActivity.createIntent(this.mAccount.name, this.mSetupWizardParams), 4);
        SetupWizardUtils.animateSliding(getActivity(), false);
    }

    protected void addDcb3(CarrierBillingInstrumentStatus instrumentStatus) {
        startActivityForResult(SetupWizardAddDcb3Activity.createIntent(this.mAccount.name, instrumentStatus, this.mSetupWizardParams), 2);
        SetupWizardUtils.animateSliding(getActivity(), false);
    }

    protected ActionEntry getDcb2Action(BillingProfileOption option) {
        FinskyLog.d("Dcb entry not shown because the api version is dcb2.", new Object[0]);
        return null;
    }

    protected void topup(TopupInfo topupInfo) {
        FinskyLog.wtf("Top-up is not supported for Setup Wizard.", new Object[0]);
    }

    protected int getCreditCardEventType() {
        return 360;
    }

    protected int getDcbEventType() {
        return 361;
    }

    protected int getRedeemEventType() {
        return 362;
    }

    protected int getTopupEventType() {
        return 363;
    }

    protected boolean supportsGenericInstruments() {
        return false;
    }

    protected int getBillingProfileRequestEnum() {
        return 3;
    }
}
