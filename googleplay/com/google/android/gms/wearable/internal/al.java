package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class al implements SafeParcelable {
    public static final Creator<al> CREATOR;
    private final String CB;
    private final String WN;
    final int mVersionCode;

    static {
        CREATOR = new am();
    }

    al(int i, String str, String str2) {
        this.mVersionCode = i;
        this.CB = str;
        this.WN = str2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (!(o instanceof al)) {
            return false;
        }
        al alVar = (al) o;
        return alVar.CB.equals(this.CB) && alVar.WN.equals(this.WN);
    }

    public String getDisplayName() {
        return this.WN;
    }

    public String getId() {
        return this.CB;
    }

    public int hashCode() {
        return ((this.CB.hashCode() + 629) * 37) + this.WN.hashCode();
    }

    public String toString() {
        return "NodeParcelable{" + this.CB + "," + this.WN + "}";
    }

    public void writeToParcel(Parcel dest, int flags) {
        am.a(this, dest, flags);
    }
}
