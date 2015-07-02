package com.google.android.finsky.billing.lightpurchase;

import android.os.Bundle;
import com.android.vending.R;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.Dcb2Util;
import com.google.android.finsky.billing.carrierbilling.action.CarrierProvisioningAction;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.protos.Toc.TocResponse;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.GetTocHelper;
import com.google.android.finsky.utils.GetTocHelper.Listener;

public class CompleteDcb2FlowFragment extends LegacyFlowWrapperFragment {
    private String mAccountName;

    public static CompleteDcb2FlowFragment newInstance(String accountName) {
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        CompleteDcb2FlowFragment fragment = new CompleteDcb2FlowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAccountName = getArguments().getString("authAccount");
        if (FinskyApp.get().getToc() == null) {
            fetchTocAndInitializeBilling(savedInstanceState);
        } else {
            initializeBilling(savedInstanceState);
        }
    }

    private void fetchTocAndInitializeBilling(final Bundle savedInstanceState) {
        if (FinskyLog.DEBUG) {
            FinskyLog.v("Loading TOC...", new Object[0]);
        }
        GetTocHelper.getToc(FinskyApp.get().getDfeApi(), false, new Listener() {
            public void onResponse(TocResponse response) {
                FinskyApp.get().setToc(new DfeToc(response));
                FinskyLog.d("TOC loaded.", new Object[0]);
                CompleteDcb2FlowFragment.this.initializeBilling(savedInstanceState);
            }

            public void onErrorResponse(VolleyError error) {
                CompleteDcb2FlowFragment.this.fail(ErrorStrings.get(FinskyApp.get(), error));
            }
        });
    }

    private void initializeBilling(final Bundle savedInstanceState) {
        CarrierBillingUtils.initializeStorageAndParams(getActivity(), false, new Runnable() {
            public void run() {
                new CarrierProvisioningAction().run(new Runnable() {
                    public void run() {
                        CompleteDcb2FlowFragment.this.onBillingInitialized(savedInstanceState);
                    }
                });
            }
        });
    }

    private void onBillingInitialized(Bundle savedInstanceState) {
        CarrierBillingProvisioning provisioning = BillingLocator.getCarrierBillingStorage().getProvisioning();
        if (provisioning == null) {
            fail(getString(R.string.carrier_network_unavailable_error));
        } else if (provisioning.isProvisioned()) {
            startOrResumeLegacyFlow(savedInstanceState);
        } else {
            fail(getString(R.string.not_provisioned_error, storage.getParams().getName()));
        }
    }

    protected BillingFlow getLegacyPurchaseFlow() {
        return Dcb2Util.getCompletePurchaseFlow(this.mAccountName, this, this, null);
    }
}
