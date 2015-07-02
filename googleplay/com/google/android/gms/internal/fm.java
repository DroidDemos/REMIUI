package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@fd
public final class fm implements SafeParcelable {
    public static final fn CREATOR;
    public final ApplicationInfo applicationInfo;
    public final String lO;
    public final gx lR;
    public final bd lV;
    public final List<String> mf;
    public final Bundle tK;
    public final ba tL;
    public final PackageInfo tM;
    public final String tN;
    public final String tO;
    public final String tP;
    public final Bundle tQ;
    public final int tR;
    public final Bundle tS;
    public final boolean tT;
    public final int versionCode;

    static {
        CREATOR = new fn();
    }

    fm(int i, Bundle bundle, ba baVar, bd bdVar, String str, ApplicationInfo applicationInfo, PackageInfo packageInfo, String str2, String str3, String str4, gx gxVar, Bundle bundle2, int i2, List<String> list, Bundle bundle3, boolean z) {
        this.versionCode = i;
        this.tK = bundle;
        this.tL = baVar;
        this.lV = bdVar;
        this.lO = str;
        this.applicationInfo = applicationInfo;
        this.tM = packageInfo;
        this.tN = str2;
        this.tO = str3;
        this.tP = str4;
        this.lR = gxVar;
        this.tQ = bundle2;
        this.tR = i2;
        this.mf = list;
        this.tS = bundle3;
        this.tT = z;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        fn.a(this, out, flags);
    }
}
