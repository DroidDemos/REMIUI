package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.internal.DataItemAssetParcelable;
import java.security.SecureRandom;
import java.util.Random;

public class PutDataRequest implements SafeParcelable {
    public static final Creator<PutDataRequest> CREATOR;
    private static final Random aWn;
    private final Bundle aWo;
    private byte[] mData;
    private final Uri mUri;
    final int mVersionCode;

    static {
        CREATOR = new c();
        aWn = new SecureRandom();
    }

    PutDataRequest(int versionCode, Uri uri, Bundle assets, byte[] data) {
        this.mVersionCode = versionCode;
        this.mUri = uri;
        this.aWo = assets;
        this.aWo.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        this.mData = data;
    }

    public int describeContents() {
        return 0;
    }

    public byte[] getData() {
        return this.mData;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public String toString() {
        return toString(Log.isLoggable("DataMap", 3));
    }

    public String toString(boolean verbose) {
        StringBuilder stringBuilder = new StringBuilder("PutDataRequest[");
        stringBuilder.append("dataSz=" + (this.mData == null ? "null" : Integer.valueOf(this.mData.length)));
        stringBuilder.append(", numAssets=" + this.aWo.size());
        stringBuilder.append(", uri=" + this.mUri);
        if (verbose) {
            stringBuilder.append("]\n  assets: ");
            for (String str : this.aWo.keySet()) {
                stringBuilder.append("\n    " + str + ": " + this.aWo.getParcelable(str));
            }
            stringBuilder.append("\n  ]");
            return stringBuilder.toString();
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public Bundle vd() {
        return this.aWo;
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
