package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class qz implements Creator<qy> {
    static void a(qy qyVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, qyVar.aCk);
        b.c(parcel, 1000, qyVar.getVersionCode());
        b.a(parcel, 2, qyVar.aCl);
        b.a(parcel, 3, qyVar.aCm, false);
        b.a(parcel, 4, qyVar.aCn);
        b.a(parcel, 5, qyVar.aCo, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gI(x0);
    }

    public qy gI(Parcel parcel) {
        Bundle bundle = null;
        boolean z = false;
        int bT = a.bT(parcel);
        String str = null;
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    z3 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    bundle = a.r(parcel, bS);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new qy(i, z3, z2, z, str, bundle);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public qy[] jj(int i) {
        return new qy[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jj(x0);
    }
}
