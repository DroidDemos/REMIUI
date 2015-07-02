package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class z implements SafeParcelable {
    public static final Creator<z> CREATOR;
    private final PendingIntent mPendingIntent;
    private final int mVersionCode;

    static {
        CREATOR = new aa();
    }

    z(int i, PendingIntent pendingIntent) {
        this.mVersionCode = i;
        this.mPendingIntent = pendingIntent;
    }

    private boolean a(z zVar) {
        return kl.equal(this.mPendingIntent, zVar.mPendingIntent);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof z) && a((z) that));
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.mPendingIntent);
    }

    public PendingIntent lU() {
        return this.mPendingIntent;
    }

    public String toString() {
        return kl.j(this).a("pendingIntent", this.mPendingIntent).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        aa.a(this, parcel, flags);
    }
}
