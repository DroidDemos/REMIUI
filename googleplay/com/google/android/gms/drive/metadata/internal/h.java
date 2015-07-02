package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class h implements Creator<MetadataBundle> {
    static void a(MetadataBundle metadataBundle, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, metadataBundle.mVersionCode);
        b.a(parcel, 2, metadataBundle.abq, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return de(x0);
    }

    public MetadataBundle de(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        Bundle bundle = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bundle = a.r(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new MetadataBundle(i, bundle);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public MetadataBundle[] eM(int i) {
        return new MetadataBundle[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eM(x0);
    }
}
