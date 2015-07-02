package com.google.android.gms.auth.firstparty.proximity.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<Authorization> {
    static void a(Authorization authorization, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, authorization.Gf);
        b.a(parcel, 2, authorization.mPermitId, false);
        b.a(parcel, 3, authorization.mPermitAccessId, false);
        b.a(parcel, 4, authorization.mData, false);
        b.J(parcel, bU);
    }

    public Authorization aV(Parcel parcel) {
        byte[] bArr = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bArr = com.google.android.gms.common.internal.safeparcel.a.s(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Authorization(i, str2, str, bArr);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public Authorization[] bz(int i) {
        return new Authorization[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aV(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bz(x0);
    }
}
