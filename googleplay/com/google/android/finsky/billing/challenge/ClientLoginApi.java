package com.google.android.finsky.billing.challenge;

import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.auth.AuthResponseListener;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class ClientLoginApi {
    private static String CLIENT_LOGIN_URI;
    private static String REQUEST_PARAM_ACCOUNT_TYPE;
    private static String REQUEST_PARAM_EMAIL;
    private static String REQUEST_PARAM_PASSWD;
    private static String REQUEST_PARAM_SERVICE;
    private static String REQUEST_PARAM_SOURCE;
    private static String REQUEST_VALUE_ACCOUNT_TYPE;
    private static String REQUEST_VALUE_SERVICE;
    private static String REQUEST_VALUE_SOURCE;
    private static String RESULT_ERROR_BAD_AUTH;
    private static String RESULT_ERROR_CAPTCHA_REQUIRED;
    private static String RESULT_ERROR_INFO_TWO_FACTOR;
    private final RequestQueue mQueue;

    private class ClientLoginRequest extends StringRequest {
        private final Map<String, String> mPostParams;

        public ClientLoginRequest(String url, String accountName, String password, Listener<String> listener, ErrorListener errorListener) {
            super(1, url, listener, errorListener);
            this.mPostParams = Maps.newHashMap();
            this.mPostParams.put(ClientLoginApi.REQUEST_PARAM_ACCOUNT_TYPE, ClientLoginApi.REQUEST_VALUE_ACCOUNT_TYPE);
            this.mPostParams.put(ClientLoginApi.REQUEST_PARAM_EMAIL, accountName);
            this.mPostParams.put(ClientLoginApi.REQUEST_PARAM_PASSWD, password);
            this.mPostParams.put(ClientLoginApi.REQUEST_PARAM_SERVICE, ClientLoginApi.REQUEST_VALUE_SERVICE);
            this.mPostParams.put(ClientLoginApi.REQUEST_PARAM_SOURCE, ClientLoginApi.REQUEST_VALUE_SOURCE + FinskyApp.get().getVersionCode());
        }

        public Map<String, String> getParams() {
            return this.mPostParams;
        }

        public Priority getPriority() {
            return Priority.HIGH;
        }
    }

    static {
        CLIENT_LOGIN_URI = "https://www.google.com/accounts/ClientLogin";
        REQUEST_PARAM_ACCOUNT_TYPE = "accountType";
        REQUEST_PARAM_EMAIL = "Email";
        REQUEST_PARAM_PASSWD = "Passwd";
        REQUEST_PARAM_SERVICE = "service";
        REQUEST_PARAM_SOURCE = "source";
        REQUEST_VALUE_ACCOUNT_TYPE = "HOSTED_OR_GOOGLE";
        REQUEST_VALUE_SERVICE = "apps";
        REQUEST_VALUE_SOURCE = "Google-GooglePlay-";
        RESULT_ERROR_BAD_AUTH = "Error=BadAuthentication";
        RESULT_ERROR_INFO_TWO_FACTOR = "Info=InvalidSecondFactor";
        RESULT_ERROR_CAPTCHA_REQUIRED = "Error=CaptchaRequired";
    }

    public ClientLoginApi(RequestQueue queue) {
        this.mQueue = queue;
    }

    public Request<?> validateUser(String accountName, String gaiaPasswd, final AuthResponseListener listener) {
        return this.mQueue.add(new ClientLoginRequest(CLIENT_LOGIN_URI, accountName, gaiaPasswd, new Listener<String>() {
            public void onResponse(String response) {
                listener.onAuthSuccess();
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                int err = 1;
                if (error.networkResponse == null || error.networkResponse.data == null || error.networkResponse.headers == null) {
                    FinskyLog.d("ClientLogin error: network response empty", new Object[0]);
                    listener.onAuthFailure(1);
                    return;
                }
                try {
                    String data = new String(error.networkResponse.data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                    if (data.contains(ClientLoginApi.RESULT_ERROR_BAD_AUTH)) {
                        if (data.contains(ClientLoginApi.RESULT_ERROR_INFO_TWO_FACTOR)) {
                            err = 2;
                            FinskyLog.d("ClientLogin error: two factor.", new Object[0]);
                        } else {
                            err = 4;
                            FinskyLog.d("ClientLogin error: bad auth.", new Object[0]);
                        }
                        listener.onAuthFailure(err);
                    }
                    if (data.contains(ClientLoginApi.RESULT_ERROR_CAPTCHA_REQUIRED)) {
                        err = 3;
                        FinskyLog.d("ClientLogin error: captcha.", new Object[0]);
                    } else {
                        FinskyLog.d("ClientLogin error: unrecognized type %s", data);
                    }
                    listener.onAuthFailure(err);
                } catch (UnsupportedEncodingException e) {
                    FinskyLog.e("Unsupported encoding %s", e);
                }
            }
        }));
    }
}
