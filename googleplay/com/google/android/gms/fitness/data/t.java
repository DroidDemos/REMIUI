package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class t implements Creator<Value> {
    static void a(Value value, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, value.getFormat());
        b.c(parcel, 1000, value.getVersionCode());
        b.a(parcel, 2, value.isSet());
        b.a(parcel, 3, value.lE());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dX(x0);
    }

    public Value dX(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        float f = 0.0f;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    f = a.l(parcel, bS);
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
            return new Value(i2, i, z, f);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Value[] fL(int i) {
        return new Value[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fL(x0);
    }
}
