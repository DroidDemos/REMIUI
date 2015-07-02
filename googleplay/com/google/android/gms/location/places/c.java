package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<NearbyAlertRequest> {
    static void a(NearbyAlertRequest nearbyAlertRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, nearbyAlertRequest.getTransitionTypes());
        b.c(parcel, 1000, nearbyAlertRequest.getVersionCode());
        b.c(parcel, 2, nearbyAlertRequest.getLoiteringTimeMillis());
        b.a(parcel, 3, nearbyAlertRequest.getFilter(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fM(x0);
    }

    public NearbyAlertRequest fM(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        int i2 = -1;
        PlaceFilter placeFilter = null;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    placeFilter = (PlaceFilter) a.a(parcel, bS, PlaceFilter.CREATOR);
                    break;
                case 1000:
                    i3 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new NearbyAlertRequest(i3, i, i2, placeFilter);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public NearbyAlertRequest[] if(int i) {
        return new NearbyAlertRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return if(x0);
    }
}
