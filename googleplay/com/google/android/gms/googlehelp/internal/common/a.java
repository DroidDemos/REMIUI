package com.google.android.gms.googlehelp.internal.common;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<OverflowMenuItem> {
    static void a(OverflowMenuItem overflowMenuItem, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, overflowMenuItem.mVersionCode);
        b.c(parcel, 2, overflowMenuItem.mId);
        b.a(parcel, 3, overflowMenuItem.Yv, false);
        b.a(parcel, 4, overflowMenuItem.mIntent, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fd(x0);
    }

    public OverflowMenuItem fd(Parcel parcel) {
        Intent intent = null;
        int i = 0;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    intent = (Intent) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, Intent.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new OverflowMenuItem(i2, i, str, intent);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public OverflowMenuItem[] hl(int i) {
        return new OverflowMenuItem[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hl(x0);
    }
}
