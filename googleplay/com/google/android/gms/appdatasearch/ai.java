package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ai implements Creator<SuggestionResults> {
    static void a(SuggestionResults suggestionResults, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, suggestionResults.mErrorMessage, false);
        b.c(parcel, 1000, suggestionResults.mVersionCode);
        b.a(parcel, 2, suggestionResults.Ex, false);
        b.a(parcel, 3, suggestionResults.Ey, false);
        b.J(parcel, bU);
    }

    public SuggestionResults R(Parcel parcel) {
        String[] strArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        String[] strArr2 = null;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    strArr2 = a.B(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    strArr = a.B(parcel, bS);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new SuggestionResults(i, str, strArr2, strArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SuggestionResults[] ap(int i) {
        return new SuggestionResults[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return R(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ap(x0);
    }
}
