package com.google.android.finsky.billing.carrierbilling.action;

import com.google.android.finsky.billing.BillingLocator;

public class CarrierBillingAction {
    private boolean canSkip() {
        return BillingLocator.getCarrierBillingStorage().isInit();
    }

    public void run(Runnable runnable) {
        if (!canSkip()) {
            BillingLocator.initCarrierBillingStorage(runnable);
        } else if (runnable != null) {
            runnable.run();
        }
    }
}
