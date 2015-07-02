package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

public class pf implements SafeParcelable {
    public static final pg CREATOR;
    static final List<ox> avO;
    LocationRequest ail;
    boolean avP;
    boolean avQ;
    boolean avR;
    List<ox> avS;
    final String mTag;
    private final int mVersionCode;

    static {
        avO = Collections.emptyList();
        CREATOR = new pg();
    }

    pf(int i, LocationRequest locationRequest, boolean z, boolean z2, boolean z3, List<ox> list, String str) {
        this.mVersionCode = i;
        this.ail = locationRequest;
        this.avP = z;
        this.avQ = z2;
        this.avR = z3;
        this.avS = list;
        this.mTag = str;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (!(other instanceof pf)) {
            return false;
        }
        pf pfVar = (pf) other;
        return kl.equal(this.ail, pfVar.ail) && this.avP == pfVar.avP && this.avQ == pfVar.avQ && this.avR == pfVar.avR && kl.equal(this.avS, pfVar.avS);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return this.ail.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.ail.toString());
        stringBuilder.append(" requestNlpDebugInfo=");
        stringBuilder.append(this.avP);
        stringBuilder.append(" restorePendingIntentListeners=");
        stringBuilder.append(this.avQ);
        stringBuilder.append(" triggerUpdate=");
        stringBuilder.append(this.avR);
        stringBuilder.append(" clients=");
        stringBuilder.append(this.avS);
        if (this.mTag != null) {
            stringBuilder.append(" tag=");
            stringBuilder.append(this.mTag);
        }
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        pg.a(this, parcel, flags);
    }
}
