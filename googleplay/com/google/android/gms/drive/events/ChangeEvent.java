package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import java.util.Locale;

public final class ChangeEvent implements SafeParcelable, ResourceEvent {
    public static final Creator<ChangeEvent> CREATOR;
    final int YK;
    final DriveId Yb;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    ChangeEvent(int versionCode, DriveId driveId, int changeFlags) {
        this.mVersionCode = versionCode;
        this.Yb = driveId;
        this.YK = changeFlags;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format(Locale.US, "ChangeEvent [id=%s,changeFlags=%x]", new Object[]{this.Yb, Integer.valueOf(this.YK)});
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
