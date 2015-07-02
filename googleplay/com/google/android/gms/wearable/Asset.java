package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class Asset implements SafeParcelable {
    public static final Creator<Asset> CREATOR;
    private String aWf;
    public ParcelFileDescriptor aWg;
    private byte[] mData;
    final int mVersionCode;
    public Uri uri;

    static {
        CREATOR = new a();
    }

    Asset(int versionCode, byte[] data, String digest, ParcelFileDescriptor fd, Uri uri) {
        this.mVersionCode = versionCode;
        this.mData = data;
        this.aWf = digest;
        this.aWg = fd;
        this.uri = uri;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Asset)) {
            return false;
        }
        Asset asset = (Asset) o;
        return kl.equal(this.mData, asset.mData) && kl.equal(this.aWf, asset.aWf) && kl.equal(this.aWg, asset.aWg) && kl.equal(this.uri, asset.uri);
    }

    public byte[] getData() {
        return this.mData;
    }

    public String getDigest() {
        return this.aWf;
    }

    public int hashCode() {
        return kl.hashCode(this.mData, this.aWf, this.aWg, this.uri);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Asset[@");
        stringBuilder.append(Integer.toHexString(hashCode()));
        if (this.aWf == null) {
            stringBuilder.append(", nodigest");
        } else {
            stringBuilder.append(", ");
            stringBuilder.append(this.aWf);
        }
        if (this.mData != null) {
            stringBuilder.append(", size=");
            stringBuilder.append(this.mData.length);
        }
        if (this.aWg != null) {
            stringBuilder.append(", fd=");
            stringBuilder.append(this.aWg);
        }
        if (this.uri != null) {
            stringBuilder.append(", uri=");
            stringBuilder.append(this.uri);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags | 1);
    }
}
