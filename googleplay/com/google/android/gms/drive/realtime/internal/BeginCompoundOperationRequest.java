package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class BeginCompoundOperationRequest implements SafeParcelable {
    public static final Creator<BeginCompoundOperationRequest> CREATOR;
    final boolean adA;
    final boolean adz;
    final String mName;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    BeginCompoundOperationRequest(int versionCode, boolean isCreation, String name, boolean isUndoable) {
        this.mVersionCode = versionCode;
        this.adz = isCreation;
        this.mName = name;
        this.adA = isUndoable;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
