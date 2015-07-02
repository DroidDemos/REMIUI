package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.car.CarMediaBrowserSourceNode.CarMediaList;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class n implements Creator<CarMediaList> {
    static void a(CarMediaList carMediaList, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, carMediaList.path, false);
        b.c(parcel, 1000, carMediaList.getVersionCode());
        b.a(parcel, 2, carMediaList.name, false);
        b.a(parcel, 3, carMediaList.albumArt, false);
        b.c(parcel, 4, carMediaList.type);
        b.J(parcel, bU);
    }

    public CarMediaList bt(Parcel parcel) {
        int i = 0;
        byte[] bArr = null;
        int bT = a.bT(parcel);
        String str = null;
        String str2 = null;
        int i2 = 0;
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
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i = a.g(parcel, bS);
                    break;
                case 1000:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CarMediaList(i2, str2, str, bArr, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CarMediaList[] ch(int i) {
        return new CarMediaList[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bt(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ch(x0);
    }
}
