package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class PlaceFilter implements SafeParcelable {
    public static final d CREATOR;
    final List<PlaceType> avV;
    private final Set<PlaceType> avW;
    private final String awd;
    private final boolean awe;
    final List<UserDataType> awf;
    final List<String> awg;
    private final Set<UserDataType> awh;
    private final Set<String> awi;
    final int mVersionCode;

    static {
        CREATOR = new d();
    }

    PlaceFilter(int versionCode, List<PlaceType> placeTypesList, String textQuery, boolean requireOpenNow, List<UserDataType> requestedUserDataTypesList, List<String> placeIdsList) {
        this.mVersionCode = versionCode;
        this.avV = placeTypesList == null ? Collections.emptyList() : Collections.unmodifiableList(placeTypesList);
        if (textQuery == null) {
            textQuery = "";
        }
        this.awd = textQuery;
        this.awe = requireOpenNow;
        this.awf = requestedUserDataTypesList == null ? Collections.emptyList() : Collections.unmodifiableList(requestedUserDataTypesList);
        this.awg = placeIdsList == null ? Collections.emptyList() : Collections.unmodifiableList(placeIdsList);
        this.avW = j(this.avV);
        this.awh = j(this.awf);
        this.awi = j(this.awg);
    }

    private static <E> Set<E> j(List<E> list) {
        return list.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(new HashSet(list));
    }

    public int describeContents() {
        d dVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PlaceFilter)) {
            return false;
        }
        PlaceFilter placeFilter = (PlaceFilter) object;
        return this.avW.equals(placeFilter.avW) && this.awe == placeFilter.awe && this.awh.equals(placeFilter.awh) && this.awi.equals(placeFilter.awi);
    }

    public boolean getRequireOpenNow() {
        return this.awe;
    }

    @Deprecated
    public String getTextQuery() {
        return this.awd;
    }

    public int hashCode() {
        return kl.hashCode(this.avW, Boolean.valueOf(this.awe), this.awh, this.awi);
    }

    public String toString() {
        return kl.j(this).a("types", this.avW).a("placeIds", this.awi).a("requireOpenNow", Boolean.valueOf(this.awe)).a("requestedUserDataTypes", this.awh).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        d dVar = CREATOR;
        d.a(this, parcel, flags);
    }
}
