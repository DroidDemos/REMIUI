package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.location.places.personalized.internal.TestDataImpl;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class b implements Creator<PlaceUserData> {
    static void a(PlaceUserData placeUserData, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, placeUserData.getUserAccountName(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, placeUserData.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, placeUserData.getPlaceId(), false);
        com.google.android.gms.common.internal.safeparcel.b.d(parcel, 5, placeUserData.getTestDataImpls(), false);
        com.google.android.gms.common.internal.safeparcel.b.d(parcel, 6, placeUserData.getPlaceAliases(), false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fZ(x0);
    }

    public PlaceUserData fZ(Parcel parcel) {
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
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    list2 = a.c(parcel, bS, TestDataImpl.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    list = a.c(parcel, bS, PlaceAlias.CREATOR);
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
            return new PlaceUserData(i, str2, str, list2, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PlaceUserData[] iu(int i) {
        return new PlaceUserData[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iu(x0);
    }
}
