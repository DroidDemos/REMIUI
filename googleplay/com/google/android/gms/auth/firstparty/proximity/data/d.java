package com.google.android.gms.auth.firstparty.proximity.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class d implements Creator<Permit> {
    static void a(Permit permit, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, permit.Gf);
        b.a(parcel, 2, permit.CB, false);
        b.a(parcel, 3, permit.Ie, false);
        b.a(parcel, 5, permit.vc, false);
        b.a(parcel, 6, permit.If, i, false);
        b.d(parcel, 7, permit.Ig, false);
        b.c(parcel, 8, permit.Ii, false);
        b.J(parcel, bU);
    }

    public Permit aY(Parcel parcel) {
        List list = null;
        int bT = a.bT(parcel);
        int i = 0;
        List list2 = null;
        PermitAccess permitAccess = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    permitAccess = (PermitAccess) a.a(parcel, bS, PermitAccess.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    list2 = a.c(parcel, bS, PermitAccess.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    list = a.E(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Permit(i, str3, str2, str, permitAccess, list2, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Permit[] bC(int i) {
        return new Permit[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aY(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bC(x0);
    }
}
