package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kn;
import com.google.android.gms.maps.internal.aa;

public final class LatLngBounds implements SafeParcelable {
    public static final g CREATOR;
    private final int mVersionCode;
    public final LatLng northeast;
    public final LatLng southwest;

    static {
        CREATOR = new g();
    }

    LatLngBounds(int versionCode, LatLng southwest, LatLng northeast) {
        kn.b((Object) southwest, (Object) "null southwest");
        kn.b((Object) northeast, (Object) "null northeast");
        kn.b(northeast.latitude >= southwest.latitude, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(southwest.latitude), Double.valueOf(northeast.latitude));
        this.mVersionCode = versionCode;
        this.southwest = southwest;
        this.northeast = northeast;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) o;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.southwest, this.northeast);
    }

    public String toString() {
        return kl.j(this).a("southwest", this.southwest).a("northeast", this.northeast).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        if (aa.qp()) {
            h.a(this, out, flags);
        } else {
            g.a(this, out, flags);
        }
    }
}
