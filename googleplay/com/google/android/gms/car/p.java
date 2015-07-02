package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.car.CarMediaBrowserRootNode.CarMediaSource;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class p implements Creator<CarMediaSource> {
    static void a(CarMediaSource carMediaSource, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, carMediaSource.sourcePath, false);
        b.c(parcel, 1000, carMediaSource.getVersionCode());
        b.a(parcel, 2, carMediaSource.name, false);
        b.a(parcel, 3, carMediaSource.albumArt, false);
        b.J(parcel, bU);
    }

    public CarMediaSource bv(Parcel parcel) {
        byte[] bArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bArr = a.s(parcel, bS);
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
            return new CarMediaSource(i, str2, str, bArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CarMediaSource[] ck(int i) {
        return new CarMediaSource[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bv(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ck(x0);
    }
}
