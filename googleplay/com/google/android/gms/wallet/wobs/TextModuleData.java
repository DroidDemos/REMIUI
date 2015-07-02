package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class TextModuleData implements SafeParcelable {
    public static final Creator<TextModuleData> CREATOR;
    String aVY;
    private final int mVersionCode;
    String tU;

    static {
        CREATOR = new f();
    }

    TextModuleData() {
        this.mVersionCode = 1;
    }

    TextModuleData(int versionCode, String header, String body) {
        this.mVersionCode = versionCode;
        this.aVY = header;
        this.tU = body;
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
