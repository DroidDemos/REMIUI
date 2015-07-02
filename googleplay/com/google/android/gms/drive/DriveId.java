package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.internal.ak;
import com.google.android.gms.drive.internal.w;
import com.google.android.gms.internal.kn;
import com.google.android.gms.internal.ye;

public class DriveId implements SafeParcelable {
    public static final Creator<DriveId> CREATOR;
    final String Yh;
    final long Yi;
    final long Yj;
    private volatile String Yk;
    final int mVersionCode;

    static {
        CREATOR = new b();
    }

    DriveId(int versionCode, String resourceId, long sqlId, long databaseInstanceId) {
        boolean z = false;
        this.Yk = null;
        this.mVersionCode = versionCode;
        this.Yh = resourceId;
        kn.L(!"".equals(resourceId));
        if (!(resourceId == null && sqlId == -1)) {
            z = true;
        }
        kn.L(z);
        this.Yi = sqlId;
        this.Yj = databaseInstanceId;
    }

    public int describeContents() {
        return 0;
    }

    public final String encodeToString() {
        if (this.Yk == null) {
            this.Yk = "DriveId:" + Base64.encodeToString(jF(), 10);
        }
        return this.Yk;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DriveId)) {
            return false;
        }
        DriveId driveId = (DriveId) obj;
        if (driveId.Yj != this.Yj) {
            w.w("DriveId", "Attempt to compare invalid DriveId detected. Has local storage been cleared?");
            return false;
        } else if (driveId.Yi == -1 && this.Yi == -1) {
            return driveId.Yh.equals(this.Yh);
        } else {
            return driveId.Yi == this.Yi;
        }
    }

    public int hashCode() {
        return this.Yi == -1 ? this.Yh.hashCode() : (String.valueOf(this.Yj) + String.valueOf(this.Yi)).hashCode();
    }

    final byte[] jF() {
        ye akVar = new ak();
        akVar.versionCode = this.mVersionCode;
        akVar.aaQ = this.Yh == null ? "" : this.Yh;
        akVar.aaR = this.Yi;
        akVar.aaS = this.Yj;
        return ye.toByteArray(akVar);
    }

    public String toString() {
        return encodeToString();
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
