package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class a implements Creator<AutocompleteFilter> {
    static void a(AutocompleteFilter autocompleteFilter, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, autocompleteFilter.getRestrictToPlaces());
        b.c(parcel, 1000, autocompleteFilter.mVersionCode);
        b.d(parcel, 2, autocompleteFilter.avV, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fK(x0);
    }

    public AutocompleteFilter fK(Parcel parcel) {
        boolean z = false;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        List list = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    z = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS, PlaceType.CREATOR);
                    break;
                case 1000:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AutocompleteFilter(i, z, list);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public AutocompleteFilter[] id(int i) {
        return new AutocompleteFilter[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return id(x0);
    }
}
