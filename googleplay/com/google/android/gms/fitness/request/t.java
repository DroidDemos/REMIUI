package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class t implements SafeParcelable {
    public static final Creator<t> CREATOR;
    private final PendingIntent mPendingIntent;
    private final int mVersionCode;

    static {
        CREATOR = new u();
    }

    t(int i, PendingIntent pendingIntent) {
        this.mVersionCode = i;
        this.mPendingIntent = pendingIntent;
    }

    private boolean a(t tVar) {
        return kl.equal(this.mPendingIntent, tVar.mPendingIntent);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof t) && a((t) that));
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
        u.a(this, parcel, flags);
    }
}
