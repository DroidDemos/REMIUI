package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class tc implements Creator<ta> {
    static void a(ta taVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, taVar.versionCode);
        b.a(parcel, 2, taVar.aHb);
        b.a(parcel, 3, taVar.tag, false);
        b.a(parcel, 4, taVar.aHc, false);
        b.a(parcel, 5, taVar.aHd, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gS(x0);
    }

    public ta gS(Parcel parcel) {
        Bundle bundle = null;
        int bT = a.bT(parcel);
        int i = 0;
        long j = 0;
        byte[] bArr = null;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bArr = a.s(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    bundle = a.r(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ta(i, j, str, bArr, bundle);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ta[] jC(int i) {
        return new ta[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jC(x0);
    }
}
