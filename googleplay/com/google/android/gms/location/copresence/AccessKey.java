package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccessKey implements SafeParcelable {
    public static final Creator<AccessKey> CREATOR;
    private final String atH;
    private final int mVersionCode;

    static {
        CREATOR = new a();
    }

    AccessKey(int versionCode, String passphrase) {
        this.mVersionCode = versionCode;
        this.atH = passphrase;
    }

    public int describeContents() {
        return 0;
    }

    public String getPassphrase() {
        return this.atH;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        return this.atH == null ? "N/A" : this.atH;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
