package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<ConnectionConfiguration> {
    static void a(ConnectionConfiguration connectionConfiguration, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, connectionConfiguration.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, connectionConfiguration.getName(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, connectionConfiguration.getAddress(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, connectionConfiguration.getType());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, connectionConfiguration.getRole());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, connectionConfiguration.isEnabled());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, connectionConfiguration.isConnected());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, connectionConfiguration.getPeerNodeId(), false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iE(x0);
    }

    public ConnectionConfiguration iE(Parcel parcel) {
        String str = null;
        boolean z = false;
        int bT = a.bT(parcel);
        boolean z2 = false;
        int i = 0;
        int i2 = 0;
        String str2 = null;
        String str3 = null;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str3 = a.p(parcel, bS);
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
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ConnectionConfiguration(i3, str3, str2, i2, i, z2, z, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ConnectionConfiguration[] lE(int i) {
        return new ConnectionConfiguration[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lE(x0);
    }
}
