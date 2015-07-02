package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class m implements Creator<GlobalSearchApplication> {
    static void a(GlobalSearchApplication globalSearchApplication, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, globalSearchApplication.appInfo, i, false);
        b.c(parcel, 1000, globalSearchApplication.mVersionCode);
        b.a(parcel, 2, globalSearchApplication.De, i, false);
        b.a(parcel, 3, globalSearchApplication.enabled);
        b.J(parcel, bU);
    }

    public GlobalSearchApplication[] Q(int i) {
        return new GlobalSearchApplication[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return w(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return Q(x0);
    }

    public GlobalSearchApplication w(Parcel parcel) {
        k[] kVarArr = null;
        boolean z = false;
        int bT = a.bT(parcel);
        GlobalSearchApplicationInfo globalSearchApplicationInfo = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int i2;
            GlobalSearchApplicationInfo globalSearchApplicationInfo2;
            boolean z2;
            k[] kVarArr2;
            int bS = a.bS(parcel);
            boolean z3;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    k[] kVarArr3 = kVarArr;
                    globalSearchApplicationInfo2 = (GlobalSearchApplicationInfo) a.a(parcel, bS, GlobalSearchApplicationInfo.CREATOR);
                    z2 = z;
                    kVarArr2 = kVarArr3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    globalSearchApplicationInfo2 = globalSearchApplicationInfo;
                    i2 = i;
                    z3 = z;
                    kVarArr2 = (k[]) a.b(parcel, bS, k.CREATOR);
                    z2 = z3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z2 = a.c(parcel, bS);
                    kVarArr2 = kVarArr;
                    globalSearchApplicationInfo2 = globalSearchApplicationInfo;
                    i2 = i;
                    break;
                case 1000:
                    z3 = z;
                    kVarArr2 = kVarArr;
                    globalSearchApplicationInfo2 = globalSearchApplicationInfo;
                    i2 = a.g(parcel, bS);
                    z2 = z3;
                    break;
                default:
                    a.b(parcel, bS);
                    z2 = z;
                    kVarArr2 = kVarArr;
                    globalSearchApplicationInfo2 = globalSearchApplicationInfo;
                    i2 = i;
                    break;
            }
            i = i2;
            globalSearchApplicationInfo = globalSearchApplicationInfo2;
            kVarArr = kVarArr2;
            z = z2;
        }
        if (parcel.dataPosition() == bT) {
            return new GlobalSearchApplication(i, globalSearchApplicationInfo, kVarArr, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }
}
