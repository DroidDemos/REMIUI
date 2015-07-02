package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.car.CarMediaBrowserListNode.CarMediaSong;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class l implements Creator<CarMediaBrowserSongNode> {
    static void a(CarMediaBrowserSongNode carMediaBrowserSongNode, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, carMediaBrowserSongNode.song, i, false);
        b.c(parcel, 1000, carMediaBrowserSongNode.getVersionCode());
        b.a(parcel, 2, carMediaBrowserSongNode.albumArt, false);
        b.c(parcel, 3, carMediaBrowserSongNode.durationSeconds);
        b.J(parcel, bU);
    }

    public CarMediaBrowserSongNode br(Parcel parcel) {
        byte[] bArr = null;
        int i = 0;
        int bT = a.bT(parcel);
        CarMediaSong carMediaSong = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int i3;
            CarMediaSong carMediaSong2;
            byte[] bArr2;
            int bS = a.bS(parcel);
            int i4;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = i2;
                    byte[] bArr3 = bArr;
                    carMediaSong2 = (CarMediaSong) a.a(parcel, bS, CarMediaSong.CREATOR);
                    bS = i;
                    bArr2 = bArr3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    carMediaSong2 = carMediaSong;
                    i3 = i2;
                    i4 = i;
                    bArr2 = a.s(parcel, bS);
                    bS = i4;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bS = a.g(parcel, bS);
                    bArr2 = bArr;
                    carMediaSong2 = carMediaSong;
                    i3 = i2;
                    break;
                case 1000:
                    i4 = i;
                    bArr2 = bArr;
                    carMediaSong2 = carMediaSong;
                    i3 = a.g(parcel, bS);
                    bS = i4;
                    break;
                default:
                    a.b(parcel, bS);
                    bS = i;
                    bArr2 = bArr;
                    carMediaSong2 = carMediaSong;
                    i3 = i2;
                    break;
            }
            i2 = i3;
            carMediaSong = carMediaSong2;
            bArr = bArr2;
            i = bS;
        }
        if (parcel.dataPosition() == bT) {
            return new CarMediaBrowserSongNode(i2, carMediaSong, bArr, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CarMediaBrowserSongNode[] cf(int i) {
        return new CarMediaBrowserSongNode[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return br(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cf(x0);
    }
}
