package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class jz implements SafeParcelable {
    public static final Creator<jz> CREATOR;
    final int VH;
    int VI;
    String VJ;
    IBinder VK;
    Scope[] VL;
    Bundle VM;
    final int version;

    static {
        CREATOR = new ka();
    }

    jz(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle) {
        this.version = i;
        this.VH = i2;
        this.VI = i3;
        this.VJ = str;
        this.VK = iBinder;
        this.VL = scopeArr;
        this.VM = bundle;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ka.a(this, dest, flags);
    }
}
