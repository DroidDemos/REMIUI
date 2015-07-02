package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.pl;

public class PlaceLikelihood implements SafeParcelable {
    public static final Creator<PlaceLikelihood> CREATOR;
    final pl awl;
    final float awm;
    final int mVersionCode;

    static {
        CREATOR = new e();
    }

    PlaceLikelihood(int versionCode, pl place, float likelihood) {
        this.mVersionCode = versionCode;
        this.awl = place;
        this.awm = likelihood;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PlaceLikelihood)) {
            return false;
        }
        PlaceLikelihood placeLikelihood = (PlaceLikelihood) object;
        return this.awl.equals(placeLikelihood.awl) && this.awm == placeLikelihood.awm;
    }

    public int hashCode() {
        return this.awl.hashCode();
    }

    public String toString() {
        return kl.j(this).a("place", this.awl).a("likelihood", Float.valueOf(this.awm)).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        e.a(this, parcel, flags);
    }
}
