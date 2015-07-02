package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ReferenceShiftedDetails implements SafeParcelable {
    public static final Creator<ReferenceShiftedDetails> CREATOR;
    final String adn;
    final String ado;
    final int adp;
    final int adq;
    final int mVersionCode;

    static {
        CREATOR = new e();
    }

    ReferenceShiftedDetails(int versionCode, String oldObjectId, String newObjectId, int oldIndex, int newIndex) {
        this.mVersionCode = versionCode;
        this.ado = oldObjectId;
        this.adn = newObjectId;
        this.adq = oldIndex;
        this.adp = newIndex;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        e.a(this, dest, flags);
    }
}
