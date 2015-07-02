package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class b implements Creator<AutocompletePrediction> {
    static void a(AutocompletePrediction autocompletePrediction, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, autocompletePrediction.mDescription, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, autocompletePrediction.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, autocompletePrediction.avY, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, autocompletePrediction.avZ, false);
        com.google.android.gms.common.internal.safeparcel.b.d(parcel, 4, autocompletePrediction.awa, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fL(x0);
    }

    public AutocompletePrediction fL(Parcel parcel) {
        List list = null;
        int bT = a.bT(parcel);
        int i = 0;
        List list2 = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    list2 = a.E(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    list = a.c(parcel, bS, AutocompletePrediction.a.CREATOR);
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
            return new AutocompletePrediction(i, str2, str, list2, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AutocompletePrediction[] ie(int i) {
        return new AutocompletePrediction[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ie(x0);
    }
}
