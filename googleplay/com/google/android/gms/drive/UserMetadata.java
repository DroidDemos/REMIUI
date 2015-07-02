package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class UserMetadata implements SafeParcelable {
    public static final Creator<UserMetadata> CREATOR;
    final String WN;
    final String YF;
    final String YG;
    final boolean YH;
    final String YI;
    final int mVersionCode;

    static {
        CREATOR = new f();
    }

    UserMetadata(int versionCode, String permissionId, String displayName, String pictureUrl, boolean isAuthenticatedUser, String emailAddress) {
        this.mVersionCode = versionCode;
        this.YF = permissionId;
        this.WN = displayName;
        this.YG = pictureUrl;
        this.YH = isAuthenticatedUser;
        this.YI = emailAddress;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format("Permission ID: '%s', Display Name: '%s', Picture URL: '%s', Authenticated User: %b, Email: '%s'", new Object[]{this.YF, this.WN, this.YG, Boolean.valueOf(this.YH), this.YI});
    }

    public void writeToParcel(Parcel dest, int flags) {
        f.a(this, dest, flags);
    }
}
