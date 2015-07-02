package com.google.android.gms.appdatasearch;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ae implements Creator<SearchResults> {
    static void a(SearchResults searchResults, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, searchResults.mErrorMessage, false);
        b.c(parcel, 1000, searchResults.mVersionCode);
        b.a(parcel, 2, searchResults.Ef, false);
        b.a(parcel, 3, searchResults.Eg, false);
        b.a(parcel, 4, searchResults.Eh, i, false);
        b.a(parcel, 5, searchResults.Ei, i, false);
        b.a(parcel, 6, searchResults.Ej, i, false);
        b.c(parcel, 7, searchResults.Ek);
        b.a(parcel, 8, searchResults.El, false);
        b.a(parcel, 9, searchResults.Em, false);
        b.a(parcel, 10, searchResults.En, false);
        b.a(parcel, 11, searchResults.Eo, false);
        b.J(parcel, bU);
    }

    public SearchResults N(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        int[] iArr = null;
        byte[] bArr = null;
        Bundle[] bundleArr = null;
        Bundle[] bundleArr2 = null;
        Bundle[] bundleArr3 = null;
        int i2 = 0;
        int[] iArr2 = null;
        String[] strArr = null;
        byte[] bArr2 = null;
        double[] dArr = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    iArr = a.v(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bArr = a.s(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bundleArr = (Bundle[]) a.b(parcel, bS, Bundle.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    bundleArr2 = (Bundle[]) a.b(parcel, bS, Bundle.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    bundleArr3 = (Bundle[]) a.b(parcel, bS, Bundle.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    iArr2 = a.v(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    strArr = a.B(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    bArr2 = a.s(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    dArr = a.z(parcel, bS);
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
            return new SearchResults(i, str, iArr, bArr, bundleArr, bundleArr2, bundleArr3, i2, iArr2, strArr, bArr2, dArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SearchResults[] al(int i) {
        return new SearchResults[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return N(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return al(x0);
    }
}
