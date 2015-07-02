package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.g;

public class OnListEntriesResponse extends g implements SafeParcelable {
    public static final Creator<OnListEntriesResponse> CREATOR;
    final boolean Zy;
    final DataHolder aaZ;
    final int mVersionCode;

    static {
        CREATOR = new ar();
    }

    OnListEntriesResponse(int versionCode, DataHolder entries, boolean moreEntriesMayExist) {
        this.mVersionCode = versionCode;
        this.aaZ = entries;
        this.Zy = moreEntriesMayExist;
    }

    protected void K(Parcel parcel, int i) {
        ar.a(this, parcel, i);
    }

    public int describeContents() {
        return 0;
    }
}
