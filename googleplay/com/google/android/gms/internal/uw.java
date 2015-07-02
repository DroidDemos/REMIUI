package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.ut.a;
import com.google.android.gms.internal.ut.c;
import com.google.android.wallet.instrumentmanager.R;

public class uw implements Creator<c> {
    static void a(c cVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, cVar.mVersionCode);
        b.a(parcel, 1, cVar.status, i, false);
        b.a(parcel, 2, cVar.aLE, i, false);
        b.a(parcel, 3, cVar.aLF);
        b.a(parcel, 4, cVar.aLG);
        b.a(parcel, 5, cVar.aLH);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hs(x0);
    }

    public c hs(Parcel parcel) {
        a[] aVarArr = null;
        long j = 0;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        long j2 = 0;
        long j3 = 0;
        Status status = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    status = (Status) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, Status.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    aVarArr = (a[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS, a.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j3 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    j2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    j = com.google.android.gms.common.internal.safeparcel.a.i(parcel, bS);
                    break;
                case 1000:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new c(i, status, aVarArr, j3, j2, j);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public c[] kd(int i) {
        return new c[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kd(x0);
    }
}
