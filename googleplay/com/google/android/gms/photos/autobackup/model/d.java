package com.google.android.gms.photos.autobackup.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<MigrationStatus> {
    static void a(MigrationStatus migrationStatus, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, migrationStatus.mVersionCode);
        b.a(parcel, 2, migrationStatus.isMigrated());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gP(x0);
    }

    public MigrationStatus gP(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new MigrationStatus(i, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public MigrationStatus[] jy(int i) {
        return new MigrationStatus[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jy(x0);
    }
}
