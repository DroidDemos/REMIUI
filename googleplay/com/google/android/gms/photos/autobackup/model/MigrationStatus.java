package com.google.android.gms.photos.autobackup.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class MigrationStatus implements SafeParcelable {
    public static final Creator<MigrationStatus> CREATOR;
    private boolean aGQ;
    public final int mVersionCode;

    static {
        CREATOR = new d();
    }

    MigrationStatus(int versionCode, boolean isMigrated) {
        this.mVersionCode = versionCode;
        this.aGQ = isMigrated;
    }

    public int describeContents() {
        return 0;
    }

    public boolean isMigrated() {
        return this.aGQ;
    }

    public void writeToParcel(Parcel out, int flags) {
        d.a(this, out, flags);
    }
}
