package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class m implements SafeParcelable, DataItem {
    public static final Creator<m> CREATOR;
    private final Map<String, DataItemAsset> aWM;
    private byte[] mData;
    private final Uri mUri;
    final int mVersionCode;

    static {
        CREATOR = new n();
    }

    m(int i, Uri uri, Bundle bundle, byte[] bArr) {
        this.mVersionCode = i;
        this.mUri = uri;
        Map hashMap = new HashMap();
        bundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        for (String str : bundle.keySet()) {
            hashMap.put(str, (DataItemAssetParcelable) bundle.getParcelable(str));
        }
        this.aWM = hashMap;
        this.mData = bArr;
    }

    public int describeContents() {
        return 0;
    }

    public /* synthetic */ Object freeze() {
        return vi();
    }

    public byte[] getData() {
        return this.mData;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public String toString() {
        return toString(Log.isLoggable("DataItem", 3));
    }

    public String toString(boolean verbose) {
        StringBuilder stringBuilder = new StringBuilder("DataItemParcelable[");
        stringBuilder.append("@");
        stringBuilder.append(Integer.toHexString(hashCode()));
        stringBuilder.append(",dataSz=" + (this.mData == null ? "null" : Integer.valueOf(this.mData.length)));
        stringBuilder.append(", numAssets=" + this.aWM.size());
        stringBuilder.append(", uri=" + this.mUri);
        if (verbose) {
            stringBuilder.append("]\n  assets: ");
            for (String str : this.aWM.keySet()) {
                stringBuilder.append("\n    " + str + ": " + this.aWM.get(str));
            }
            stringBuilder.append("\n  ]");
            return stringBuilder.toString();
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public Bundle vd() {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        for (Entry entry : this.aWM.entrySet()) {
            bundle.putParcelable((String) entry.getKey(), new DataItemAssetParcelable((DataItemAsset) entry.getValue()));
        }
        return bundle;
    }

    public m vi() {
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        n.a(this, dest, flags);
    }
}
