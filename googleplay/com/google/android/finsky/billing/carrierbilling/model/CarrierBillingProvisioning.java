package com.google.android.finsky.billing.carrierbilling.model;

import com.google.android.finsky.utils.Objects;

public class CarrierBillingProvisioning {
    private final String mAccountType;
    private final String mAddressSnippet;
    private final int mApiVersion;
    private final String mCountry;
    private final CarrierBillingCredentials mCredentials;
    private final EncryptedSubscriberInfo mEncryptedSubscriberInfo;
    private final boolean mIsProvisioned;
    private final String mPasswordForgotUrl;
    private final String mPasswordPrompt;
    private final boolean mPasswordRequired;
    private final String mProvisioningId;
    private final String mSubscriberCurrency;
    private final SubscriberInfo mSubscriberInfo;
    private final String mTosUrl;
    private final String mTosVersion;
    private final long mTransactionLimit;

    public static class Builder {
        private String accountType;
        private String addressSnippet;
        private int apiVersion;
        private String country;
        private CarrierBillingCredentials credentials;
        private EncryptedSubscriberInfo encryptedSubscriberInfo;
        private boolean isProvisioned;
        private String passwordForgotUrl;
        private String passwordPrompt;
        private boolean passwordRequired;
        private String provisioningId;
        private String subscriberCurrency;
        private SubscriberInfo subscriberInfo;
        private String tosUrl;
        private String tosVersion;
        private long transactionLimit;

        public Builder(CarrierBillingProvisioning original) {
            this.apiVersion = original.getApiVersion();
            this.isProvisioned = original.isProvisioned();
            this.provisioningId = original.getProvisioningId();
            this.tosUrl = original.getTosUrl();
            this.tosVersion = original.getTosVersion();
            this.subscriberCurrency = original.getSubscriberCurrency();
            this.transactionLimit = original.getTransactionLimit();
            this.accountType = original.getAccountType();
            this.subscriberInfo = original.getSubscriberInfo();
            this.credentials = original.getCredentials();
            this.passwordRequired = original.isPasswordRequired();
            this.passwordPrompt = original.getPasswordPrompt();
            this.passwordForgotUrl = original.getPasswordForgotUrl();
            this.encryptedSubscriberInfo = original.getEncryptedSubscriberInfo();
            this.addressSnippet = original.getAddressSnippet();
            this.country = original.getCountry();
        }

        public Builder setApiVersion(int apiVersion) {
            this.apiVersion = apiVersion;
            return this;
        }

        public Builder setIsProvisioned(boolean isProvisioned) {
            this.isProvisioned = isProvisioned;
            return this;
        }

        public Builder setProvisioningId(String provisioningId) {
            this.provisioningId = provisioningId;
            return this;
        }

        public Builder setTosUrl(String tosUrl) {
            this.tosUrl = tosUrl;
            return this;
        }

        public Builder setTosVersion(String tosVersion) {
            this.tosVersion = tosVersion;
            return this;
        }

        public Builder setSubscriberCurrency(String subscriberCurrency) {
            this.subscriberCurrency = subscriberCurrency;
            return this;
        }

        public Builder setTransactionLimit(long transactionLimit) {
            this.transactionLimit = transactionLimit;
            return this;
        }

        public Builder setAccountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder setSubscriberInfo(SubscriberInfo subscriberInfo) {
            this.subscriberInfo = subscriberInfo;
            return this;
        }

        public Builder setCredentials(CarrierBillingCredentials credentials) {
            this.credentials = credentials;
            return this;
        }

        public Builder setPasswordRequired(boolean passwordRequired) {
            this.passwordRequired = passwordRequired;
            return this;
        }

        public Builder setPasswordPrompt(String passwordPrompt) {
            this.passwordPrompt = passwordPrompt;
            return this;
        }

        public Builder setPasswordForgotUrl(String passwordForgotUrl) {
            this.passwordForgotUrl = passwordForgotUrl;
            return this;
        }

        public Builder setEncryptedSubscriberInfo(EncryptedSubscriberInfo encryptedSubscriberInfo) {
            this.encryptedSubscriberInfo = encryptedSubscriberInfo;
            return this;
        }

        public Builder setAddressSnippet(String addressSnippet) {
            this.addressSnippet = addressSnippet;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public CarrierBillingProvisioning build() {
            return new CarrierBillingProvisioning();
        }
    }

    private CarrierBillingProvisioning(Builder builder) {
        this.mApiVersion = builder.apiVersion;
        this.mIsProvisioned = builder.isProvisioned;
        this.mProvisioningId = builder.provisioningId;
        this.mTosUrl = builder.tosUrl;
        this.mTosVersion = builder.tosVersion;
        this.mSubscriberCurrency = builder.subscriberCurrency;
        this.mTransactionLimit = builder.transactionLimit;
        this.mAccountType = builder.accountType;
        this.mSubscriberInfo = builder.subscriberInfo;
        this.mCredentials = builder.credentials;
        this.mPasswordRequired = builder.passwordRequired;
        this.mPasswordPrompt = builder.passwordPrompt;
        this.mPasswordForgotUrl = builder.passwordForgotUrl;
        this.mEncryptedSubscriberInfo = builder.encryptedSubscriberInfo;
        this.mAddressSnippet = builder.addressSnippet;
        this.mCountry = builder.country;
    }

    public int getApiVersion() {
        return this.mApiVersion;
    }

    public boolean isProvisioned() {
        return this.mIsProvisioned;
    }

    public String getProvisioningId() {
        return this.mProvisioningId;
    }

    public String getTosUrl() {
        return this.mTosUrl;
    }

    public String getTosVersion() {
        return this.mTosVersion;
    }

    public String getSubscriberCurrency() {
        return this.mSubscriberCurrency;
    }

    public long getTransactionLimit() {
        return this.mTransactionLimit;
    }

    public String getAccountType() {
        return this.mAccountType;
    }

    public SubscriberInfo getSubscriberInfo() {
        return this.mSubscriberInfo;
    }

    public CarrierBillingCredentials getCredentials() {
        return this.mCredentials;
    }

    public boolean isPasswordRequired() {
        return this.mPasswordRequired;
    }

    public String getPasswordPrompt() {
        return this.mPasswordPrompt;
    }

    public String getPasswordForgotUrl() {
        return this.mPasswordForgotUrl;
    }

    public EncryptedSubscriberInfo getEncryptedSubscriberInfo() {
        return this.mEncryptedSubscriberInfo;
    }

    public String getAddressSnippet() {
        return this.mAddressSnippet;
    }

    public String getCountry() {
        return this.mCountry;
    }

    public String toString() {
        return "CarrierBillingProvisioning:" + "\n" + "  apiVersion             : " + this.mApiVersion + "\n" + "  isProvisioned          : " + this.mIsProvisioned + "\n" + "  provisioningId         : " + this.mProvisioningId + "\n" + "  tosUrl                 : " + this.mTosUrl + "\n" + "  tosVersion             : " + this.mTosVersion + "\n" + "  subscriberCurrency     : " + this.mSubscriberCurrency + "\n" + "  transactionLimit       : " + this.mTransactionLimit + "\n" + "  accountType            : " + this.mAccountType + "\n" + "  subscriberInfo         : " + this.mSubscriberInfo + "\n" + "  credentials            : " + this.mCredentials + "\n" + "  passwordRequired       : " + this.mPasswordRequired + "\n" + "  passwordPrompt         : " + this.mPasswordPrompt + "\n" + "  passwordForgotUrl      : " + this.mPasswordForgotUrl + "\n" + "  encryptedSubscriberInfo: " + this.mEncryptedSubscriberInfo + "\n" + "  addressSnippet         : " + this.mAddressSnippet + "\n" + "  country                : " + this.mCountry + "\n";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarrierBillingProvisioning)) {
            return false;
        }
        CarrierBillingProvisioning that = (CarrierBillingProvisioning) o;
        if (Objects.equal(Integer.valueOf(this.mApiVersion), Integer.valueOf(that.mApiVersion)) && Objects.equal(Boolean.valueOf(this.mIsProvisioned), Boolean.valueOf(that.mIsProvisioned)) && Objects.equal(this.mProvisioningId, that.mProvisioningId) && Objects.equal(this.mTosUrl, that.mTosUrl) && Objects.equal(this.mTosVersion, that.mTosVersion) && Objects.equal(this.mSubscriberCurrency, that.mSubscriberCurrency) && Objects.equal(Long.valueOf(this.mTransactionLimit), Long.valueOf(that.mTransactionLimit)) && Objects.equal(this.mAccountType, that.mAccountType) && Objects.equal(this.mSubscriberInfo, that.mSubscriberInfo) && Objects.equal(this.mCredentials, that.mCredentials) && Objects.equal(Boolean.valueOf(this.mPasswordRequired), Boolean.valueOf(that.mPasswordRequired)) && Objects.equal(this.mPasswordPrompt, that.mPasswordPrompt) && Objects.equal(this.mPasswordForgotUrl, that.mPasswordForgotUrl) && Objects.equal(this.mEncryptedSubscriberInfo, that.mEncryptedSubscriberInfo) && Objects.equal(this.mAddressSnippet, that.mAddressSnippet) && Objects.equal(this.mCountry, that.mCountry)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mApiVersion), Boolean.valueOf(this.mIsProvisioned), this.mProvisioningId, this.mTosUrl, this.mTosVersion, this.mSubscriberCurrency, Long.valueOf(this.mTransactionLimit), this.mAccountType, this.mSubscriberInfo, this.mCredentials, Boolean.valueOf(this.mPasswordRequired), this.mPasswordPrompt, this.mPasswordForgotUrl, this.mEncryptedSubscriberInfo, this.mAddressSnippet, this.mCountry);
    }
}
