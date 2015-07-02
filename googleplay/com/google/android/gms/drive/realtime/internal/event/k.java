package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class k implements Creator<ValuesSetDetails> {
    static void a(ValuesSetDetails valuesSetDetails, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, valuesSetDetails.mVersionCode);
        b.c(parcel, 2, valuesSetDetails.mIndex);
        b.c(parcel, 3, valuesSetDetails.aeL);
        b.c(parcel, 4, valuesSetDetails.aeM);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dE(x0);
    }

    public ValuesSetDetails dE(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i4 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ValuesSetDetails(i4, i3, i2, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ValuesSetDetails[] fq(int i) {
        return new ValuesSetDetails[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fq(x0);
    }
}
