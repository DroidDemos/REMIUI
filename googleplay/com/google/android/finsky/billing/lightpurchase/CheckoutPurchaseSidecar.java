package com.google.android.finsky.billing.lightpurchase;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeApi.GaiaAuthParameters;
import com.google.android.finsky.api.DfeRequest;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.IabParameters;
import com.google.android.finsky.billing.creditcard.EscrowRequest;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.protos.Acquisition.SuccessInfo;
import com.google.android.finsky.protos.ChallengeProto.Challenge;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryInAppDetails;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryMutation;
import com.google.android.finsky.protos.LibraryUpdateProto.LibrarySubscriptionDetails;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryUpdate;
import com.google.android.finsky.protos.Purchase.ChangeSubscription;
import com.google.android.finsky.protos.Purchase.ClientCart;
import com.google.android.finsky.protos.Purchase.CommitPurchaseResponse;
import com.google.android.finsky.protos.Purchase.PreparePurchaseResponse;
import com.google.android.finsky.protos.Purchase.PurchaseStatus;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.wallet.instrumentmanager.R;
import java.util.Map;

public class CheckoutPurchaseSidecar extends SidecarFragment {
    private ClientCart mCart;
    private ChangeSubscription mChangeSubscription;
    private CheckoutPurchaseError mCheckoutPurchaseError;
    private DfeRequest<?> mCommitRequest;
    private long mCommitStartedMs;
    private Challenge mCompleteChallenge;
    private String mCvcEscrowHandle;
    private DfeApi mDfeApi;
    private FinskyEventLog mEventLogger;
    private Bundle mExtraPurchaseData;
    private Challenge mPrepareChallenge;
    private PurchaseParams mPreparePurchaseParams;
    private DfeRequest<?> mPrepareRequest;
    private long mPrepareStartedMs;
    private byte[] mServerLogsCookie;
    private SuccessInfo mSuccessInfo;
    private VolleyError mVolleyError;

    public static class CheckoutPurchaseError implements Parcelable {
        public static Creator<CheckoutPurchaseError> CREATOR;
        public final String errorMessageHtml;
        public final int permissionError;

        public CheckoutPurchaseError(int permissionError, String errorMessageHtml) {
            this.permissionError = permissionError;
            this.errorMessageHtml = errorMessageHtml;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.permissionError);
            parcel.writeByte((byte) (this.errorMessageHtml != null ? 1 : 0));
            if (this.errorMessageHtml != null) {
                parcel.writeString(this.errorMessageHtml);
            }
        }

        static {
            CREATOR = new Creator<CheckoutPurchaseError>() {
                public CheckoutPurchaseError createFromParcel(Parcel parcel) {
                    int permissionError = parcel.readInt();
                    String errorMessageHtml = null;
                    if (parcel.readByte() != (byte) 0) {
                        errorMessageHtml = parcel.readString();
                    }
                    return new CheckoutPurchaseError(permissionError, errorMessageHtml);
                }

                public CheckoutPurchaseError[] newArray(int size) {
                    return new CheckoutPurchaseError[size];
                }
            };
        }
    }

    private class CommitErrorListener implements ErrorListener {
        private CommitErrorListener() {
        }

        public void onErrorResponse(VolleyError volleyError) {
            CheckoutPurchaseSidecar.this.mVolleyError = volleyError;
            CheckoutPurchaseSidecar.this.logVolleyError(305, volleyError, CheckoutPurchaseSidecar.this.getCommitServerLatencyMs(), CheckoutPurchaseSidecar.this.getTimeElapsedSinceCommitMs());
            CheckoutPurchaseSidecar.this.setState(3, 3);
        }
    }

    private class CommitListener implements Listener<CommitPurchaseResponse> {
        private CommitListener() {
        }

        public void onResponse(final CommitPurchaseResponse response) {
            CheckoutPurchaseSidecar.this.mServerLogsCookie = response.serverLogsCookie;
            final PurchaseStatus status = response.purchaseStatus;
            CheckoutPurchaseSidecar.this.log(305, status.statusCode, CheckoutPurchaseSidecar.this.getCommitServerLatencyMs(), CheckoutPurchaseSidecar.this.getTimeElapsedSinceCommitMs());
            CheckoutPurchaseSidecar.this.handleLibraryUpdates(response.libraryUpdate, "CheckoutPurchaseSidecar.commit", new Runnable() {
                public void run() {
                    if (response.appDeliveryData != null) {
                        if (!TextUtils.isEmpty(response.encodedDeliveryToken)) {
                            FinskyApp.get().getInstaller().setDeliveryToken(CheckoutPurchaseSidecar.this.mPreparePurchaseParams.docidStr, response.encodedDeliveryToken);
                        }
                        CheckoutPurchaseSidecar.this.installApp();
                    } else {
                        FinskyLog.w("missing delivery data for %s", CheckoutPurchaseSidecar.this.mPreparePurchaseParams.docidStr);
                    }
                    switch (status.statusCode) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            CheckoutPurchaseSidecar.this.mSuccessInfo = response.successInfo;
                            CheckoutPurchaseSidecar.this.setState(2, 7);
                            return;
                        case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            CheckoutPurchaseSidecar.this.setState(3, 4);
                            return;
                        case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            CheckoutPurchaseSidecar.this.mCompleteChallenge = response.challenge;
                            CheckoutPurchaseSidecar.this.setState(7, 0);
                            return;
                        case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            CheckoutPurchaseSidecar.this.mCheckoutPurchaseError = new CheckoutPurchaseError(status.permissionError, status.errorMessageHtml);
                            CheckoutPurchaseSidecar.this.setState(3, 5);
                            return;
                        default:
                            FinskyLog.wtf("Unknown purchase status: %d", Integer.valueOf(status.statusCode));
                            CheckoutPurchaseSidecar.this.mCheckoutPurchaseError = new CheckoutPurchaseError(0, null);
                            CheckoutPurchaseSidecar.this.setState(3, 5);
                            return;
                    }
                }
            });
        }
    }

    private class EscrowErrorListener implements ErrorListener {
        private EscrowErrorListener() {
        }

        public void onErrorResponse(VolleyError volleyError) {
            CheckoutPurchaseSidecar.this.mVolleyError = volleyError;
            CheckoutPurchaseSidecar.this.logVolleyError(306, volleyError, -1, -1);
            CheckoutPurchaseSidecar.this.setState(3, 3);
        }
    }

    private class EscrowResponseListener implements Listener<String> {
        private EscrowResponseListener() {
        }

        public void onResponse(String response) {
            CheckoutPurchaseSidecar.this.mEventLogger.logBackgroundEvent(306, null);
            CheckoutPurchaseSidecar.this.mCvcEscrowHandle = response;
            CheckoutPurchaseSidecar.this.setState(8, 0);
        }
    }

    private class PrepareErrorListener implements ErrorListener {
        private PrepareErrorListener() {
        }

        public void onErrorResponse(VolleyError volleyError) {
            CheckoutPurchaseSidecar.this.mVolleyError = volleyError;
            CheckoutPurchaseSidecar.this.logVolleyError(303, volleyError, CheckoutPurchaseSidecar.this.getPrepareServerLatencyMs(), CheckoutPurchaseSidecar.this.getTimeElapsedSincePrepareMs());
            CheckoutPurchaseSidecar.this.setState(3, 3);
        }
    }

    private class PrepareListener implements Listener<PreparePurchaseResponse> {
        private PrepareListener() {
        }

        public void onResponse(PreparePurchaseResponse response) {
            int state = 5;
            CheckoutPurchaseSidecar.this.mPrepareChallenge = null;
            CheckoutPurchaseSidecar.this.mCart = null;
            CheckoutPurchaseSidecar.this.mChangeSubscription = null;
            CheckoutPurchaseSidecar.this.mServerLogsCookie = response.serverLogsCookie;
            PurchaseStatus status = response.purchaseStatus;
            CheckoutPurchaseSidecar.this.log(303, status.statusCode, CheckoutPurchaseSidecar.this.getPrepareServerLatencyMs(), CheckoutPurchaseSidecar.this.getTimeElapsedSincePrepareMs());
            switch (status.statusCode) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    CheckoutPurchaseSidecar.this.mCart = response.cart;
                    CheckoutPurchaseSidecar.this.mChangeSubscription = response.changeSubscription;
                    if (CheckoutPurchaseSidecar.this.mChangeSubscription != null) {
                        state = 4;
                    }
                    CheckoutPurchaseSidecar.this.setState(state, 0);
                    return;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    CheckoutPurchaseSidecar.this.mPrepareChallenge = response.challenge;
                    CheckoutPurchaseSidecar.this.mCart = response.cart;
                    CheckoutPurchaseSidecar.this.setState(6, 0);
                    return;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    CheckoutPurchaseSidecar.this.mCheckoutPurchaseError = new CheckoutPurchaseError(status.permissionError, status.errorMessageHtml);
                    CheckoutPurchaseSidecar.this.setState(3, 5);
                    return;
                default:
                    FinskyLog.wtf("Unknown status returned from server: %d", Integer.valueOf(status.statusCode));
                    CheckoutPurchaseSidecar.this.mCheckoutPurchaseError = new CheckoutPurchaseError(0, null);
                    CheckoutPurchaseSidecar.this.setState(3, 5);
                    return;
            }
        }
    }

    public static CheckoutPurchaseSidecar newInstance(String accountName) {
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        CheckoutPurchaseSidecar result = new CheckoutPurchaseSidecar();
        result.setArguments(args);
        return result;
    }

    public CheckoutPurchaseSidecar() {
        setRetainInstance(true);
    }

    public void onCreate(Bundle savedInstanceState) {
        this.mDfeApi = FinskyApp.get().getDfeApi(getArguments().getString("authAccount"));
        this.mEventLogger = FinskyApp.get().getEventLogger(this.mDfeApi.getAccount());
        super.onCreate(savedInstanceState);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("CheckoutPurchaseSidecar.cart", ParcelableProto.forProto(this.mCart));
        outState.putParcelable("CheckoutPurchaseSidecar.changeSubscription", ParcelableProto.forProto(this.mChangeSubscription));
        outState.putParcelable("CheckoutPurchaseSidecar.prepareChallenge", ParcelableProto.forProto(this.mPrepareChallenge));
        outState.putParcelable("CheckoutPurchaseSidecar.completeChallenge", ParcelableProto.forProto(this.mCompleteChallenge));
        outState.putBundle("CheckoutPurchaseSidecar.extraPurchaseData", this.mExtraPurchaseData);
        outState.putParcelable("CheckoutPurchaseSidecar.successInfo", ParcelableProto.forProto(this.mSuccessInfo));
        outState.putParcelable("CheckoutPurchaseSidecar.checkoutPurchaseError", this.mCheckoutPurchaseError);
        outState.putByteArray("CheckoutPurchaseSidecar.serverLogsCookie", this.mServerLogsCookie);
    }

    protected void restoreFromSavedInstanceState(Bundle savedInstanceState) {
        super.restoreFromSavedInstanceState(savedInstanceState);
        this.mCart = (ClientCart) ParcelableProto.getProtoFromBundle(savedInstanceState, "CheckoutPurchaseSidecar.cart");
        this.mChangeSubscription = (ChangeSubscription) ParcelableProto.getProtoFromBundle(savedInstanceState, "CheckoutPurchaseSidecar.changeSubscription");
        this.mPrepareChallenge = (Challenge) ParcelableProto.getProtoFromBundle(savedInstanceState, "CheckoutPurchaseSidecar.prepareChallenge");
        this.mCompleteChallenge = (Challenge) ParcelableProto.getProtoFromBundle(savedInstanceState, "CheckoutPurchaseSidecar.completeChallenge");
        this.mExtraPurchaseData = savedInstanceState.getBundle("CheckoutPurchaseSidecar.extraPurchaseData");
        this.mSuccessInfo = (SuccessInfo) ParcelableProto.getProtoFromBundle(savedInstanceState, "CheckoutPurchaseSidecar.successInfo");
        this.mCheckoutPurchaseError = (CheckoutPurchaseError) savedInstanceState.getParcelable("CheckoutPurchaseSidecar.checkoutPurchaseError");
        this.mServerLogsCookie = savedInstanceState.getByteArray("CheckoutPurchaseSidecar.serverLogsCookie");
    }

    public void prepare(PurchaseParams purchaseParams, String instrumentId, VoucherParams voucherParams, Bundle prepareChallengeResponses, GaiaAuthParameters gaiaAuthParams, Map<String, String> extraPostParams) {
        this.mPreparePurchaseParams = purchaseParams;
        log(302, 0, -1, -1);
        IabParameters iabParams = this.mPreparePurchaseParams.iabParameters;
        Map<String, String> extraParams = bundleToMap(prepareChallengeResponses);
        if (extraPostParams != null) {
            extraParams.putAll(extraPostParams);
        }
        DfeApi.IabParameters dfeIabParams = iabParams == null ? null : new DfeApi.IabParameters(iabParams.billingApiVersion, iabParams.packageName, iabParams.packageSignatureHash, iabParams.packageVersionCode, iabParams.developerPayload, iabParams.oldSkusAsDocidStrings);
        this.mPrepareStartedMs = System.currentTimeMillis();
        this.mPrepareRequest = this.mDfeApi.preparePurchase(this.mPreparePurchaseParams.docidStr, this.mPreparePurchaseParams.offerType, dfeIabParams, gaiaAuthParams, instrumentId, voucherParams, this.mPreparePurchaseParams.appVersionCode, extraParams, new PrepareListener(), new PrepareErrorListener());
        this.mCommitStartedMs = 0;
        this.mCommitRequest = null;
        setState(1, 1);
    }

    public void commit(Bundle commitChallengeResponses, Map<String, String> extraPostParams) {
        if (this.mCart.completePurchaseChallenge == null || !commitChallengeResponses.keySet().isEmpty()) {
            log(304, 0, -1, -1);
            Map<String, String> postParams = bundleToMap(commitChallengeResponses);
            if (extraPostParams != null) {
                postParams.putAll(extraPostParams);
            }
            String riskHeader = BillingUtils.getRiskHeader();
            this.mCommitStartedMs = System.currentTimeMillis();
            this.mCommitRequest = this.mDfeApi.commitPurchase(this.mCart.purchaseContextToken, postParams, riskHeader, new CommitListener(), new CommitErrorListener());
            setState(1, 2);
            return;
        }
        this.mCompleteChallenge = this.mCart.completePurchaseChallenge;
        setState(7, 0);
    }

    public void escrowCvcCode(String cvc) {
        if (getState() == 1) {
            FinskyLog.wtf("escrowCvcCode() called while RUNNING.", new Object[0]);
        }
        FinskyApp.get().getRequestQueue().add(new EscrowRequest(null, cvc, new EscrowResponseListener(), new EscrowErrorListener()));
        setState(1, 6);
    }

    public void switchFromChangeSubscriptionToCart() {
        if (getState() != 4) {
            FinskyLog.wtf("switchFromChangeSubscriptionToCart() called in state %d", Integer.valueOf(getState()));
        }
        setState(5, 0);
    }

    public void switchToInstrumentManager() {
        if (getState() != 6) {
            FinskyLog.wtf("switchToInstrumentManager() called in state %d", Integer.valueOf(getState()));
        }
        setState(9, 0);
    }

    public void installApp() {
        FinskyApp.get().getInstallerDataStore().setContinueUrl(this.mPreparePurchaseParams.docidStr, this.mPreparePurchaseParams.appContinueUrl);
        FinskyApp.get().getInstaller().requestInstall(this.mPreparePurchaseParams.docidStr, this.mPreparePurchaseParams.appVersionCode, this.mDfeApi.getAccountName(), this.mCart.title, false, "single_install", 2);
    }

    public void confirmAuthChoiceSelected() {
        if (!(getState() == 2 || getSubstate() == 7)) {
            FinskyLog.wtf("confirmAuthChoiceSelected() called in state %d and substate %d", Integer.valueOf(getState()), Integer.valueOf(getSubstate()));
        }
        setState(2, 8);
    }

    private void log(int tag, int errorCode, long serverLatencyMs, long clientLatencyMs) {
        this.mEventLogger.logPurchaseBackgroundEvent(tag, this.mPreparePurchaseParams.docidStr, this.mPreparePurchaseParams.offerType, null, errorCode, this.mServerLogsCookie, serverLatencyMs, clientLatencyMs);
    }

    private void logVolleyError(int tag, VolleyError volleyError, long serverLatencyMs, long clientLatencyMs) {
        this.mEventLogger.logPurchaseBackgroundEvent(tag, this.mPreparePurchaseParams.docidStr, this.mPreparePurchaseParams.offerType, volleyError.getClass().getSimpleName(), 0, this.mServerLogsCookie, serverLatencyMs, clientLatencyMs);
    }

    public byte[] getServerLogsCookie() {
        return this.mServerLogsCookie;
    }

    public Bundle getExtraPurchaseData() {
        return this.mExtraPurchaseData;
    }

    public SuccessInfo getSuccessInfo() {
        return this.mSuccessInfo;
    }

    public Challenge getPrepareChallenge() {
        return this.mPrepareChallenge;
    }

    public Challenge getCompleteChallenge() {
        return this.mCompleteChallenge;
    }

    public String getCvcEscrowHandle() {
        return this.mCvcEscrowHandle;
    }

    public ClientCart getCart() {
        return this.mCart;
    }

    public ChangeSubscription getChangeSubscription() {
        return this.mChangeSubscription;
    }

    public VolleyError getVolleyError() {
        return this.mVolleyError;
    }

    public CheckoutPurchaseError getCheckoutPurchaseError() {
        return this.mCheckoutPurchaseError;
    }

    private void handleLibraryUpdates(LibraryUpdate[] libraryUpdates, String debugEventName, Runnable postUpdateCallback) {
        FinskyApp.get().getLibraryReplicators().applyLibraryUpdates(this.mDfeApi.getAccount(), debugEventName, postUpdateCallback, libraryUpdates);
        this.mExtraPurchaseData = null;
        LibraryUpdate[] arr$ = libraryUpdates;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            this.mExtraPurchaseData = extractExtraPurchaseData(arr$[i$], this.mPreparePurchaseParams.docid);
            if (this.mExtraPurchaseData == null) {
                i$++;
            } else {
                return;
            }
        }
    }

    private long getTimeElapsedSincePrepareMs() {
        if (this.mPrepareStartedMs > 0) {
            return System.currentTimeMillis() - this.mPrepareStartedMs;
        }
        FinskyLog.wtf("Prepare not started.", new Object[0]);
        return 0;
    }

    private long getPrepareServerLatencyMs() {
        if (this.mPrepareRequest != null) {
            return this.mPrepareRequest.getServerLatencyMs();
        }
        FinskyLog.wtf("Unexpected null prepare request.", new Object[0]);
        return -1;
    }

    private long getTimeElapsedSinceCommitMs() {
        if (this.mCommitStartedMs > 0) {
            return System.currentTimeMillis() - this.mCommitStartedMs;
        }
        FinskyLog.wtf("Commit not started.", new Object[0]);
        return -1;
    }

    private long getCommitServerLatencyMs() {
        if (this.mCommitRequest != null) {
            return this.mCommitRequest.getServerLatencyMs();
        }
        FinskyLog.wtf("Unexpected null commit request.", new Object[0]);
        return -1;
    }

    public static Bundle extractExtraPurchaseData(LibraryUpdate libraryUpdate, Docid docid) {
        if (libraryUpdate == null || libraryUpdate.mutation == null) {
            return null;
        }
        for (LibraryMutation mutation : libraryUpdate.mutation) {
            Bundle extraParams;
            if (mutation.docid.type == 11 && TextUtils.equals(mutation.docid.backendDocid, docid.backendDocid) && mutation.inAppDetails != null) {
                LibraryInAppDetails inAppDetails = mutation.inAppDetails;
                if (inAppDetails.hasSignature && inAppDetails.hasSignedPurchaseData) {
                    extraParams = new Bundle();
                    extraParams.putString("inapp_signed_purchase_data", inAppDetails.signedPurchaseData);
                    extraParams.putString("inapp_purchase_data_signature", inAppDetails.signature);
                    return extraParams;
                }
            } else if (mutation.docid.type == 15 && TextUtils.equals(mutation.docid.backendDocid, docid.backendDocid) && mutation.subscriptionDetails != null) {
                LibrarySubscriptionDetails subsDetails = mutation.subscriptionDetails;
                if (subsDetails.hasSignature && subsDetails.hasSignedPurchaseData) {
                    extraParams = new Bundle();
                    extraParams.putString("inapp_signed_purchase_data", subsDetails.signedPurchaseData);
                    extraParams.putString("inapp_purchase_data_signature", subsDetails.signature);
                    return extraParams;
                }
            }
        }
        return null;
    }

    private static Map<String, String> bundleToMap(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        Map<String, String> result = Maps.newHashMap();
        for (String key : bundle.keySet()) {
            result.put(key, bundle.getString(key));
        }
        return result;
    }
}
