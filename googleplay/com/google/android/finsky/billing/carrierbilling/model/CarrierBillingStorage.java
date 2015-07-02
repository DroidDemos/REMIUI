package com.google.android.finsky.billing.carrierbilling.model;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters.Builder;
import com.google.android.finsky.utils.Md5Util;
import com.google.android.finsky.utils.persistence.FileBasedKeyValueStore;
import com.google.android.finsky.utils.persistence.WriteThroughKeyValueStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarrierBillingStorage {
    private final String mCurrentSimIdentifier;
    private volatile boolean mIsInit;
    private final WriteThroughKeyValueStore mStore;

    public CarrierBillingStorage(Context context) {
        this.mIsInit = false;
        this.mCurrentSimIdentifier = CarrierBillingUtils.getSimIdentifier();
        this.mStore = new WriteThroughKeyValueStore(new FileBasedKeyValueStore(context.getDir("carrier_billing", 0), this.mCurrentSimIdentifier));
    }

    CarrierBillingStorage(WriteThroughKeyValueStore store) {
        this.mIsInit = false;
        this.mStore = store;
        this.mCurrentSimIdentifier = "invalid_sim_id";
    }

    public void init(final Runnable runnable) {
        this.mStore.load(new Runnable() {
            public void run() {
                CarrierBillingStorage.this.setInit(true);
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    public boolean isInit() {
        return this.mIsInit;
    }

    void setInit(boolean value) {
        this.mIsInit = value;
    }

    public CarrierBillingParameters getParams() {
        if (isInit()) {
            Map<String, String> values = this.mStore.get(getParamsKey());
            if (values == null) {
                return null;
            }
            Builder builder = new Builder().setId((String) values.get("carrier_id")).setName((String) values.get("carrier_name")).setGetProvisioningUrl((String) values.get("get_provisioning_url")).setGetCredentialsUrl((String) values.get("get_credentials_url"));
            Integer carrierApiVersion = stringToInteger((String) values.get("carrier_api_version"));
            if (carrierApiVersion != null) {
                builder.setCarrierApiVersion(carrierApiVersion.intValue());
            }
            Boolean showCarrierTos = stringToBoolean((String) values.get("show_carrier_tos"));
            if (showCarrierTos != null) {
                builder.setShowCarrierTos(showCarrierTos.booleanValue());
            }
            Boolean perTransactionCredentialsRequired = stringToBoolean((String) values.get("per_transaction_credentials_required"));
            if (perTransactionCredentialsRequired != null) {
                builder.setPerTransactionCredentialsRequired(perTransactionCredentialsRequired.booleanValue());
            }
            Boolean sendSubscriberInfoWithCarrierRequests = stringToBoolean((String) values.get("per_transaction_credentials_required"));
            if (sendSubscriberInfoWithCarrierRequests != null) {
                builder.setSendSubscriberInfoWithCarrierRequests(sendSubscriberInfoWithCarrierRequests.booleanValue());
            }
            return builder.build();
        }
        throw new IllegalStateException("Attempt to fetch params when key store isn't ready.");
    }

    public CarrierBillingProvisioning getProvisioning() {
        if (isInit()) {
            Map<String, String> values = this.mStore.get("provisioning");
            CarrierBillingCredentials credentials = getCredentials();
            if (values != null) {
                CarrierBillingProvisioning.Builder builder = new CarrierBillingProvisioning.Builder();
                CarrierBillingProvisioning.Builder provisioningId = builder.setProvisioningId((String) values.get("id"));
                provisioningId = r16.setTosUrl((String) values.get("tos_url"));
                provisioningId = r16.setTosVersion((String) values.get("tos_version"));
                provisioningId = r16.setSubscriberCurrency((String) values.get("subscriber_currency"));
                provisioningId = r16.setAccountType((String) values.get("account_type"));
                provisioningId = r16.setPasswordPrompt((String) values.get("password_prompt"));
                provisioningId = r16.setPasswordForgotUrl((String) values.get("password_forgot_url"));
                provisioningId = r16.setAddressSnippet((String) values.get("address_snippet"));
                r16.setCountry((String) values.get("country")).setCredentials(credentials);
                Integer apiVersion = stringToInteger((String) values.get("api_version"));
                if (apiVersion != null) {
                    builder.setApiVersion(apiVersion.intValue());
                }
                Boolean isProvisioned = stringToBoolean((String) values.get("is_provisioned"));
                if (isProvisioned != null) {
                    builder.setIsProvisioned(isProvisioned.booleanValue());
                }
                Long transactionLimit = stringToLong((String) values.get("transaction_limit"));
                if (transactionLimit != null) {
                    builder.setTransactionLimit(transactionLimit.longValue());
                }
                Boolean passwordRequired = stringToBoolean((String) values.get("password_required"));
                if (passwordRequired != null) {
                    builder.setPasswordRequired(passwordRequired.booleanValue());
                }
                String obfuscatedSubscriberInfo = (String) values.get("subscriber_token");
                if (!TextUtils.isEmpty(obfuscatedSubscriberInfo)) {
                    SubscriberInfo subscriberInfo = SubscriberInfo.fromObfuscatedString(obfuscatedSubscriberInfo);
                    if (subscriberInfo != null) {
                        builder.setSubscriberInfo(subscriberInfo);
                    }
                }
                EncryptedSubscriberInfo.Builder builder2 = new EncryptedSubscriberInfo.Builder();
                builder2 = r16.setMessage((String) values.get("encrypted_message"));
                builder2 = r16.setEncryptedKey((String) values.get("encrypted_key"));
                builder2 = r16.setSignature((String) values.get("encrypted_signature"));
                EncryptedSubscriberInfo.Builder encryptedSubscriberInfoBuilder = r16.setInitVector((String) values.get("encrypted_init_vector"));
                Integer googleKeyVersion = stringToInteger((String) values.get("encrypted_google_key_version"));
                if (googleKeyVersion != null) {
                    encryptedSubscriberInfoBuilder.setGoogleKeyVersion(googleKeyVersion.intValue());
                }
                Integer carrierKeyVersion = stringToInteger((String) values.get("encrypted_carrier_key_version"));
                if (carrierKeyVersion != null) {
                    encryptedSubscriberInfoBuilder.setCarrierKeyVersion(carrierKeyVersion.intValue());
                }
                EncryptedSubscriberInfo encryptedSubscriberInfo = encryptedSubscriberInfoBuilder.build();
                if (!encryptedSubscriberInfo.isEmpty()) {
                    builder.setEncryptedSubscriberInfo(encryptedSubscriberInfo);
                }
                return builder.build();
            } else if (credentials != null) {
                return new CarrierBillingProvisioning.Builder().setCredentials(credentials).build();
            } else {
                return null;
            }
        }
        throw new IllegalStateException("Attempt to fetch provisioning when key store isn't ready.");
    }

    public CarrierBillingCredentials getCredentials() {
        if (isInit()) {
            Map<String, String> values = this.mStore.get("credentials");
            if (values == null) {
                return null;
            }
            CarrierBillingCredentials.Builder builder = new CarrierBillingCredentials.Builder().setCredentials((String) values.get("credentials"));
            Integer apiVersion = stringToInteger((String) values.get("api_version"));
            if (apiVersion != null) {
                builder.setApiVersion(apiVersion.intValue());
            }
            Long expirationTime = stringToLong((String) values.get("expiration_time"));
            if (expirationTime != null) {
                builder.setExpirationTime(expirationTime.longValue());
            }
            Boolean isProvisioned = stringToBoolean((String) values.get("is_provisioned"));
            if (isProvisioned != null) {
                builder.setIsProvisioned(isProvisioned.booleanValue());
            }
            Boolean invalidPassword = stringToBoolean((String) values.get("invalid_password"));
            if (invalidPassword != null) {
                builder.setInvalidPassword(invalidPassword.booleanValue());
            }
            return builder.build();
        }
        throw new IllegalStateException("Attempt to fetch credentials when key store isn't ready.");
    }

    public void setParams(CarrierBillingParameters params) {
        Map<String, String> values = new HashMap();
        values.put("carrier_id", params.getId());
        values.put("carrier_name", params.getName());
        values.put("get_provisioning_url", params.getGetProvisioningUrl());
        values.put("get_credentials_url", params.getGetCredentialsUrl());
        values.put("show_carrier_tos", booleanToString(Boolean.valueOf(params.showCarrierTos())));
        values.put("carrier_api_version", integerToString(Integer.valueOf(params.getCarrierApiVersion())));
        values.put("per_transaction_credentials_required", booleanToString(Boolean.valueOf(params.perTransactionCredentialsRequired())));
        values.put("send_subscriber_info_with_carrier_requests", booleanToString(Boolean.valueOf(params.sendSubscriberInfoWithCarrierRequests())));
        this.mStore.put(getParamsKey(), values);
    }

    public void clearParams() {
        this.mStore.delete(getParamsKey());
    }

    public void setProvisioning(CarrierBillingProvisioning provisioning) {
        Map<String, String> values = new HashMap();
        values.put("api_version", integerToString(Integer.valueOf(provisioning.getApiVersion())));
        values.put("is_provisioned", booleanToString(Boolean.valueOf(provisioning.isProvisioned())));
        values.put("id", provisioning.getProvisioningId());
        values.put("tos_url", provisioning.getTosUrl());
        values.put("tos_version", provisioning.getTosVersion());
        values.put("subscriber_currency", provisioning.getSubscriberCurrency());
        values.put("transaction_limit", longToString(Long.valueOf(provisioning.getTransactionLimit())));
        values.put("account_type", provisioning.getAccountType());
        if (provisioning.getSubscriberInfo() != null) {
            values.put("subscriber_token", provisioning.getSubscriberInfo().toObfuscatedString());
        }
        values.put("password_required", booleanToString(Boolean.valueOf(provisioning.isPasswordRequired())));
        values.put("password_prompt", provisioning.getPasswordPrompt());
        values.put("password_forgot_url", provisioning.getPasswordForgotUrl());
        values.put("address_snippet", provisioning.getAddressSnippet());
        values.put("country", provisioning.getCountry());
        EncryptedSubscriberInfo encryptedSubscriberInfo = provisioning.getEncryptedSubscriberInfo();
        if (encryptedSubscriberInfo != null) {
            values.put("encrypted_message", encryptedSubscriberInfo.getMessage());
            values.put("encrypted_key", encryptedSubscriberInfo.getEncryptedKey());
            values.put("encrypted_signature", encryptedSubscriberInfo.getSignature());
            values.put("encrypted_init_vector", encryptedSubscriberInfo.getInitVector());
            values.put("encrypted_carrier_key_version", integerToString(Integer.valueOf(encryptedSubscriberInfo.getCarrierKeyVersion())));
            values.put("encrypted_google_key_version", integerToString(Integer.valueOf(encryptedSubscriberInfo.getGoogleKeyVersion())));
        }
        CarrierBillingCredentials credentials = provisioning.getCredentials();
        if (credentials != null) {
            setCredentials(credentials);
        }
        this.mStore.put("provisioning", values);
    }

    public void clearProvisioning() {
        this.mStore.delete("provisioning");
    }

    public void setCredentials(CarrierBillingCredentials credentials) {
        Map<String, String> values = new HashMap();
        values.put("credentials", credentials.getCredentials());
        values.put("expiration_time", longToString(Long.valueOf(credentials.getExpirationTime())));
        values.put("is_provisioned", booleanToString(Boolean.valueOf(credentials.isProvisioned())));
        values.put("invalid_password", booleanToString(Boolean.valueOf(credentials.invalidPassword())));
        this.mStore.put("credentials", values);
    }

    private String getParamsKey() {
        if (FinskyApp.get().getCurrentAccountName() == null) {
            return "params";
        }
        return "params" + Md5Util.secureHash(FinskyApp.get().getCurrentAccountName().getBytes());
    }

    public void clearCredentials() {
        this.mStore.delete("credentials");
    }

    String listToString(List<String> list) {
        return list == null ? null : TextUtils.join(",", list);
    }

    List<String> stringToList(String str) {
        return str == null ? null : new ArrayList(Arrays.asList(str.split(",")));
    }

    private String integerToString(Integer i) {
        return i == null ? null : Integer.toString(i.intValue());
    }

    private Integer stringToInteger(String str) {
        return str == null ? null : Integer.valueOf(Integer.parseInt(str));
    }

    private String booleanToString(Boolean b) {
        return b == null ? null : Boolean.toString(b.booleanValue());
    }

    private Boolean stringToBoolean(String str) {
        return str == null ? null : Boolean.valueOf(Boolean.parseBoolean(str));
    }

    private String longToString(Long l) {
        return l == null ? null : Long.toString(l.longValue());
    }

    private Long stringToLong(String str) {
        return str == null ? null : Long.valueOf(Long.parseLong(str));
    }

    public String getCurrentSimIdentifier() {
        return this.mCurrentSimIdentifier;
    }
}
