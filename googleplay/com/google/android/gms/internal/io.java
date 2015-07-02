package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class io implements Creator<in> {
    static void a(in inVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, inVar.getVersionCode());
        b.a(parcel, 2, inVar.hv());
        b.a(parcel, 3, inVar.hF());
        b.c(parcel, 4, inVar.hw());
        b.a(parcel, 5, inVar.getApplicationMetadata(), i, false);
        b.c(parcel, 6, inVar.hx());
        b.J(parcel, bU);
    }

    public in bK(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        double d = 0.0d;
        ApplicationMetadata applicationMetadata = null;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    d = a.m(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    applicationMetadata = (ApplicationMetadata) a.a(parcel, bS, ApplicationMetadata.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new in(i3, d, z, i2, applicationMetadata, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public in[] cK(int i) {
        return new in[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bK(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cK(x0);
    }
}
