package com.google.android.vending.verifier.api;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.protobuf.nano.MessageNano;

public abstract class BaseVerificationRequestWithResult<T, U extends MessageNano> extends BaseVerificationRequest<T, U> {
    private final Listener<T> mListener;

    public BaseVerificationRequestWithResult(String url, Listener<T> listener, ErrorListener errorListener, U request) {
        super(url, errorListener, request);
        this.mListener = listener;
    }

    protected void deliverResponse(T response) {
        this.mListener.onResponse(response);
    }
}
