package com.google.android.gms.appdatasearch;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class y implements Creator<RegisterCorpusInfo> {
    static void a(RegisterCorpusInfo registerCorpusInfo, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, registerCorpusInfo.name, false);
        b.c(parcel, 1000, registerCorpusInfo.mVersionCode);
        b.a(parcel, 2, registerCorpusInfo.version, false);
        b.a(parcel, 3, registerCorpusInfo.contentProviderUri, i, false);
        b.a(parcel, 4, registerCorpusInfo.sections, i, false);
        b.a(parcel, 7, registerCorpusInfo.globalSearchConfig, i, false);
        b.a(parcel, 8, registerCorpusInfo.trimmable);
        b.a(parcel, 9, registerCorpusInfo.account, i, false);
        b.a(parcel, 10, registerCorpusInfo.imeConfig, i, false);
        b.a(parcel, 11, registerCorpusInfo.schemaOrgType, false);
        b.J(parcel, bU);
    }

    public RegisterCorpusInfo I(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = "0";
        boolean z = true;
        RegisterCorpusIMEInfo registerCorpusIMEInfo = null;
        Account account = null;
        GlobalSearchCorpusConfig globalSearchCorpusConfig = null;
        RegisterSectionInfo[] registerSectionInfoArr = null;
        Uri uri = null;
        String str3 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    uri = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    registerSectionInfoArr = (RegisterSectionInfo[]) a.b(parcel, bS, RegisterSectionInfo.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    globalSearchCorpusConfig = (GlobalSearchCorpusConfig) a.a(parcel, bS, GlobalSearchCorpusConfig.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    account = (Account) a.a(parcel, bS, Account.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    registerCorpusIMEInfo = (RegisterCorpusIMEInfo) a.a(parcel, bS, RegisterCorpusIMEInfo.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    str = a.p(parcel, bS);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new RegisterCorpusInfo(i, str3, str2, uri, registerSectionInfoArr, globalSearchCorpusConfig, z, account, registerCorpusIMEInfo, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public RegisterCorpusInfo[] ac(int i) {
        return new RegisterCorpusInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return I(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ac(x0);
    }
}
