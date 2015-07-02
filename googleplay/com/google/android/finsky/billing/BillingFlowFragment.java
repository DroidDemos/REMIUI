package com.google.android.finsky.billing;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class BillingFlowFragment extends Fragment {
    protected BillingFlowHost mHost;

    public interface BillingFlowHost {
        void hideProgress();

        void onFlowCanceled(BillingFlowFragment billingFlowFragment);

        void onFlowError(BillingFlowFragment billingFlowFragment, String str);

        void onFlowFinished(BillingFlowFragment billingFlowFragment, Bundle bundle);

        void setHostTitle(int i);

        void showProgress(int i);
    }

    public abstract void back();

    public abstract boolean canGoBack();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getTargetFragment() instanceof BillingFlowHost) {
            this.mHost = (BillingFlowHost) getTargetFragment();
        } else if (getParentFragment() instanceof BillingFlowHost) {
            this.mHost = (BillingFlowHost) getParentFragment();
        } else {
            this.mHost = (BillingFlowHost) getActivity();
        }
    }

    public void finish(Bundle result) {
        this.mHost.onFlowFinished(this, result);
    }

    public void cancel() {
        this.mHost.onFlowCanceled(this);
    }

    public void fail(String error) {
        this.mHost.onFlowError(this, error);
    }
}
