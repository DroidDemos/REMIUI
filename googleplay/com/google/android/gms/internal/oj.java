package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class oj implements SafeParcelable {
    public static final Creator<oj> CREATOR;
    public final int auR;
    public final ol auS;
    public final or auT;
    public final op auU;
    public final ot auV;
    private final int mVersionCode;

    static {
        CREATOR = new ok();
    }

    oj(int i, int i2, ol olVar, or orVar, op opVar, ot otVar) {
        this.mVersionCode = i;
        this.auR = i2;
        this.auS = olVar;
        this.auT = orVar;
        this.auU = opVar;
        this.auV = otVar;
    }

    public int describeContents() {
        return 0;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ok.a(this, dest, flags);
    }
}
