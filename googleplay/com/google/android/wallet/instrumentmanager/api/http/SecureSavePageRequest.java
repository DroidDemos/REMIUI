package com.google.android.wallet.instrumentmanager.api.http;

import com.android.volley.Response.ErrorListener;
import com.google.android.wallet.instrumentmanager.common.util.PaymentUtils;
import com.google.android.wallet.instrumentmanager.common.util.ProtoUtils;
import com.google.android.wallet.instrumentmanager.sidecar.BackgroundEventRequestResponseListener;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.PageValue;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.SavePageRequest;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.SavePageResponse;
import com.google.protobuf.nano.MessageNano;

public class SecureSavePageRequest extends SecurePageRequest<SecureSavePageRequest, SavePageResponse> {
    public final SavePageRequest mRequest;

    public SecureSavePageRequest(ApiContext apiContext, SavePageRequest request, byte[] sessionData, BackgroundEventRequestResponseListener<SecureSavePageRequest, SavePageResponse> responseListener, ErrorListener errorListener) {
        super(apiContext, sessionData, SavePageResponse.class, responseListener, errorListener);
        this.mRequest = (SavePageRequest) ProtoUtils.copyFrom(request);
    }

    public int getBackgroundEventReceivedType() {
        return 721;
    }

    public byte[] getProtoRequestAsByteArray() {
        this.mRequest.context = PaymentUtils.createRequestContext(this.mApiContext.applicationContext, this.mSessionData);
        return MessageNano.toByteArray(this.mRequest);
    }

    protected PageValue getPageValueToEncode() {
        return this.mRequest.pageValue;
    }

    protected String getPageActionUrl() {
        return "InstrumentManager/SavePage";
    }
}
