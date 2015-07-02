package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class gy implements Creator<gx> {
    static void a(gx gxVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, gxVar.versionCode);
        b.a(parcel, 2, gxVar.wR, false);
        b.c(parcel, 3, gxVar.wS);
        b.c(parcel, 4, gxVar.wT);
        b.a(parcel, 5, gxVar.wU);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return j(x0);
    }

    public gx j(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        String str = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new gx(i3, str, i2, i, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return w(x0);
    }

    public gx[] w(int i) {
        return new gx[i];
    }
}
