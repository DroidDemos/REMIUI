package com.google.android.gms.location.reporting;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<InactiveReason> {
    static void a(InactiveReason inactiveReason, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, inactiveReason.getVersionCode());
        b.c(parcel, 2, inactiveReason.getIdentifier());
        b.a(parcel, 3, inactiveReason.getName(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gc(x0);
    }

    public InactiveReason gc(Parcel parcel) {
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
            return new InactiveReason(i2, i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public InactiveReason[] ix(int i) {
        return new InactiveReason[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ix(x0);
    }
}
