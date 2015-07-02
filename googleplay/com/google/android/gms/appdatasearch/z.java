package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class z implements Creator<RegisterSectionInfo> {
    static void a(RegisterSectionInfo registerSectionInfo, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, registerSectionInfo.name, false);
        b.c(parcel, 1000, registerSectionInfo.mVersionCode);
        b.a(parcel, 2, registerSectionInfo.format, false);
        b.a(parcel, 3, registerSectionInfo.noIndex);
        b.c(parcel, 4, registerSectionInfo.weight);
        b.a(parcel, 5, registerSectionInfo.indexPrefixes);
        b.a(parcel, 6, registerSectionInfo.subsectionSeparator, false);
        b.a(parcel, 7, registerSectionInfo.features, i, false);
        b.a(parcel, 8, registerSectionInfo.DU, false);
        b.a(parcel, 11, registerSectionInfo.schemaOrgProperty, false);
        b.J(parcel, bU);
    }

    public RegisterSectionInfo J(Parcel parcel) {
        boolean z = false;
        String str = null;
        int bT = a.bT(parcel);
        int i = 1;
        int[] iArr = null;
        Feature[] featureArr = null;
        String str2 = null;
        boolean z2 = false;
        String str3 = null;
        String str4 = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    featureArr = (Feature[]) a.b(parcel, bS, Feature.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    iArr = a.v(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    str = a.p(parcel, bS);
                    break;
                case 1000:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new RegisterSectionInfo(i2, str4, str3, z2, i, z, str2, featureArr, iArr, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public RegisterSectionInfo[] ad(int i) {
        return new RegisterSectionInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return J(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ad(x0);
    }
}
