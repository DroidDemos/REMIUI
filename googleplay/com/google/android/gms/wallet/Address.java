package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@Deprecated
public final class Address implements SafeParcelable {
    public static final Creator<Address> CREATOR;
    String aTd;
    String aTe;
    String asT;
    String asU;
    String asV;
    String ata;
    boolean atc;
    String atd;
    private final int mVersionCode;
    String name;
    String phoneNumber;
    String vk;

    static {
        CREATOR = new a();
    }

    Address() {
        this.mVersionCode = 1;
    }

    Address(int versionCode, String name, String address1, String address2, String address3, String countryCode, String city, String state, String postalCode, String phoneNumber, boolean isPostBox, String companyName) {
        this.mVersionCode = versionCode;
        this.name = name;
        this.asT = address1;
        this.asU = address2;
        this.asV = address3;
        this.vk = countryCode;
        this.aTd = city;
        this.aTe = state;
        this.ata = postalCode;
        this.phoneNumber = phoneNumber;
        this.atc = isPostBox;
        this.atd = companyName;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
