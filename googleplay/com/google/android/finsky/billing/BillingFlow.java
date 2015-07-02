package com.google.android.finsky.billing;

import android.os.Bundle;

public abstract class BillingFlow {
    protected final BillingFlowContext mBillingFlowContext;
    private boolean mFinished;
    private final BillingFlowListener mListener;
    protected final Bundle mParameters;

    public abstract void resumeFromSavedState(Bundle bundle);

    public abstract void saveState(Bundle bundle);

    public abstract void start();

    public BillingFlow(BillingFlowContext billingFlowContext, BillingFlowListener listener, Bundle parameters) {
        this.mParameters = parameters;
        this.mBillingFlowContext = billingFlowContext;
        this.mListener = listener;
    }

    public void onActivityResume() {
    }

    public void cancel() {
        this.mFinished = true;
        notifyFinished(true, null);
    }

    public boolean isFinished() {
        return this.mFinished;
    }

    public void back() {
        throw new UnsupportedOperationException();
    }

    public boolean canGoBack() {
        return false;
    }

    protected void finish() {
        finish(null);
    }

    protected void finish(Bundle result) {
        this.mFinished = true;
        notifyFinished(false, result);
    }

    protected void fail(String error) {
        this.mFinished = true;
        notifyError(error);
    }

    private void notifyFinished(boolean canceled, Bundle result) {
        this.mListener.onFinished(this, canceled, result);
    }

    private void notifyError(String error) {
        this.mListener.onError(this, error);
    }
}
