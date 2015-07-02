package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class g implements Creator<TimeInterval> {
    static void a(TimeInterval timeInterval, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, timeInterval.getVersionCode());
        b.a(parcel, 2, timeInterval.aVZ);
        b.a(parcel, 3, timeInterval.aWa);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iA(x0);
    }

    public TimeInterval iA(Parcel parcel) {
        long j = 0;
        int bT = a.bT(parcel);
        int i = 0;
        long j2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new TimeInterval(i, j2, j);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public TimeInterval[] lA(int i) {
        return new TimeInterval[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lA(x0);
    }
}
