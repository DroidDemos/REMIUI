package com.google.android.finsky.billing.giftcard.steps;

import android.os.Bundle;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.billing.SuccessStep;
import com.google.android.finsky.billing.giftcard.RedeemCodeFragment;
import com.google.android.finsky.protos.Acquisition.SuccessInfo;

public class RedeemSuccessStep extends SuccessStep<RedeemCodeFragment> {
    private final PlayStoreUiElement mPlayStoreUiElement;

    public RedeemSuccessStep() {
        this.mPlayStoreUiElement = FinskyEventLog.obtainPlayStoreUiElement(885);
    }

    public static RedeemSuccessStep newInstance(SuccessInfo successInfo, int mode) {
        Bundle args = SuccessStep.createArgs(successInfo);
        args.putInt("ui_mode", mode);
        RedeemSuccessStep result = new RedeemSuccessStep();
        result.mSuccessInfo = successInfo;
        result.setArguments(args);
        return result;
    }

    public void onContinueButtonClicked() {
        logClick(886);
        ((RedeemCodeFragment) getMultiStepFragment()).performSuccessActionAndFinish();
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mPlayStoreUiElement;
    }
}
