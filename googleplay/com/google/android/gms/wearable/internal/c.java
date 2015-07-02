package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<b> {
    static void a(b bVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, bVar.mVersionCode);
        b.a(parcel, 2, bVar.ve(), false);
        b.a(parcel, 3, bVar.aWu, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iG(x0);
    }

    public b iG(Parcel parcel) {
        IntentFilter[] intentFilterArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        IBinder iBinder = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    iBinder = a.q(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    intentFilterArr = (IntentFilter[]) a.b(parcel, bS, IntentFilter.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new b(i, iBinder, intentFilterArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public b[] lG(int i) {
        return new b[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lG(x0);
    }
}
