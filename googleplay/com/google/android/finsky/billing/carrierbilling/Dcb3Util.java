package com.google.android.finsky.billing.carrierbilling;

import android.accounts.Account;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingPreferences;
import com.google.android.finsky.billing.carrierbilling.flow.CompleteDcb3Flow;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.ParcelableProto;
import java.util.Map;

public class Dcb3Util {
    public static BillingFlow getCompletePurchaseFlow(BillingFlowContext billingFlowContext, BillingFlowListener listener, Bundle parameters, Instrument instrument) {
        String accountName = parameters.getString("authAccount");
        parameters.putParcelable("dcb_instrument", ParcelableProto.forProto(instrument));
        Account account = AccountHandler.findAccount(accountName, FinskyApp.get());
        if (account == null) {
            FinskyLog.e("Invalid account passed in parameters.", new Object[0]);
            return null;
        }
        return new CompleteDcb3Flow(billingFlowContext, FinskyApp.get().getDfeApi(account.name), listener, FinskyApp.get().getEventLogger(), parameters);
    }

    public static Map<String, String> getCompleteParameters(Bundle completeFlowResult) {
        Map<String, String> map = Maps.newHashMap();
        String simIdentifier = BillingLocator.getCarrierBillingStorage().getCurrentSimIdentifier();
        if (!TextUtils.isEmpty(simIdentifier)) {
            map.put("dcbch", simIdentifier);
        }
        String encryptedPassphrase = null;
        if (completeFlowResult != null) {
            encryptedPassphrase = completeFlowResult.getString("encrypted_passphrase");
        }
        if (!TextUtils.isEmpty(encryptedPassphrase)) {
            map.put("dcbpassphrase", encryptedPassphrase);
        }
        return map;
    }

    public static void addPrepareOrBillingProfileParams(boolean isBillingProfile, Map<String, String> outParams) {
        String simIdentifier = BillingLocator.getCarrierBillingStorage().getCurrentSimIdentifier();
        if (!TextUtils.isEmpty(simIdentifier)) {
            outParams.put("dcbch", simIdentifier);
        }
        if ((isBillingProfile || ((Boolean) G.dcb3PrepareSendDeviceRebooted.get()).booleanValue()) && performDeviceBootedCheck()) {
            outParams.put("dcbdevicerebooted", "true");
        }
    }

    private static boolean performDeviceBootedCheck() {
        boolean hasBootedSinceLastCheck = System.currentTimeMillis() - ((Long) BillingPreferences.LAST_DCB3_PROVISIONING_TIME_MILLIS.get()).longValue() > SystemClock.elapsedRealtime();
        BillingPreferences.LAST_DCB3_PROVISIONING_TIME_MILLIS.put(Long.valueOf(System.currentTimeMillis()));
        return hasBootedSinceLastCheck;
    }
}
