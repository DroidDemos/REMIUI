package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;

public class CreateContentsRequest implements SafeParcelable {
    public static final Creator<CreateContentsRequest> CREATOR;
    final int Ya;
    final int mVersionCode;

    static {
        CREATOR = new h();
    }

    CreateContentsRequest(int versionCode, int mode) {
        this.mVersionCode = versionCode;
        boolean z = mode == 536870912 || mode == 805306368;
        kn.b(z, (Object) "Cannot create a new read-only contents!");
        this.Ya = mode;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        h.a(this, dest, flags);
    }
}
