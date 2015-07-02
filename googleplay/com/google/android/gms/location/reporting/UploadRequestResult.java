package com.google.android.gms.location.reporting;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public final class UploadRequestResult implements SafeParcelable {
    public static final f CREATOR;
    private final long Qy;
    private final int mVersionCode;
    private final int sX;

    static {
        CREATOR = new f();
    }

    UploadRequestResult(int versionCode, int resultCode, long requestId) {
        this.mVersionCode = versionCode;
        this.sX = resultCode;
        this.Qy = requestId;
    }

    public int describeContents() {
        f fVar = CREATOR;
        return 0;
    }

    public boolean equals(Object o) {
        if (!(o instanceof UploadRequestResult)) {
            return false;
        }
        UploadRequestResult uploadRequestResult = (UploadRequestResult) o;
        return this.Qy == uploadRequestResult.Qy && this.sX == uploadRequestResult.sX;
    }

    public long getRequestId() {
        return this.Qy;
    }

    public int getResultCode() {
        return this.sX;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.sX), Long.valueOf(this.Qy));
    }

    public String toString() {
        return "Result{mVersionCode=" + this.mVersionCode + ", mResultCode=" + this.sX + ", mRequestId=" + this.Qy + '}';
    }

    public void writeToParcel(Parcel out, int flags) {
        f fVar = CREATOR;
        f.a(this, out, flags);
    }
}
