package com.google.android.wallet.instrumentmanager.sidecar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.android.wallet.instrumentmanager.analytics.util.AnalyticsUtil;
import com.google.android.wallet.instrumentmanager.api.InstrumentManagerRequestQueue;
import com.google.android.wallet.instrumentmanager.api.http.ApiContext;
import com.google.android.wallet.instrumentmanager.api.http.AuthHandlingRetryPolicy;
import com.google.android.wallet.instrumentmanager.api.http.BackgroundEventRequest;
import com.google.android.wallet.instrumentmanager.common.util.ParcelableProto;
import com.google.android.wallet.instrumentmanager.config.G;
import com.google.android.wallet.instrumentmanager.config.G.dcb;
import com.google.commerce.payments.orchestration.proto.api.integratorbuyflow.DataTokens.AndroidEnvironmentConfig;
import java.util.ArrayList;

public abstract class BaseInstrumentManagerSidecar extends SidecarFragment {
    protected ApiContext mApiContext;
    boolean mAttemptedToHandleAuth;
    int mNoConnectionErrorRetryCounter;
    ArrayList<Request<?>> mQueuedRequests;
    private RequestQueue mRequestQueue;

    public final class InstrumentManagerErrorListener implements ErrorListener {
        private final byte[] mPreviousResponseLogToken;
        private BackgroundEventRequest<?> mRequest;

        public InstrumentManagerErrorListener(byte[] previousResponseLogToken) {
            this.mPreviousResponseLogToken = previousResponseLogToken;
        }

        public void setRequest(BackgroundEventRequest<?> request) {
            this.mRequest = request;
        }

        public void onErrorResponse(VolleyError error) {
            if ((error instanceof AuthFailureError) && !BaseInstrumentManagerSidecar.this.mAttemptedToHandleAuth) {
                Intent resolutionIntent = ((AuthFailureError) error).getResolutionIntent();
                if (resolutionIntent != null) {
                    resolutionIntent.setFlags(resolutionIntent.getFlags() & -268435457);
                    BaseInstrumentManagerSidecar.this.startActivityForResult(resolutionIntent, 100);
                    BaseInstrumentManagerSidecar.this.setState(1, 1);
                    BaseInstrumentManagerSidecar.this.sendRequest(this.mRequest);
                    return;
                }
                BaseInstrumentManagerSidecar.this.setState(3, 1);
            } else if ((error instanceof NoConnectionError) && BaseInstrumentManagerSidecar.this.shouldRetryNoConnectionErrors(BaseInstrumentManagerSidecar.this.mNoConnectionErrorRetryCounter)) {
                BaseInstrumentManagerSidecar baseInstrumentManagerSidecar = BaseInstrumentManagerSidecar.this;
                baseInstrumentManagerSidecar.mNoConnectionErrorRetryCounter++;
                AnalyticsUtil.createAndSendRetryAttemptBackgroundEvent(772, BaseInstrumentManagerSidecar.this.mNoConnectionErrorRetryCounter, this.mPreviousResponseLogToken);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        BaseInstrumentManagerSidecar.this.sendRequest(InstrumentManagerErrorListener.this.mRequest, false);
                    }
                }, (long) ((Integer) dcb.verifyAssociationRetryDelayMs.get()).intValue());
                return;
            } else if (error instanceof ServerError) {
                BaseInstrumentManagerSidecar.this.setState(3, 3);
            } else if ((error instanceof NetworkError) || (error instanceof TimeoutError)) {
                BaseInstrumentManagerSidecar.this.setState(3, 2);
            } else if (error instanceof AuthFailureError) {
                BaseInstrumentManagerSidecar.this.setState(3, 1);
            } else {
                Log.i("BaseInstrumentManagerSidecar", "Unexpected error returned from Volley", error);
                BaseInstrumentManagerSidecar.this.setState(3, 3);
            }
            AnalyticsUtil.createAndSendResponseReceivedBackgroundEvent(this.mRequest.getBackgroundEventReceivedType(), 1, error.getClass().getSimpleName(), this.mRequest.getClientLatencyMs(), -1, this.mPreviousResponseLogToken);
        }
    }

    protected abstract void clearPreviousResponses();

    public BaseInstrumentManagerSidecar() {
        this.mAttemptedToHandleAuth = false;
    }

    protected static Bundle createArgs(AndroidEnvironmentConfig androidEnvironmentConfig) {
        Bundle args = new Bundle();
        args.putParcelable("androidConfig", ParcelableProto.forProto(androidEnvironmentConfig));
        return args;
    }

    public void onCreate(Bundle savedInstanceState) {
        this.mApiContext = ApiContext.create(getActivity(), (AndroidEnvironmentConfig) ParcelableProto.getProtoFromBundle(getArguments(), "androidConfig"));
        this.mRequestQueue = InstrumentManagerRequestQueue.getApiRequestQueue(getActivity().getApplicationContext());
        super.onCreate(savedInstanceState);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("attemptedToHandleAuth", this.mAttemptedToHandleAuth);
        outState.putInt("noConnectionErrorRetryCounter", this.mNoConnectionErrorRetryCounter);
    }

    protected void restoreFromSavedInstanceState(Bundle savedInstanceState) {
        super.restoreFromSavedInstanceState(savedInstanceState);
        this.mAttemptedToHandleAuth = savedInstanceState.getBoolean("attemptedToHandleAuth");
        this.mNoConnectionErrorRetryCounter = savedInstanceState.getInt("noConnectionErrorRetryCounter");
    }

    protected void sendRequest(Request<?> request) {
        sendRequest(request, true);
    }

    private void sendRequest(Request<?> request, boolean resetRetryCounter) {
        clearPreviousResponses();
        if (resetRetryCounter) {
            this.mNoConnectionErrorRetryCounter = 0;
        }
        if (isWaitingForAuth()) {
            if (this.mQueuedRequests == null) {
                this.mQueuedRequests = new ArrayList(2);
            }
            this.mQueuedRequests.add(request);
            return;
        }
        request.setRetryPolicy(createRetryPolicy(this.mApiContext));
        this.mRequestQueue.add(request);
        setState(1, 0);
    }

    protected void cancelRequestsByTag(Object tag) {
        this.mRequestQueue.cancelAll(tag);
        if (this.mQueuedRequests != null) {
            for (int i = this.mQueuedRequests.size() - 1; i >= 0; i--) {
                Request request = (Request) this.mQueuedRequests.get(i);
                if (request != null && tag.equals(request.getTag())) {
                    this.mQueuedRequests.remove(i);
                }
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != 100) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (resultCode == -1) {
            this.mAttemptedToHandleAuth = true;
            setState(1, 0);
            executePendingRequests();
        } else {
            setState(3, 1);
        }
    }

    protected final boolean isWaitingForAuth() {
        return getState() == 1 && getSubstate() == 1;
    }

    protected boolean shouldRetryNoConnectionErrors(int retryCount) {
        return false;
    }

    protected void executePendingRequests() {
        if (this.mQueuedRequests != null) {
            int length = this.mQueuedRequests.size();
            for (int i = 0; i < length; i++) {
                sendRequest((Request) this.mQueuedRequests.get(i));
            }
            this.mQueuedRequests.clear();
        }
    }

    private static AuthHandlingRetryPolicy createRetryPolicy(ApiContext apiContext) {
        return new AuthHandlingRetryPolicy(((Integer) G.volleyApiRequestDefaultTimeoutMs.get()).intValue(), apiContext);
    }
}
