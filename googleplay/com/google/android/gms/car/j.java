package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.car.CarMediaBrowserListNode.CarMediaSong;
import com.google.android.gms.car.CarMediaBrowserSourceNode.CarMediaList;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class j implements Creator<CarMediaBrowserListNode> {
    static void a(CarMediaBrowserListNode carMediaBrowserListNode, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, carMediaBrowserListNode.list, i, false);
        b.c(parcel, 1000, carMediaBrowserListNode.getVersionCode());
        b.c(parcel, 2, carMediaBrowserListNode.start);
        b.c(parcel, 3, carMediaBrowserListNode.total);
        b.a(parcel, 4, carMediaBrowserListNode.songs, i, false);
        b.J(parcel, bU);
    }

    public CarMediaBrowserListNode bp(Parcel parcel) {
        CarMediaSong[] carMediaSongArr = null;
        int i = 0;
        int bT = a.bT(parcel);
        int i2 = 0;
        CarMediaList carMediaList = null;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    carMediaList = (CarMediaList) a.a(parcel, bS, CarMediaList.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    carMediaSongArr = (CarMediaSong[]) a.b(parcel, bS, CarMediaSong.CREATOR);
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
            return new CarMediaBrowserListNode(i3, carMediaList, i2, i, carMediaSongArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CarMediaBrowserListNode[] cd(int i) {
        return new CarMediaBrowserListNode[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bp(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cd(x0);
    }
}
