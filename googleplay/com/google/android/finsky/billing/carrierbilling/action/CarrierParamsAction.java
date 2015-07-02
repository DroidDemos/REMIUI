package com.google.android.finsky.billing.carrierbilling.action;

import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters.Builder;
import com.google.android.finsky.protos.Toc.CarrierBillingConfig;
import com.google.android.finsky.utils.FinskyLog;

public class CarrierParamsAction {
    private final CarrierBillingConfig mConfig;

    public CarrierParamsAction(CarrierBillingConfig config) {
        this.mConfig = config;
    }

    public void run(Runnable finishCallback) {
        CarrierBillingParameters carrierBillingParams = createCarrierBillingParameters(this.mConfig);
        if (carrierBillingParams != null) {
            BillingLocator.getCarrierBillingStorage().setParams(carrierBillingParams);
        } else {
            BillingLocator.getCarrierBillingStorage().clearParams();
        }
        finishCallback.run();
    }

    CarrierBillingParameters createCarrierBillingParameters(CarrierBillingConfig config) {
        if (config == null) {
            FinskyLog.d("Carrier billing config is null. Device is not targeted for DCB 2.", new Object[0]);
            return null;
        }
        try {
            return new Builder().setId(config.id).setName(config.name).setGetProvisioningUrl(config.provisioningUrl).setGetCredentialsUrl(config.credentialsUrl).setShowCarrierTos(config.tosRequired).setCarrierApiVersion(config.apiVersion).setPerTransactionCredentialsRequired(config.perTransactionCredentialsRequired).setSendSubscriberInfoWithCarrierRequests(config.sendSubscriberIdWithCarrierBillingRequests).build();
        } catch (IllegalArgumentException e) {
            FinskyLog.e("Missing fields for creating carrier billing parameters", new Object[0]);
            return null;
        }
    }
}
