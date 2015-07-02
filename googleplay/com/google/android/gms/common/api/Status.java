package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public final class Status implements Result, SafeParcelable {
    public static final StatusCreator CREATOR;
    public static final Status Th;
    public static final Status Ti;
    public static final Status Tj;
    public static final Status Tk;
    public static final Status Tl;
    private final int Rk;
    private final String Tm;
    private final PendingIntent mPendingIntent;
    private final int mVersionCode;

    static {
        Th = new Status(0);
        Ti = new Status(14);
        Tj = new Status(8);
        Tk = new Status(15);
        Tl = new Status(16);
        CREATOR = new StatusCreator();
    }

    public Status(int statusCode) {
        this(statusCode, null);
    }

    Status(int versionCode, int statusCode, String statusMessage, PendingIntent pendingIntent) {
        this.mVersionCode = versionCode;
        this.Rk = statusCode;
        this.Tm = statusMessage;
        this.mPendingIntent = pendingIntent;
    }

    public Status(int statusCode, String statusMessage) {
        this(1, statusCode, statusMessage, null);
    }

    public Status(int statusCode, String statusMessage, PendingIntent pendingIntent) {
        this(1, statusCode, statusMessage, pendingIntent);
    }

    private String hP() {
        return this.Tm != null ? this.Tm : CommonStatusCodes.getStatusCodeString(this.Rk);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.mVersionCode == status.mVersionCode && this.Rk == status.Rk && kl.equal(this.Tm, status.Tm) && kl.equal(this.mPendingIntent, status.mPendingIntent);
    }

    PendingIntent getPendingIntent() {
        return this.mPendingIntent;
    }

    public Status getStatus() {
        return this;
    }

    public int getStatusCode() {
        return this.Rk;
    }

    public String getStatusMessage() {
        return this.Tm;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.mVersionCode), Integer.valueOf(this.Rk), this.Tm, this.mPendingIntent);
    }

    @Deprecated
    public ConnectionResult il() {
        return new ConnectionResult(this.Rk, this.mPendingIntent);
    }

    public boolean isSuccess() {
        return this.Rk <= 0;
    }

    public String toString() {
        return kl.j(this).a("statusCode", hP()).a("resolution", this.mPendingIntent).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        StatusCreator.a(this, out, flags);
    }
}
