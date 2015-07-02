package com.google.android.finsky.billing;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.finsky.utils.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IabParameters implements Parcelable {
    public static final Creator<IabParameters> CREATOR;
    public final int billingApiVersion;
    public final String developerPayload;
    public final List<String> oldSkusAsDocidStrings;
    public final String packageName;
    public final String packageSignatureHash;
    public final int packageVersionCode;

    static {
        CREATOR = new Creator<IabParameters>() {
            public IabParameters createFromParcel(Parcel parcel) {
                String packageName = parcel.readString();
                int packageVersionCode = parcel.readInt();
                String packageSignatureHash = parcel.readString();
                String developerPayload = null;
                if (parcel.readByte() > (byte) 0) {
                    developerPayload = parcel.readString();
                }
                int apiVersion = parcel.readInt();
                ArrayList<String> oldSkus = null;
                if (parcel.readByte() > (byte) 0) {
                    oldSkus = new ArrayList();
                    parcel.readStringList(oldSkus);
                }
                return new IabParameters(apiVersion, packageName, packageVersionCode, packageSignatureHash, developerPayload, oldSkus);
            }

            public IabParameters[] newArray(int size) {
                return new IabParameters[size];
            }
        };
    }

    public IabParameters(int billingApiVersion, String packageName, int packageVersionCode, String packageSignatureHash, String developerPayload, List<String> oldSkusAsDocidStrings) {
        this.billingApiVersion = billingApiVersion;
        this.packageName = packageName;
        this.packageVersionCode = packageVersionCode;
        this.packageSignatureHash = packageSignatureHash;
        this.developerPayload = developerPayload;
        this.oldSkusAsDocidStrings = oldSkusAsDocidStrings == null ? null : Collections.unmodifiableList(oldSkusAsDocidStrings);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.packageName);
        parcel.writeInt(this.packageVersionCode);
        parcel.writeString(this.packageSignatureHash);
        if (this.developerPayload != null) {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.developerPayload);
        } else {
            parcel.writeByte((byte) 0);
        }
        parcel.writeInt(this.billingApiVersion);
        if (this.oldSkusAsDocidStrings == null) {
            parcel.writeByte((byte) 0);
            return;
        }
        parcel.writeByte((byte) 1);
        parcel.writeStringList(this.oldSkusAsDocidStrings);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IabParameters that = (IabParameters) o;
        if (this.packageVersionCode != that.packageVersionCode) {
            return false;
        }
        if (this.developerPayload == null ? that.developerPayload != null : !this.developerPayload.equals(that.developerPayload)) {
            return false;
        }
        if (this.packageName == null ? that.packageName != null : !this.packageName.equals(that.packageName)) {
            return false;
        }
        if (this.packageSignatureHash == null ? that.packageSignatureHash != null : !this.packageSignatureHash.equals(that.packageSignatureHash)) {
            return false;
        }
        if (this.billingApiVersion != that.billingApiVersion) {
            return false;
        }
        if (Objects.equal(this.oldSkusAsDocidStrings, that.oldSkusAsDocidStrings)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return 42;
    }
}
