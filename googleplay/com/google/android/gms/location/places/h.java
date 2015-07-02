package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class h implements Creator<PlaceType> {
    static void a(PlaceType placeType, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, placeType.vc, false);
        b.c(parcel, 1000, placeType.mVersionCode);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fR(x0);
    }

    public PlaceType fR(Parcel parcel) {
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
            return new PlaceType(i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PlaceType[] im(int i) {
        return new PlaceType[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return im(x0);
    }
}
