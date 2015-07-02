package com.google.android.gms.googlehelp;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<OfflineSuggestion> {
    static void a(OfflineSuggestion offlineSuggestion, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, offlineSuggestion.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, offlineSuggestion.CB, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, offlineSuggestion.Yv, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, offlineSuggestion.pE, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, offlineSuggestion.ase, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fc(x0);
    }

    public OfflineSuggestion fc(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str4 = a.p(parcel, bS);
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
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new OfflineSuggestion(i, str4, str3, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public OfflineSuggestion[] hk(int i) {
        return new OfflineSuggestion[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hk(x0);
    }
}
