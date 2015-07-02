package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class an implements Creator<PackageStorageInfo> {
    static void a(PackageStorageInfo packageStorageInfo, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, packageStorageInfo.versionCode);
        b.a(parcel, 2, packageStorageInfo.packageName, false);
        b.a(parcel, 3, packageStorageInfo.label, false);
        b.a(parcel, 4, packageStorageInfo.totalSizeBytes);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iS(x0);
    }

    public PackageStorageInfo iS(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        long j = 0;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    j = a.i(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new PackageStorageInfo(i, str2, str, j);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PackageStorageInfo[] lS(int i) {
        return new PackageStorageInfo[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lS(x0);
    }
}
