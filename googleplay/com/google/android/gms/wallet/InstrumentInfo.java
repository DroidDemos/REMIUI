package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class InstrumentInfo implements SafeParcelable {
    public static final Creator<InstrumentInfo> CREATOR;
    private String aTG;
    private String aTH;
    private final int mVersionCode;

    static {
        CREATOR = new h();
    }

    InstrumentInfo(int versionCode, String instrumentType, String instrumentDetails) {
        this.mVersionCode = versionCode;
        this.aTG = instrumentType;
        this.aTH = instrumentDetails;
    }

    public int describeContents() {
        return 0;
    }

    public String getInstrumentDetails() {
        return this.aTH;
    }

    public String getInstrumentType() {
        return this.aTG;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel out, int flags) {
        h.a(this, out, flags);
    }
}
