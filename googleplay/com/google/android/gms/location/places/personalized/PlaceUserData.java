package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.location.places.personalized.internal.TestDataImpl;
import java.util.List;

public class PlaceUserData implements SafeParcelable {
    public static final b CREATOR;
    private final String Fl;
    private final String avY;
    private final List<TestDataImpl> axy;
    private final List<PlaceAlias> axz;
    final int mVersionCode;

    static {
        CREATOR = new b();
    }

    PlaceUserData(int versionCode, String accountName, String placeId, List<TestDataImpl> testDataImpls, List<PlaceAlias> placeAliases) {
        this.mVersionCode = versionCode;
        this.Fl = accountName;
        this.avY = placeId;
        this.axy = testDataImpls;
        this.axz = placeAliases;
    }

    public int describeContents() {
        b bVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PlaceUserData)) {
            return false;
        }
        PlaceUserData placeUserData = (PlaceUserData) object;
        return this.Fl.equals(placeUserData.Fl) && this.avY.equals(placeUserData.avY) && this.axy.equals(placeUserData.axy) && this.axz.equals(placeUserData.axz);
    }

    public List<PlaceAlias> getPlaceAliases() {
        return this.axz;
    }

    public String getPlaceId() {
        return this.avY;
    }

    public List<TestDataImpl> getTestDataImpls() {
        return this.axy;
    }

    public String getUserAccountName() {
        return this.Fl;
    }

    public int hashCode() {
        return kl.hashCode(this.Fl, this.avY, this.axy, this.axz);
    }

    public String toString() {
        return kl.j(this).a("accountName", this.Fl).a("placeId", this.avY).a("testDataImpls", this.axy).a("placeAliases", this.axz).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        b bVar = CREATOR;
        b.a(this, parcel, flags);
    }
}
