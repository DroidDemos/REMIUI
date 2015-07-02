package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class g implements Creator<PlaceRequest> {
    static void a(PlaceRequest placeRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, placeRequest.mVersionCode);
        b.a(parcel, 2, placeRequest.getFilter(), i, false);
        b.a(parcel, 3, placeRequest.getInterval());
        b.c(parcel, 4, placeRequest.getPriority());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fQ(x0);
    }

    public PlaceRequest fQ(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        PlaceFilter placeFilter = null;
        long j = PlaceRequest.DEFAULT_INTERVAL;
        int i2 = 102;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    placeFilter = (PlaceFilter) a.a(parcel, bS, PlaceFilter.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = a.g(parcel, bS);
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
            return new PlaceRequest(i, placeFilter, j, i2);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PlaceRequest[] il(int i) {
        return new PlaceRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return il(x0);
    }
}
