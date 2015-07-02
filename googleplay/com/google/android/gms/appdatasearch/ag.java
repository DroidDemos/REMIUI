package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ag implements Creator<StorageStats> {
    static void a(StorageStats storageStats, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, storageStats.packageStats, i, false);
        b.c(parcel, 1000, storageStats.mVersionCode);
        b.a(parcel, 2, storageStats.otherReclaimableBytes);
        b.a(parcel, 3, storageStats.searchDiskUsageBytes);
        b.a(parcel, 4, storageStats.allServicesDiskUsageBytes);
        b.J(parcel, bU);
    }

    public StorageStats P(Parcel parcel) {
        long j = 0;
        int bT = a.bT(parcel);
        int i = 0;
        RegisteredPackageInfo[] registeredPackageInfoArr = null;
        long j2 = 0;
        long j3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    registeredPackageInfoArr = (RegisteredPackageInfo[]) a.b(parcel, bS, RegisteredPackageInfo.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j3 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j2 = a.i(parcel, bS);
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
            return new StorageStats(i, registeredPackageInfoArr, j3, j2, j);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public StorageStats[] an(int i) {
        return new StorageStats[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return P(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return an(x0);
    }
}
