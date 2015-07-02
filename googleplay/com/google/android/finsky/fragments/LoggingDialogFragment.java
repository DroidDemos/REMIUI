package com.google.android.finsky.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.utils.FinskyLog;

public abstract class LoggingDialogFragment extends DialogFragment implements PlayStoreUiElementNode {
    protected FinskyEventLog mEventLog;
    private final PlayStoreUiElement mUiElement;

    protected abstract int getPlayStoreUiElementType();

    public LoggingDialogFragment() {
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(getPlayStoreUiElementType());
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof PlayStoreUiElementNode)) {
            throw new IllegalStateException("Parent activity must implement PlayStoreUiElementNode.");
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String accountName = getArguments().getString("authAccount");
        if (accountName == null) {
            FinskyLog.wtf("authAccount argument not set.", new Object[0]);
        }
        this.mEventLog = FinskyApp.get().getEventLogger(accountName);
        if (savedInstanceState == null) {
            this.mEventLog.logPathImpression(0, this);
        }
    }

    public void onDismiss(DialogInterface dialog) {
        if (this.mEventLog != null) {
            this.mEventLog.logPathImpression(0, 603, this);
        }
        super.onDismiss(dialog);
    }

    protected void logClickEvent(int type) {
        this.mEventLog.logClickEvent(type, null, this);
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
    }

    public PlayStoreUiElementNode getParentNode() {
        return (PlayStoreUiElementNode) getActivity();
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        throw new UnsupportedOperationException("Unwanted children.");
    }
}
