package com.google.android.gms.wallet.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class BrokerAndRelationships implements SafeParcelable {
    public static final Creator<BrokerAndRelationships> CREATOR;
    String aVy;
    String[] aVz;
    private final int mVersionCode;

    static {
        CREATOR = new b();
    }

    BrokerAndRelationships() {
        this(1, null, null);
    }

    BrokerAndRelationships(int versionCode, String brokerId, String[] relationships) {
        this.mVersionCode = versionCode;
        this.aVy = brokerId;
        if (relationships == null) {
            relationships = new String[0];
        }
        this.aVz = relationships;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
