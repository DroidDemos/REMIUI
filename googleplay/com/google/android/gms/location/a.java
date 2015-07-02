package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.ph;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class a implements Creator<GeofencingRequest> {
    static void a(GeofencingRequest geofencingRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, geofencingRequest.oZ(), false);
        b.c(parcel, 1000, geofencingRequest.getVersionCode());
        b.c(parcel, 2, geofencingRequest.getInitialTrigger());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fi(x0);
    }

    public GeofencingRequest fi(Parcel parcel) {
        int i = 0;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        List list = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    list = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS, ph.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case 1000:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new GeofencingRequest(i2, list, i);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public GeofencingRequest[] hs(int i) {
        return new GeofencingRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hs(x0);
    }
}
