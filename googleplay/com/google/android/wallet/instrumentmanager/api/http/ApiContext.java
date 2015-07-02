package com.google.android.wallet.instrumentmanager.api.http;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;
import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.google.commerce.payments.orchestration.proto.api.integratorbuyflow.DataTokens.AndroidEnvironmentConfig;
import java.util.HashMap;
import java.util.Map;

public class ApiContext {
    public final Context applicationContext;
    public final Uri baseUrl;
    public final Uri eesBaseUrl;
    private final String mAuthTokenType;
    private final AndroidAuthenticator mAuthenticator;
    private String mLastAuthToken;

    public static ApiContext create(Context context, AndroidEnvironmentConfig androidEnvironmentConfig) {
        return new ApiContext(context, androidEnvironmentConfig, new AndroidAuthenticator(context, new Account(androidEnvironmentConfig.accountName, "com.google"), androidEnvironmentConfig.authTokenType));
    }

    private ApiContext(Context context, AndroidEnvironmentConfig androidEnvironmentConfig, AndroidAuthenticator authenticator) {
        this.applicationContext = context.getApplicationContext();
        this.baseUrl = Uri.parse(androidEnvironmentConfig.serverBasePath);
        this.eesBaseUrl = Uri.parse(androidEnvironmentConfig.serverEesBasePath);
        this.mAuthTokenType = androidEnvironmentConfig.authTokenType;
        this.mAuthenticator = authenticator;
    }

    public synchronized void invalidateAuthToken() {
        if (this.mLastAuthToken != null) {
            this.mAuthenticator.invalidateAuthToken(this.mLastAuthToken);
            this.mLastAuthToken = null;
        }
    }

    public synchronized Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers;
        this.mLastAuthToken = this.mAuthenticator.getAuthToken();
        headers = new HashMap();
        if (this.mAuthTokenType.startsWith("oauth2:")) {
            headers.put("Authorization", "Bearer " + this.mLastAuthToken);
        } else {
            headers.put("Authorization", "GoogleLogin auth=" + this.mLastAuthToken);
        }
        return headers;
    }
}
