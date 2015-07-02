package com.google.android.gms.wallet.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<BuyFlowConfig> {
    static void a(BuyFlowConfig buyFlowConfig, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, buyFlowConfig.mVersionCode);
        b.a(parcel, 2, buyFlowConfig.aVB, false);
        b.a(parcel, 3, buyFlowConfig.aVC, i, false);
        b.a(parcel, 4, buyFlowConfig.aVD, false);
        b.a(parcel, 5, buyFlowConfig.aVE, false);
        b.a(parcel, 6, buyFlowConfig.aVF, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return is(x0);
    }

    public BuyFlowConfig is(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        ApplicationParameters applicationParameters = null;
        String str4 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    applicationParameters = (ApplicationParameters) a.a(parcel, bS, ApplicationParameters.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new BuyFlowConfig(i, str4, applicationParameters, str3, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public BuyFlowConfig[] lr(int i) {
        return new BuyFlowConfig[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lr(x0);
    }
}
