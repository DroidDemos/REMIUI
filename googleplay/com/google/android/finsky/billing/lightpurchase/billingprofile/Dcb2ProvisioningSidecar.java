package com.google.android.finsky.billing.lightpurchase.billingprofile;

import android.os.Bundle;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.fragments.SidecarFragment;

public class Dcb2ProvisioningSidecar extends SidecarFragment {
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setState(1, 0);
        CarrierBillingUtils.initializeStorageAndParams(getActivity(), false, new Runnable() {
            public void run() {
                if (savedInstanceState == null) {
                    CarrierBillingUtils.initializeCarrierBillingProvisioning(new Runnable() {
                        public void run() {
                            Dcb2ProvisioningSidecar.this.updateState();
                        }
                    });
                } else {
                    Dcb2ProvisioningSidecar.this.updateState();
                }
            }
        });
    }

    private void updateState() {
        CarrierBillingStorage storage = BillingLocator.getCarrierBillingStorage();
        if (storage == null || storage.getProvisioning() == null) {
            setState(3, 0);
        } else {
            setState(2, 0);
        }
    }

    public boolean isProvisioned() {
        return CarrierBillingUtils.isProvisioned(BillingLocator.getCarrierBillingStorage());
    }

    public String getCarrierName() {
        CarrierBillingParameters params = BillingLocator.getCarrierBillingStorage().getParams();
        if (params != null) {
            return params.getName();
        }
        return null;
    }
}
