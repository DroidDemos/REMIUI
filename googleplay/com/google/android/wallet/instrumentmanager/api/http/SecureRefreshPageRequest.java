package com.google.android.wallet.instrumentmanager.api.http;

import com.android.volley.Response.ErrorListener;
import com.google.android.wallet.instrumentmanager.common.util.PaymentUtils;
import com.google.android.wallet.instrumentmanager.common.util.ProtoUtils;
import com.google.android.wallet.instrumentmanager.sidecar.BackgroundEventRequestResponseListener;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.PageValue;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.RefreshPageRequest;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.RefreshPageResponse;
import com.google.protobuf.nano.MessageNano;

public class SecureRefreshPageRequest extends SecurePageRequest<SecureRefreshPageRequest, RefreshPageResponse> {
    public final RefreshPageRequest mRequest;

    public SecureRefreshPageRequest(ApiContext apiContext, RefreshPageRequest request, byte[] sessionData, BackgroundEventRequestResponseListener<SecureRefreshPageRequest, RefreshPageResponse> responseListener, ErrorListener errorListener) {
        super(apiContext, sessionData, RefreshPageResponse.class, responseListener, errorListener);
        this.mRequest = (RefreshPageRequest) ProtoUtils.copyFrom(request);
    }

    public int getBackgroundEventReceivedType() {
        return 723;
    }

    protected byte[] getProtoRequestAsByteArray() {
        this.mRequest.context = PaymentUtils.createRequestContext(this.mApiContext.applicationContext, this.mSessionData);
        return MessageNano.toByteArray(this.mRequest);
    }

    protected PageValue getPageValueToEncode() {
        return this.mRequest.pageValue;
    }

    protected String getPageActionUrl() {
        return "InstrumentManager/RefreshPage";
    }
}
