package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class w implements Creator<v> {
    static void a(v vVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, vVar.versionCode);
        b.c(parcel, 2, vVar.statusCode);
        b.d(parcel, 3, vVar.aWQ, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iM(x0);
    }

    public v iM(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        List list = null;
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
                    list = a.c(parcel, bS, al.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new v(i2, i, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public v[] lM(int i) {
        return new v[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lM(x0);
    }
}
