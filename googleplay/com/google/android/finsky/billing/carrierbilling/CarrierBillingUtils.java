package com.google.android.finsky.billing.carrierbilling;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.carrierbilling.action.CarrierBillingAction;
import com.google.android.finsky.billing.carrierbilling.action.CarrierParamsAction;
import com.google.android.finsky.billing.carrierbilling.action.CarrierProvisioningAction;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingCredentials;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.protos.ChallengeProto.InputValidationError;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Sha1Util;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.Map;

public class CarrierBillingUtils {
    public static boolean areCredentialsValid(CarrierBillingStorage dcbStorage) {
        CarrierBillingCredentials credentials = dcbStorage.getCredentials();
        if (credentials == null) {
            return false;
        }
        boolean z = isProvisioned(dcbStorage) && !TextUtils.isEmpty(credentials.getCredentials()) && credentials.getExpirationTime() - ((Long) G.vendingCarrierCredentialsBufferMs.get()).longValue() > System.currentTimeMillis();
        return z;
    }

    public static boolean isProvisioned(CarrierBillingStorage dcbStorage) {
        if (dcbStorage == null) {
            FinskyLog.wtf("CarrierBillingStorage is null. Return false", new Object[0]);
            return false;
        }
        CarrierBillingProvisioning provisioning = dcbStorage.getProvisioning();
        if (provisioning != null) {
            return provisioning.isProvisioned();
        }
        return false;
    }

    public static boolean isDcb30(CarrierBillingStorage dcbStorage) {
        if (dcbStorage == null) {
            FinskyLog.wtf("CarrierBillingStorage is null, fallback to 3.0", new Object[0]);
            return true;
        }
        CarrierBillingParameters params = dcbStorage.getParams();
        if (params == null || params.getCarrierApiVersion() == 3) {
            return true;
        }
        return false;
    }

    private static ArrayList<Integer> getInvalidEntries(InputValidationError[] inputErrors) {
        ArrayList<Integer> errors = new ArrayList();
        for (InputValidationError error : inputErrors) {
            int inputField = error.inputField;
            switch (inputField) {
                case R.styleable.WalletImFormEditText_required /*4*/:
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    errors.add(Integer.valueOf(inputField));
                    break;
                default:
                    FinskyLog.d("InputValidationError that can't be edited: type=%d, message=%s", Integer.valueOf(inputField), error.errorMessage);
                    break;
            }
        }
        return errors;
    }

    public static ArrayList<Integer> getRetriableErrors(UpdateInstrumentResponse response) {
        if (response.result == 2) {
            ArrayList<Integer> retriableErrorList = getInvalidEntries(response.errorInputField);
            if (!(retriableErrorList == null || retriableErrorList.isEmpty())) {
                return retriableErrorList;
            }
        }
        return null;
    }

    public static void initializeCarrierBillingStorage(Runnable completionCallback) {
        new CarrierBillingAction().run(completionCallback);
    }

    public static void initializeCarrierBillingParams(final Context context, final boolean getProvisioningIfNotOnWifi, final Runnable completionCallback) {
        DfeToc toc = FinskyApp.get().getToc();
        new CarrierParamsAction(toc == null ? null : toc.getCarrierBillingConfig()).run(new Runnable() {
            public void run() {
                if (getProvisioningIfNotOnWifi && CarrierBillingUtils.isCarrierBillingParamsAvailable() && !CarrierBillingUtils.isDcb30(BillingLocator.getCarrierBillingStorage())) {
                    new CarrierProvisioningAction().runIfNotOnWifi(context);
                }
                if (completionCallback != null) {
                    completionCallback.run();
                }
            }
        });
    }

    public static void initializeStorageAndParams(final Context context, final boolean getProvisioningIfNotOnWifi, final Runnable successRunnable) {
        initializeCarrierBillingStorage(new Runnable() {
            public void run() {
                CarrierBillingUtils.initializeCarrierBillingParams(context, getProvisioningIfNotOnWifi, successRunnable);
            }
        });
    }

    public static void initializeCarrierBillingProvisioning(Runnable completionCallback) {
        if (isCarrierBillingParamsAvailable()) {
            new CarrierProvisioningAction().run(completionCallback);
        } else if (completionCallback != null) {
            completionCallback.run();
        }
    }

    public static boolean isCarrierBillingParamsAvailable() {
        return (BillingLocator.getCarrierBillingStorage() == null || BillingLocator.getCarrierBillingStorage().getParams() == null) ? false : true;
    }

    public static void addPrepareOrBillingProfileParams(boolean billingProfile, boolean optimisticProvisioning, Map<String, String> outParams) {
        if (!isDcb30(BillingLocator.getCarrierBillingStorage())) {
            Dcb2Util.addPrepareOrBillingProfileParams(optimisticProvisioning, outParams);
        }
        Dcb3Util.addPrepareOrBillingProfileParams(billingProfile, outParams);
    }

    public static String getSimIdentifier() {
        String subscriberId = BillingLocator.getSubscriberIdFromTelephony();
        if (subscriberId != null) {
            return Sha1Util.secureHash(subscriberId.getBytes());
        }
        String deviceId = BillingLocator.getDeviceIdFromTelephony();
        if (deviceId != null) {
            return Sha1Util.secureHash(deviceId.getBytes());
        }
        return "invalid_sim_id";
    }
}
