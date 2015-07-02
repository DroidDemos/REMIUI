package com.google.android.finsky.billing.carrierbilling.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.finsky.utils.Objects;
import com.google.android.finsky.utils.Utils;

public class SubscriberInfo implements Parcelable {
    public static final Creator<SubscriberInfo> CREATOR;
    private final String mAddress1;
    private final String mAddress2;
    private final String mCity;
    private final String mCountry;
    private final String mIdentifier;
    private final String mName;
    private final String mPostalCode;
    private final String mState;

    public static class Builder {
        private String address1;
        private String address2;
        private String city;
        private String country;
        private String identifier;
        private String name;
        private String postalCode;
        private String state;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setIdentifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder setAddress1(String address1) {
            this.address1 = address1;
            return this;
        }

        public Builder setAddress2(String address2) {
            this.address2 = address2;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public SubscriberInfo build() {
            return new SubscriberInfo();
        }
    }

    private static String switchChars(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        for (char c : s.toCharArray()) {
            char c2;
            if ((c2 >= 'a' && c2 <= 'm') || (c2 >= 'A' && c2 <= 'M')) {
                c2 = (char) (c2 + 13);
            } else if ((c2 >= 'n' && c2 <= 'z') || (c2 >= 'N' && c2 <= 'Z')) {
                c2 = (char) (c2 - 13);
            } else if (c2 >= '0' && c2 <= '4') {
                c2 = (char) (c2 + 5);
            } else if (c2 >= '5' && c2 <= '9') {
                c2 = (char) (c2 - 5);
            }
            sb.append(c2);
        }
        return sb.toString();
    }

    public String toObfuscatedString() {
        String safeName = this.mName == null ? "" : Utils.urlEncode(this.mName);
        String safeIdentifier = this.mIdentifier == null ? "" : Utils.urlEncode(this.mIdentifier);
        String safeAddress1 = this.mAddress1 == null ? "" : Utils.urlEncode(this.mAddress1);
        String safeAddress2 = this.mAddress2 == null ? "" : Utils.urlEncode(this.mAddress2);
        String safeCity = this.mCity == null ? "" : Utils.urlEncode(this.mCity);
        String safeState = this.mState == null ? "" : Utils.urlEncode(this.mState);
        return switchChars(new String(Base64.encode((safeName + "," + safeIdentifier + "," + safeAddress1 + "," + safeAddress2 + "," + safeCity + "," + safeState + "," + (this.mPostalCode == null ? "" : Utils.urlEncode(this.mPostalCode)) + "," + (this.mCountry == null ? "" : Utils.urlEncode(this.mCountry))).getBytes(), 0)));
    }

    public static SubscriberInfo fromObfuscatedString(String obfuscated) {
        Builder builder = new Builder();
        String[] csvValues = new String(Base64.decode(switchChars(obfuscated).getBytes(), 0)).split(",", 8);
        if (csvValues.length != 8) {
            throw new IllegalArgumentException("SubscriberInfo could not be parsed from " + obfuscated);
        }
        builder.setName(Utils.urlDecode(csvValues[0])).setIdentifier(Utils.urlDecode(csvValues[1])).setAddress1(Utils.urlDecode(csvValues[2])).setAddress2(Utils.urlDecode(csvValues[3])).setCity(Utils.urlDecode(csvValues[4])).setState(Utils.urlDecode(csvValues[5])).setPostalCode(Utils.urlDecode(csvValues[6])).setCountry(Utils.urlDecode(csvValues[7]));
        return builder.build();
    }

    private SubscriberInfo(Builder builder) {
        this.mName = builder.name;
        this.mIdentifier = builder.identifier;
        this.mAddress1 = builder.address1;
        this.mAddress2 = builder.address2;
        this.mCity = builder.city;
        this.mState = builder.state;
        this.mPostalCode = builder.postalCode;
        this.mCountry = builder.country;
    }

    public String getName() {
        return this.mName;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public String getAddress1() {
        return this.mAddress1;
    }

    public String getAddress2() {
        return this.mAddress2;
    }

    public String getCity() {
        return this.mCity;
    }

    public String getState() {
        return this.mState;
    }

    public String getPostalCode() {
        return this.mPostalCode;
    }

    public String getCountry() {
        return this.mCountry;
    }

    public String toString() {
        return toObfuscatedString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriberInfo)) {
            return false;
        }
        SubscriberInfo that = (SubscriberInfo) o;
        if (Objects.equal(this.mName, that.mName) && Objects.equal(this.mIdentifier, that.mIdentifier) && Objects.equal(this.mAddress1, that.mAddress1) && Objects.equal(this.mAddress2, that.mAddress2) && Objects.equal(this.mCity, that.mCity) && Objects.equal(this.mState, that.mState) && Objects.equal(this.mPostalCode, that.mPostalCode) && Objects.equal(this.mCountry, that.mCountry)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.mName, this.mIdentifier, this.mAddress1, this.mAddress2, this.mCity, this.mState, this.mPostalCode, this.mCountry);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.mName);
        out.writeString(this.mIdentifier);
        out.writeString(this.mAddress1);
        out.writeString(this.mAddress2);
        out.writeString(this.mCity);
        out.writeString(this.mState);
        out.writeString(this.mPostalCode);
        out.writeString(this.mCountry);
    }

    static {
        CREATOR = new Creator<SubscriberInfo>() {
            public SubscriberInfo createFromParcel(Parcel in) {
                return new SubscriberInfo(in);
            }

            public SubscriberInfo[] newArray(int size) {
                return new SubscriberInfo[size];
            }
        };
    }

    private SubscriberInfo(Parcel in) {
        this.mName = in.readString();
        this.mIdentifier = in.readString();
        this.mAddress1 = in.readString();
        this.mAddress2 = in.readString();
        this.mCity = in.readString();
        this.mState = in.readString();
        this.mPostalCode = in.readString();
        this.mCountry = in.readString();
    }
}
