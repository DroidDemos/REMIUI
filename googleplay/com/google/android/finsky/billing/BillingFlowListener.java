package com.google.android.finsky.billing;

import android.os.Bundle;

public interface BillingFlowListener {
    void onError(BillingFlow billingFlow, String str);

    void onFinished(BillingFlow billingFlow, boolean z, Bundle bundle);
}
