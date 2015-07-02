package com.google.android.wallet.instrumentmanager.sidecar;

import android.util.Pair;
import com.android.volley.Response.Listener;
import com.google.android.wallet.instrumentmanager.api.http.BackgroundEventRequest;
import com.google.protobuf.nano.MessageNano;

public abstract class BackgroundEventRequestResponseListener<RequestT extends BackgroundEventRequest<?>, ResponseT extends MessageNano> implements Listener<Pair<RequestT, ResponseT>> {
    protected abstract void handleResponse(RequestT requestT, ResponseT responseT);

    public final void onResponse(Pair<RequestT, ResponseT> volleyResponse) {
        handleResponse((BackgroundEventRequest) volleyResponse.first, (MessageNano) volleyResponse.second);
    }
}
