package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.car.CarMediaBrowserRootNode.CarMediaSource;
import com.google.android.gms.car.CarMediaBrowserSourceNode.CarMediaList;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class m implements Creator<CarMediaBrowserSourceNode> {
    static void a(CarMediaBrowserSourceNode carMediaBrowserSourceNode, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, carMediaBrowserSourceNode.mediaSource, i, false);
        b.c(parcel, 1000, carMediaBrowserSourceNode.getVersionCode());
        b.c(parcel, 2, carMediaBrowserSourceNode.start);
        b.c(parcel, 3, carMediaBrowserSourceNode.total);
        b.a(parcel, 4, carMediaBrowserSourceNode.lists, i, false);
        b.J(parcel, bU);
    }

    public CarMediaBrowserSourceNode bs(Parcel parcel) {
        CarMediaList[] carMediaListArr = null;
        int i = 0;
        int bT = a.bT(parcel);
        int i2 = 0;
        CarMediaSource carMediaSource = null;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    carMediaSource = (CarMediaSource) a.a(parcel, bS, CarMediaSource.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    carMediaListArr = (CarMediaList[]) a.b(parcel, bS, CarMediaList.CREATOR);
                    break;
                case 1000:
                    i3 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CarMediaBrowserSourceNode(i3, carMediaSource, i2, i, carMediaListArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CarMediaBrowserSourceNode[] cg(int i) {
        return new CarMediaBrowserSourceNode[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bs(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cg(x0);
    }
}
