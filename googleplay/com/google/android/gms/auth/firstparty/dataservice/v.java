package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class v implements Creator<GplusInfoResponse> {
    static void a(GplusInfoResponse gplusInfoResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, gplusInfoResponse.version);
        b.a(parcel, 2, gplusInfoResponse.Hv);
        b.a(parcel, 3, gplusInfoResponse.firstName, false);
        b.a(parcel, 4, gplusInfoResponse.lastName, false);
        b.a(parcel, 5, gplusInfoResponse.Hw, false);
        b.a(parcel, 6, gplusInfoResponse.Hx);
        b.a(parcel, 7, gplusInfoResponse.Hy);
        b.a(parcel, 8, gplusInfoResponse.Hz, false);
        b.a(parcel, 9, gplusInfoResponse.Hu, false);
        b.a(parcel, 10, gplusInfoResponse.HA, false);
        b.J(parcel, bU);
    }

    public GplusInfoResponse aB(Parcel parcel) {
        boolean z = false;
        String str = null;
        int bT = a.bT(parcel);
        String str2 = null;
        String str3 = null;
        boolean z2 = false;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        boolean z3 = false;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z3 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str6 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str3 = a.p(parcel, bS);
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
            return new GplusInfoResponse(i, z3, str6, str5, str4, z2, z, str3, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public GplusInfoResponse[] bf(int i) {
        return new GplusInfoResponse[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aB(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bf(x0);
    }
}
