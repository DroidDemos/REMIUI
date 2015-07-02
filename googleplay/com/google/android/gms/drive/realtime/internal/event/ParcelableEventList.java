package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class ParcelableEventList implements SafeParcelable {
    public static final Creator<ParcelableEventList> CREATOR;
    final DataHolder aeW;
    final boolean aeX;
    final List<String> aeY;
    final int mVersionCode;
    final List<ParcelableEvent> ms;

    static {
        CREATOR = new d();
    }

    ParcelableEventList(int versionCode, List<ParcelableEvent> events, DataHolder eventData, boolean undoRedoStateChanged, List<String> affectedObjectIds) {
        this.mVersionCode = versionCode;
        this.ms = events;
        this.aeW = eventData;
        this.aeX = undoRedoStateChanged;
        this.aeY = affectedObjectIds;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        d.a(this, dest, flags);
    }
}
