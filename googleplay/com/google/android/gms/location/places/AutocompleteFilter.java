package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutocompleteFilter implements SafeParcelable {
    public static final a CREATOR;
    final boolean avU;
    final List<PlaceType> avV;
    private final Set<PlaceType> avW;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    AutocompleteFilter(int versionCode, boolean restrictToPlaces, List<PlaceType> placeTypesList) {
        this.mVersionCode = versionCode;
        this.avU = restrictToPlaces;
        this.avV = placeTypesList == null ? Collections.emptyList() : Collections.unmodifiableList(placeTypesList);
        if (this.avV.isEmpty()) {
            this.avW = Collections.emptySet();
        } else {
            this.avW = Collections.unmodifiableSet(new HashSet(this.avV));
        }
    }

    public int describeContents() {
        a aVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AutocompleteFilter)) {
            return false;
        }
        AutocompleteFilter autocompleteFilter = (AutocompleteFilter) object;
        return this.avW.equals(autocompleteFilter.avW) && this.avU == autocompleteFilter.avU;
    }

    public boolean getRestrictToPlaces() {
        return this.avU;
    }

    public int hashCode() {
        return kl.hashCode(this.avW, Boolean.valueOf(this.avU));
    }

    public void writeToParcel(Parcel parcel, int flags) {
        a aVar = CREATOR;
        a.a(this, parcel, flags);
    }
}
