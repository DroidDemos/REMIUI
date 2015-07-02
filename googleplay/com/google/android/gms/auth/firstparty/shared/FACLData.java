package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class FACLData implements SafeParcelable {
    public static final f CREATOR;
    FACLConfig IC;
    String IE;
    boolean IF;
    String IG;
    final int version;

    static {
        CREATOR = new f();
    }

    FACLData(int version, FACLConfig faclConfig, String activityText, boolean isSpeedbumpNeeded, String speedbumpText) {
        this.version = version;
        this.IC = faclConfig;
        this.IE = activityText;
        this.IF = isSpeedbumpNeeded;
        this.IG = speedbumpText;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        f.a(this, dest, flags);
    }
}
