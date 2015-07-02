package com.google.android.gms.feedback;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<FileTeleporter> {
    static void a(FileTeleporter fileTeleporter, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, fileTeleporter.mVersionCode);
        b.a(parcel, 2, fileTeleporter.TQ, i, false);
        b.a(parcel, 3, fileTeleporter.afU, false);
        b.a(parcel, 4, fileTeleporter.afV, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dH(x0);
    }

    public FileTeleporter dH(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        ParcelFileDescriptor parcelFileDescriptor = null;
        while (parcel.dataPosition() < bT) {
            ParcelFileDescriptor parcelFileDescriptor2;
            int g;
            String str3;
            int bS = a.bS(parcel);
            String str4;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str4 = str;
                    str = str2;
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    g = a.g(parcel, bS);
                    str3 = str4;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    str4 = str2;
                    parcelFileDescriptor2 = (ParcelFileDescriptor) a.a(parcel, bS, ParcelFileDescriptor.CREATOR);
                    str3 = str;
                    str = str4;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    g = i;
                    str4 = str;
                    str = a.p(parcel, bS);
                    str3 = str4;
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str3 = a.p(parcel, bS);
                    str = str2;
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
                    str3 = str;
                    str = str2;
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    g = i;
                    break;
            }
            i = g;
            parcelFileDescriptor = parcelFileDescriptor2;
            str2 = str;
            str = str3;
        }
        if (parcel.dataPosition() == bT) {
            return new FileTeleporter(i, parcelFileDescriptor, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public FileTeleporter[] fu(int i) {
        return new FileTeleporter[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fu(x0);
    }
}
