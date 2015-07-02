package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class UserAddress implements SafeParcelable {
    public static final Creator<UserAddress> CREATOR;
    String asT;
    String asU;
    String asV;
    String asW;
    String asX;
    String asY;
    String asZ;
    String ata;
    String atb;
    boolean atc;
    String atd;
    String ate;
    private final int mVersionCode;
    String name;
    String phoneNumber;
    String vk;

    static {
        CREATOR = new b();
    }

    UserAddress() {
        this.mVersionCode = 1;
    }

    UserAddress(int versionCode, String name, String address1, String address2, String address3, String address4, String address5, String administrativeArea, String locality, String countryCode, String postalCode, String sortingCode, String phoneNumber, boolean isPostBox, String companyName, String emailAddress) {
        this.mVersionCode = versionCode;
        this.name = name;
        this.asT = address1;
        this.asU = address2;
        this.asV = address3;
        this.asW = address4;
        this.asX = address5;
        this.asY = administrativeArea;
        this.asZ = locality;
        this.vk = countryCode;
        this.ata = postalCode;
        this.atb = sortingCode;
        this.phoneNumber = phoneNumber;
        this.atc = isPostBox;
        this.atd = companyName;
        this.ate = emailAddress;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
