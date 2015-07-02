package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<BitmapTeleporter> {
    static void a(BitmapTeleporter bitmapTeleporter, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, bitmapTeleporter.mVersionCode);
        b.a(parcel, 2, bitmapTeleporter.TQ, i, false);
        b.c(parcel, 3, bitmapTeleporter.EB);
        b.J(parcel, bU);
    }

    public BitmapTeleporter bN(Parcel parcel) {
        int i = 0;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        ParcelFileDescriptor parcelFileDescriptor = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            ParcelFileDescriptor parcelFileDescriptor2;
            int g;
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    int i3 = i;
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    bS = i3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i2;
                    ParcelFileDescriptor parcelFileDescriptor3 = (ParcelFileDescriptor) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, ParcelFileDescriptor.CREATOR);
                    bS = i;
                    parcelFileDescriptor2 = parcelFileDescriptor3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bS = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    g = i2;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    bS = i;
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    g = i2;
                    break;
            }
            i2 = g;
            parcelFileDescriptor = parcelFileDescriptor2;
            i = bS;
        }
        if (parcel.dataPosition() == bT) {
            return new BitmapTeleporter(i2, parcelFileDescriptor, i);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public BitmapTeleporter[] cU(int i) {
        return new BitmapTeleporter[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bN(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cU(x0);
    }
}
