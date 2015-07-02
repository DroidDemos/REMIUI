package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class AccountChangeEventCreator implements Creator<AccountChangeEvent> {
    static void a(AccountChangeEvent accountChangeEvent, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, accountChangeEvent.Gf);
        b.a(parcel, 2, accountChangeEvent.Gg);
        b.a(parcel, 3, accountChangeEvent.Fl, false);
        b.c(parcel, 4, accountChangeEvent.Gh);
        b.c(parcel, 5, accountChangeEvent.Gi);
        b.a(parcel, 6, accountChangeEvent.Gj, false);
        b.J(parcel, bU);
    }

    public AccountChangeEvent createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int bT = a.bT(parcel);
        long j = 0;
        int i2 = 0;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AccountChangeEvent(i3, j, str2, i2, i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AccountChangeEvent[] newArray(int size) {
        return new AccountChangeEvent[size];
    }
}
