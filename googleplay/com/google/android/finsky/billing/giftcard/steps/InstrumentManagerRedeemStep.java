package com.google.android.finsky.billing.giftcard.steps;

import android.os.Bundle;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.billing.giftcard.RedeemCodeFragment;
import com.google.android.finsky.billing.instrumentmanager.InstrumentManagerStep;
import com.google.android.finsky.protos.SingleFopPaymentsIntegrator.SingleFopPaymentsIntegratorContext;

public class InstrumentManagerRedeemStep extends InstrumentManagerStep<RedeemCodeFragment> {
    private PlayStoreUiElement mPlayStoreUiElement;

    public InstrumentManagerRedeemStep() {
        this.mPlayStoreUiElement = FinskyEventLog.obtainPlayStoreUiElement(1106);
    }

    public static InstrumentManagerRedeemStep newInstance(String accountName, SingleFopPaymentsIntegratorContext tokens) {
        InstrumentManagerRedeemStep result = new InstrumentManagerRedeemStep();
        result.setArguments(InstrumentManagerStep.createArgs(accountName, tokens));
        return result;
    }

    public void onInstrumentManagerResult(int resultCode, Bundle data) {
        ((RedeemCodeFragment) getMultiStepFragment()).instrumentManagerFinished(isSuccess(resultCode));
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mPlayStoreUiElement;
    }
}
