package com.google.android.finsky.billing.genericinstrument;

import android.os.Bundle;
import android.text.TextUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.protos.BuyInstruments.CreateInstrumentRequest;
import com.google.android.finsky.protos.BuyInstruments.CreateInstrumentResponse;
import com.google.android.finsky.protos.BuyInstruments.GetInitialInstrumentFlowStateResponse;
import com.google.android.finsky.protos.CommonDevice.GenericInstrument;
import com.google.android.finsky.protos.CreateInstrument.DeviceCreateInstrumentFlowState;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.wallet.instrumentmanager.R;

public class GenericInstrumentSidecar extends SidecarFragment {
    private final CreateInstrumentListener mCreateInstrumentListener;
    private CreateInstrumentResponse mCreateInstrumentResponse;
    private DfeApi mDfeApi;
    private String mErrorHtml;
    private FinskyEventLog mEventLog;
    private GenericInstrument mGenericInstrument;
    private final GetInitialInstrumentFlowStateListener mInitialFlowStateListener;
    private GetInitialInstrumentFlowStateResponse mInitialInstrumentFlowStateResponse;

    private class CreateInstrumentListener implements ErrorListener, Listener<CreateInstrumentResponse> {
        private CreateInstrumentListener() {
        }

        public void onResponse(CreateInstrumentResponse response) {
            GenericInstrumentSidecar.this.mCreateInstrumentResponse = response;
            GenericInstrumentSidecar.this.mErrorHtml = response.userMessageHtml;
            switch (response.result) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    GenericInstrumentSidecar.this.logBackgroundEvent(711);
                    if (TextUtils.isEmpty(response.instrumentId)) {
                        throw new IllegalArgumentException("Missing instrument with success");
                    }
                    GenericInstrumentSidecar.this.setState(2, 0);
                    return;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    GenericInstrumentSidecar.this.logBackgroundEvent(713);
                    GenericInstrumentSidecar.this.setState(4, 0);
                    return;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    GenericInstrumentSidecar.this.logBackgroundEvent(712, 2, null);
                    GenericInstrumentSidecar.this.setState(3, 1);
                    return;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    GenericInstrumentSidecar.this.logBackgroundEvent(712, 3, null);
                    GenericInstrumentSidecar.this.setState(3, 0);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown create instrument flow state: " + response.result);
            }
        }

        public void onErrorResponse(VolleyError error) {
            FinskyLog.w("Error received for %s: %s", GenericInstrumentSidecar.this.getInstrumentType(), error);
            GenericInstrumentSidecar.this.logBackgroundEvent(712, 1, error);
            GenericInstrumentSidecar.this.mErrorHtml = ErrorStrings.get(FinskyApp.get(), error);
            GenericInstrumentSidecar.this.setState(3, 0);
        }
    }

    private class GetInitialInstrumentFlowStateListener implements ErrorListener, Listener<GetInitialInstrumentFlowStateResponse> {
        private GetInitialInstrumentFlowStateListener() {
        }

        public void onResponse(GetInitialInstrumentFlowStateResponse response) {
            GenericInstrumentSidecar.this.mInitialInstrumentFlowStateResponse = response;
            GenericInstrumentSidecar.this.mErrorHtml = response.userMessageHtml;
            switch (response.result) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    GenericInstrumentSidecar.this.logBackgroundEvent(701);
                    GenericInstrumentSidecar.this.setState(4, 0);
                    return;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    GenericInstrumentSidecar.this.logBackgroundEvent(702, 2, null);
                    GenericInstrumentSidecar.this.setState(3, 0);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown initial flow state: " + response.result);
            }
        }

        public void onErrorResponse(VolleyError error) {
            FinskyLog.w("Error received for %s: %s", GenericInstrumentSidecar.this.getInstrumentType(), error);
            GenericInstrumentSidecar.this.logBackgroundEvent(702, 1, error);
            GenericInstrumentSidecar.this.mErrorHtml = ErrorStrings.get(FinskyApp.get(), error);
            GenericInstrumentSidecar.this.setState(3, 0);
        }
    }

    public GenericInstrumentSidecar() {
        this.mInitialFlowStateListener = new GetInitialInstrumentFlowStateListener();
        this.mCreateInstrumentListener = new CreateInstrumentListener();
    }

    public static GenericInstrumentSidecar newInstance(String accountName, GenericInstrument genericInstrument) {
        GenericInstrumentSidecar result = new GenericInstrumentSidecar();
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putParcelable("GenericInstrumentSidecar.generic_instrument", ParcelableProto.forProto(genericInstrument));
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        Bundle args = getArguments();
        this.mDfeApi = FinskyApp.get().getDfeApi(args.getString("authAccount"));
        this.mEventLog = FinskyApp.get().getEventLogger(this.mDfeApi.getAccount());
        this.mGenericInstrument = (GenericInstrument) ParcelableProto.getProtoFromBundle(args, "GenericInstrumentSidecar.generic_instrument");
        super.onCreate(savedInstanceState);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("GenericInstrumentSidecar.initial_instrument_flow_state_response", ParcelableProto.forProto(this.mInitialInstrumentFlowStateResponse));
        outState.putParcelable("GenericInstrumentSidecar.create_instrument_response", ParcelableProto.forProto(this.mCreateInstrumentResponse));
        outState.putString("GenericInstrumentSidecar.error_html", this.mErrorHtml);
    }

    protected void restoreFromSavedInstanceState(Bundle savedInstanceState) {
        super.restoreFromSavedInstanceState(savedInstanceState);
        this.mInitialInstrumentFlowStateResponse = (GetInitialInstrumentFlowStateResponse) ParcelableProto.getProtoFromBundle(savedInstanceState, "GenericInstrumentSidecar.initial_instrument_flow_state_response");
        this.mCreateInstrumentResponse = (CreateInstrumentResponse) ParcelableProto.getProtoFromBundle(savedInstanceState, "GenericInstrumentSidecar.create_instrument_response");
        this.mErrorHtml = savedInstanceState.getString("GenericInstrumentSidecar.error_html");
    }

    public void requestInitialInstrumentFlowState() {
        if (getState() != 0) {
            FinskyLog.wtf("%s requestInitialInstrumentFlowState() called while state=%d", getInstrumentType(), Integer.valueOf(getState()));
        }
        logBackgroundEvent(700);
        this.mInitialInstrumentFlowStateResponse = null;
        this.mCreateInstrumentResponse = null;
        this.mErrorHtml = null;
        this.mDfeApi.getInitialInstrumentFlowState(this.mGenericInstrument.createInstrumentFlowHandle, this.mInitialFlowStateListener, this.mInitialFlowStateListener);
        setState(1, 0);
    }

    public void createInstrument(CreateInstrumentRequest request) {
        if (getState() == 1) {
            FinskyLog.wtf("%s createInstrument() called while RUNNING.", getInstrumentType());
        }
        logBackgroundEvent(710);
        this.mCreateInstrumentResponse = null;
        this.mErrorHtml = null;
        this.mDfeApi.createInstrument(request, this.mCreateInstrumentListener, this.mCreateInstrumentListener);
        setState(1, 0);
    }

    public void retryAfterError() {
        if (getState() != 3) {
            FinskyLog.wtf("%s retryAfterError() called while state=%d", getInstrumentType(), Integer.valueOf(getState()));
        }
        GetInitialInstrumentFlowStateResponse response = this.mInitialInstrumentFlowStateResponse;
        if (response == null || response.result != 1) {
            setState(0, 0);
        } else {
            setState(4, 0);
        }
    }

    public String getInstrumentId() {
        if (getState() == 2) {
            return this.mCreateInstrumentResponse.instrumentId;
        }
        throw new IllegalStateException("Instrument requested while state=" + getState());
    }

    public DeviceCreateInstrumentFlowState getInstrumentFlowState() {
        if (this.mCreateInstrumentResponse != null) {
            return this.mCreateInstrumentResponse.createInstrumentFlowState;
        }
        if (this.mInitialInstrumentFlowStateResponse != null) {
            return this.mInitialInstrumentFlowStateResponse.createInstrumentFlowState;
        }
        return null;
    }

    public String getErrorHtml() {
        return this.mErrorHtml;
    }

    private String getInstrumentType() {
        return this.mGenericInstrument.createInstrumentMetadata.instrumentType;
    }

    private void logBackgroundEvent(int type) {
        logBackgroundEvent(type, 0, null);
    }

    private void logBackgroundEvent(int type, int errorCode, VolleyError volleyError) {
        this.mEventLog.logBackgroundEvent(new BackgroundEventBuilder(type).setExceptionType((Throwable) volleyError).setErrorCode(errorCode).build());
    }
}
