package com.google.android.finsky.billing.lightpurchase.multistep;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.utils.FinskyLog;

public abstract class StepFragment<T extends MultiStepFragment> extends Fragment implements PlayStoreUiElementNode {
    public abstract String getContinueButtonLabel(Resources resources);

    public abstract void onContinueButtonClicked();

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof PlayStoreUiElementNode)) {
            throw new IllegalStateException("Activity must implement PlayStoreUiElementNode");
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getMultiStepFragment().logImpression((PlayStoreUiElementNode) this);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("StepFragment.exists", true);
    }

    public boolean allowButtonBar() {
        return true;
    }

    public boolean allowContinueButtonIcon() {
        return false;
    }

    protected T getMultiStepFragment() {
        return (MultiStepFragment) getParentFragment();
    }

    protected void logClick(int leafType) {
        logClick(leafType, null);
    }

    protected void logClick(int leafType, PlayStoreUiElementInfo leafClientLogsCookie) {
        getMultiStepFragment().logClick(leafType, leafClientLogsCookie, this);
    }

    public PlayStoreUiElementNode getParentNode() {
        return (PlayStoreUiElementNode) getActivity();
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyLog.wtf("Not using tree impressions.", new Object[0]);
    }
}
