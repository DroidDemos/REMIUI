package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

public class UserAddedPlace implements SafeParcelable {
    public static final Creator<UserAddedPlace> CREATOR;
    private final String agh;
    private final LatLng awP;
    private final List<PlaceType> awQ;
    private final String awR;
    private final String mName;
    private final String mPhoneNumber;
    final int mVersionCode;

    static {
        CREATOR = new k();
    }

    UserAddedPlace(int versionCode, String name, LatLng latLng, String address, List<PlaceType> types, String phoneNumber, String websiteUri) {
        this.mVersionCode = versionCode;
        this.mName = name;
        this.awP = latLng;
        this.agh = address;
        this.awQ = new ArrayList(types);
        this.mPhoneNumber = phoneNumber;
        this.awR = websiteUri;
    }

    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.agh;
    }

    public LatLng getLatLng() {
        return this.awP;
    }

    public String getName() {
        return this.mName;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public List<PlaceType> getTypes() {
        return this.awQ;
    }

    public String getWebsiteUri() {
        return this.awR;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        k.a(this, parcel, flags);
    }
}
