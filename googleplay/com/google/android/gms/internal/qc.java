package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class qc implements Creator<qb> {
    static void a(qb qbVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, qbVar.getVersionCode());
        b.a(parcel, 2, qbVar.getAccountName(), false);
        b.a(parcel, 3, qbVar.isOptedInForWebAndAppHistory());
        b.a(parcel, 4, qbVar.isOptedInForDeviceStateAndContent());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gh(x0);
    }

    public qb gh(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        String str = null;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z2 = a.c(parcel, bS);
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
            return new qb(i, str, z2, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public qb[] iG(int i) {
        return new qb[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iG(x0);
    }
}
