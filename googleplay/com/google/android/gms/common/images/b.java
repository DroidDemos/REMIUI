package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<WebImage> {
    static void a(WebImage webImage, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, webImage.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, webImage.getUrl(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, webImage.getWidth());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, webImage.getHeight());
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public WebImage bP(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        Uri uri = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            Uri uri2;
            int g;
            int bS = a.bS(parcel);
            int i4;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i4 = i;
                    i = i2;
                    uri2 = uri;
                    g = a.g(parcel, bS);
                    bS = i4;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i3;
                    i4 = i2;
                    uri2 = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    bS = i;
                    i = i4;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    uri2 = uri;
                    g = i3;
                    i4 = i;
                    i = a.g(parcel, bS);
                    bS = i4;
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bS = a.g(parcel, bS);
                    i = i2;
                    uri2 = uri;
                    g = i3;
                    break;
                default:
                    a.b(parcel, bS);
                    bS = i;
                    i = i2;
                    uri2 = uri;
                    g = i3;
                    break;
            }
            i3 = g;
            uri = uri2;
            i2 = i;
            i = bS;
        }
        if (parcel.dataPosition() == bT) {
            return new WebImage(i3, uri, i2, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bP(x0);
    }

    public WebImage[] dd(int i) {
        return new WebImage[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dd(x0);
    }
}
