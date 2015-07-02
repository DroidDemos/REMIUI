package com.google.android.finsky.billing.carrierbilling.api;

import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;

public class DcbApiContext {
    private final CarrierBillingStorage mDcbStorage;
    private final String mLine1Number;
    private final String mSubscriberId;

    public DcbApiContext(CarrierBillingStorage dcbStorage, String line1Number, String subscriberId) {
        this.mDcbStorage = dcbStorage;
        this.mLine1Number = line1Number;
        this.mSubscriberId = subscriberId;
    }

    public CarrierBillingParameters getCarrierBillingParameters() {
        return this.mDcbStorage.getParams();
    }

    public String getLine1Number() {
        return this.mLine1Number;
    }

    public String getSubscriberId() {
        return this.mSubscriberId;
    }

    public String toString() {
        return "[DcbApiContext: " + "Line1Number: " + this.mLine1Number + ", " + "SubscriberId: " + this.mSubscriberId + "]";
    }
}
