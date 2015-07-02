package com.google.android.finsky.billing.giftcard;

import android.os.Bundle;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.protos.Acquisition.SuccessInfo;
import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.protos.ChallengeProto.AddressChallenge;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryUpdate;
import com.google.android.finsky.protos.PromoCode.RedeemCodeRequest;
import com.google.android.finsky.protos.PromoCode.RedeemCodeResponse;
import com.google.android.finsky.protos.PromoCode.UserConfirmationChallenge;
import com.google.android.finsky.protos.SingleFopPaymentsIntegrator.SingleFopPaymentsIntegratorContext;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.pub.InstrumentManagerUtil;

public class RedeemCodeSidecar extends SidecarFragment {
    private DfeApi mDfeApi;
    private String mErrorHtml;
    private FinskyEventLog mEventLogger;
    private RedeemCodeResponse mLastRedeemCodeResponse;
    private final RedemptionListener mRedemptionListener;
    private RedeemCodeRequest mRequest;
    private VolleyError mVolleyError;

    private class RedemptionListener implements ErrorListener, Listener<RedeemCodeResponse> {
        private RedemptionListener() {
        }

        public void onResponse(RedeemCodeResponse response) {
            RedeemCodeSidecar.this.mLastRedeemCodeResponse = response;
            int resultCode = response.result;
            RedeemCodeSidecar.this.logBackgroundEvent(801, resultCode, response.serverLogsCookie);
            switch (resultCode) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    LibraryUpdate libraryUpdate;
                    if (response.successInfo == null) {
                        libraryUpdate = null;
                    } else {
                        libraryUpdate = response.successInfo.libraryUpdate;
                    }
                    FinskyApp.get().getLibraryReplicators().applyLibraryUpdates(RedeemCodeSidecar.this.mDfeApi.getAccount(), "redeem-code-sidecar", new Runnable() {
                        public void run() {
                            RedeemCodeSidecar.this.setState(2, 0);
                        }
                    }, libraryUpdate);
                    return;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    RedeemCodeSidecar.this.setState(4, 0);
                    return;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    RedeemCodeSidecar.this.setState(5, 0);
                    return;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    RedeemCodeSidecar.this.setState(6, 0);
                    return;
                default:
                    FinskyLog.w("Received error/unknown result: %d", Integer.valueOf(response.result));
                    RedeemCodeSidecar.this.mErrorHtml = response.errorMessageHtml;
                    RedeemCodeSidecar.this.setState(3, 0);
                    return;
            }
        }

        public void onErrorResponse(VolleyError volleyError) {
            RedeemCodeSidecar.this.mVolleyError = volleyError;
            RedeemCodeSidecar.this.mEventLogger.logBackgroundEvent(801, null, null, -1, volleyError.getClass().getSimpleName(), null);
            RedeemCodeSidecar.this.setState(3, 1);
        }
    }

    public RedeemCodeSidecar() {
        this.mRedemptionListener = new RedemptionListener();
        this.mRequest = new RedeemCodeRequest();
    }

    public static RedeemCodeSidecar newInstance(String accountName, int redemptionContext, Docid docid, int offerType, String partnerPayload) {
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putInt("RedeemCodeSidecar.redemption_context", redemptionContext);
        args.putParcelable("RedeemCodeSidecar.docid", ParcelableProto.forProto(docid));
        args.putInt("RedeemCodeSidecar.offer_type", offerType);
        args.putString("RedeemCodeSidecar.partner_payload", partnerPayload);
        RedeemCodeSidecar result = new RedeemCodeSidecar();
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        String accountName = getArguments().getString("authAccount");
        this.mDfeApi = FinskyApp.get().getDfeApi(accountName);
        this.mEventLogger = FinskyApp.get().getEventLogger(accountName);
        if (savedInstanceState != null) {
            this.mRequest = (RedeemCodeRequest) ParcelableProto.getProtoFromBundle(savedInstanceState, "RedeemCodeSidecar.request");
            this.mLastRedeemCodeResponse = (RedeemCodeResponse) ParcelableProto.getProtoFromBundle(savedInstanceState, "RedeemCodeSidecar.last_redeem_code_response");
            this.mErrorHtml = savedInstanceState.getString("RedeemCodeSidecar.error_html");
        } else {
            this.mRequest.redemptionContext = getArguments().getInt("RedeemCodeSidecar.redemption_context");
            this.mRequest.hasRedemptionContext = true;
            this.mRequest.docid = (Docid) ParcelableProto.getProtoFromBundle(getArguments(), "RedeemCodeSidecar.docid");
            int offerType = getArguments().getInt("RedeemCodeSidecar.offer_type");
            if (offerType != 0) {
                this.mRequest.offerType = offerType;
                this.mRequest.hasOfferType = true;
            }
            String partnerPayload = getArguments().getString("RedeemCodeSidecar.partner_payload");
            if (partnerPayload != null) {
                this.mRequest.partnerPayload = partnerPayload;
                this.mRequest.hasPartnerPayload = true;
            }
        }
        this.mRequest.paymentsIntegratorClientContextToken = InstrumentManagerUtil.createClientToken(getActivity());
        this.mRequest.hasPaymentsIntegratorClientContextToken = true;
        super.onCreate(savedInstanceState);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("RedeemCodeSidecar.request", ParcelableProto.forProto(this.mRequest));
        outState.putParcelable("RedeemCodeSidecar.last_redeem_code_response", ParcelableProto.forProto(this.mLastRedeemCodeResponse));
        outState.putString("RedeemCodeSidecar.error_html", this.mErrorHtml);
    }

    public String getErrorHtml() {
        return this.mErrorHtml;
    }

    public VolleyError getVolleyError() {
        return this.mVolleyError;
    }

    public void redeem(String code) {
        this.mRequest.code = code;
        this.mRequest.hasCode = true;
        sendRedemptionRequest();
    }

    public void respondWithConfirmation() {
        if (getState() != 5) {
            FinskyLog.wtf("Invalid state: %d", Integer.valueOf(getState()));
            return;
        }
        this.mRequest.hasUserConfirmation = true;
        sendRedemptionRequest();
    }

    public void respondWithAddress(Address address, String[] checkedCheckboxIds) {
        if (getState() != 4) {
            FinskyLog.wtf("Invalid state: %d", Integer.valueOf(getState()));
            return;
        }
        this.mRequest.address = address;
        this.mRequest.addressCheckedCheckboxId = checkedCheckboxIds;
        sendRedemptionRequest();
    }

    public void respondInstrumentManagerSucceeded() {
        if (getState() != 6) {
            FinskyLog.wtf("Invalid state: %d", Integer.valueOf(getState()));
            return;
        }
        sendRedemptionRequest();
    }

    private void sendRedemptionRequest() {
        this.mEventLogger.logBackgroundEvent(800, null);
        if (this.mLastRedeemCodeResponse == null || !this.mLastRedeemCodeResponse.hasToken) {
            this.mRequest.token = "";
            this.mRequest.hasToken = false;
        } else {
            this.mRequest.token = this.mLastRedeemCodeResponse.token;
            this.mRequest.hasToken = true;
        }
        this.mRequest.consumptionAppVersionCode = 0;
        this.mRequest.hasConsumptionAppVersionCode = false;
        if (!(this.mLastRedeemCodeResponse == null || this.mLastRedeemCodeResponse.consumptionAppDocid == null || this.mLastRedeemCodeResponse.consumptionAppDocid.backend != 3)) {
            PackageState packageState = FinskyApp.get().getPackageInfoRepository().get(this.mLastRedeemCodeResponse.consumptionAppDocid.backendDocid);
            if (packageState != null) {
                this.mRequest.consumptionAppVersionCode = (long) packageState.installedVersion;
                this.mRequest.hasConsumptionAppVersionCode = true;
            }
        }
        this.mLastRedeemCodeResponse = null;
        this.mVolleyError = null;
        this.mErrorHtml = null;
        this.mDfeApi.redeemCode(this.mRequest, this.mRedemptionListener, this.mRedemptionListener);
        setState(1, 0);
    }

    public String getLastRedeemCode() {
        return this.mRequest == null ? null : this.mRequest.code;
    }

    public UserConfirmationChallenge getUserConfirmationChallenge() {
        if (getState() == 5) {
            return this.mLastRedeemCodeResponse.userConfirmationChallenge;
        }
        FinskyLog.wtf("Invalid state: %d", Integer.valueOf(getState()));
        return null;
    }

    public AddressChallenge getAddressChallenge() {
        if (getState() == 4) {
            return this.mLastRedeemCodeResponse.addressChallenge;
        }
        FinskyLog.wtf("Invalid state: %d", Integer.valueOf(getState()));
        return null;
    }

    public SingleFopPaymentsIntegratorContext getInstrumentManagerTokens() {
        if (getState() == 6) {
            return this.mLastRedeemCodeResponse.paymentsIntegratorContext;
        }
        FinskyLog.wtf("Invalid state: %d", Integer.valueOf(getState()));
        return null;
    }

    public SuccessInfo getSuccessInfo() {
        if (getState() == 2) {
            return this.mLastRedeemCodeResponse.successInfo;
        }
        FinskyLog.wtf("Invalid state: %d", Integer.valueOf(getState()));
        return null;
    }

    public String getRedeemedOfferHtml() {
        if (getState() != 2) {
            FinskyLog.wtf("Invalid state: %d", Integer.valueOf(getState()));
            return null;
        } else if (this.mLastRedeemCodeResponse.redeemedOffer != null) {
            return this.mLastRedeemCodeResponse.redeemedOffer.descriptionHtml;
        } else {
            return null;
        }
    }

    public String getStoredValueInstrumentId() {
        if (getState() == 2) {
            return this.mLastRedeemCodeResponse.storedValueInstrumentId;
        }
        FinskyLog.wtf("Invalid state: %d", Integer.valueOf(getState()));
        return null;
    }

    public Document getRedeemedItemDoc() {
        return (this.mLastRedeemCodeResponse == null || this.mLastRedeemCodeResponse.doc == null) ? null : new Document(this.mLastRedeemCodeResponse.doc);
    }

    public Docid getConsumptionAppDocid() {
        return this.mLastRedeemCodeResponse == null ? null : this.mLastRedeemCodeResponse.consumptionAppDocid;
    }

    private void logBackgroundEvent(int type, int resultCode, byte[] serverLogsCookie) {
        BackgroundEventBuilder builder = new BackgroundEventBuilder(type).setServerLogsCookie(serverLogsCookie);
        if (resultCode != 1) {
            builder.setErrorCode(resultCode);
        }
        this.mEventLogger.logBackgroundEvent(builder.build());
    }
}
