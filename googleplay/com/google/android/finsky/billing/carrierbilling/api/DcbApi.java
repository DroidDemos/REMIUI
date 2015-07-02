package com.google.android.finsky.billing.carrierbilling.api;

import android.text.TextUtils;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.billing.carrierbilling.JsonUtils;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingCredentials;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning.Builder;
import com.google.android.finsky.billing.carrierbilling.model.EncryptedSubscriberInfo;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;
import org.json.JSONException;
import org.json.JSONObject;

public class DcbApi {
    private static final int DCB_TIMEOUT_MS;
    private final DcbApiContext mDcbContext;
    private final RequestQueue mRequestQueue;

    private class CredentialsJsonConverter implements Listener<JSONObject> {
        private final Listener<CarrierBillingCredentials> mListener;

        public CredentialsJsonConverter(Listener<CarrierBillingCredentials> listener) {
            this.mListener = listener;
        }

        public void onResponse(JSONObject response) {
            DcbApi.this.mRequestQueue.stop();
            this.mListener.onResponse(DcbApi.buildCredentials(response));
        }
    }

    private class ProvisioningJsonConverter implements Listener<JSONObject> {
        private final Listener<CarrierBillingProvisioning> mListener;

        public ProvisioningJsonConverter(Listener<CarrierBillingProvisioning> listener) {
            this.mListener = listener;
        }

        public void onResponse(JSONObject response) {
            DcbApi.this.mRequestQueue.stop();
            this.mListener.onResponse(DcbApi.buildProvisioning(response));
        }
    }

    private class RequestQueueErrorListener implements ErrorListener {
        private final ErrorListener mErrorListener;

        public RequestQueueErrorListener(ErrorListener errorListener) {
            this.mErrorListener = errorListener;
        }

        public void onErrorResponse(VolleyError error) {
            DcbApi.this.mRequestQueue.stop();
            this.mErrorListener.onErrorResponse(error);
        }
    }

    static {
        DCB_TIMEOUT_MS = ((Integer) DfeApiConfig.purchaseStatusTimeoutMs.get()).intValue();
    }

    public DcbApi(RequestQueue requestQueue, DcbApiContext dcbContext) {
        this.mRequestQueue = requestQueue;
        this.mDcbContext = dcbContext;
    }

    public Request<?> getProvisioning(String acceptedTosVersion, Listener<CarrierBillingProvisioning> listener, ErrorListener errorListener) {
        JSONObject jsonRequest;
        CarrierBillingParameters params = this.mDcbContext.getCarrierBillingParameters();
        if (((Boolean) G.vendingCarrierProvisioningUseTosVersion.get()).booleanValue()) {
            jsonRequest = getProvisioningParametersAsJsonObject(acceptedTosVersion);
        } else {
            jsonRequest = getProvisioningParametersAsJsonObject(null);
        }
        JsonObjectRequest request = new JsonObjectRequest(params.getGetProvisioningUrl(), jsonRequest, new ProvisioningJsonConverter(listener), new RequestQueueErrorListener(errorListener));
        request.setRetryPolicy(new DefaultRetryPolicy(DCB_TIMEOUT_MS, 0, 0.0f));
        this.mRequestQueue.start();
        return this.mRequestQueue.add(request);
    }

    public Request<?> getCredentials(String provisioningId, String password, Listener<CarrierBillingCredentials> listener, ErrorListener errorListener) {
        CarrierBillingParameters params = this.mDcbContext.getCarrierBillingParameters();
        JsonObjectRequest request = new JsonObjectRequest(params.getGetCredentialsUrl(), getCredentialsParametersAsJsonObject(provisioningId, password), new CredentialsJsonConverter(listener), new RequestQueueErrorListener(errorListener));
        request.setRetryPolicy(new DefaultRetryPolicy(DCB_TIMEOUT_MS, 0, 0.0f));
        this.mRequestQueue.start();
        return this.mRequestQueue.add(request);
    }

    JSONObject getProvisioningParametersAsJsonObject(String acceptedTosVersion) {
        try {
            JSONObject jsonObject = getBaseParametersAsJsonObject();
            if (acceptedTosVersion == null) {
                return jsonObject;
            }
            jsonObject.put("acceptedTosVersion", acceptedTosVersion);
            return jsonObject;
        } catch (JSONException jsone) {
            FinskyLog.e("JSONException while creating provisioning request: %s", jsone);
            return null;
        }
    }

    JSONObject getCredentialsParametersAsJsonObject(String provisioningId, String password) {
        try {
            JSONObject jsonObject = getBaseParametersAsJsonObject();
            if (provisioningId != null) {
                jsonObject.put("provisioningId", provisioningId);
            }
            if (TextUtils.isEmpty(password)) {
                return jsonObject;
            }
            jsonObject.put("password", password);
            return jsonObject;
        } catch (JSONException jsone) {
            FinskyLog.e("JSONException while creating credentials request: %s", jsone);
            return null;
        }
    }

    JSONObject getBaseParametersAsJsonObject() throws JSONException {
        CarrierBillingParameters params = this.mDcbContext.getCarrierBillingParameters();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("format", "json");
        Integer apiVersion = Integer.valueOf(params.getCarrierApiVersion());
        if (apiVersion == null || apiVersion.intValue() <= 0) {
            apiVersion = Integer.valueOf(1);
        }
        jsonObject.put("version", apiVersion);
        if (params.sendSubscriberInfoWithCarrierRequests()) {
            String line1Number = this.mDcbContext.getLine1Number();
            if (!TextUtils.isEmpty(line1Number)) {
                jsonObject.put("line1Number", line1Number);
            }
            String subscriberId = this.mDcbContext.getSubscriberId();
            if (!TextUtils.isEmpty(subscriberId)) {
                jsonObject.put("subscriberId", subscriberId);
            }
        }
        return jsonObject;
    }

    static CarrierBillingProvisioning buildProvisioning(JSONObject object) {
        try {
            long longValue;
            boolean booleanValue;
            object = JsonUtils.toLowerCase(object);
            Boolean isProvisioned = JsonUtils.getBoolean(object, "isprovisioned");
            Long transactionLimit = JsonUtils.getLong(object, "transactionlimit");
            Boolean passwordRequired = JsonUtils.getBoolean(object, "passwordrequired");
            SubscriberInfo subscriberInfo = buildSubscriberInfo(JsonUtils.getString(object, "subscribername"), JsonUtils.getString(object, "subscriberidentifier"), JsonUtils.getObject(object, "subscriberaddress"));
            EncryptedSubscriberInfo encryptedSubscriberInfo = buildEncryptedSubscriberInfo(JsonUtils.getObject(object, "encryptedsubscriberinfo"));
            Builder subscriberCurrency = new Builder().setApiVersion(JsonUtils.getInt(object, "version").intValue()).setIsProvisioned(isProvisioned != null ? isProvisioned.booleanValue() : false).setProvisioningId(JsonUtils.getString(object, "provisioningid")).setTosUrl(JsonUtils.getString(object, "tosurl")).setTosVersion(JsonUtils.getString(object, "tosversion")).setSubscriberCurrency(JsonUtils.getString(object, "subscribercurrency"));
            if (transactionLimit != null) {
                longValue = transactionLimit.longValue();
            } else {
                longValue = 0;
            }
            Builder credentials = subscriberCurrency.setTransactionLimit(longValue).setAccountType(JsonUtils.getString(object, "accounttype")).setSubscriberInfo(subscriberInfo).setCredentials(buildCredentials(object));
            if (passwordRequired != null) {
                booleanValue = passwordRequired.booleanValue();
            } else {
                booleanValue = false;
            }
            return credentials.setPasswordRequired(booleanValue).setPasswordPrompt(JsonUtils.getString(object, "passwordprompt")).setPasswordForgotUrl(JsonUtils.getString(object, "passwordforgoturl")).setEncryptedSubscriberInfo(encryptedSubscriberInfo).setAddressSnippet(JsonUtils.getString(object, "addresssnippet")).setCountry(JsonUtils.getString(object, "country")).build();
        } catch (JSONException e) {
            FinskyLog.e("JSON Exception while building provisioning", new Object[0]);
            return null;
        }
    }

    static CarrierBillingCredentials buildCredentials(JSONObject object) {
        try {
            int intValue;
            object = JsonUtils.toLowerCase(object);
            Integer apiVersion = JsonUtils.getInt(object, "version");
            Long expirationTime = JsonUtils.getLong(object, "credentialexpirationtime");
            Boolean isProvisioned = JsonUtils.getBoolean(object, "isprovisioned");
            Boolean passwordInvalid = JsonUtils.getBoolean(object, "passwordinvalid");
            CarrierBillingCredentials.Builder builder = new CarrierBillingCredentials.Builder();
            if (apiVersion != null) {
                intValue = apiVersion.intValue();
            } else {
                intValue = 0;
            }
            return builder.setApiVersion(intValue).setCredentials(JsonUtils.getString(object, "credential")).setExpirationTime(expirationTime != null ? expirationTime.longValue() : 0).setIsProvisioned(isProvisioned != null ? isProvisioned.booleanValue() : false).setInvalidPassword(passwordInvalid != null ? passwordInvalid.booleanValue() : false).build();
        } catch (JSONException e) {
            FinskyLog.e("JSON Exception while building credentials", new Object[0]);
            return null;
        }
    }

    private static SubscriberInfo buildSubscriberInfo(String name, String identifier, JSONObject object) {
        if (object == null) {
            return null;
        }
        return new SubscriberInfo.Builder().setName(name).setIdentifier(identifier).setAddress1(JsonUtils.getString(object, "address1")).setAddress2(JsonUtils.getString(object, "address2")).setCity(JsonUtils.getString(object, "city")).setState(JsonUtils.getString(object, "state")).setPostalCode(JsonUtils.getString(object, "postalcode")).setCountry(JsonUtils.getString(object, "country")).build();
    }

    private static EncryptedSubscriberInfo buildEncryptedSubscriberInfo(JSONObject object) {
        int i = 0;
        if (object == null) {
            return null;
        }
        Integer googleKeyVersion = JsonUtils.getInt(object, "googlekeyversion");
        Integer carrierKeyVersion = JsonUtils.getInt(object, "carrierkeyversion");
        EncryptedSubscriberInfo.Builder googleKeyVersion2 = new EncryptedSubscriberInfo.Builder().setMessage(JsonUtils.getString(object, "message")).setEncryptedKey(JsonUtils.getString(object, "encryptedkey")).setSignature(JsonUtils.getString(object, "signature")).setInitVector(JsonUtils.getString(object, "initvector")).setGoogleKeyVersion(googleKeyVersion != null ? googleKeyVersion.intValue() : 0);
        if (carrierKeyVersion != null) {
            i = carrierKeyVersion.intValue();
        }
        return googleKeyVersion2.setCarrierKeyVersion(i).build();
    }
}
