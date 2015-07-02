package com.google.android.gms.maps.internal;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class z implements Creator<y> {
    static void a(y yVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, yVar.getVersionCode());
        b.a(parcel, 2, yVar.qo(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gl(x0);
    }

    public y gl(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        Point point = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    point = (Point) a.a(parcel, bS, Point.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new y(i, point);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public y[] iK(int i) {
        return new y[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iK(x0);
    }
}
