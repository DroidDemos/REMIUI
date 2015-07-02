package com.google.android.gms.location.copresence;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.oa;
import com.google.android.gms.internal.oc;
import java.util.Arrays;

public class f implements SafeParcelable, Cloneable {
    public static final g CREATOR;
    private final boolean aua;
    private final boolean aub;
    private oc[] auc;
    private oa[] aud;
    private final int mVersionCode;

    static {
        CREATOR = new g();
    }

    f(int i, boolean z, boolean z2, oc[] ocVarArr, oa[] oaVarArr) {
        this.mVersionCode = i;
        this.aua = z;
        this.aub = z2;
        this.auc = ocVarArr;
        this.aud = oaVarArr;
    }

    public Object clone() {
        oa[] oaVarArr = null;
        int i = this.mVersionCode;
        boolean z = this.aua;
        boolean z2 = this.aub;
        oc[] ocVarArr = this.auc == null ? null : (oc[]) this.auc.clone();
        if (this.aud != null) {
            oaVarArr = (oa[]) this.aud.clone();
        }
        return new f(i, z, z2, ocVarArr, oaVarArr);
    }

    public int describeContents() {
        g gVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        f fVar = (f) obj;
        return this.mVersionCode == fVar.mVersionCode && this.aua == fVar.aua && this.aub == fVar.aub && Arrays.equals(this.auc, fVar.auc) && Arrays.equals(this.aud, fVar.aud);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.mVersionCode), Boolean.valueOf(this.aub), Boolean.valueOf(this.aua), Integer.valueOf(Arrays.hashCode(this.auc)), Integer.valueOf(Arrays.hashCode(this.aud)));
    }

    public boolean isEnabled() {
        return this.aua;
    }

    public boolean pc() {
        return this.aub;
    }

    public oc[] pd() {
        return this.auc == null ? null : (oc[]) this.auc.clone();
    }

    public oa[] pe() {
        return this.aud == null ? null : (oa[]) this.aud.clone();
    }

    public void writeToParcel(Parcel dest, int flags) {
        g gVar = CREATOR;
        g.a(this, dest, flags);
    }
}
