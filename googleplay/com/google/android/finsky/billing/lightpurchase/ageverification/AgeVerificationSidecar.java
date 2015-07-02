package com.google.android.finsky.billing.lightpurchase.ageverification;

import android.os.Bundle;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.protos.ChallengeAction.ChallengeResponse;
import com.google.android.finsky.protos.ChallengeProto.AgeChallenge;
import com.google.android.finsky.protos.ChallengeProto.Challenge;
import com.google.android.finsky.protos.ChallengeProto.ChallengeError;
import com.google.android.finsky.protos.ChallengeProto.SmsCodeChallenge;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import java.util.Map;

public class AgeVerificationSidecar extends SidecarFragment implements ErrorListener, Listener<ChallengeResponse>, OnDataChangedListener {
    private DfeApi mDfeApi;
    private DfeDetails mDfeDetails;
    private String mErrorHtml;
    private FinskyEventLog mEventLogger;
    private Challenge mLastChallenge;

    public static AgeVerificationSidecar newInstance(String accountName) {
        AgeVerificationSidecar result = new AgeVerificationSidecar();
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        this.mDfeApi = FinskyApp.get().getDfeApi(getArguments().getString("authAccount"));
        this.mEventLogger = FinskyApp.get().getEventLogger(this.mDfeApi.getAccount());
        super.onCreate(savedInstanceState);
    }

    public void checkDocumentMaturity(String docidStr) {
        if (docidStr == null) {
            setState(4, 0);
            return;
        }
        this.mDfeDetails = new DfeDetails(this.mDfeApi, DfeUtils.createDetailsUrlFromId(docidStr));
        this.mDfeDetails.addDataChangedListener(this);
        this.mDfeDetails.addErrorListener(this);
        setState(1, 0);
    }

    public void requestAgeVerificationForm() {
        this.mDfeApi.requestAgeVerificationForm(this, this);
        setState(1, 0);
    }

    public void verifyAge(String url, Map<String, String> postParams) {
        this.mDfeApi.verifyAge(url, postParams, this, this);
        setState(1, 0);
    }

    public void resendSmsCode(String url) {
        this.mDfeApi.resendAgeVerificationCode(url, this, this);
        setState(1, 0);
    }

    public void verifySmsCode(String url, String codeParamKey, String code) {
        this.mDfeApi.verifyAgeVerificationCode(url, codeParamKey, code, this, this);
        setState(1, 0);
    }

    public void startChallenge(Challenge challenge) {
        this.mLastChallenge = challenge;
        if (this.mLastChallenge.ageChallenge != null) {
            setState(5, 0);
        } else if (this.mLastChallenge.smsCodeChallenge != null) {
            setState(6, 0);
        } else {
            throw new IllegalStateException("Received unknown challenge.");
        }
    }

    public AgeChallenge getAgeChallenge() {
        if (getState() == 5) {
            return this.mLastChallenge.ageChallenge;
        }
        throw new IllegalStateException("Invalid state: " + getState());
    }

    public SmsCodeChallenge getSmsCodeChallenge() {
        if (getState() == 6) {
            return this.mLastChallenge.smsCodeChallenge;
        }
        throw new IllegalStateException("Invalid state: " + getState());
    }

    public ChallengeError getChallengeError() {
        if (getState() == 3 && getSubstate() == 1) {
            return this.mLastChallenge.error;
        }
        throw new IllegalStateException("Invalid state: " + getState() + " with substate: " + getSubstate());
    }

    public String getErrorHtml() {
        if (getState() == 3 && getSubstate() != 1) {
            return this.mErrorHtml;
        }
        throw new IllegalStateException("Invalid state: " + getState() + " with substate: " + getSubstate());
    }

    private void logBackgroundEvent(int resultCode, String exceptionType) {
        BackgroundEventBuilder builder = new BackgroundEventBuilder(518).setExceptionType(exceptionType);
        if (resultCode != -1) {
            builder.setErrorCode(resultCode);
        }
        this.mEventLogger.logBackgroundEvent(builder.build());
    }

    public void onDataChanged() {
        Document doc = this.mDfeDetails.getDocument();
        if (doc == null) {
            this.mErrorHtml = getString(R.string.item_unavailable_message);
            setState(3, 0);
        } else if (doc.isMature()) {
            setState(4, 0);
        } else {
            setState(7, 0);
        }
    }

    public void onResponse(ChallengeResponse response) {
        this.mLastChallenge = response.challenge;
        if (this.mLastChallenge == null) {
            if (response.challengePassed) {
                logBackgroundEvent(-1, null);
                setState(2, 0);
                return;
            }
            throw new IllegalStateException("Received no challenge.");
        } else if (this.mLastChallenge.ageChallenge != null) {
            logBackgroundEvent(2, null);
            setState(5, 0);
        } else if (this.mLastChallenge.smsCodeChallenge != null) {
            logBackgroundEvent(3, null);
            setState(6, 0);
        } else if (this.mLastChallenge.error != null) {
            logBackgroundEvent(4, null);
            setState(3, 1);
        } else {
            throw new IllegalStateException("Received unknown challenge.");
        }
    }

    public void onErrorResponse(VolleyError error) {
        FinskyLog.w("Volley error received: %s", error);
        logBackgroundEvent(1, error.getClass().getSimpleName());
        this.mErrorHtml = ErrorStrings.get(FinskyApp.get(), error);
        setState(3, 0);
    }
}
