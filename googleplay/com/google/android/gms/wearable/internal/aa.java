package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class aa implements Creator<z> {
    static void a(z zVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, zVar.versionCode);
        b.c(parcel, 2, zVar.statusCode);
        b.a(parcel, 3, zVar.aWS, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iO(x0);
    }

    public z iO(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        ParcelFileDescriptor parcelFileDescriptor = null;
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
                    parcelFileDescriptor = (ParcelFileDescriptor) a.a(parcel, bS, ParcelFileDescriptor.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new z(i2, i, parcelFileDescriptor);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public z[] lO(int i) {
        return new z[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lO(x0);
    }
}
