package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<DrivePreferences> {
    static void a(DrivePreferences drivePreferences, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, drivePreferences.mVersionCode);
        b.a(parcel, 2, drivePreferences.Yl);
        b.J(parcel, bU);
    }

    public DrivePreferences ci(Parcel parcel) {
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
            return new DrivePreferences(i, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ci(x0);
    }

    public DrivePreferences[] dH(int i) {
        return new DrivePreferences[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dH(x0);
    }
}
