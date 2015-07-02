package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;

public final class SnapshotContentsEntity implements SafeParcelable, SnapshotContents {
    public static final SnapshotContentsEntityCreator CREATOR;
    private static final Object aqX;
    private Contents ZL;
    private final int mVersionCode;

    static {
        aqX = new Object();
        CREATOR = new SnapshotContentsEntityCreator();
    }

    SnapshotContentsEntity(int versionCode, Contents contents) {
        this.mVersionCode = versionCode;
        this.ZL = contents;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean isClosed() {
        return this.ZL == null;
    }

    public Contents jC() {
        return this.ZL;
    }

    public void writeToParcel(Parcel out, int flags) {
        SnapshotContentsEntityCreator.a(this, out, flags);
    }
}
