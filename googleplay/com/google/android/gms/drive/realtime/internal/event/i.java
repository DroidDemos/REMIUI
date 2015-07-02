package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class i implements Creator<ValuesAddedDetails> {
    static void a(ValuesAddedDetails valuesAddedDetails, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, valuesAddedDetails.mVersionCode);
        b.c(parcel, 2, valuesAddedDetails.mIndex);
        b.c(parcel, 3, valuesAddedDetails.aeL);
        b.c(parcel, 4, valuesAddedDetails.aeM);
        b.a(parcel, 5, valuesAddedDetails.afb, false);
        b.c(parcel, 6, valuesAddedDetails.afc);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dC(x0);
    }

    public ValuesAddedDetails dC(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        String str = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i5 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i4 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ValuesAddedDetails(i5, i4, i3, i2, str, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ValuesAddedDetails[] fo(int i) {
        return new ValuesAddedDetails[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fo(x0);
    }
}
