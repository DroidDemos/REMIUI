package com.google.android.finsky.billing.lightpurchase.purchasesteps;

import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.billing.SuccessStep;
import com.google.android.finsky.billing.lightpurchase.PurchaseFragment;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.Acquisition.SuccessInfo;
import com.google.android.finsky.utils.UiUtils;

public class LightPurchaseSuccessStep extends SuccessStep<PurchaseFragment> {
    private final Runnable mFinishRunnable;
    private final Handler mHandler;
    private final PlayStoreUiElement mUiElement;

    public LightPurchaseSuccessStep() {
        this.mFinishRunnable = new Runnable() {
            public void run() {
                PurchaseFragment purchaseFragment = (PurchaseFragment) LightPurchaseSuccessStep.this.getMultiStepFragment();
                if (purchaseFragment != null) {
                    purchaseFragment.performSuccessActionAndFinish();
                }
            }
        };
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(775);
        this.mHandler = new Handler();
    }

    public static LightPurchaseSuccessStep newInstance(SuccessInfo successInfo) {
        Bundle args = SuccessStep.createArgs(successInfo);
        LightPurchaseSuccessStep result = new LightPurchaseSuccessStep();
        result.mSuccessInfo = successInfo;
        result.setArguments(args);
        return result;
    }

    public void onStart() {
        super.onStart();
        if (getContinueButtonLabel(getResources()) == null) {
            UiUtils.sendAccessibilityEventWithText(getActivity(), Html.fromHtml(this.mSuccessInfo.messageHtml).toString(), getView());
            this.mHandler.postDelayed(this.mFinishRunnable, ((Long) G.lightPurchaseAutoDismissMs.get()).longValue());
        }
    }

    public void onStop() {
        this.mHandler.removeCallbacks(this.mFinishRunnable);
        super.onStop();
    }

    public void onContinueButtonClicked() {
        logClick(778);
        ((PurchaseFragment) getMultiStepFragment()).performSuccessActionAndFinish();
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
    }
}
