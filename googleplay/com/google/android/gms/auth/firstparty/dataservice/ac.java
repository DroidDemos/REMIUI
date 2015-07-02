package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ac implements Creator<PinSettings> {
    static void a(PinSettings pinSettings, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, pinSettings.version);
        b.a(parcel, 2, pinSettings.status, false);
        b.a(parcel, 3, pinSettings.resetUrl, false);
        b.a(parcel, 4, pinSettings.setupUrl, false);
        b.c(parcel, 5, pinSettings.length);
        b.J(parcel, bU);
    }

    public PinSettings aH(Parcel parcel) {
        int i = 0;
        String str = null;
        int bT = a.bT(parcel);
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new PinSettings(i2, str3, str2, str, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PinSettings[] bl(int i) {
        return new PinSettings[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aH(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bl(x0);
    }
}
