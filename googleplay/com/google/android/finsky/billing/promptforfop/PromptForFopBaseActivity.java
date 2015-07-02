package com.google.android.finsky.billing.promptforfop;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.billing.PromptForFopHelper;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.LoggingFragmentActivity;
import com.google.android.finsky.billing.promptforfop.PromptForFopFragment.Listener;

public abstract class PromptForFopBaseActivity extends LoggingFragmentActivity implements Listener {
    protected Account mAccount;
    protected FinskyEventLog mEventLog;
    protected byte[] mServerLogsCookie;

    protected abstract Fragment createContentFragment();

    protected abstract void displayMessage(int i, int i2, String str);

    protected abstract int getActivityLayout();

    protected abstract int getAlreadySetupEventType();

    protected abstract int getBillingProfileErrorEventType();

    protected abstract int getSnoozeEventType();

    protected static void putIntentExtras(Account account, byte[] serverLogsCookie, Intent intent) {
        intent.putExtra("PromptForFopBaseActivity.account", account);
        intent.putExtra("authAccount", account.name);
        intent.putExtra("PromptForFopBaseActivity.server_logs_cookie", serverLogsCookie);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());
        this.mAccount = (Account) getIntent().getParcelableExtra("PromptForFopBaseActivity.account");
        this.mServerLogsCookie = getIntent().getByteArrayExtra("PromptForFopBaseActivity.server_logs_cookie");
        this.mEventLog = FinskyApp.get().getEventLogger(this.mAccount);
        if (savedInstanceState == null) {
            PromptForFopHelper.recordFopSelectorImpression(this.mAccount.name);
        }
    }

    protected void onResume() {
        super.onResume();
        if (getSupportFragmentManager().findFragmentByTag("PromptForFopBaseActivity.fragment") == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.content_frame, createContentFragment(), "PromptForFopBaseActivity.fragment").commit();
        }
    }

    public void onInstrumentCreated(String redeemedOfferHtml) {
        PromptForFopHelper.recordFopAdded(this.mAccount.name);
        displayMessage(R.string.setup_account_success, 1005, redeemedOfferHtml);
    }

    public void onNoneSelected() {
        this.mEventLog.logBackgroundEvent(getSnoozeEventType(), this.mServerLogsCookie);
        PromptForFopHelper.snooze(this.mAccount.name);
        setResult(-1);
        finish();
    }

    public void onFatalError(String errorMessageHtml) {
        this.mEventLog.logBackgroundEvent(getBillingProfileErrorEventType(), this.mServerLogsCookie);
        displayMessage(R.string.setup_account_fatal_error, 1004, null);
    }

    public void onAlreadySetup() {
        this.mEventLog.logBackgroundEvent(getAlreadySetupEventType(), this.mServerLogsCookie);
        PromptForFopHelper.expireHasNoFop(this.mAccount.name);
        displayMessage(R.string.setup_account_success, 1003, null);
    }
}
