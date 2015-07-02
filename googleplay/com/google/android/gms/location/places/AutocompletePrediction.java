package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.List;

public class AutocompletePrediction implements SafeParcelable {
    public static final Creator<AutocompletePrediction> CREATOR;
    final String avY;
    final List<String> avZ;
    final List<a> awa;
    final String mDescription;
    final int mVersionCode;

    public static class a implements SafeParcelable {
        public static final Creator<a> CREATOR;
        final int mLength;
        final int mOffset;
        final int mVersionCode;

        static {
            CREATOR = new j();
        }

        public a(int i, int i2, int i3) {
            this.mVersionCode = i;
            this.mOffset = i2;
            this.mLength = i3;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof a)) {
                return false;
            }
            a aVar = (a) object;
            return kl.equal(Integer.valueOf(this.mOffset), Integer.valueOf(aVar.mOffset)) && kl.equal(Integer.valueOf(this.mLength), Integer.valueOf(aVar.mLength));
        }

        public int hashCode() {
            return kl.hashCode(Integer.valueOf(this.mOffset), Integer.valueOf(this.mLength));
        }

        public String toString() {
            return kl.j(this).a("offset", Integer.valueOf(this.mOffset)).a("length", Integer.valueOf(this.mLength)).toString();
        }

        public void writeToParcel(Parcel parcel, int flags) {
            j.a(this, parcel, flags);
        }
    }

    static {
        CREATOR = new b();
    }

    AutocompletePrediction(int versionCode, String description, String placeId, List<String> placeTypes, List<a> substrings) {
        this.mVersionCode = versionCode;
        this.mDescription = description;
        this.avY = placeId;
        this.avZ = placeTypes;
        this.awa = substrings;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AutocompletePrediction)) {
            return false;
        }
        AutocompletePrediction autocompletePrediction = (AutocompletePrediction) object;
        return kl.equal(this.mDescription, autocompletePrediction.mDescription) && kl.equal(this.avY, autocompletePrediction.avY) && kl.equal(this.avZ, autocompletePrediction.avZ) && kl.equal(this.awa, autocompletePrediction.awa);
    }

    public int hashCode() {
        return kl.hashCode(this.mDescription, this.avY, this.avZ, this.awa);
    }

    public String toString() {
        return kl.j(this).a("description", this.mDescription).a("placeId", this.avY).a("placeTypes", this.avZ).a("substrings", this.awa).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        b.a(this, parcel, flags);
    }
}
