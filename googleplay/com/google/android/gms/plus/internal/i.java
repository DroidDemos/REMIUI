package com.google.android.gms.plus.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.Arrays;

public class i implements SafeParcelable {
    public static final k CREATOR;
    private final String Fl;
    private final String[] aHX;
    private final String[] aHY;
    private final String[] aHZ;
    private final String aIa;
    private final String aIb;
    private final String aIc;
    private final String aId;
    private final PlusCommonExtras aIe;
    private final int mVersionCode;

    static {
        CREATOR = new k();
    }

    i(int i, String str, String[] strArr, String[] strArr2, String[] strArr3, String str2, String str3, String str4, String str5, PlusCommonExtras plusCommonExtras) {
        this.mVersionCode = i;
        this.Fl = str;
        this.aHX = strArr;
        this.aHY = strArr2;
        this.aHZ = strArr3;
        this.aIa = str2;
        this.aIb = str3;
        this.aIc = str4;
        this.aId = str5;
        this.aIe = plusCommonExtras;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return this.mVersionCode == iVar.mVersionCode && kl.equal(this.Fl, iVar.Fl) && Arrays.equals(this.aHX, iVar.aHX) && Arrays.equals(this.aHY, iVar.aHY) && Arrays.equals(this.aHZ, iVar.aHZ) && kl.equal(this.aIa, iVar.aIa) && kl.equal(this.aIb, iVar.aIb) && kl.equal(this.aIc, iVar.aIc) && kl.equal(this.aId, iVar.aId) && kl.equal(this.aIe, iVar.aIe);
    }

    public String getAccountName() {
        return this.Fl;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.mVersionCode), this.Fl, this.aHX, this.aHY, this.aHZ, this.aIa, this.aIb, this.aIc, this.aId, this.aIe);
    }

    public String[] rA() {
        return this.aHY;
    }

    public String[] rB() {
        return this.aHZ;
    }

    public String rC() {
        return this.aIa;
    }

    public String rD() {
        return this.aIb;
    }

    public String rE() {
        return this.aIc;
    }

    public String rF() {
        return this.aId;
    }

    public PlusCommonExtras rG() {
        return this.aIe;
    }

    public String[] rz() {
        return this.aHX;
    }

    public String toString() {
        return kl.j(this).a("versionCode", Integer.valueOf(this.mVersionCode)).a("accountName", this.Fl).a("requestedScopes", this.aHX).a("visibleActivities", this.aHY).a("requiredFeatures", this.aHZ).a("packageNameForAuth", this.aIa).a("callingPackageName", this.aIb).a("applicationName", this.aIc).a("extra", this.aIe.toString()).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        k.a(this, out, flags);
    }
}
