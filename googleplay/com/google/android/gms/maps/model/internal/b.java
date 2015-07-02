package com.google.android.gms.maps.model.internal;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<a> {
    static void a(a aVar, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, aVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, aVar.qv());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, aVar.qw(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, aVar.getBitmap(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gB(x0);
    }

    public a gB(Parcel parcel) {
        Bitmap bitmap = null;
        byte b = (byte) 0;
        int bT = a.bT(parcel);
        Bundle bundle = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    b = a.e(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bitmap = (Bitmap) a.a(parcel, bS, Bitmap.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new a(i, b, bundle, bitmap);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public a[] ja(int i) {
        return new a[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ja(x0);
    }
}
