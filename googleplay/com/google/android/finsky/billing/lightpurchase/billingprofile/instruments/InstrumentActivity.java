package com.google.android.finsky.billing.lightpurchase.billingprofile.instruments;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowFragment;
import com.google.android.finsky.billing.BillingFlowFragment.BillingFlowHost;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.ProgressDialogFragment;
import com.google.android.finsky.billing.PromptForFopHelper;
import com.google.android.finsky.setup.SetupWizardNavBar;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.SetupWizardUtils;
import com.google.android.finsky.utils.SetupWizardUtils.SetupWizardParams;

public abstract class InstrumentActivity extends LoggingFragmentActivity implements Listener, BillingFlowContext, BillingFlowHost, BillingFlowListener {
    protected ViewGroup mFragmentContainer;
    protected View mMainView;
    private boolean mNeedsHideProgress;
    private ProgressDialogFragment mProgressDialog;
    private BillingFlow mRunningFlow;
    private BillingFlowFragment mRunningFlowFragment;
    private boolean mSaveInstanceStateCalled;
    protected Bundle mSavedFlowState;
    private SetupWizardNavBar mSetupWizardNavBar;
    protected TextView mTitleView;

    protected abstract BillingFlow getBillingFlow();

    protected abstract BillingFlowFragment getBillingFlowFragment();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mMainView = View.inflate(this, getActivityLayout(), null);
        this.mTitleView = (TextView) this.mMainView.findViewById(R.id.title);
        setContentView(this.mMainView);
        this.mFragmentContainer = (ViewGroup) findViewById(R.id.content_frame);
        this.mSetupWizardNavBar = getBillingUiMode() == 1 ? SetupWizardUtils.getNavBarIfPossible(this) : null;
        if (savedInstanceState != null) {
            this.mSavedFlowState = savedInstanceState.getBundle("InstrumentActivity.saved_flow_state");
            this.mProgressDialog = (ProgressDialogFragment) restoreFragment(savedInstanceState, "InstrumentActivity.progress_dialog");
            if (this.mProgressDialog != null) {
                this.mProgressDialog.dismiss();
                this.mProgressDialog = null;
            }
            if (savedInstanceState.containsKey("InstrumentActivity.title")) {
                this.mTitleView.setText(savedInstanceState.getString("InstrumentActivity.title"));
            }
        }
    }

    protected int getActivityLayout() {
        return R.layout.light_add_instrument_activity;
    }

    protected int getBillingUiMode() {
        return 0;
    }

    protected void onResume() {
        super.onResume();
        if (this.mRunningFlow != null) {
            this.mRunningFlow.onActivityResume();
        }
        this.mSaveInstanceStateCalled = false;
        if (this.mNeedsHideProgress) {
            hideProgress();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        stopFlow();
    }

    public void onBackPressed() {
        if (this.mRunningFlow != null) {
            this.mEventLog.logClickEvent(600, null, this);
            if (this.mRunningFlow.canGoBack()) {
                this.mRunningFlow.back();
            } else {
                FinskyLog.d("Cannot interrupt the current flow.", new Object[0]);
            }
        } else if (this.mRunningFlowFragment != null) {
            this.mEventLog.logClickEvent(600, null, this);
            if (this.mRunningFlowFragment.canGoBack()) {
                this.mRunningFlowFragment.back();
            } else {
                FinskyLog.d("Cannot interrupt the current flow.", new Object[0]);
            }
        } else {
            super.onBackPressed();
        }
    }

    protected void startOrResumeFlow(Bundle savedInstanceState) {
        this.mRunningFlowFragment = (BillingFlowFragment) getSupportFragmentManager().findFragmentByTag("InstrumentActivity.billing_flow_fragment");
        if (this.mRunningFlowFragment == null) {
            this.mRunningFlowFragment = getBillingFlowFragment();
            if (this.mRunningFlowFragment != null) {
                getSupportFragmentManager().beginTransaction().add(getContainerId(), this.mRunningFlowFragment, "InstrumentActivity.billing_flow_fragment").commit();
            } else {
                this.mRunningFlow = getBillingFlow();
            }
            if (this.mRunningFlow == null && this.mRunningFlowFragment == null) {
                FinskyLog.wtf("Couldn't instantiate BillingFlow for FOP.", new Object[0]);
                finish();
                return;
            }
            if (this.mRunningFlow != null) {
                if (savedInstanceState == null) {
                    this.mRunningFlow.start();
                } else {
                    this.mRunningFlow.resumeFromSavedState(savedInstanceState);
                }
            }
            this.mFragmentContainer.setVisibility(0);
        } else if (FinskyLog.DEBUG) {
            FinskyLog.v("Re-attached to billing flow fragment.", new Object[0]);
        }
    }

    public SetupWizardNavBar getSetupWizardNavBar() {
        return this.mSetupWizardNavBar;
    }

    protected void onSaveInstanceState(Bundle outState) {
        this.mSaveInstanceStateCalled = true;
        super.onSaveInstanceState(outState);
        if (this.mRunningFlow != null) {
            Bundle bundle = new Bundle();
            this.mRunningFlow.saveState(bundle);
            outState.putBundle("InstrumentActivity.saved_flow_state", bundle);
        }
        if (this.mProgressDialog != null) {
            persistFragment(outState, "InstrumentActivity.progress_dialog", this.mProgressDialog);
        }
        if (!TextUtils.isEmpty(this.mTitleView.getText())) {
            outState.putString("InstrumentActivity.title", this.mTitleView.getText().toString());
        }
    }

    public void setTitle(CharSequence title) {
        this.mTitleView.setText(title);
    }

    private void stopFlow() {
        this.mFragmentContainer.setVisibility(8);
        this.mRunningFlow = null;
    }

    public void showFragment(Fragment fragment, String title, boolean addToBackStack) {
        if (!this.mSaveInstanceStateCalled) {
            setTitle(title);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add((int) R.id.content_frame, fragment);
            if (addToBackStack) {
                transaction.addToBackStack(null);
            }
            transaction.commitAllowingStateLoss();
            if (this.mSetupWizardNavBar != null) {
                this.mSetupWizardNavBar.resetButtonsState();
            }
        }
    }

    public void showDialogFragment(DialogFragment fragment, String tag) {
        if (!this.mSaveInstanceStateCalled) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment prev = getSupportFragmentManager().findFragmentByTag(tag);
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);
            fragment.show(getSupportFragmentManager(), tag);
        }
    }

    public void hideFragment(Fragment fragment, boolean addToBackStack) {
        if (!this.mSaveInstanceStateCalled) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.remove(fragment);
            if (addToBackStack) {
                transaction.addToBackStack(null);
            }
            transaction.commit();
        }
    }

    public void persistFragment(Bundle bundle, String key, Fragment fragment) {
        getSupportFragmentManager().putFragment(bundle, key, fragment);
    }

    public Fragment restoreFragment(Bundle bundle, String key) {
        return getSupportFragmentManager().getFragment(bundle, key);
    }

    public void onFinished(BillingFlow flow, boolean canceled, Bundle flowResult) {
        int resultCode = canceled ? 0 : -1;
        Intent result = new Intent();
        result.putExtra("billing_flow_error", false);
        result.putExtra("billing_flow_canceled", canceled);
        if (!(canceled || flowResult == null)) {
            if (flowResult.containsKey("instrument_id")) {
                result.putExtra("instrument_id", flowResult.getString("instrument_id"));
            }
        }
        if (!(canceled || flowResult == null)) {
            if (flowResult.containsKey("redeemed_offer_message_html")) {
                String redeemedOfferHtml = flowResult.getString("redeemed_offer_message_html");
                result.putExtra("redeemed_offer_message_html", redeemedOfferHtml);
                SetupWizardParams setupWizardParams = (SetupWizardParams) getIntent().getParcelableExtra("InstrumentActivity.setup_wizard_params");
                if (getBillingUiMode() == 0 || !(setupWizardParams == null || setupWizardParams.hasSetupCompleteScreen())) {
                    Account account = AccountHandler.findAccount(this.mAccountName, this);
                    Bundle args = new Bundle();
                    args.putParcelable("result_intent", result);
                    args.putInt("result_code", resultCode);
                    Builder builder = new Builder();
                    builder.setMessageHtml(redeemedOfferHtml).setPositiveId(R.string.ok).setEventLog(1220, null, 1221, -1, account).setCanceledOnTouchOutside(false).setCallback(null, 1, args);
                    builder.build().show(getSupportFragmentManager(), "redeemed_promo_offer");
                    return;
                }
            }
        }
        if (!canceled) {
            PromptForFopHelper.expireHasNoFop(this.mAccountName);
        }
        setResult(resultCode, result);
        finish();
    }

    public int getContainerId() {
        return this.mFragmentContainer.getId();
    }

    public void setHostTitle(int resId) {
        setTitle(getString(resId));
    }

    public void showProgress(int messageId) {
        if (!this.mSaveInstanceStateCalled) {
            this.mProgressDialog = ProgressDialogFragment.newInstance(messageId);
            this.mProgressDialog.show(getSupportFragmentManager(), "progress_dialog");
        }
    }

    public void hideProgress() {
        this.mNeedsHideProgress = false;
        if (this.mProgressDialog == null) {
            return;
        }
        if (this.mSaveInstanceStateCalled) {
            this.mNeedsHideProgress = true;
            return;
        }
        this.mProgressDialog.dismiss();
        this.mProgressDialog = null;
    }

    public void onError(BillingFlow flow, String error) {
        Intent result = new Intent();
        result.putExtra("billing_flow_error", true);
        result.putExtra("billing_flow_error_message", error);
        setResult(0, result);
        finish();
    }

    public void onFlowFinished(BillingFlowFragment flow, Bundle result) {
        onFinished(null, false, result);
    }

    public void onFlowCanceled(BillingFlowFragment flow) {
        onFinished(null, true, null);
    }

    public void onFlowError(BillingFlowFragment flow, String error) {
        onError(null, error);
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 1) {
            setResult(extraArguments.getInt("result_code"), (Intent) extraArguments.getParcelable("result_intent"));
            finish();
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
    }
}
