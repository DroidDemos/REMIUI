package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<StorageStats> {
    static void a(StorageStats storageStats, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, storageStats.mVersionCode);
        b.a(parcel, 2, storageStats.YA);
        b.a(parcel, 3, storageStats.YB);
        b.a(parcel, 4, storageStats.YC);
        b.a(parcel, 5, storageStats.YD);
        b.c(parcel, 6, storageStats.YE);
        b.J(parcel, bU);
    }

    public StorageStats ck(Parcel parcel) {
        int i = 0;
        long j = 0;
        int bT = a.bT(parcel);
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j4 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j3 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new StorageStats(i2, j4, j3, j2, j, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ck(x0);
    }

    public StorageStats[] dL(int i) {
        return new StorageStats[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dL(x0);
    }
}
