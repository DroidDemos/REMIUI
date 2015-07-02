package com.google.android.gms.auth.firstparty.proximity.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<PermitAccess> {
    static void a(PermitAccess permitAccess, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, permitAccess.Gf);
        b.a(parcel, 2, permitAccess.CB, false);
        b.a(parcel, 3, permitAccess.vc, false);
        b.a(parcel, 4, permitAccess.mData, false);
        b.J(parcel, bU);
    }

    public PermitAccess aX(Parcel parcel) {
        byte[] bArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bArr = a.s(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new PermitAccess(i, str2, str, bArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PermitAccess[] bB(int i) {
        return new PermitAccess[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aX(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bB(x0);
    }
}
