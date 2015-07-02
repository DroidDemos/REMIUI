package com.google.android.finsky.billing.carrierbilling;

import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.carrierbilling.action.CarrierProvisioningAction;
import com.google.android.finsky.billing.carrierbilling.flow.CompleteCarrierBillingFlow;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingCredentials;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import java.util.Collections;
import java.util.Map;

public class Dcb2Util {
    public static void addPrepareOrBillingProfileParams(boolean optimisticProvisioning, Map<String, String> outParams) {
        CarrierBillingStorage storage = BillingLocator.getCarrierBillingStorage();
        CarrierBillingProvisioning provisioning = storage.getProvisioning();
        if (provisioning != null && !provisioning.isProvisioned()) {
            FinskyLog.d("Not provisioned, not including identifier with params", new Object[0]);
        } else if (provisioning != null || optimisticProvisioning) {
            Long transactionLimit = null;
            String subscriberCurrency = null;
            if (provisioning != null) {
                transactionLimit = Long.valueOf(provisioning.getTransactionLimit());
                subscriberCurrency = provisioning.getSubscriberCurrency();
            }
            outParams.put("dcbch", storage.getCurrentSimIdentifier());
            if (transactionLimit != null) {
                outParams.put("dcbtl", transactionLimit.toString());
            }
            if (subscriberCurrency != null) {
                outParams.put("dcbsc", subscriberCurrency);
            }
        } else {
            new CarrierProvisioningAction().run(null);
        }
    }

    public static Map<String, String> getCompleteParameters() {
        CarrierBillingStorage storage = BillingLocator.getCarrierBillingStorage();
        CarrierBillingProvisioning provisioning = storage.getProvisioning();
        CarrierBillingCredentials credentials = storage.getCredentials();
        if (credentials == null) {
            return null;
        }
        Map<String, String> map = Maps.newHashMap();
        map.put("dcbct", credentials.getCredentials());
        Long expiration = Long.valueOf(credentials.getExpirationTime());
        if (expiration != null) {
            map.put("dcbctt", expiration.toString());
        }
        Long transactionLimit = null;
        String subscriberCurrency = null;
        if (provisioning != null) {
            transactionLimit = Long.valueOf(provisioning.getTransactionLimit());
            subscriberCurrency = provisioning.getSubscriberCurrency();
        }
        if (transactionLimit != null) {
            map.put("dcbtl", transactionLimit.toString());
        }
        if (subscriberCurrency != null) {
            map.put("dcbsc", subscriberCurrency);
        }
        return Collections.unmodifiableMap(map);
    }

    public static BillingFlow getCompletePurchaseFlow(String accountName, BillingFlowContext billingFlowContext, BillingFlowListener listener, Bundle parameters) {
        return new CompleteCarrierBillingFlow(accountName, billingFlowContext, listener, FinskyApp.get().getEventLogger(), parameters);
    }
}
