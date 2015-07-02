package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class k implements Creator<UserAddedPlace> {
    static void a(UserAddedPlace userAddedPlace, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, userAddedPlace.getName(), false);
        b.c(parcel, 1000, userAddedPlace.mVersionCode);
        b.a(parcel, 2, userAddedPlace.getLatLng(), i, false);
        b.a(parcel, 3, userAddedPlace.getAddress(), false);
        b.d(parcel, 4, userAddedPlace.getTypes(), false);
        b.a(parcel, 5, userAddedPlace.getPhoneNumber(), false);
        b.a(parcel, 6, userAddedPlace.getWebsiteUri(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fT(x0);
    }

    public UserAddedPlace fT(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        List list = null;
        String str3 = null;
        LatLng latLng = null;
        String str4 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    latLng = (LatLng) a.a(parcel, bS, LatLng.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    list = a.c(parcel, bS, PlaceType.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str = a.p(parcel, bS);
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
            return new UserAddedPlace(i, str4, latLng, str3, list, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public UserAddedPlace[] io(int i) {
        return new UserAddedPlace[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return io(x0);
    }
}
