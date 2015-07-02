package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<LaunchOptions> {
    static void a(LaunchOptions launchOptions, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, launchOptions.getVersionCode());
        b.a(parcel, 2, launchOptions.getRelaunchIfRunning());
        b.a(parcel, 3, launchOptions.getLanguage(), false);
        b.J(parcel, bU);
    }

    public LaunchOptions bI(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z = a.c(parcel, bS);
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
            return new LaunchOptions(i, z, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public LaunchOptions[] cC(int i) {
        return new LaunchOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bI(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cC(x0);
    }
}
