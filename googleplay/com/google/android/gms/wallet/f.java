package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class f implements Creator<FullWalletRequest> {
    static void a(FullWalletRequest fullWalletRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, fullWalletRequest.getVersionCode());
        b.a(parcel, 2, fullWalletRequest.aTl, false);
        b.a(parcel, 3, fullWalletRequest.aTm, false);
        b.a(parcel, 4, fullWalletRequest.aTv, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ib(x0);
    }

    public FullWalletRequest ib(Parcel parcel) {
        Cart cart = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    cart = (Cart) a.a(parcel, bS, Cart.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new FullWalletRequest(i, str2, str, cart);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public FullWalletRequest[] kW(int i) {
        return new FullWalletRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kW(x0);
    }
}
