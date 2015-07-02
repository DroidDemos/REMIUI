package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class n implements Creator<GlobalSearchApplicationInfo> {
    static void a(GlobalSearchApplicationInfo globalSearchApplicationInfo, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, globalSearchApplicationInfo.packageName, false);
        b.c(parcel, 1000, globalSearchApplicationInfo.mVersionCode);
        b.c(parcel, 2, globalSearchApplicationInfo.labelId);
        b.c(parcel, 3, globalSearchApplicationInfo.settingsDescriptionId);
        b.c(parcel, 4, globalSearchApplicationInfo.iconId);
        b.a(parcel, 5, globalSearchApplicationInfo.defaultIntentAction, false);
        b.a(parcel, 6, globalSearchApplicationInfo.defaultIntentData, false);
        b.a(parcel, 7, globalSearchApplicationInfo.defaultIntentActivity, false);
        b.J(parcel, bU);
    }

    public GlobalSearchApplicationInfo[] R(int i) {
        return new GlobalSearchApplicationInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return x(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return R(x0);
    }

    public GlobalSearchApplicationInfo x(Parcel parcel) {
        String str = null;
        int i = 0;
        int bT = a.bT(parcel);
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        int i3 = 0;
        String str4 = null;
        int i4 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str = a.p(parcel, bS);
                    break;
                case 1000:
                    i4 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new GlobalSearchApplicationInfo(i4, str4, i3, i2, i, str3, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }
}
