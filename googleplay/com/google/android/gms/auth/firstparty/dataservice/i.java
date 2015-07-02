package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class i implements Creator<AccountRemovalRequest> {
    static void a(AccountRemovalRequest accountRemovalRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, accountRemovalRequest.version);
        b.a(parcel, 2, accountRemovalRequest.accountName, false);
        b.J(parcel, bU);
    }

    public AccountRemovalRequest[] aS(int i) {
        return new AccountRemovalRequest[i];
    }

    public AccountRemovalRequest ao(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AccountRemovalRequest(i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ao(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aS(x0);
    }
}
