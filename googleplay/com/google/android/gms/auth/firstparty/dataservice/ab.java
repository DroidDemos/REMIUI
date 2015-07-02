package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ab implements Creator<PasswordSettings> {
    static void a(PasswordSettings passwordSettings, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, passwordSettings.version);
        b.a(parcel, 2, passwordSettings.status, false);
        b.J(parcel, bU);
    }

    public PasswordSettings aG(Parcel parcel) {
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
            return new PasswordSettings(i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PasswordSettings[] bk(int i) {
        return new PasswordSettings[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aG(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bk(x0);
    }
}
