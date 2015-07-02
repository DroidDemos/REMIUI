package com.google.android.gms.location.reporting;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<GmmSettings> {
    static void a(GmmSettings gmmSettings, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, gmmSettings.getVersionCode());
        b.a(parcel, 2, gmmSettings.getReadMillis());
        b.a(parcel, 3, gmmSettings.getAccount(), i, false);
        b.a(parcel, 4, gmmSettings.isReportingSelected());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gb(x0);
    }

    public GmmSettings gb(Parcel parcel) {
        boolean z = false;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        long j = 0;
        Account account = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j = com.google.android.gms.common.internal.safeparcel.a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    account = (Account) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, Account.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new GmmSettings(i, j, account, z);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public GmmSettings[] iw(int i) {
        return new GmmSettings[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iw(x0);
    }
}
