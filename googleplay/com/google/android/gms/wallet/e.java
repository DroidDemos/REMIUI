package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<FullWallet> {
    static void a(FullWallet fullWallet, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, fullWallet.getVersionCode());
        b.a(parcel, 2, fullWallet.aTl, false);
        b.a(parcel, 3, fullWallet.aTm, false);
        b.a(parcel, 4, fullWallet.aTn, i, false);
        b.a(parcel, 5, fullWallet.aTo, false);
        b.a(parcel, 6, fullWallet.aTp, i, false);
        b.a(parcel, 7, fullWallet.aTq, i, false);
        b.a(parcel, 8, fullWallet.aTr, false);
        b.a(parcel, 9, fullWallet.aTs, i, false);
        b.a(parcel, 10, fullWallet.aTt, i, false);
        b.a(parcel, 11, fullWallet.aTu, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ia(x0);
    }

    public FullWallet ia(Parcel parcel) {
        InstrumentInfo[] instrumentInfoArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        UserAddress userAddress = null;
        UserAddress userAddress2 = null;
        String[] strArr = null;
        Address address = null;
        Address address2 = null;
        String str = null;
        ProxyCard proxyCard = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    proxyCard = (ProxyCard) a.a(parcel, bS, ProxyCard.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    address2 = (Address) a.a(parcel, bS, Address.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    address = (Address) a.a(parcel, bS, Address.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    strArr = a.B(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    userAddress2 = (UserAddress) a.a(parcel, bS, UserAddress.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    userAddress = (UserAddress) a.a(parcel, bS, UserAddress.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    instrumentInfoArr = (InstrumentInfo[]) a.b(parcel, bS, InstrumentInfo.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new FullWallet(i, str3, str2, proxyCard, str, address2, address, strArr, userAddress2, userAddress, instrumentInfoArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public FullWallet[] kV(int i) {
        return new FullWallet[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kV(x0);
    }
}
