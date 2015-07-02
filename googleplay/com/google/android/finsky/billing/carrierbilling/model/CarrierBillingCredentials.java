package com.google.android.finsky.billing.carrierbilling.model;

import com.google.android.finsky.utils.Objects;

public class CarrierBillingCredentials {
    private final int mApiVersion;
    private final String mCredentials;
    private final long mExpirationTime;
    private final boolean mInvalidPassword;
    private final boolean mIsProvisioned;

    public static class Builder {
        private int apiVersion;
        private String credentials;
        private long expirationTime;
        private boolean invalidPassword;
        private boolean isProvisioned;

        public Builder(CarrierBillingCredentials oldCreds) {
            this.apiVersion = oldCreds.mApiVersion;
            this.credentials = oldCreds.mCredentials;
            this.expirationTime = oldCreds.mExpirationTime;
            this.isProvisioned = oldCreds.mIsProvisioned;
            this.invalidPassword = oldCreds.mInvalidPassword;
        }

        public Builder setApiVersion(int apiVersion) {
            this.apiVersion = apiVersion;
            return this;
        }

        public Builder setCredentials(String credentials) {
            this.credentials = credentials;
            return this;
        }

        public Builder setExpirationTime(long expirationTime) {
            this.expirationTime = expirationTime;
            return this;
        }

        public Builder setIsProvisioned(boolean isProvisioned) {
            this.isProvisioned = isProvisioned;
            return this;
        }

        public Builder setInvalidPassword(boolean invalidPassword) {
            this.invalidPassword = invalidPassword;
            return this;
        }

        public CarrierBillingCredentials build() {
            return new CarrierBillingCredentials();
        }
    }

    private CarrierBillingCredentials(Builder builder) {
        this.mApiVersion = builder.apiVersion;
        this.mCredentials = builder.credentials;
        this.mExpirationTime = builder.expirationTime;
        this.mIsProvisioned = builder.isProvisioned;
        this.mInvalidPassword = builder.invalidPassword;
    }

    public int getApiVersion() {
        return this.mApiVersion;
    }

    public String getCredentials() {
        return this.mCredentials;
    }

    public long getExpirationTime() {
        return this.mExpirationTime;
    }

    public boolean isProvisioned() {
        return this.mIsProvisioned;
    }

    public boolean invalidPassword() {
        return this.mInvalidPassword;
    }

    public String toString() {
        return "CarrierBillingCredentials: " + "  apiVersion     : " + this.mApiVersion + "\n" + "  credentials    : " + this.mCredentials + "\n" + "  expirationTime : " + this.mExpirationTime + "\n" + "  isProvisioned  : " + this.mIsProvisioned + "\n" + "  invalidPassword: " + this.mInvalidPassword + "\n";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarrierBillingCredentials)) {
            return false;
        }
        CarrierBillingCredentials that = (CarrierBillingCredentials) o;
        if (Objects.equal(Integer.valueOf(this.mApiVersion), Integer.valueOf(that.mApiVersion)) && Objects.equal(this.mCredentials, that.mCredentials) && Objects.equal(Long.valueOf(this.mExpirationTime), Long.valueOf(that.mExpirationTime)) && Objects.equal(Boolean.valueOf(this.mIsProvisioned), Boolean.valueOf(that.mIsProvisioned)) && Objects.equal(Boolean.valueOf(this.mInvalidPassword), Boolean.valueOf(that.mInvalidPassword))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mApiVersion), this.mCredentials, Long.valueOf(this.mExpirationTime), Boolean.valueOf(this.mIsProvisioned), Boolean.valueOf(this.mInvalidPassword));
    }
}
