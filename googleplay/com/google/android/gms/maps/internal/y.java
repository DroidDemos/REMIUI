package com.google.android.gms.maps.internal;

import android.graphics.Point;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class y implements SafeParcelable {
    public static final z CREATOR;
    private final Point azP;
    private final int versionCode;

    static {
        CREATOR = new z();
    }

    public y(int i, Point point) {
        this.versionCode = i;
        this.azP = point;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof y)) {
            return false;
        }
        return this.azP.equals(((y) o).azP);
    }

    int getVersionCode() {
        return this.versionCode;
    }

    public int hashCode() {
        return this.azP.hashCode();
    }

    public Point qo() {
        return this.azP;
    }

    public String toString() {
        return this.azP.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        z.a(this, out, flags);
    }
}
