package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class qb implements SafeParcelable {
    public static final Creator<qb> CREATOR;
    public static final int[] ayo;
    private final String Fl;
    private final boolean aym;
    private final boolean ayn;
    private final int mVersionCode;

    static {
        CREATOR = new qc();
        ayo = new int[]{1, 2};
    }

    qb(int i, String str, boolean z, boolean z2) {
        this.mVersionCode = i;
        this.Fl = str;
        this.aym = z;
        this.ayn = z2;
    }

    public int describeContents() {
        return 0;
    }

    public String getAccountName() {
        return this.Fl;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean isOptedInForDeviceStateAndContent() {
        return this.ayn;
    }

    public boolean isOptedInForWebAndAppHistory() {
        return this.aym;
    }

    public void writeToParcel(Parcel out, int flags) {
        qc.a(this, out, flags);
    }
}
