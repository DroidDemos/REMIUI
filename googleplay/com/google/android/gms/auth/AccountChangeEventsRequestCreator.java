package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class AccountChangeEventsRequestCreator implements Creator<AccountChangeEventsRequest> {
    static void a(AccountChangeEventsRequest accountChangeEventsRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, accountChangeEventsRequest.Gf);
        b.c(parcel, 2, accountChangeEventsRequest.Gi);
        b.a(parcel, 3, accountChangeEventsRequest.Fl, false);
        b.J(parcel, bU);
    }

    public AccountChangeEventsRequest createFromParcel(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AccountChangeEventsRequest(i2, i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AccountChangeEventsRequest[] newArray(int size) {
        return new AccountChangeEventsRequest[size];
    }
}
