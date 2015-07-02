package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class w implements Creator<TileOverlayOptions> {
    static void a(TileOverlayOptions tileOverlayOptions, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, tileOverlayOptions.getVersionCode());
        b.a(parcel, 2, tileOverlayOptions.qu(), false);
        b.a(parcel, 3, tileOverlayOptions.isVisible());
        b.a(parcel, 4, tileOverlayOptions.getZIndex());
        b.a(parcel, 5, tileOverlayOptions.getFadeIn());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gz(x0);
    }

    public TileOverlayOptions gz(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        IBinder iBinder = null;
        float f = 0.0f;
        boolean z2 = true;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    iBinder = a.q(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    f = a.l(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z2 = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new TileOverlayOptions(i, iBinder, z, f, z2);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public TileOverlayOptions[] iY(int i) {
        return new TileOverlayOptions[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iY(x0);
    }
}
