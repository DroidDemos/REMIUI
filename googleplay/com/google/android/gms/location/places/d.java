package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class d implements Creator<PlaceFilter> {
    static void a(PlaceFilter placeFilter, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, placeFilter.avV, false);
        b.c(parcel, 1000, placeFilter.mVersionCode);
        b.a(parcel, 2, placeFilter.getTextQuery(), false);
        b.a(parcel, 3, placeFilter.getRequireOpenNow());
        b.d(parcel, 4, placeFilter.awf, false);
        b.c(parcel, 6, placeFilter.awg, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fN(x0);
    }

    public PlaceFilter fN(Parcel parcel) {
        boolean z = false;
        List list = null;
        int bT = a.bT(parcel);
        List list2 = null;
        String str = null;
        List list3 = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    list3 = a.c(parcel, bS, PlaceType.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    list2 = a.c(parcel, bS, UserDataType.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    list = a.E(parcel, bS);
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
            return new PlaceFilter(i, list3, str, z, list2, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PlaceFilter[] ig(int i) {
        return new PlaceFilter[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ig(x0);
    }
}
