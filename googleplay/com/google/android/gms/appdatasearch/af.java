package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class af implements Creator<Section> {
    static void a(Section section, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, section.name, false);
        b.c(parcel, 1000, section.mVersionCode);
        b.a(parcel, 2, section.snippeted);
        b.c(parcel, 3, section.snippetLength);
        b.J(parcel, bU);
    }

    public Section O(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        String str = null;
        boolean z = false;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                case 1000:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Section(i2, str, z, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Section[] am(int i) {
        return new Section[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return O(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return am(x0);
    }
}
