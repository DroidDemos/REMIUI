package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class PlaceAlias implements SafeParcelable {
    public static final a CREATOR;
    public static final PlaceAlias HOME;
    public static final PlaceAlias WORK;
    private final String axx;
    final int mVersionCode;

    static {
        CREATOR = new a();
        HOME = new PlaceAlias(0, "Home");
        WORK = new PlaceAlias(0, "Work");
    }

    PlaceAlias(int versionCode, String alias) {
        this.mVersionCode = versionCode;
        this.axx = alias;
    }

    public int describeContents() {
        a aVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PlaceAlias)) {
            return false;
        }
        return kl.equal(this.axx, ((PlaceAlias) object).axx);
    }

    public String getAlias() {
        return this.axx;
    }

    public int hashCode() {
        return kl.hashCode(this.axx);
    }

    public String toString() {
        return kl.j(this).a("alias", this.axx).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        a aVar = CREATOR;
        a.a(this, parcel, flags);
    }
}
