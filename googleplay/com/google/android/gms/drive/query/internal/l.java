package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class l implements Creator<Operator> {
    static void a(Operator operator, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, operator.mVersionCode);
        b.a(parcel, 1, operator.mTag, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dq(x0);
    }

    public Operator dq(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Operator(i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Operator[] eY(int i) {
        return new Operator[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eY(x0);
    }
}
