package com.google.android.gms.search.global;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.search.global.GetGlobalSearchSourcesCall.CorpusInfo;
import com.google.android.gms.search.global.GetGlobalSearchSourcesCall.GlobalSearchSource;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<GlobalSearchSource> {
    static void a(GlobalSearchSource globalSearchSource, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, globalSearchSource.mVersionCode);
        b.a(parcel, 1, globalSearchSource.packageName, false);
        b.c(parcel, 2, globalSearchSource.labelId);
        b.c(parcel, 3, globalSearchSource.settingsDescriptionId);
        b.c(parcel, 4, globalSearchSource.iconId);
        b.a(parcel, 5, globalSearchSource.defaultIntentAction, false);
        b.a(parcel, 6, globalSearchSource.defaultIntentData, false);
        b.a(parcel, 7, globalSearchSource.defaultIntentActivity, false);
        b.a(parcel, 8, globalSearchSource.corpora, i, false);
        b.a(parcel, 9, globalSearchSource.enabled);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hE(x0);
    }

    public GlobalSearchSource hE(Parcel parcel) {
        CorpusInfo[] corpusInfoArr = null;
        boolean z = false;
        int bT = a.bT(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        int i = 0;
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
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    corpusInfoArr = (CorpusInfo[]) a.b(parcel, bS, CorpusInfo.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    z = a.c(parcel, bS);
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
            return new GlobalSearchSource(i4, str4, i3, i2, i, str3, str2, str, corpusInfoArr, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public GlobalSearchSource[] kp(int i) {
        return new GlobalSearchSource[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kp(x0);
    }
}
