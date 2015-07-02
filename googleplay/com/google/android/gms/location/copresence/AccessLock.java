package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccessLock implements SafeParcelable {
    public static final Creator<AccessLock> CREATOR;
    private final String atI;
    private final int mVersionCode;

    static {
        CREATOR = new b();
    }

    AccessLock(int versionCode, String requiredPassphrase) {
        this.mVersionCode = versionCode;
        this.atI = requiredPassphrase;
    }

    public int describeContents() {
        return 0;
    }

    public String getRequiredPassphrase() {
        return this.atI;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
