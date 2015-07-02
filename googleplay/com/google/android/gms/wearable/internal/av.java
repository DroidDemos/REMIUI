package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class av implements Creator<StorageInfoResponse> {
    static void a(StorageInfoResponse storageInfoResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, storageInfoResponse.versionCode);
        b.c(parcel, 2, storageInfoResponse.statusCode);
        b.a(parcel, 3, storageInfoResponse.totalSizeBytes);
        b.d(parcel, 4, storageInfoResponse.packageStorageInfo, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iW(x0);
    }

    public StorageInfoResponse iW(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        long j = 0;
        List list = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    list = a.c(parcel, bS, PackageStorageInfo.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new StorageInfoResponse(i2, i, j, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public StorageInfoResponse[] lX(int i) {
        return new StorageInfoResponse[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lX(x0);
    }
}
