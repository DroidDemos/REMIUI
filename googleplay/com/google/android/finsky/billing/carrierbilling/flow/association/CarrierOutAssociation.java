package com.google.android.finsky.billing.carrierbilling.flow.association;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.android.volley.NoConnectionError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.carrierbilling.flow.association.SmsSender.SmsSendListener;
import com.google.android.finsky.billing.carrierbilling.flow.association.SmsSender.SmsSendListener.SmsSenderResult;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.CarrierBilling.InitiateAssociationResponse;
import com.google.android.finsky.protos.CarrierBilling.VerifyAssociationResponse;
import com.google.android.finsky.utils.FinskyLog;

public class CarrierOutAssociation implements ErrorListener, Listener<InitiateAssociationResponse>, AssociationAction, SmsSendListener {
    private final String mAcceptedPiiTosVersion;
    private final DfeApi mDfeApi;
    private ErrorListener mErrorListener;
    private final boolean mGetSubscriberAddress;
    private Listener<VerifyAssociationResponse> mListener;
    private String mSmsAddress;
    private String mSmsPrefix;
    private State mState;
    private final CarrierBillingStorage mStorage;
    private int mVerificationRetries;

    private enum State {
        INITIATING_ASSOCIATION,
        VERIFYING_ASSOCIATION
    }

    public CarrierOutAssociation(DfeApi dfeApi, String smsAddress, String smsPrefix, String acceptedPiiTosVersion, boolean getSubscriberAddress) {
        this(dfeApi, BillingLocator.getCarrierBillingStorage(), smsAddress, smsPrefix, acceptedPiiTosVersion, getSubscriberAddress);
    }

    CarrierOutAssociation(DfeApi dfeApi, CarrierBillingStorage storage, String smsAddress, String smsPrefix, String acceptedPiiTosVersion, boolean getSubscriberAddress) {
        this.mState = State.INITIATING_ASSOCIATION;
        this.mStorage = storage;
        this.mDfeApi = dfeApi;
        this.mSmsAddress = smsAddress;
        this.mSmsPrefix = smsPrefix;
        this.mAcceptedPiiTosVersion = acceptedPiiTosVersion;
        this.mGetSubscriberAddress = getSubscriberAddress;
    }

    public void start(Listener<VerifyAssociationResponse> listener, ErrorListener errorListener) {
        this.mListener = listener;
        this.mErrorListener = errorListener;
        initiateAssociation();
    }

    public void cancel() {
        this.mListener = null;
        this.mErrorListener = null;
    }

    public void setListener(Listener<VerifyAssociationResponse> listener, ErrorListener errorListener) {
        this.mListener = listener;
        this.mErrorListener = errorListener;
    }

    private void sendGutToCarrier(String googleUserToken) {
        String addressToSendSms = this.mSmsAddress;
        if (TextUtils.isEmpty(addressToSendSms)) {
            FinskyLog.w("Address to send SMS is unavailable", new Object[0]);
            dispatchError();
            return;
        }
        SmsSender.send(FinskyApp.get(), addressToSendSms, null, formattedTextToSend(googleUserToken), this);
        verifyAssociation();
    }

    private String formattedTextToSend(String googleUserToken) {
        return (this.mSmsPrefix + ":") + googleUserToken;
    }

    public void onResult(SmsSenderResult response) {
        if (response == SmsSenderResult.RESULT_ERROR) {
            FinskyLog.w("Sending Sms Failed", new Object[0]);
            dispatchError();
            cancel();
        }
    }

    public void onResponse(InitiateAssociationResponse response) {
        if (this.mListener != null) {
            String googleUserToken = response.userToken;
            if (TextUtils.isEmpty(googleUserToken)) {
                FinskyLog.w("Invalid Google User Token received.", new Object[0]);
                dispatchError();
                return;
            }
            sendGutToCarrier(googleUserToken);
        }
    }

    private void dispatchError() {
        VerifyAssociationResponse response = new VerifyAssociationResponse();
        response.status = 4;
        response.hasStatus = true;
        dispatch(response);
    }

    public void onErrorResponse(VolleyError error) {
        if (shouldRetryVerification(error)) {
            this.mVerificationRetries++;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    CarrierOutAssociation.this.verifyAssociation();
                }
            }, 3000);
            return;
        }
        FinskyLog.w("Error while receiving Volley response in state " + this.mState.name(), new Object[0]);
        if (this.mErrorListener != null) {
            this.mErrorListener.onErrorResponse(error);
        }
    }

    private boolean shouldRetryVerification(VolleyError error) {
        return this.mVerificationRetries < ((Integer) G.dcb3VerifyAssociationRetries.get()).intValue() && (error instanceof NoConnectionError) && this.mState == State.VERIFYING_ASSOCIATION;
    }

    private void dispatch(VerifyAssociationResponse response) {
        if (this.mListener != null) {
            this.mListener.onResponse(response);
        }
    }

    private void initiateAssociation() {
        this.mState = State.INITIATING_ASSOCIATION;
        this.mDfeApi.initiateAssociation(this.mStorage.getCurrentSimIdentifier(), this, this);
    }

    private void verifyAssociation() {
        this.mState = State.VERIFYING_ASSOCIATION;
        if (this.mListener != null) {
            this.mDfeApi.verifyAssociation(this.mStorage.getCurrentSimIdentifier(), this.mAcceptedPiiTosVersion, this.mGetSubscriberAddress, new Listener<VerifyAssociationResponse>() {
                public void onResponse(VerifyAssociationResponse response) {
                    CarrierOutAssociation.this.dispatch(response);
                }
            }, this);
        }
    }

    public void saveState(Bundle bundle) {
        bundle.putString("association_state", this.mState.name());
        bundle.putInt("association_retries", this.mVerificationRetries);
        cancel();
    }

    public void resumeState(Bundle bundle, Listener<VerifyAssociationResponse> listener, ErrorListener errorListener) {
        this.mState = State.valueOf(bundle.getString("association_state"));
        this.mVerificationRetries = bundle.getInt("association_retries");
        this.mListener = listener;
        this.mErrorListener = errorListener;
        if (this.mState == State.VERIFYING_ASSOCIATION) {
            verifyAssociation();
        } else {
            initiateAssociation();
        }
    }
}
