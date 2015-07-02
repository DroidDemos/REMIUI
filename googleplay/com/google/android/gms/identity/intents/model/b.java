package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<UserAddress> {
    static void a(UserAddress userAddress, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, userAddress.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, userAddress.name, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, userAddress.asT, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, userAddress.asU, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, userAddress.asV, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, userAddress.asW, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, userAddress.asX, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, userAddress.asY, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, userAddress.asZ, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, userAddress.vk, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, userAddress.ata, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, userAddress.atb, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 13, userAddress.phoneNumber, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, userAddress.atc);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, userAddress.atd, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 16, userAddress.ate, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fh(x0);
    }

    public UserAddress fh(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        boolean z = false;
        String str13 = null;
        String str14 = null;
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
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str6 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str7 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str8 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    str9 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    str10 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    str11 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    str12 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    str13 = a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    str14 = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new UserAddress(i, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, z, str13, str14);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public UserAddress[] hp(int i) {
        return new UserAddress[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hp(x0);
    }
}
