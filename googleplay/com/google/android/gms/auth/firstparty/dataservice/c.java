package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.Country;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class c implements Creator<AccountRecoveryData> {
    static void a(AccountRecoveryData accountRecoveryData, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, accountRecoveryData.version);
        b.a(parcel, 2, accountRecoveryData.guidance, i, false);
        b.a(parcel, 3, accountRecoveryData.action, false);
        b.a(parcel, 4, accountRecoveryData.allowedRecoveryOption, false);
        b.a(parcel, 5, accountRecoveryData.accountName, false);
        b.a(parcel, 6, accountRecoveryData.secondaryEmail, false);
        b.a(parcel, 7, accountRecoveryData.phoneNumber, false);
        b.d(parcel, 8, accountRecoveryData.countries, false);
        b.a(parcel, 9, accountRecoveryData.defaultCountryCode, false);
        b.a(parcel, 10, accountRecoveryData.error, false);
        b.J(parcel, bU);
    }

    public AccountRecoveryData[] aM(int i) {
        return new AccountRecoveryData[i];
    }

    public AccountRecoveryData ai(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        List list = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        AccountRecoveryGuidance accountRecoveryGuidance = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    accountRecoveryGuidance = (AccountRecoveryGuidance) a.a(parcel, bS, AccountRecoveryGuidance.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str7 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str6 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    list = a.c(parcel, bS, Country.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AccountRecoveryData(i, accountRecoveryGuidance, str7, str6, str5, str4, str3, list, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ai(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aM(x0);
    }
}
