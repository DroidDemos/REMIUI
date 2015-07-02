package com.google.android.gms.appdatasearch;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.appdatasearch.GetRecentContextCall.Request;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class i implements Creator<Request> {
    static void a(Request request, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, request.filterAccount, i, false);
        b.c(parcel, 1000, request.mVersionCode);
        b.J(parcel, bU);
    }

    public Request[] N(int i) {
        return new Request[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return t(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return N(x0);
    }

    public Request t(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        Account account = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    account = (Account) a.a(parcel, bS, Account.CREATOR);
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
            return new Request(i, account);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }
}
