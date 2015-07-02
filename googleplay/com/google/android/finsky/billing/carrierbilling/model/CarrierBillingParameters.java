package com.google.android.finsky.billing.carrierbilling.model;

import com.google.android.finsky.utils.Objects;

public class CarrierBillingParameters {
    private final int mCarrierApiVersion;
    private final String mGetCredentialsUrl;
    private final String mGetProvisioningUrl;
    private final String mId;
    private final String mName;
    private final boolean mPerTransactionCredentialsRequired;
    private final boolean mSendSubscriberInfoWithCarrierRequests;
    private final boolean mShowCarrierTos;

    public static class Builder {
        private int carrierApiVersion;
        private String getCredentialsUrl;
        private String getProvisioningUrl;
        private String id;
        private String name;
        private boolean perTransactionCredentialsRequired;
        private boolean sendSubscriberInfoWithCarrierRequests;
        private boolean showCarrierTos;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setGetProvisioningUrl(String getProvisioningUrl) {
            this.getProvisioningUrl = getProvisioningUrl;
            return this;
        }

        public Builder setGetCredentialsUrl(String getCredentialsUrl) {
            this.getCredentialsUrl = getCredentialsUrl;
            return this;
        }

        public Builder setShowCarrierTos(boolean showCarrierTos) {
            this.showCarrierTos = showCarrierTos;
            return this;
        }

        public Builder setCarrierApiVersion(int carrierApiVersion) {
            this.carrierApiVersion = carrierApiVersion;
            return this;
        }

        public Builder setPerTransactionCredentialsRequired(boolean perTransactionCredentialsRequired) {
            this.perTransactionCredentialsRequired = perTransactionCredentialsRequired;
            return this;
        }

        public Builder setSendSubscriberInfoWithCarrierRequests(boolean sendSubscriberInfoWithCarrierRequests) {
            this.sendSubscriberInfoWithCarrierRequests = sendSubscriberInfoWithCarrierRequests;
            return this;
        }

        public CarrierBillingParameters build() {
            return new CarrierBillingParameters();
        }
    }

    private CarrierBillingParameters(Builder builder) {
        this.mId = builder.id;
        this.mName = builder.name;
        this.mGetProvisioningUrl = builder.getProvisioningUrl;
        this.mGetCredentialsUrl = builder.getCredentialsUrl;
        this.mShowCarrierTos = builder.showCarrierTos;
        this.mCarrierApiVersion = builder.carrierApiVersion;
        this.mPerTransactionCredentialsRequired = builder.perTransactionCredentialsRequired;
        this.mSendSubscriberInfoWithCarrierRequests = builder.sendSubscriberInfoWithCarrierRequests;
    }

    public String getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getGetProvisioningUrl() {
        return this.mGetProvisioningUrl;
    }

    public String getGetCredentialsUrl() {
        return this.mGetCredentialsUrl;
    }

    public boolean showCarrierTos() {
        return this.mShowCarrierTos;
    }

    public int getCarrierApiVersion() {
        return this.mCarrierApiVersion;
    }

    public boolean perTransactionCredentialsRequired() {
        return this.mPerTransactionCredentialsRequired;
    }

    public boolean sendSubscriberInfoWithCarrierRequests() {
        return this.mSendSubscriberInfoWithCarrierRequests;
    }

    public String toString() {
        return "CarrierBillingParameters:" + "\n" + "  id                                   : " + this.mId + "\n" + "  name                                 : " + this.mName + "\n" + "  getProvisioningUrl                   : " + this.mGetProvisioningUrl + "\n" + "  getCredentialsUrl                    : " + this.mGetCredentialsUrl + "\n" + "  showCarrierTos                       : " + this.mShowCarrierTos + "\n" + "  carrierApiVersion                    : " + this.mCarrierApiVersion + "\n" + "  perTransactionCredentialsRequired    : " + this.mPerTransactionCredentialsRequired + "\n" + "  sendSubscriberInfoWithCarrierRequests: " + this.mSendSubscriberInfoWithCarrierRequests + "\n";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarrierBillingParameters)) {
            return false;
        }
        CarrierBillingParameters that = (CarrierBillingParameters) o;
        if (Objects.equal(this.mId, that.mId) && Objects.equal(this.mName, that.mName) && Objects.equal(this.mGetProvisioningUrl, that.mGetProvisioningUrl) && Objects.equal(this.mGetCredentialsUrl, that.mGetCredentialsUrl) && Objects.equal(Boolean.valueOf(this.mShowCarrierTos), Boolean.valueOf(that.mShowCarrierTos)) && Objects.equal(Integer.valueOf(this.mCarrierApiVersion), Integer.valueOf(that.mCarrierApiVersion)) && Objects.equal(Boolean.valueOf(this.mPerTransactionCredentialsRequired), Boolean.valueOf(that.mPerTransactionCredentialsRequired)) && Objects.equal(Boolean.valueOf(this.mSendSubscriberInfoWithCarrierRequests), Boolean.valueOf(that.mSendSubscriberInfoWithCarrierRequests))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.mId, this.mName, this.mGetProvisioningUrl, this.mGetCredentialsUrl, Boolean.valueOf(this.mShowCarrierTos), Integer.valueOf(this.mCarrierApiVersion), Boolean.valueOf(this.mPerTransactionCredentialsRequired), Boolean.valueOf(this.mSendSubscriberInfoWithCarrierRequests));
    }
}
