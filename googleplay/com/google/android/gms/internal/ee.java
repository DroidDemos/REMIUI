package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ee implements Creator<ef> {
    static void a(ef efVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, efVar.versionCode);
        b.a(parcel, 3, efVar.cu(), false);
        b.a(parcel, 4, efVar.cv(), false);
        b.a(parcel, 5, efVar.cw(), false);
        b.a(parcel, 6, efVar.ct(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return g(x0);
    }

    public ef g(Parcel parcel) {
        IBinder iBinder = null;
        int bT = a.bT(parcel);
        int i = 0;
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        IBinder iBinder4 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    iBinder4 = a.q(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    iBinder3 = a.q(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    iBinder2 = a.q(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    iBinder = a.q(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ef(i, iBinder4, iBinder3, iBinder2, iBinder);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return o(x0);
    }

    public ef[] o(int i) {
        return new ef[i];
    }
}
