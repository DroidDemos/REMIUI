package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;
import com.google.android.gms.wearable.DataItemAsset;

public class DataItemAssetParcelable implements SafeParcelable, DataItemAsset {
    public static final Creator<DataItemAssetParcelable> CREATOR;
    private final String CB;
    private final String TN;
    final int mVersionCode;

    static {
        CREATOR = new j();
    }

    DataItemAssetParcelable(int versionCode, String id, String key) {
        this.mVersionCode = versionCode;
        this.CB = id;
        this.TN = key;
    }

    public DataItemAssetParcelable(DataItemAsset value) {
        this.mVersionCode = 1;
        this.CB = (String) kn.k(value.getId());
        this.TN = (String) kn.k(value.getDataItemKey());
    }

    public int describeContents() {
        return 0;
    }

    public /* synthetic */ Object freeze() {
        return vg();
    }

    public String getDataItemKey() {
        return this.TN;
    }

    public String getId() {
        return this.CB;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DataItemAssetParcelable[");
        stringBuilder.append("@");
        stringBuilder.append(Integer.toHexString(hashCode()));
        if (this.CB == null) {
            stringBuilder.append(",noid");
        } else {
            stringBuilder.append(",");
            stringBuilder.append(this.CB);
        }
        stringBuilder.append(", key=");
        stringBuilder.append(this.TN);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public DataItemAsset vg() {
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        j.a(this, dest, flags);
    }
}
