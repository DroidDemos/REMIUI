package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.j;
import com.google.android.gms.fitness.data.j.a;

public class p implements SafeParcelable {
    public static final Creator<p> CREATOR;
    private final j aid;
    private final PendingIntent mPendingIntent;
    private final int mVersionCode;

    static {
        CREATOR = new q();
    }

    p(int i, IBinder iBinder, PendingIntent pendingIntent) {
        this.mVersionCode = i;
        this.aid = iBinder == null ? null : a.bK(iBinder);
        this.mPendingIntent = pendingIntent;
    }

    public int describeContents() {
        return 0;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public PendingIntent lU() {
        return this.mPendingIntent;
    }

    IBinder lZ() {
        return this.aid == null ? null : this.aid.asBinder();
    }

    public String toString() {
        return String.format("SensorUnregistrationRequest{%s}", new Object[]{this.aid});
    }

    public void writeToParcel(Parcel parcel, int flags) {
        q.a(this, parcel, flags);
    }
}
