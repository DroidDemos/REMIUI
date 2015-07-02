package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class OnResourceIdSetResponse implements SafeParcelable {
    public static final Creator<OnResourceIdSetResponse> CREATOR;
    private final List<String> Zb;
    private final int mVersionCode;

    static {
        CREATOR = new av();
    }

    OnResourceIdSetResponse(int versionCode, List<String> resourceIds) {
        this.mVersionCode = versionCode;
        this.Zb = resourceIds;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public List<String> jQ() {
        return this.Zb;
    }

    public void writeToParcel(Parcel dest, int flags) {
        av.a(this, dest, flags);
    }
}
