package com.google.android.finsky.billing.lightpurchase.billingprofile.instruments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.utils.SetupWizardUtils;
import com.google.android.finsky.utils.SetupWizardUtils.SetupWizardParams;

public class SetupWizardRedeemCodeActivity extends RedeemCodeBaseActivity implements Listener {
    private SetupWizardParams mSetupWizardParam;
    private TextView mTitleView;

    public static Intent createIntent(String accountName, SetupWizardParams params) {
        Intent intent = new Intent(FinskyApp.get(), SetupWizardRedeemCodeActivity.class);
        RedeemCodeBaseActivity.putIntentExtras(intent, accountName, 4, -1, null, 0, null, null);
        intent.putExtra("setup_wizard_params", params);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        this.mSetupWizardParam = (SetupWizardParams) getIntent().getParcelableExtra("setup_wizard_params");
        setTheme(this.mSetupWizardParam.isLightTheme() ? R.style.SetupWizardTheme.Light : R.style.SetupWizardTheme);
        super.onCreate(savedInstanceState);
        this.mTitleView = (TextView) findViewById(R.id.title);
        SetupWizardUtils.configureBasicUi(this, this.mSetupWizardParam, 0, true, true, true);
    }

    protected int getActivityLayout() {
        return R.layout.setup_wizard_play_frame;
    }

    protected int getBillingUiMode() {
        return 1;
    }

    public void setTitle(CharSequence title) {
        this.mTitleView.setText(title);
    }

    protected void handleMessageAndFinish(String redeemedOfferHtml) {
        if (this.mSetupWizardParam.hasSetupCompleteScreen() || TextUtils.isEmpty(redeemedOfferHtml)) {
            finish();
            return;
        }
        Builder builder = new Builder();
        builder.setMessageHtml(redeemedOfferHtml).setPositiveId(R.string.ok).setEventLog(1220, null, 1221, -1, AccountHandler.findAccount(this.mAccountName, this)).setCanceledOnTouchOutside(false).setCallback(null, 1, null);
        builder.build().show(getSupportFragmentManager(), "redeemed_promo_offer");
    }

    public void finish() {
        super.finish();
        SetupWizardUtils.animateSliding(this, true);
    }

    protected int getPlayStoreUiElementType() {
        return 898;
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 1) {
            finish();
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
    }
}
