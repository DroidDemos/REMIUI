package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class n implements Creator<m> {
    static void a(m mVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, mVar.mVersionCode);
        b.a(parcel, 2, mVar.getUri(), i, false);
        b.a(parcel, 4, mVar.vd(), false);
        b.a(parcel, 5, mVar.getData(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iI(x0);
    }

    public m iI(Parcel parcel) {
        byte[] bArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        Bundle bundle = null;
        Uri uri = null;
        while (parcel.dataPosition() < bT) {
            Bundle bundle2;
            Uri uri2;
            int g;
            byte[] bArr2;
            int bS = a.bS(parcel);
            byte[] bArr3;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    bArr3 = bArr;
                    bundle2 = bundle;
                    uri2 = uri;
                    g = a.g(parcel, bS);
                    bArr2 = bArr3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    Bundle bundle3 = bundle;
                    uri2 = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    bArr2 = bArr;
                    bundle2 = bundle3;
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    uri2 = uri;
                    g = i;
                    bArr3 = bArr;
                    bundle2 = a.r(parcel, bS);
                    bArr2 = bArr3;
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    bArr2 = a.s(parcel, bS);
                    bundle2 = bundle;
                    uri2 = uri;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
                    bArr2 = bArr;
                    bundle2 = bundle;
                    uri2 = uri;
                    g = i;
                    break;
            }
            i = g;
            uri = uri2;
            bundle = bundle2;
            bArr = bArr2;
        }
        if (parcel.dataPosition() == bT) {
            return new m(i, uri, bundle, bArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public m[] lI(int i) {
        return new m[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lI(x0);
    }
}
