package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public final class NearbyAlertRequest implements SafeParcelable {
    public static final c CREATOR;
    private final int atm;
    private final int awb;
    private final PlaceFilter awc;
    private final int mVersionCode;

    static {
        CREATOR = new c();
    }

    NearbyAlertRequest(int versionCode, int transitionTypes, int loiteringTimeMillis, PlaceFilter placeFilter) {
        this.mVersionCode = versionCode;
        this.atm = transitionTypes;
        this.awb = loiteringTimeMillis;
        this.awc = placeFilter;
    }

    public int describeContents() {
        c cVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof NearbyAlertRequest)) {
            return false;
        }
        NearbyAlertRequest nearbyAlertRequest = (NearbyAlertRequest) object;
        return this.atm == nearbyAlertRequest.atm && this.awb == nearbyAlertRequest.awb && this.awc.equals(nearbyAlertRequest.awc);
    }

    public PlaceFilter getFilter() {
        return this.awc;
    }

    public int getLoiteringTimeMillis() {
        return this.awb;
    }

    public int getTransitionTypes() {
        return this.atm;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.atm), Integer.valueOf(this.awb));
    }

    public String toString() {
        return kl.j(this).a("transitionTypes", Integer.valueOf(this.atm)).a("loiteringTimeMillis", Integer.valueOf(this.awb)).a("placeFilter", this.awc).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        c cVar = CREATOR;
        c.a(this, parcel, flags);
    }
}
