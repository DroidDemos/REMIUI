package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class k implements Creator<OptInParams> {
    static void a(OptInParams optInParams, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, optInParams.getVersionCode());
        b.a(parcel, 2, optInParams.getPackageName(), false);
        b.c(parcel, 3, optInParams.getAppTitleResId());
        b.c(parcel, 4, optInParams.getAppDetailResId());
        b.c(parcel, 5, optInParams.getIntroImageResId());
        b.c(parcel, 6, optInParams.getIntroLabelResId());
        b.a(parcel, 7, optInParams.getNamedAclName(), false);
        b.a(parcel, 8, optInParams.getFeatureId(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ft(x0);
    }

    public OptInParams ft(Parcel parcel) {
        String str = null;
        int i = 0;
        int bT = a.bT(parcel);
        String str2 = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        String str3 = null;
        int i5 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i5 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i4 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str2 = a.p(parcel, bS);
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
            return new OptInParams(i5, str3, i4, i3, i2, i, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public OptInParams[] hK(int i) {
        return new OptInParams[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hK(x0);
    }
}
