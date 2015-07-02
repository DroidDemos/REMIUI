package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.car.CarMediaBrowserRootNode.CarMediaSource;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class k implements Creator<CarMediaBrowserRootNode> {
    static void a(CarMediaBrowserRootNode carMediaBrowserRootNode, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, carMediaBrowserRootNode.path, false);
        b.c(parcel, 1000, carMediaBrowserRootNode.getVersionCode());
        b.a(parcel, 2, carMediaBrowserRootNode.mediaSources, i, false);
        b.J(parcel, bU);
    }

    public CarMediaBrowserRootNode bq(Parcel parcel) {
        CarMediaSource[] carMediaSourceArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    carMediaSourceArr = (CarMediaSource[]) a.b(parcel, bS, CarMediaSource.CREATOR);
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
            return new CarMediaBrowserRootNode(i, str, carMediaSourceArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CarMediaBrowserRootNode[] ce(int i) {
        return new CarMediaBrowserRootNode[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bq(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ce(x0);
    }
}
