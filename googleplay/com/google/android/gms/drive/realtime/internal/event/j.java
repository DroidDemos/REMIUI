package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class j implements Creator<ValuesRemovedDetails> {
    static void a(ValuesRemovedDetails valuesRemovedDetails, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, valuesRemovedDetails.mVersionCode);
        b.c(parcel, 2, valuesRemovedDetails.mIndex);
        b.c(parcel, 3, valuesRemovedDetails.aeL);
        b.c(parcel, 4, valuesRemovedDetails.aeM);
        b.a(parcel, 5, valuesRemovedDetails.afd, false);
        b.c(parcel, 6, valuesRemovedDetails.afe);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dD(x0);
    }

    public ValuesRemovedDetails dD(Parcel parcel) {
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
            return new ValuesRemovedDetails(i5, i4, i3, i2, str, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ValuesRemovedDetails[] fp(int i) {
        return new ValuesRemovedDetails[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fp(x0);
    }
}
