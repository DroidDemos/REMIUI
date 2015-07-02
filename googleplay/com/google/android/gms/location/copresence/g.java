package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.oa;
import com.google.android.gms.internal.oc;
import com.google.android.wallet.instrumentmanager.R;

public class g implements Creator<f> {
    static void a(f fVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, fVar.getVersionCode());
        b.a(parcel, 2, fVar.isEnabled());
        b.a(parcel, 3, fVar.pc());
        b.a(parcel, 7, fVar.pd(), i, false);
        b.a(parcel, 8, fVar.pe(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fp(x0);
    }

    public f fp(Parcel parcel) {
        oa[] oaVarArr = null;
        boolean z = false;
        int bT = a.bT(parcel);
        oc[] ocVarArr = null;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    ocVarArr = (oc[]) a.b(parcel, bS, oc.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    oaVarArr = (oa[]) a.b(parcel, bS, oa.CREATOR);
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
            return new f(i, z2, z, ocVarArr, oaVarArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public f[] hG(int i) {
        return new f[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hG(x0);
    }
}
