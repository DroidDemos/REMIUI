package com.google.android.finsky.billing.lightpurchase;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowFragment;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.ProgressDialogFragment;
import com.google.android.finsky.utils.FinskyLog;

public abstract class LegacyFlowWrapperFragment extends BillingFlowFragment implements BillingFlowContext, BillingFlowListener {
    protected BillingFlow mLegacyFlow;

    protected abstract BillingFlow getLegacyPurchaseFlow();

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle flowState = new Bundle();
        if (this.mLegacyFlow != null) {
            this.mLegacyFlow.saveState(flowState);
        }
        outState.putBundle("CompleteDcb3Flow.flowState", flowState);
    }

    protected void startOrResumeLegacyFlow(Bundle savedInstanceState) {
        this.mLegacyFlow = getLegacyPurchaseFlow();
        if (savedInstanceState == null || !savedInstanceState.containsKey("CompleteDcb3Flow.flowState")) {
            this.mLegacyFlow.start();
            return;
        }
        this.mLegacyFlow.resumeFromSavedState(savedInstanceState.getBundle("CompleteDcb3Flow.flowState"));
    }

    public boolean canGoBack() {
        return this.mLegacyFlow != null && this.mLegacyFlow.canGoBack();
    }

    public void back() {
        if (this.mLegacyFlow != null) {
            this.mLegacyFlow.back();
        }
    }

    public void showFragment(Fragment fragment, String title, boolean addToBackStack) {
        FinskyLog.wtf("Not implemented.", new Object[0]);
    }

    public void showDialogFragment(DialogFragment fragment, String tag) {
        fragment.show(getFragmentManager(), tag);
    }

    public void hideFragment(Fragment fragment, boolean addToBackStack) {
        FinskyLog.wtf("Not implemented.", new Object[0]);
    }

    public void persistFragment(Bundle bundle, String key, Fragment fragment) {
        getFragmentManager().putFragment(bundle, key, fragment);
    }

    public Fragment restoreFragment(Bundle bundle, String key) {
        return getFragmentManager().getFragment(bundle, key);
    }

    public void showProgress(int messageId) {
        if (getFragmentManager().findFragmentByTag("CompleteDcb3Flow.progressDialog") != null) {
            FinskyLog.wtf("Duplicate progress dialog.", new Object[0]);
        } else {
            ProgressDialogFragment.newInstance(messageId).show(getFragmentManager(), "CompleteDcb3Flow.progressDialog");
        }
    }

    public void hideProgress() {
        ProgressDialogFragment progressDialog = (ProgressDialogFragment) getFragmentManager().findFragmentByTag("CompleteDcb3Flow.progressDialog");
        if (progressDialog == null) {
            FinskyLog.wtf("Progress dialog not shown.", new Object[0]);
        } else {
            progressDialog.dismiss();
        }
    }

    public void onFinished(BillingFlow flow, boolean canceled, Bundle result) {
        if (canceled) {
            cancel();
        } else {
            finish(result);
        }
    }

    public void onError(BillingFlow flow, String error) {
        fail(error);
    }
}
