package com.google.android.gms.identity.intents;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class a implements Creator<UserAddressRequest> {
    static void a(UserAddressRequest userAddressRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, userAddressRequest.getVersionCode());
        b.d(parcel, 2, userAddressRequest.asQ, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ff(x0);
    }

    public UserAddressRequest ff(Parcel parcel) {
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS, CountrySpecification.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new UserAddressRequest(i, list);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public UserAddressRequest[] hn(int i) {
        return new UserAddressRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hn(x0);
    }
}
