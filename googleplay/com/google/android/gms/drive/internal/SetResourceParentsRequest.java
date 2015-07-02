package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import java.util.List;

public class SetResourceParentsRequest implements SafeParcelable {
    public static final Creator<SetResourceParentsRequest> CREATOR;
    final DriveId abe;
    final List<DriveId> abf;
    final int mVersionCode;

    static {
        CREATOR = new bf();
    }

    SetResourceParentsRequest(int versionCode, DriveId targetId, List<DriveId> parentIds) {
        this.mVersionCode = versionCode;
        this.abe = targetId;
        this.abf = parentIds;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        bf.a(this, dest, flags);
    }
}
