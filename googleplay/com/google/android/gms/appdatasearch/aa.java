package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class aa implements Creator<RegisteredPackageInfo> {
    static void a(RegisteredPackageInfo registeredPackageInfo, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, registeredPackageInfo.packageName, false);
        b.c(parcel, 1000, registeredPackageInfo.mVersionCode);
        b.a(parcel, 2, registeredPackageInfo.usedDiskBytes);
        b.a(parcel, 3, registeredPackageInfo.blocked);
        b.a(parcel, 4, registeredPackageInfo.reclaimableDiskBytes);
        b.J(parcel, bU);
    }

    public RegisteredPackageInfo K(Parcel parcel) {
        long j = 0;
        boolean z = false;
        int bT = a.bT(parcel);
        String str = null;
        long j2 = 0;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    j = a.i(parcel, bS);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new RegisteredPackageInfo(i, str, j2, z, j);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public RegisteredPackageInfo[] ae(int i) {
        return new RegisteredPackageInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return K(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ae(x0);
    }
}
