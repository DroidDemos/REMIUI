package com.google.android.gms.udc;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ConsentFlowConfig implements SafeParcelable {
    public static final a CREATOR;
    private boolean aQS;
    private boolean aQT;
    private final int mVersionCode;

    static {
        CREATOR = new a();
    }

    private ConsentFlowConfig() {
        this(1, false, true);
    }

    ConsentFlowConfig(int versionCode, boolean isPermissionExpanded, boolean isIconRequired) {
        this.mVersionCode = versionCode;
        this.aQS = isPermissionExpanded;
        this.aQT = isIconRequired;
    }

    public int describeContents() {
        a aVar = CREATOR;
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean isIconRequired() {
        return this.aQT;
    }

    public boolean isPermissionExpanded() {
        return this.aQS;
    }

    public void writeToParcel(Parcel out, int flags) {
        a aVar = CREATOR;
        a.a(this, out, flags);
    }
}
