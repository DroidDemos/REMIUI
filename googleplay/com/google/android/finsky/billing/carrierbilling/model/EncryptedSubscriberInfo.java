package com.google.android.finsky.billing.carrierbilling.model;

import android.text.TextUtils;
import com.google.android.finsky.utils.Objects;

public class EncryptedSubscriberInfo {
    public final int mCarrierKeyVersion;
    public final String mEncryptedKey;
    public final int mGoogleKeyVersion;
    public final String mInitVector;
    public final String mMessage;
    public final String mSignature;

    public static class Builder {
        private int carrierKeyVersion;
        private String encryptedKey;
        private int googleKeyVersion;
        private String initVector;
        private String message;
        private String signature;

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setEncryptedKey(String encryptedKey) {
            this.encryptedKey = encryptedKey;
            return this;
        }

        public Builder setSignature(String signature) {
            this.signature = signature;
            return this;
        }

        public Builder setInitVector(String initVector) {
            this.initVector = initVector;
            return this;
        }

        public Builder setCarrierKeyVersion(int carrierKeyVersion) {
            this.carrierKeyVersion = carrierKeyVersion;
            return this;
        }

        public Builder setGoogleKeyVersion(int googleKeyVersion) {
            this.googleKeyVersion = googleKeyVersion;
            return this;
        }

        public EncryptedSubscriberInfo build() {
            return new EncryptedSubscriberInfo();
        }
    }

    private EncryptedSubscriberInfo(Builder builder) {
        this.mMessage = builder.message;
        this.mEncryptedKey = builder.encryptedKey;
        this.mSignature = builder.signature;
        this.mInitVector = builder.initVector;
        this.mCarrierKeyVersion = builder.carrierKeyVersion;
        this.mGoogleKeyVersion = builder.googleKeyVersion;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getEncryptedKey() {
        return this.mEncryptedKey;
    }

    public String getSignature() {
        return this.mSignature;
    }

    public String getInitVector() {
        return this.mInitVector;
    }

    public int getCarrierKeyVersion() {
        return this.mCarrierKeyVersion;
    }

    public int getGoogleKeyVersion() {
        return this.mGoogleKeyVersion;
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(getMessage()) && TextUtils.isEmpty(getEncryptedKey()) && TextUtils.isEmpty(getSignature()) && TextUtils.isEmpty(getInitVector());
    }

    public com.google.android.finsky.protos.EncryptedSubscriberInfo toProto() {
        com.google.android.finsky.protos.EncryptedSubscriberInfo info = new com.google.android.finsky.protos.EncryptedSubscriberInfo();
        info.data = this.mMessage;
        info.hasData = true;
        info.encryptedKey = this.mEncryptedKey;
        info.hasEncryptedKey = true;
        info.signature = this.mSignature;
        info.hasSignature = true;
        info.initVector = this.mInitVector;
        info.hasInitVector = true;
        info.googleKeyVersion = this.mGoogleKeyVersion;
        info.hasGoogleKeyVersion = true;
        info.carrierKeyVersion = this.mCarrierKeyVersion;
        info.hasCarrierKeyVersion = true;
        return info;
    }

    public String toString() {
        return "EncryptedSubscriberInfo:" + "\n" + "  message          : " + this.mMessage + "\n" + "  encryptedKey     : " + this.mEncryptedKey + "\n" + "  signature        : " + this.mSignature + "\n" + "  initVector       : " + this.mInitVector + "\n" + "  carrierKeyVersion: " + this.mCarrierKeyVersion + "\n" + "  googleKeyVersion : " + this.mGoogleKeyVersion + "\n";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EncryptedSubscriberInfo)) {
            return false;
        }
        EncryptedSubscriberInfo that = (EncryptedSubscriberInfo) o;
        if (Objects.equal(this.mMessage, that.mMessage) && Objects.equal(this.mEncryptedKey, that.mEncryptedKey) && Objects.equal(this.mSignature, that.mSignature) && Objects.equal(this.mInitVector, that.mInitVector) && Objects.equal(Integer.valueOf(this.mCarrierKeyVersion), Integer.valueOf(that.mCarrierKeyVersion)) && Objects.equal(Integer.valueOf(this.mGoogleKeyVersion), Integer.valueOf(that.mGoogleKeyVersion))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.mMessage, this.mEncryptedKey, this.mSignature, this.mInitVector, Integer.valueOf(this.mCarrierKeyVersion), Integer.valueOf(this.mGoogleKeyVersion));
    }
}
