package com.google.android.finsky.billing.lightpurchase.purchasesteps;

import android.os.Bundle;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.billing.instrumentmanager.InstrumentManagerStep;
import com.google.android.finsky.billing.lightpurchase.PurchaseFragment;
import com.google.android.finsky.protos.SingleFopPaymentsIntegrator.SingleFopPaymentsIntegratorContext;

public class InstrumentManagerPurchaseStep extends InstrumentManagerStep<PurchaseFragment> {
    private PlayStoreUiElement mPlayStoreUiElement;

    public InstrumentManagerPurchaseStep() {
        this.mPlayStoreUiElement = FinskyEventLog.obtainPlayStoreUiElement(740);
    }

    public static InstrumentManagerPurchaseStep newInstance(String accountName, SingleFopPaymentsIntegratorContext tokens) {
        InstrumentManagerPurchaseStep result = new InstrumentManagerPurchaseStep();
        result.setArguments(InstrumentManagerStep.createArgs(accountName, tokens));
        return result;
    }

    public void onInstrumentManagerResult(int resultCode, Bundle data) {
        if (isSuccess(resultCode)) {
            ((PurchaseFragment) getMultiStepFragment()).preparePurchase();
        } else {
            ((PurchaseFragment) getMultiStepFragment()).finish();
        }
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mPlayStoreUiElement;
    }
}
