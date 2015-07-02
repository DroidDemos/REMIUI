package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ok implements Creator<oj> {
    static void a(oj ojVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, ojVar.getVersionCode());
        b.c(parcel, 2, ojVar.auR);
        b.a(parcel, 3, ojVar.auS, i, false);
        b.a(parcel, 4, ojVar.auT, i, false);
        b.a(parcel, 5, ojVar.auU, i, false);
        b.a(parcel, 6, ojVar.auV, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fB(x0);
    }

    public oj fB(Parcel parcel) {
        int i = 0;
        ot otVar = null;
        int bT = a.bT(parcel);
        op opVar = null;
        or orVar = null;
        ol olVar = null;
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
                    olVar = (ol) a.a(parcel, bS, ol.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    orVar = (or) a.a(parcel, bS, or.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    opVar = (op) a.a(parcel, bS, op.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    otVar = (ot) a.a(parcel, bS, ot.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new oj(i2, i, olVar, orVar, opVar, otVar);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public oj[] hS(int i) {
        return new oj[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hS(x0);
    }
}
