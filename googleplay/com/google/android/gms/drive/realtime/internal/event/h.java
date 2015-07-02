package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class h implements Creator<ValueChangedDetails> {
    static void a(ValueChangedDetails valueChangedDetails, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, valueChangedDetails.mVersionCode);
        b.c(parcel, 2, valueChangedDetails.afa);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dB(x0);
    }

    public ValueChangedDetails dB(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ValueChangedDetails(i2, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ValueChangedDetails[] fn(int i) {
        return new ValueChangedDetails[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fn(x0);
    }
}
