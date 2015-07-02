package com.google.android.finsky.api;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.protobuf.nano.MessageNano;

public class ProtoDfeRequest<T extends MessageNano> extends DfeRequest<T> {
    private final MessageNano mRequest;

    public ProtoDfeRequest(String url, MessageNano request, DfeApiContext apiContext, Class<T> responseClass, Listener<T> listener, ErrorListener errorListener) {
        super(1, url, apiContext, responseClass, listener, errorListener);
        this.mRequest = request;
        setShouldCache(false);
    }

    public String getBodyContentType() {
        return "application/x-protobuf";
    }

    public byte[] getBody() {
        return MessageNano.toByteArray(this.mRequest);
    }
}
