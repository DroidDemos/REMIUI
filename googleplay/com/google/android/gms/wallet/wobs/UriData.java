package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class UriData implements SafeParcelable {
    public static final Creator<UriData> CREATOR;
    String aRz;
    String description;
    private final int mVersionCode;

    static {
        CREATOR = new h();
    }

    UriData() {
        this.mVersionCode = 1;
    }

    UriData(int versionCode, String uri, String description) {
        this.mVersionCode = versionCode;
        this.aRz = uri;
        this.description = description;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        h.a(this, dest, flags);
    }
}
