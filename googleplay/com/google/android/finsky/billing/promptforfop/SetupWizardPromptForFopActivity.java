package com.google.android.finsky.billing.promptforfop;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.SetupWizardUtils;
import com.google.android.finsky.utils.SetupWizardUtils.SetupWizardParams;

public class SetupWizardPromptForFopActivity extends PromptForFopBaseActivity {
    private SetupWizardParams mSetupWizardParams;

    public static Intent createIntent(Account account, SetupWizardParams params) {
        Intent intent = createExternalSetupWizardIntent(account);
        intent.putExtra("setup_wizard_params", params);
        return intent;
    }

    public static Intent createExternalSetupWizardIntent(Account account) {
        Intent intent = new Intent(FinskyApp.get(), SetupWizardPromptForFopActivity.class);
        PromptForFopBaseActivity.putIntentExtras(account, null, intent);
        intent.putExtra("via_create_intent", true);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (!intent.getBooleanExtra("via_create_intent", false) && "com.android.vending.billing.ADD_CREDIT_CARD".equals(intent.getAction())) {
            if (intent.hasExtra("authAccount")) {
                Account account = AccountHandler.findAccount(intent.getStringExtra("authAccount"), this);
                if (account == null) {
                    FinskyLog.e("Cannot find the account: %s", accountName);
                    setResult(-1);
                    finish();
                    return;
                }
                PromptForFopBaseActivity.putIntentExtras(account, null, intent);
            } else {
                FinskyLog.e("No account name passed.", new Object[0]);
                setResult(-1);
                finish();
                return;
            }
        }
        this.mSetupWizardParams = (SetupWizardParams) intent.getParcelableExtra("setup_wizard_params");
        if (this.mSetupWizardParams == null) {
            this.mSetupWizardParams = new SetupWizardParams(intent);
        }
        setTheme(this.mSetupWizardParams.isLightTheme() ? R.style.SetupWizardTheme.Light : R.style.SetupWizardTheme);
        super.onCreate(savedInstanceState);
        ((TextView) findViewById(R.id.title)).setText(R.string.setup_wizard_play_add_fop_title);
        SetupWizardUtils.configureBasicUi(this, this.mSetupWizardParams, 0, false, false, false);
    }

    protected int getActivityLayout() {
        return R.layout.setup_wizard_play_frame;
    }

    protected Fragment createContentFragment() {
        return SetupWizardPromptForFopFragment.newInstance(this.mAccount, this.mServerLogsCookie, this.mSetupWizardParams);
    }

    protected int getPlayStoreUiElementType() {
        return 892;
    }

    protected void displayMessage(int messageId, int logType, String redeemedOfferHtml) {
        Intent result = new Intent();
        if (redeemedOfferHtml != null) {
            result.putExtra("redeemed_offer_message_html", redeemedOfferHtml);
        }
        setResult(-1, result);
        finish();
    }

    protected int getSnoozeEventType() {
        return 364;
    }

    protected int getBillingProfileErrorEventType() {
        return 366;
    }

    protected int getAlreadySetupEventType() {
        return 365;
    }

    public void finish() {
        super.finish();
        SetupWizardUtils.animateSliding(this, false);
    }
}
