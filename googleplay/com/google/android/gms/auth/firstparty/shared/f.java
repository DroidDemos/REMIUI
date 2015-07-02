package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class f implements Creator<FACLData> {
    static void a(FACLData fACLData, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, fACLData.version);
        b.a(parcel, 2, fACLData.IC, i, false);
        b.a(parcel, 3, fACLData.IE, false);
        b.a(parcel, 4, fACLData.IF);
        b.a(parcel, 5, fACLData.IG, false);
        b.J(parcel, bU);
    }

    public FACLData[] bI(int i) {
        return new FACLData[i];
    }

    public FACLData be(Parcel parcel) {
        boolean z = false;
        String str = null;
        int bT = a.bT(parcel);
        String str2 = null;
        FACLConfig fACLConfig = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    fACLConfig = (FACLConfig) a.a(parcel, bS, FACLConfig.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z = a.c(parcel, bS);
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
            return new FACLData(i, fACLConfig, str2, z, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return be(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bI(x0);
    }
}
