package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<DriveId> {
    static void a(DriveId driveId, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, driveId.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, driveId.Yh, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, driveId.Yi);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, driveId.Yj);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public DriveId ch(Parcel parcel) {
        long j = 0;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        long j2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j2 = a.i(parcel, bS);
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
            return new DriveId(i, str, j2, j);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ch(x0);
    }

    public DriveId[] dG(int i) {
        return new DriveId[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dG(x0);
    }
}
