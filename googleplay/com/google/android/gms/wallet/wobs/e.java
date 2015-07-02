package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<LoyaltyPoints> {
    static void a(LoyaltyPoints loyaltyPoints, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, loyaltyPoints.getVersionCode());
        b.a(parcel, 2, loyaltyPoints.label, false);
        b.a(parcel, 3, loyaltyPoints.aVP, i, false);
        b.a(parcel, 4, loyaltyPoints.type, false);
        b.a(parcel, 5, loyaltyPoints.aTU, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iy(x0);
    }

    public LoyaltyPoints iy(Parcel parcel) {
        TimeInterval timeInterval = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        LoyaltyPointsBalance loyaltyPointsBalance = null;
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
                    loyaltyPointsBalance = (LoyaltyPointsBalance) a.a(parcel, bS, LoyaltyPointsBalance.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    timeInterval = (TimeInterval) a.a(parcel, bS, TimeInterval.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new LoyaltyPoints(i, str2, loyaltyPointsBalance, str, timeInterval);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public LoyaltyPoints[] ly(int i) {
        return new LoyaltyPoints[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ly(x0);
    }
}
