package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class aj implements Creator<ai> {
    static void a(ai aiVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, aiVar.mVersionCode);
        b.c(parcel, 2, aiVar.getRequestId());
        b.a(parcel, 3, aiVar.getPath(), false);
        b.a(parcel, 4, aiVar.getData(), false);
        b.a(parcel, 5, aiVar.getSourceNodeId(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iQ(x0);
    }

    public ai iQ(Parcel parcel) {
        int i = 0;
        String str = null;
        int bT = a.bT(parcel);
        byte[] bArr = null;
        String str2 = null;
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
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bArr = a.s(parcel, bS);
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
            return new ai(i2, i, str2, bArr, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ai[] lQ(int i) {
        return new ai[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lQ(x0);
    }
}
