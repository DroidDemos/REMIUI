package com.google.android.finsky.billing.creditcard;

import android.text.TextUtils;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.ServerError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EscrowRequest extends StringRequest {
    private final String mCreditCardNumber;
    private final String mCvc;
    private final String mUserId;

    public EscrowRequest(String creditCardNumber, String cvc, Listener<String> listener, ErrorListener errorListener) {
        super(1, getAndCheckUrl(creditCardNumber), listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(10000, 0, 0.0f));
        this.mUserId = generateUserId();
        this.mCreditCardNumber = creditCardNumber;
        if (TextUtils.isEmpty(cvc)) {
            throw new IllegalArgumentException("CVC cannot be NULL.");
        }
        this.mCvc = cvc;
    }

    protected Map<String, String> getParams() {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("gid", this.mUserId);
        params.put("cvv", this.mCvc);
        if (!TextUtils.isEmpty(this.mCreditCardNumber)) {
            params.put("cardNumber", this.mCreditCardNumber);
        }
        return params;
    }

    private String generateUserId() {
        return Integer.toString(Math.abs(new Random(((Long) DfeApiConfig.androidId.get()).longValue() ^ System.currentTimeMillis()).nextInt() & -2));
    }

    private static String getAndCheckUrl(String creditCardNumber) {
        String url = ((String) G.walletEscrowUrl.get()) + (!TextUtils.isEmpty(creditCardNumber) ? "?s7e=cardNumber%3Bcvv" : "?s7e=cvv");
        Utils.checkUrlIsSecure(url);
        return url;
    }

    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        if (response.data.length != 0) {
            return super.parseNetworkResponse(response);
        }
        if (((Boolean) G.enableSensitiveLogging.get()).booleanValue()) {
            FinskyLog.w("Empty escrow handle for card number %s cvc %s", this.mCreditCardNumber, this.mCvc);
        }
        FinskyLog.wtf("Null response for Escrow string with id %s", this.mUserId);
        return Response.error(new ServerError(response));
    }
}
