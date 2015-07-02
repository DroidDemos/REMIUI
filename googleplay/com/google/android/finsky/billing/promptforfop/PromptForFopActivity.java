package com.google.android.finsky.billing.promptforfop;

import android.accounts.Account;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.promptforfop.PromptForFopMessageFragment.Listener;

public class PromptForFopActivity extends PromptForFopBaseActivity implements Listener {
    public static Intent createIntent(Account account, byte[] serverLogsCookie) {
        Intent intent = new Intent(FinskyApp.get(), PromptForFopActivity.class);
        PromptForFopBaseActivity.putIntentExtras(account, serverLogsCookie, intent);
        return intent;
    }

    protected int getActivityLayout() {
        return R.layout.prompt_for_fop_activity;
    }

    protected Fragment createContentFragment() {
        return PromptForFopFragment.newInstance(this.mAccount, this.mServerLogsCookie);
    }

    protected int getPlayStoreUiElementType() {
        return 1001;
    }

    protected void displayMessage(int messageId, int logType, String redeemedOfferHtml) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, PromptForFopMessageFragment.newInstance(this.mAccount.name, getString(messageId), logType), "PromptForFopBaseActivity.fragment").commit();
    }

    protected int getSnoozeEventType() {
        return 354;
    }

    protected int getBillingProfileErrorEventType() {
        return 356;
    }

    protected int getAlreadySetupEventType() {
        return 355;
    }

    public void onContinueClicked() {
        setResult(-1);
        finish();
    }
}
