package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class PlaceReport implements SafeParcelable {
    public static final f CREATOR;
    private final String avY;
    private final String awo;
    private final String mTag;
    final int mVersionCode;

    static {
        CREATOR = new f();
    }

    PlaceReport(int versionCode, String placeId, String tag, String source) {
        this.mVersionCode = versionCode;
        this.avY = placeId;
        this.mTag = tag;
        this.awo = source;
    }

    public int describeContents() {
        f fVar = CREATOR;
        return 0;
    }

    public boolean equals(Object that) {
        if (!(that instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) that;
        return kl.equal(this.avY, placeReport.avY) && kl.equal(this.mTag, placeReport.mTag) && kl.equal(this.awo, placeReport.awo);
    }

    public String getPlaceId() {
        return this.avY;
    }

    public String getSource() {
        return this.awo;
    }

    public String getTag() {
        return this.mTag;
    }

    public int hashCode() {
        return kl.hashCode(this.avY, this.mTag, this.awo);
    }

    public String toString() {
        return kl.j(this).a("mPlaceId", this.avY).a("mTag", this.mTag).a("mSource", this.awo).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        f fVar = CREATOR;
        f.a(this, out, flags);
    }
}
