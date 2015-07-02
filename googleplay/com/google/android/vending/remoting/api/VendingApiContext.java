package com.google.android.vending.remoting.api;

import android.accounts.Account;
import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.VendingProtos.RequestPropertiesProto;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Utils;
import com.google.android.volley.UrlTools;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import java.util.Locale;
import java.util.Map;

public class VendingApiContext {
    private final AndroidAuthenticator mAuthenticator;
    private final Context mContext;
    private boolean mHasPerformedInitialSecureTokenInvalidation;
    private boolean mHasPerformedInitialTokenInvalidation;
    private final Map<String, String> mHeaders;
    private String mLastAuthToken;
    private String mLastSecureAuthToken;
    private boolean mReauthenticate;
    private RequestPropertiesProto mRequestProperties;
    private final AndroidAuthenticator mSecureAuthenticator;

    public VendingApiContext(Context context, Account account, Locale userLocale, String aid, int softwareVersion, String operatorName, String simOperatorName, String operatorNumericName, String simOperatorNumericName, String deviceName, String sdkVersion, String clientId, String loggingId) {
        this.mReauthenticate = false;
        this.mHeaders = Maps.newHashMap();
        this.mContext = context;
        this.mHeaders.put("User-Agent", "Android-Market/2");
        this.mAuthenticator = new AndroidAuthenticator(context, account, (String) G.vendingAuthTokenType.get());
        this.mSecureAuthenticator = new AndroidAuthenticator(context, account, (String) G.vendingSecureAuthTokenType.get());
        this.mRequestProperties = new RequestPropertiesProto();
        this.mRequestProperties.aid = aid;
        this.mRequestProperties.hasAid = true;
        this.mRequestProperties.userCountry = userLocale.getCountry();
        this.mRequestProperties.hasUserCountry = true;
        this.mRequestProperties.userLanguage = userLocale.getLanguage();
        this.mRequestProperties.hasUserLanguage = true;
        this.mRequestProperties.softwareVersion = softwareVersion;
        this.mRequestProperties.hasSoftwareVersion = true;
        if (operatorName != null) {
            this.mRequestProperties.operatorName = operatorName;
            this.mRequestProperties.hasOperatorName = true;
        }
        if (simOperatorName != null) {
            this.mRequestProperties.simOperatorName = simOperatorName;
            this.mRequestProperties.hasSimOperatorName = true;
        }
        if (operatorNumericName != null) {
            this.mRequestProperties.operatorNumericName = operatorNumericName;
            this.mRequestProperties.hasOperatorNumericName = true;
        }
        if (simOperatorNumericName != null) {
            this.mRequestProperties.simOperatorNumericName = simOperatorNumericName;
            this.mRequestProperties.hasSimOperatorNumericName = true;
        }
        this.mRequestProperties.productNameAndVersion = deviceName + ":" + sdkVersion;
        this.mRequestProperties.hasProductNameAndVersion = true;
        this.mRequestProperties.clientId = clientId;
        this.mRequestProperties.hasClientId = true;
        this.mRequestProperties.loggingId = loggingId;
        this.mRequestProperties.hasLoggingId = true;
        checkUrlRewrites();
    }

    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    public String getAuthToken() throws AuthFailureError {
        if (this.mReauthenticate) {
            this.mAuthenticator.invalidateAuthToken(this.mLastAuthToken);
            this.mReauthenticate = false;
        }
        this.mLastAuthToken = this.mAuthenticator.getAuthToken();
        return this.mLastAuthToken;
    }

    public String getSecureAuthToken() throws AuthFailureError {
        if (this.mReauthenticate) {
            this.mSecureAuthenticator.invalidateAuthToken(this.mLastAuthToken);
            this.mReauthenticate = false;
        }
        this.mLastSecureAuthToken = this.mSecureAuthenticator.getAuthToken();
        return this.mLastSecureAuthToken;
    }

    private void checkUrlRewrites() {
        checkRewrittenToSecureUrl("https://android.clients.google.com/vending/api/ApiRequest");
    }

    private void checkRewrittenToSecureUrl(String url) {
        String rewritten = UrlTools.rewrite(this.mContext, url);
        if (rewritten == null) {
            throw new RuntimeException("URL blocked: " + url);
        }
        Utils.checkUrlIsSecure(rewritten);
    }

    public RequestPropertiesProto getRequestProperties(boolean secureToken) throws AuthFailureError {
        if ((secureToken && !this.mHasPerformedInitialSecureTokenInvalidation) || !(secureToken || this.mHasPerformedInitialTokenInvalidation)) {
            invalidateAuthToken(secureToken);
            if (secureToken) {
                this.mHasPerformedInitialSecureTokenInvalidation = true;
            } else {
                this.mHasPerformedInitialTokenInvalidation = true;
            }
        }
        RequestPropertiesProto result = new RequestPropertiesProto();
        try {
            MessageNano.mergeFrom(result, MessageNano.toByteArray(this.mRequestProperties));
            result.userAuthToken = secureToken ? getSecureAuthToken() : getAuthToken();
            result.hasUserAuthToken = true;
            result.userAuthTokenSecure = secureToken;
            result.hasUserAuthTokenSecure = true;
            return result;
        } catch (InvalidProtocolBufferNanoException e) {
            throw new IllegalStateException("Cannot happen.");
        }
    }

    public Account getAccount() {
        return this.mAuthenticator.getAccount();
    }

    public void invalidateAuthToken(boolean secureToken) {
        String token = secureToken ? this.mLastSecureAuthToken : this.mLastAuthToken;
        if (token != null) {
            this.mAuthenticator.invalidateAuthToken(token);
        }
        if (secureToken) {
            this.mLastSecureAuthToken = null;
        } else {
            this.mLastAuthToken = null;
        }
    }

    public void scheduleReauthentication(boolean secureToken) {
        this.mReauthenticate = true;
    }
}
