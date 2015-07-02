package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.pl;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<PlaceLikelihood> {
    static void a(PlaceLikelihood placeLikelihood, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, placeLikelihood.awl, i, false);
        b.c(parcel, 1000, placeLikelihood.mVersionCode);
        b.a(parcel, 2, placeLikelihood.awm);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fO(x0);
    }

    public PlaceLikelihood fO(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        pl plVar = null;
        float f = 0.0f;
        while (parcel.dataPosition() < bT) {
            int i2;
            float f2;
            pl plVar2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    pl plVar3 = (pl) a.a(parcel, bS, pl.CREATOR);
                    f2 = f;
                    plVar2 = plVar3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    f2 = a.l(parcel, bS);
                    plVar2 = plVar;
                    i2 = i;
                    break;
                case 1000:
                    float f3 = f;
                    plVar2 = plVar;
                    i2 = a.g(parcel, bS);
                    f2 = f3;
                    break;
                default:
                    a.b(parcel, bS);
                    f2 = f;
                    plVar2 = plVar;
                    i2 = i;
                    break;
            }
            i = i2;
            plVar = plVar2;
            f = f2;
        }
        if (parcel.dataPosition() == bT) {
            return new PlaceLikelihood(i, plVar, f);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PlaceLikelihood[] ii(int i) {
        return new PlaceLikelihood[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ii(x0);
    }
}
