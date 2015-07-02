package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<Scope> {
    static void a(Scope scope, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, scope.mVersionCode);
        b.a(parcel, 2, scope.ik(), false);
        b.J(parcel, bU);
    }

    public Scope bM(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Scope(i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Scope[] cO(int i) {
        return new Scope[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bM(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cO(x0);
    }
}
