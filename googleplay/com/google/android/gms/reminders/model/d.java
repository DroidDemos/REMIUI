package com.google.android.gms.reminders.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class d implements SafeParcelable, Location {
    public static final Creator<d> CREATOR;
    private final Double aKT;
    private final Double aKU;
    private final Integer aKV;
    private final Integer aKW;
    private final String aKX;
    private final String mName;
    public final int mVersionCode;

    static {
        CREATOR = new c();
    }

    d(int i, Double d, Double d2, String str, Integer num, Integer num2, String str2) {
        this.aKT = d;
        this.aKU = d2;
        this.mName = str;
        this.aKV = num;
        this.aKW = num2;
        this.aKX = str2;
        this.mVersionCode = i;
    }

    public d(Location location) {
        this(location.getLat(), location.getLng(), location.getName(), location.getRadiusMeters(), location.getLocationType(), location.getDisplayAddress(), false);
    }

    d(Double d, Double d2, String str, Integer num, Integer num2, String str2, boolean z) {
        this(1, d, d2, str, num, num2, str2);
    }

    public static boolean a(Location location, Location location2) {
        return kl.equal(location.getLat(), location2.getLat()) && kl.equal(location.getLng(), location2.getLng()) && kl.equal(location.getName(), location2.getName()) && kl.equal(location.getRadiusMeters(), location2.getRadiusMeters()) && kl.equal(location.getLocationType(), location2.getLocationType()) && kl.equal(location.getDisplayAddress(), location2.getDisplayAddress());
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            return this == obj ? true : a(this, (Location) obj);
        } else {
            return false;
        }
    }

    public /* synthetic */ Object freeze() {
        return sg();
    }

    public String getDisplayAddress() {
        return this.aKX;
    }

    public Double getLat() {
        return this.aKT;
    }

    public Double getLng() {
        return this.aKU;
    }

    public Integer getLocationType() {
        return this.aKW;
    }

    public String getName() {
        return this.mName;
    }

    public Integer getRadiusMeters() {
        return this.aKV;
    }

    public int hashCode() {
        return kl.hashCode(getLat(), getLng(), getName(), getRadiusMeters(), getLocationType(), getDisplayAddress());
    }

    public Location sg() {
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        c.a(this, out, flags);
    }
}
