package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.android.wallet.instrumentmanager.R;

public class k implements Creator<MaskedWallet> {
    static void a(MaskedWallet maskedWallet, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, maskedWallet.getVersionCode());
        b.a(parcel, 2, maskedWallet.aTl, false);
        b.a(parcel, 3, maskedWallet.aTm, false);
        b.a(parcel, 4, maskedWallet.aTr, false);
        b.a(parcel, 5, maskedWallet.aTo, false);
        b.a(parcel, 6, maskedWallet.aTp, i, false);
        b.a(parcel, 7, maskedWallet.aTq, i, false);
        b.a(parcel, 8, maskedWallet.aUf, i, false);
        b.a(parcel, 9, maskedWallet.aUg, i, false);
        b.a(parcel, 10, maskedWallet.aTs, i, false);
        b.a(parcel, 11, maskedWallet.aTt, i, false);
        b.a(parcel, 12, maskedWallet.aTu, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ig(x0);
    }

    public MaskedWallet ig(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String[] strArr = null;
        String str3 = null;
        Address address = null;
        Address address2 = null;
        LoyaltyWalletObject[] loyaltyWalletObjectArr = null;
        OfferWalletObject[] offerWalletObjectArr = null;
        UserAddress userAddress = null;
        UserAddress userAddress2 = null;
        InstrumentInfo[] instrumentInfoArr = null;
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
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    strArr = a.B(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    address = (Address) a.a(parcel, bS, Address.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    address2 = (Address) a.a(parcel, bS, Address.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    loyaltyWalletObjectArr = (LoyaltyWalletObject[]) a.b(parcel, bS, LoyaltyWalletObject.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    offerWalletObjectArr = (OfferWalletObject[]) a.b(parcel, bS, OfferWalletObject.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    userAddress = (UserAddress) a.a(parcel, bS, UserAddress.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    userAddress2 = (UserAddress) a.a(parcel, bS, UserAddress.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    instrumentInfoArr = (InstrumentInfo[]) a.b(parcel, bS, InstrumentInfo.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new MaskedWallet(i, str, str2, strArr, str3, address, address2, loyaltyWalletObjectArr, offerWalletObjectArr, userAddress, userAddress2, instrumentInfoArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public MaskedWallet[] lb(int i) {
        return new MaskedWallet[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lb(x0);
    }
}
