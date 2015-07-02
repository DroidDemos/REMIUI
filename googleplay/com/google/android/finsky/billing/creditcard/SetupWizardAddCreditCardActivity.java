package com.google.android.finsky.billing.creditcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.billing.BillingFlowFragment;
import com.google.android.finsky.billing.BillingFlowFragment.BillingFlowHost;
import com.google.android.finsky.billing.PromptForFopHelper;
import com.google.android.finsky.billing.lightpurchase.billingprofile.instruments.LoggingFragmentActivity;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.SetupWizardUtils;
import com.google.android.finsky.utils.SetupWizardUtils.SetupWizardParams;

public class SetupWizardAddCreditCardActivity extends LoggingFragmentActivity implements Listener, BillingFlowHost {
    private String mAccountName;
    protected ViewGroup mFragmentContainer;
    protected View mMainView;
    private boolean mNeedsHideProgress;
    private boolean mSaveInstanceStateCalled;
    private SetupWizardParams mSetupWizardParams;
    protected TextView mTitleView;

    public static Intent createIntent(String accountName, SetupWizardParams params) {
        Intent intent = new Intent(FinskyApp.get(), SetupWizardAddCreditCardActivity.class);
        intent.putExtra("authAccount", accountName);
        intent.putExtra("setup_wizard_params", params);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        this.mAccountName = getIntent().getStringExtra("authAccount");
        if (AccountHandler.hasAccount(this.mAccountName, this)) {
            this.mSetupWizardParams = (SetupWizardParams) intent.getParcelableExtra("setup_wizard_params");
            setTheme(this.mSetupWizardParams.isLightTheme() ? R.style.SetupWizardTheme.Light : R.style.SetupWizardTheme);
            super.onCreate(savedInstanceState);
            this.mMainView = View.inflate(this, R.layout.setup_wizard_play_frame, null);
            setContentView(this.mMainView);
            this.mFragmentContainer = (ViewGroup) findViewById(R.id.content_frame);
            this.mTitleView = (TextView) this.mMainView.findViewById(R.id.title);
            this.mTitleView.setText(R.string.add_credit_card);
            SetupWizardUtils.configureBasicUi(this, this.mSetupWizardParams, 0, true, true, true);
            startOrResumeFlow();
            return;
        }
        FinskyLog.e("Invalid account supplied in the intent: %s", FinskyLog.scrubPii(this.mAccountName));
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onResume() {
        super.onResume();
        this.mSaveInstanceStateCalled = false;
        if (this.mNeedsHideProgress) {
            hideProgress();
        }
    }

    private void startOrResumeFlow() {
        if (((AddCreditCardFlowFragment) getSupportFragmentManager().findFragmentByTag("billing_flow_fragment")) == null) {
            getSupportFragmentManager().beginTransaction().add(getContainerId(), AddCreditCardFlowFragment.newInstance(this.mAccountName, this.mSetupWizardParams.getCardholderName(), 1, this.mSetupWizardParams.isLightTheme()), "billing_flow_fragment").commit();
            this.mFragmentContainer.setVisibility(0);
            findViewById(R.id.loading_indicator).setVisibility(8);
        } else if (FinskyLog.DEBUG) {
            FinskyLog.v("Re-attached to billing flow fragment.", new Object[0]);
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        this.mSaveInstanceStateCalled = true;
        super.onSaveInstanceState(outState);
    }

    public void onFinished(boolean canceled, Bundle flowResult) {
        Intent result = new Intent();
        if (!(canceled || flowResult == null || !flowResult.containsKey("instrument_id"))) {
            result.putExtra("instrument_id", flowResult.getString("instrument_id"));
        }
        if (!(canceled || flowResult == null || !flowResult.containsKey("redeemed_offer_message_html"))) {
            String redeemedOfferHtml = flowResult.getString("redeemed_offer_message_html");
            result.putExtra("redeemed_offer_message_html", redeemedOfferHtml);
            if (!this.mSetupWizardParams.hasSetupCompleteScreen()) {
                Builder builder = new Builder();
                builder.setMessageHtml(redeemedOfferHtml).setPositiveId(R.string.ok).setEventLog(1220, null, 1221, -1, AccountHandler.findAccount(this.mAccountName, this)).setCanceledOnTouchOutside(false);
                Bundle args = new Bundle();
                args.putParcelable("result_intent", result);
                builder.setCallback(null, 1, args);
                builder.build().show(getSupportFragmentManager(), "redeemed_promo_offer");
                return;
            }
        }
        if (!canceled) {
            PromptForFopHelper.expireHasNoFop(this.mAccountName);
        }
        setResult(-1, result);
        finish();
    }

    public void finish() {
        super.finish();
        SetupWizardUtils.animateSliding(this, true);
    }

    public int getContainerId() {
        return this.mFragmentContainer.getId();
    }

    public void setHostTitle(int resId) {
        FinskyLog.d("Swallowing title: resId=%d", Integer.valueOf(resId));
    }

    public void showProgress(int messageId) {
        if (!this.mSaveInstanceStateCalled) {
            findViewById(R.id.divider).setVisibility(4);
            findViewById(R.id.progress).setVisibility(0);
        }
    }

    public void hideProgress() {
        this.mNeedsHideProgress = false;
        findViewById(R.id.divider).setVisibility(0);
        findViewById(R.id.progress).setVisibility(4);
    }

    public void onFlowFinished(BillingFlowFragment flow, Bundle result) {
        onFinished(false, result);
    }

    public void onFlowCanceled(BillingFlowFragment flow) {
        onFinished(true, null);
    }

    public void onFlowError(BillingFlowFragment flow, String error) {
        setResult(-1, new Intent());
        finish();
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 1) {
            setResult(-1, (Intent) extraArguments.getParcelable("result_intent"));
            finish();
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
    }

    protected int getPlayStoreUiElementType() {
        return 891;
    }
}
