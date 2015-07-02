package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ah implements Creator<ParcelableIndexReference> {
    static void a(ParcelableIndexReference parcelableIndexReference, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, parcelableIndexReference.mVersionCode);
        b.a(parcel, 2, parcelableIndexReference.ael, false);
        b.c(parcel, 3, parcelableIndexReference.mIndex);
        b.a(parcel, 4, parcelableIndexReference.aem);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return du(x0);
    }

    public ParcelableIndexReference du(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        String str = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ParcelableIndexReference(i2, str, i, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ParcelableIndexReference[] fe(int i) {
        return new ParcelableIndexReference[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fe(x0);
    }
}
