package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<LoyaltyPointsBalance> {
    static void a(LoyaltyPointsBalance loyaltyPointsBalance, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, loyaltyPointsBalance.getVersionCode());
        b.c(parcel, 2, loyaltyPointsBalance.aVR);
        b.a(parcel, 3, loyaltyPointsBalance.aVS, false);
        b.a(parcel, 4, loyaltyPointsBalance.aVT);
        b.a(parcel, 5, loyaltyPointsBalance.aVU, false);
        b.a(parcel, 6, loyaltyPointsBalance.aVV);
        b.c(parcel, 7, loyaltyPointsBalance.aVW);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ix(x0);
    }

    public LoyaltyPointsBalance ix(Parcel parcel) {
        String str = null;
        int i = 0;
        int bT = a.bT(parcel);
        double d = 0.0d;
        long j = 0;
        int i2 = -1;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    d = a.m(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new LoyaltyPointsBalance(i3, i, str2, d, str, j, i2);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public LoyaltyPointsBalance[] lx(int i) {
        return new LoyaltyPointsBalance[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lx(x0);
    }
}
