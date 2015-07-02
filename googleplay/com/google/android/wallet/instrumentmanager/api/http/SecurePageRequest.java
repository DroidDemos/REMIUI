package com.google.android.wallet.instrumentmanager.api.http;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.android.wallet.instrumentmanager.common.util.ProtoUtils;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CreditCardExpirationDateFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CreditCardFormValue;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.PageValue;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class SecurePageRequest<RequestT extends SecurePageRequest<RequestT, ResponseT>, ResponseT extends MessageNano> extends BackgroundEventRequest<Pair<RequestT, ResponseT>> {
    protected final ApiContext mApiContext;
    private final LinkedHashMap<String, String> mEesParams;
    private boolean mInitialized;
    private final Class<ResponseT> mResponseClass;
    public final byte[] mSessionData;
    private String mUrl;

    protected abstract String getPageActionUrl();

    protected abstract PageValue getPageValueToEncode();

    protected abstract byte[] getProtoRequestAsByteArray();

    public SecurePageRequest(ApiContext apiContext, byte[] sessionData, Class<ResponseT> responseClass, Listener<Pair<RequestT, ResponseT>> responseListener, ErrorListener errorListener) {
        super(1, null, responseListener, errorListener);
        this.mEesParams = new LinkedHashMap();
        this.mApiContext = apiContext;
        this.mSessionData = sessionData;
        this.mResponseClass = responseClass;
    }

    public Map<String, String> getParams() {
        initialize();
        HashMap<String, String> params = new HashMap();
        params.putAll(this.mEesParams);
        params.put("requestContentType", "application/protobuf");
        params.put("request", Base64.encodeToString(getProtoRequestAsByteArray(), 11));
        return params;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        initialize();
        return this.mApiContext.getHeaders();
    }

    public String getUrl() {
        initialize();
        return this.mUrl;
    }

    protected Response<Pair<RequestT, ResponseT>> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            MessageNano response = (MessageNano) this.mResponseClass.cast(MessageNano.mergeFrom((MessageNano) this.mResponseClass.newInstance(), networkResponse.data));
            ProtoUtils.logResponse(response, getUrl());
            return Response.success(Pair.create(this, response), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to create proto object.", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Failed to create proto object.", e2);
        } catch (IOException e3) {
            Log.e("SecurePageRequest", "Couldn't parse proto response for url=" + getUrl());
            return Response.error(new ParseError(networkResponse));
        }
    }

    private void initialize() {
        if (!this.mInitialized) {
            PageValue pageValue = getPageValueToEncode();
            boolean eesEncodingRequired = false;
            if (pageValue.newInstrument != null && pageValue.newInstrument.creditCard != null) {
                eesEncodeCreditCardFormValue(pageValue.newInstrument.creditCard);
                eesEncodingRequired = true;
            } else if (pageValue.newCreditCardExpirationDate != null) {
                eesEncodeCreditCardExpirationDateFormValue(pageValue.newCreditCardExpirationDate);
                eesEncodingRequired = true;
            } else if (!(pageValue.newCustomer == null || pageValue.newCustomer.instrument == null || pageValue.newCustomer.instrument.creditCard == null)) {
                eesEncodeCreditCardFormValue(pageValue.newCustomer.instrument.creditCard);
                eesEncodingRequired = true;
            }
            if (this.mEesParams.isEmpty() && eesEncodingRequired) {
                throw new IllegalArgumentException("SecurePageRequest should only be used for creating / updating credit card instruments / customer information");
            }
            this.mUrl = this.mApiContext.eesBaseUrl.buildUpon().appendEncodedPath(getPageActionUrl()).appendQueryParameter("s7e", TextUtils.join(";", this.mEesParams.keySet())).toString();
            this.mInitialized = true;
        }
    }

    private void eesEncodeCreditCardFormValue(CreditCardFormValue value) {
        this.mEesParams.put("credit_card_number", value.cardNumber);
        value.cardNumber = "__param:credit_card_number";
        this.mEesParams.put("cvc", value.cvc);
        value.cvc = "__param:cvc";
    }

    private void eesEncodeCreditCardExpirationDateFormValue(CreditCardExpirationDateFormValue value) {
        this.mEesParams.put("cvc", value.cvc);
        value.cvc = "__param:cvc";
    }
}
