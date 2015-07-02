package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class qw implements SafeParcelable {
    public static final qx CREATOR;
    final boolean aCk;
    final boolean aCl;
    final String aCm;
    final boolean aCn;
    final Bundle aCo;
    private final int mVersionCode;

    static {
        CREATOR = new qx();
    }

    qw(int i, boolean z, boolean z2, boolean z3, String str, Bundle bundle) {
        this.mVersionCode = i;
        this.aCk = z;
        this.aCl = z2;
        this.aCm = str;
        this.aCn = z3;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.aCo = bundle;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        return kl.j(this).a("useOfflineDatabase", Boolean.valueOf(this.aCk)).a("useWebData", Boolean.valueOf(this.aCl)).a("endpoint", this.aCm).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        qx.a(this, out, flags);
    }
}
