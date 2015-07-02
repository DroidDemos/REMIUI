package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CarFacet implements SafeParcelable {
    public static final Creator<CarFacet> CREATOR;
    final int mVersionCode;
    public String packageName;
    public int type;

    static {
        CREATOR = new f();
    }

    public CarFacet(int versionCode, int type, String packageName) {
        this.mVersionCode = versionCode;
        this.type = type;
        this.packageName = packageName;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        f.a(this, dest, flags);
    }
}
