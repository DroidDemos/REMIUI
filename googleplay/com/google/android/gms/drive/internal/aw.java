package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.StorageStats;
import com.google.android.wallet.instrumentmanager.R;

public class aw implements Creator<OnStorageStatsResponse> {
    static void a(OnStorageStatsResponse onStorageStatsResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, onStorageStatsResponse.mVersionCode);
        b.a(parcel, 2, onStorageStatsResponse.aaF, i, false);
        b.J(parcel, bU);
    }

    public OnStorageStatsResponse cQ(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        StorageStats storageStats = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    storageStats = (StorageStats) a.a(parcel, bS, StorageStats.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new OnStorageStatsResponse(i, storageStats);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cQ(x0);
    }

    public OnStorageStatsResponse[] ey(int i) {
        return new OnStorageStatsResponse[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ey(x0);
    }
}
