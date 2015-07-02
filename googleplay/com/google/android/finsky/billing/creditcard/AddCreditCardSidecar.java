package com.google.android.finsky.billing.creditcard;

import android.os.Bundle;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.protos.BuyInstruments.UpdateInstrumentRequest;
import com.google.android.finsky.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;

public class AddCreditCardSidecar extends SidecarFragment implements ErrorListener, Listener<UpdateInstrumentResponse> {
    private UpdateInstrumentResponse mAddCreditCardResponse;
    private DfeApi mDfeApi;
    private String mErrorHtml;
    private FinskyEventLog mEventLog;
    private Instrument mInstrument;
    private VolleyError mVolleyError;

    private class EscrowErrorListener implements ErrorListener {
        private EscrowErrorListener() {
        }

        public void onErrorResponse(VolleyError error) {
            AddCreditCardSidecar.this.logError(2, error.getClass().getSimpleName());
            FinskyLog.w("Error during escrowing: %s", error);
            AddCreditCardSidecar.this.setVolleyError(error, 1);
        }
    }

    private class EscrowResponseListener implements Listener<String> {
        private EscrowResponseListener() {
        }

        public void onResponse(String response) {
            AddCreditCardSidecar.this.mInstrument.creditCard.escrowHandle = response;
            AddCreditCardSidecar.this.mInstrument.creditCard.hasEscrowHandle = true;
            AddCreditCardSidecar.this.performRequest();
        }
    }

    public static AddCreditCardSidecar newInstance(String accountName) {
        AddCreditCardSidecar result = new AddCreditCardSidecar();
        Bundle arguments = new Bundle();
        arguments.putString("authAccount", accountName);
        result.setArguments(arguments);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        this.mDfeApi = FinskyApp.get().getDfeApi(getArguments().getString("authAccount"));
        this.mEventLog = FinskyApp.get().getEventLogger(this.mDfeApi.getAccount());
        super.onCreate(savedInstanceState);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("AddCreditCardSidecar.message", this.mErrorHtml);
        outState.putParcelable("AddCreditCardSidecar.addCreditCardResponse", ParcelableProto.forProto(this.mAddCreditCardResponse));
    }

    protected void restoreFromSavedInstanceState(Bundle savedInstanceState) {
        super.restoreFromSavedInstanceState(savedInstanceState);
        if (getSubstate() == 2) {
            setState(0, 0);
        }
        this.mErrorHtml = savedInstanceState.getString("AddCreditCardSidecar.message");
        this.mAddCreditCardResponse = (UpdateInstrumentResponse) ParcelableProto.getProtoFromBundle(savedInstanceState, "AddCreditCardSidecar.addCreditCardResponse");
    }

    public void saveCreditCard(String creditCardNumber, String cvc, Instrument instrument) {
        if (getState() == 1) {
            FinskyLog.wtf("saveCreditCard() called while RUNNING.", new Object[0]);
        }
        this.mEventLog.logBackgroundEvent(330, null, null, 0, null, null);
        clearState();
        this.mInstrument = instrument;
        queueEscrowCredentialsRequest(creditCardNumber, cvc);
        setState(1, 0);
    }

    private void clearState() {
        this.mInstrument = null;
        this.mErrorHtml = null;
        this.mVolleyError = null;
        this.mAddCreditCardResponse = null;
    }

    private static boolean isInputValidationResponse(UpdateInstrumentResponse updateFopResponse) {
        return updateFopResponse.result == 2;
    }

    private boolean isSuccess(UpdateInstrumentResponse updateFopResponse) {
        return updateFopResponse.result == 0;
    }

    public UpdateInstrumentResponse getResponse() {
        return this.mAddCreditCardResponse;
    }

    public VolleyError getVolleyError() {
        return this.mVolleyError;
    }

    public String getErrorHtml() {
        return this.mErrorHtml;
    }

    private void queueEscrowCredentialsRequest(String creditCardNumber, String cvc) {
        FinskyApp.get().getRequestQueue().add(new EscrowRequest(creditCardNumber, cvc, new EscrowResponseListener(), new EscrowErrorListener()));
    }

    private void setErrorWithMessage(String htmlMessage) {
        this.mErrorHtml = htmlMessage;
        setState(3, 3);
    }

    private void setErrorWithMessageWithChoice(String htmlMessage) {
        this.mErrorHtml = htmlMessage;
        setState(3, 4);
    }

    private void setVolleyError(VolleyError error, int substate) {
        this.mVolleyError = error;
        setState(3, substate);
    }

    private void logError(int errorCode, String exceptionType) {
        this.mEventLog.logBackgroundEvent(332, null, null, errorCode, exceptionType, null);
    }

    public void onResponse(UpdateInstrumentResponse response) {
        this.mAddCreditCardResponse = response;
        if (isSuccess(this.mAddCreditCardResponse)) {
            this.mEventLog.logBackgroundEvent(331, null, null, 0, null, null);
            setState(2, 0);
        } else if (isInputValidationResponse(this.mAddCreditCardResponse)) {
            logError(4, null);
            setState(3, 5);
        } else if (this.mAddCreditCardResponse.hasUserMessageHtml) {
            logError(5, null);
            setErrorWithMessageWithChoice(this.mAddCreditCardResponse.userMessageHtml);
        } else {
            logError(0, null);
            setErrorWithMessage(FinskyApp.get().getString(R.string.add_credit_card_error));
        }
    }

    public void onErrorResponse(VolleyError error) {
        FinskyLog.w("Error received: %s", error);
        setVolleyError(error, 2);
    }

    private void performRequest() {
        UpdateInstrumentRequest request = new UpdateInstrumentRequest();
        request.instrument = this.mInstrument;
        this.mDfeApi.updateInstrument(request, this, this);
    }
}
