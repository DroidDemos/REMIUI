package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.view.Surface;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class w implements Creator<DrawingSpec> {
    static void a(DrawingSpec drawingSpec, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, drawingSpec.width);
        b.c(parcel, 1000, drawingSpec.getVersionCode());
        b.c(parcel, 2, drawingSpec.height);
        b.c(parcel, 3, drawingSpec.dpi);
        b.a(parcel, 4, drawingSpec.surface, i, false);
        b.J(parcel, bU);
    }

    public DrawingSpec bA(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        Surface surface = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    surface = (Surface) a.a(parcel, bS, Surface.CREATOR);
                    break;
                case 1000:
                    i4 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new DrawingSpec(i4, i3, i2, i, surface);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public DrawingSpec[] cr(int i) {
        return new DrawingSpec[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bA(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cr(x0);
    }
}
