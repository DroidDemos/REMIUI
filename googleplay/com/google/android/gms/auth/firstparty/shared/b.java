package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<AppDescription> {
    static void a(AppDescription appDescription, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, appDescription.version);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, appDescription.Ir);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, appDescription.tP, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, appDescription.Is, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, appDescription.It, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, appDescription.GN);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public AppDescription[] bE(int i) {
        return new AppDescription[i];
    }

    public AppDescription ba(Parcel parcel) {
        String str = null;
        boolean z = false;
        int bT = a.bT(parcel);
        String str2 = null;
        String str3 = null;
        int i = 0;
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
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AppDescription(i2, i, str3, str2, str, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ba(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bE(x0);
    }
}
