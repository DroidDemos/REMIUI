package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class l implements Creator<MaskedWalletRequest> {
    static void a(MaskedWalletRequest maskedWalletRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, maskedWalletRequest.getVersionCode());
        b.a(parcel, 2, maskedWalletRequest.aTm, false);
        b.a(parcel, 3, maskedWalletRequest.aTz);
        b.a(parcel, 4, maskedWalletRequest.aTA);
        b.a(parcel, 5, maskedWalletRequest.aTB);
        b.a(parcel, 6, maskedWalletRequest.aUi, false);
        b.a(parcel, 7, maskedWalletRequest.aTg, false);
        b.a(parcel, 8, maskedWalletRequest.aUj, false);
        b.a(parcel, 9, maskedWalletRequest.aTv, i, false);
        b.a(parcel, 10, maskedWalletRequest.aUk);
        b.a(parcel, 11, maskedWalletRequest.aUl);
        b.a(parcel, 12, maskedWalletRequest.aTD, i, false);
        b.a(parcel, 13, maskedWalletRequest.aUm);
        b.a(parcel, 14, maskedWalletRequest.aUn);
        b.d(parcel, 15, maskedWalletRequest.aTE, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ih(x0);
    }

    public MaskedWalletRequest ih(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Cart cart = null;
        boolean z4 = false;
        boolean z5 = false;
        CountrySpecification[] countrySpecificationArr = null;
        boolean z6 = true;
        boolean z7 = true;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z3 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    cart = (Cart) a.a(parcel, bS, Cart.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    z4 = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    z5 = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    countrySpecificationArr = (CountrySpecification[]) a.b(parcel, bS, CountrySpecification.CREATOR);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    z6 = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    z7 = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    arrayList = a.c(parcel, bS, CountrySpecification.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new MaskedWalletRequest(i, str, z, z2, z3, str2, str3, str4, cart, z4, z5, countrySpecificationArr, z6, z7, arrayList);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public MaskedWalletRequest[] lc(int i) {
        return new MaskedWalletRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lc(x0);
    }
}
