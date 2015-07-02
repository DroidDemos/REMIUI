package com.google.android.gms.wallet;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class g implements Creator<ImmediateFullWalletRequest> {
    static void a(ImmediateFullWalletRequest immediateFullWalletRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, immediateFullWalletRequest.getVersionCode());
        b.c(parcel, 2, immediateFullWalletRequest.environment);
        b.a(parcel, 3, immediateFullWalletRequest.account, i, false);
        b.a(parcel, 4, immediateFullWalletRequest.aTx, false);
        b.c(parcel, 5, immediateFullWalletRequest.aTy);
        b.a(parcel, 6, immediateFullWalletRequest.aTz);
        b.a(parcel, 7, immediateFullWalletRequest.aTA);
        b.a(parcel, 8, immediateFullWalletRequest.aTB);
        b.a(parcel, 9, immediateFullWalletRequest.tP, false);
        b.a(parcel, 10, immediateFullWalletRequest.aTC);
        b.a(parcel, 11, immediateFullWalletRequest.aTD, i, false);
        b.d(parcel, 12, immediateFullWalletRequest.aTE, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ic(x0);
    }

    public ImmediateFullWalletRequest ic(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        int i2 = 0;
        Account account = null;
        String str = null;
        int i3 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        String str2 = null;
        boolean z4 = false;
        CountrySpecification[] countrySpecificationArr = null;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    account = (Account) a.a(parcel, bS, Account.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    z3 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    z4 = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    countrySpecificationArr = (CountrySpecification[]) a.b(parcel, bS, CountrySpecification.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    arrayList = a.c(parcel, bS, CountrySpecification.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ImmediateFullWalletRequest(i, i2, account, str, i3, z, z2, z3, str2, z4, countrySpecificationArr, arrayList);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ImmediateFullWalletRequest[] kX(int i) {
        return new ImmediateFullWalletRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kX(x0);
    }
}
