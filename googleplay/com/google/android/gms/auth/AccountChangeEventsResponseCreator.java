package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class AccountChangeEventsResponseCreator implements Creator<AccountChangeEventsResponse> {
    static void a(AccountChangeEventsResponse accountChangeEventsResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, accountChangeEventsResponse.Gf);
        b.d(parcel, 2, accountChangeEventsResponse.ms, false);
        b.J(parcel, bU);
    }

    public AccountChangeEventsResponse createFromParcel(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list = a.c(parcel, bS, AccountChangeEvent.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AccountChangeEventsResponse(i, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AccountChangeEventsResponse[] newArray(int size) {
        return new AccountChangeEventsResponse[size];
    }
}
