package com.google.android.wallet.instrumentmanager.sidecar;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.wallet.instrumentmanager.analytics.util.AnalyticsUtil;
import com.google.android.wallet.instrumentmanager.api.http.BackgroundEventRequest;
import com.google.android.wallet.instrumentmanager.api.http.SecureRefreshPageRequest;
import com.google.android.wallet.instrumentmanager.api.http.SecureSavePageRequest;
import com.google.android.wallet.instrumentmanager.common.util.ParcelableProto;
import com.google.android.wallet.instrumentmanager.common.util.SmsSender;
import com.google.android.wallet.instrumentmanager.common.util.SmsSender.SmsSendListener;
import com.google.android.wallet.instrumentmanager.config.G.dcb;
import com.google.android.wallet.instrumentmanager.sidecar.BaseInstrumentManagerSidecar.InstrumentManagerErrorListener;
import com.google.commerce.payments.orchestration.proto.api.integratorbuyflow.DataTokens.AndroidEnvironmentConfig;
import com.google.commerce.payments.orchestration.proto.ui.common.ResponseContextOuterClass.ResponseContext;
import com.google.commerce.payments.orchestration.proto.ui.common.UiErrorOuterClass.UiError;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.RefreshPageRequest;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.RefreshPageResponse;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.SavePageRequest;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.SavePageResponse;

public class InstrumentManagerSidecar extends BaseInstrumentManagerSidecar {
    static final Object REQUEST_DEPENDENT_ON_SMS_TAG;
    private RefreshPageResponse mRefreshPageResponse;
    private SavePageResponse mSavePageResponse;
    boolean mSendingRequestAfterSms;

    public final class InstrumentManagerSmsSendListener implements SmsSendListener {
        private final byte[] mPreviousResponseLogToken;

        public InstrumentManagerSmsSendListener(byte[] previousResponseLogToken) {
            this.mPreviousResponseLogToken = previousResponseLogToken;
        }

        public void onResult(int result) {
            int resultCode = 0;
            if (result == 2) {
                resultCode = 4;
                InstrumentManagerSidecar.this.cancelRequestsByTag(InstrumentManagerSidecar.REQUEST_DEPENDENT_ON_SMS_TAG);
                InstrumentManagerSidecar.this.setState(3, 1000);
            }
            AnalyticsUtil.createAndSendResponseReceivedBackgroundEvent(771, resultCode, this.mPreviousResponseLogToken);
        }
    }

    class RefreshPageResponseListener extends BackgroundEventRequestResponseListener<SecureRefreshPageRequest, RefreshPageResponse> {
        RefreshPageResponseListener() {
        }

        public void handleResponse(SecureRefreshPageRequest request, RefreshPageResponse response) {
            InstrumentManagerSidecar.this.mRefreshPageResponse = response;
            InstrumentManagerSidecar.this.updateStateAndSendAnalyticsEvent(request, response.context, response.error);
        }
    }

    class SavePageResponseListener extends BackgroundEventRequestResponseListener<SecureSavePageRequest, SavePageResponse> {
        SavePageResponseListener() {
        }

        public void handleResponse(SecureSavePageRequest request, SavePageResponse response) {
            InstrumentManagerSidecar.this.mSavePageResponse = response;
            InstrumentManagerSidecar.this.updateStateAndSendAnalyticsEvent(request, response.context, response.error);
        }
    }

    static {
        REQUEST_DEPENDENT_ON_SMS_TAG = new Object();
    }

    public static InstrumentManagerSidecar newInstance(AndroidEnvironmentConfig androidConfig) {
        InstrumentManagerSidecar fragment = new InstrumentManagerSidecar();
        fragment.setArguments(BaseInstrumentManagerSidecar.createArgs(androidConfig));
        return fragment;
    }

    public void savePage(SavePageRequest request, ResponseContext previousResponseContext) {
        savePage(request, previousResponseContext, null);
    }

    private void savePage(SavePageRequest request, ResponseContext previousResponseContext, Object requestTag) {
        if (request.context != null) {
            throw new IllegalArgumentException("SavePageRequest's RequestContext should not be set by the caller");
        }
        InstrumentManagerErrorListener errorListener = new InstrumentManagerErrorListener(previousResponseContext.logToken);
        SecureSavePageRequest networkRequest = new SecureSavePageRequest(this.mApiContext, request, previousResponseContext.sessionData, new SavePageResponseListener(), errorListener);
        networkRequest.setTag(requestTag);
        errorListener.setRequest(networkRequest);
        sendRequest(networkRequest);
        AnalyticsUtil.createAndSendRequestSentBackgroundEvent(720, previousResponseContext.logToken);
        this.mSendingRequestAfterSms = false;
    }

    public void sendSmsAndSavePage(String destinationAddress, String text, SavePageRequest request, ResponseContext previousResponseContext) {
        new SmsSender(getActivity().getApplicationContext(), new InstrumentManagerSmsSendListener(previousResponseContext.logToken)).send(destinationAddress, text);
        savePage(request, previousResponseContext, REQUEST_DEPENDENT_ON_SMS_TAG);
        this.mSendingRequestAfterSms = true;
    }

    protected boolean shouldRetryNoConnectionErrors(int retryCount) {
        return this.mSendingRequestAfterSms && retryCount < ((Integer) dcb.verifyAssociationRetries.get()).intValue();
    }

    public void refreshPage(RefreshPageRequest request, ResponseContext previousResponseContext) {
        if (request.context != null) {
            throw new IllegalArgumentException("RefreshPageRequest's RequestContext should not be set by the caller");
        }
        InstrumentManagerErrorListener errorListener = new InstrumentManagerErrorListener(previousResponseContext.logToken);
        SecureRefreshPageRequest networkRequest = new SecureRefreshPageRequest(this.mApiContext, request, previousResponseContext.sessionData, new RefreshPageResponseListener(), errorListener);
        errorListener.setRequest(networkRequest);
        sendRequest(networkRequest);
        AnalyticsUtil.createAndSendRequestSentBackgroundEvent(722, previousResponseContext.logToken);
        this.mSendingRequestAfterSms = false;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mSavePageResponse != null) {
            outState.putParcelable("savePageResponse", ParcelableProto.forProto(this.mSavePageResponse));
        }
        if (this.mRefreshPageResponse != null) {
            outState.putParcelable("refreshPageResponse", ParcelableProto.forProto(this.mRefreshPageResponse));
        }
        outState.putBoolean("sendingRequestAfterSms", this.mSendingRequestAfterSms);
    }

    protected void restoreFromSavedInstanceState(Bundle savedInstanceState) {
        super.restoreFromSavedInstanceState(savedInstanceState);
        this.mSavePageResponse = (SavePageResponse) ParcelableProto.getProtoFromBundle(savedInstanceState, "savePageResponse");
        this.mRefreshPageResponse = (RefreshPageResponse) ParcelableProto.getProtoFromBundle(savedInstanceState, "refreshPageResponse");
        this.mSendingRequestAfterSms = savedInstanceState.getBoolean("sendingRequestAfterSms");
    }

    public SavePageResponse getSavePageResponse() {
        return this.mSavePageResponse;
    }

    public RefreshPageResponse getRefreshPageResponse() {
        return this.mRefreshPageResponse;
    }

    protected void clearPreviousResponses() {
        this.mSavePageResponse = null;
        this.mRefreshPageResponse = null;
    }

    private void updateStateAndSendAnalyticsEvent(BackgroundEventRequest<?> request, ResponseContext responseContext, UiError error) {
        int analyticsResultCode;
        if (error == null) {
            setState(2, 0);
            analyticsResultCode = 0;
        } else {
            if (!TextUtils.isEmpty(error.internalDetails)) {
                Log.e("InstrumentManagerSidecar", error.internalDetails);
            }
            if (!TextUtils.isEmpty(error.message)) {
                setState(3, 5);
                analyticsResultCode = 3;
            } else if (error.action != 1 || error.formFieldMessage.length <= 0) {
                throw new IllegalArgumentException("No error found in error response");
            } else {
                setState(3, 4);
                analyticsResultCode = 2;
            }
        }
        AnalyticsUtil.createAndSendResponseReceivedBackgroundEvent(request.getBackgroundEventReceivedType(), analyticsResultCode, null, request.getClientLatencyMs(), responseContext.responseTimeMillis, responseContext.logToken);
    }
}
