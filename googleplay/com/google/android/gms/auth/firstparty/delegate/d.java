package com.google.android.gms.auth.firstparty.delegate;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.FACLConfig;
import com.google.android.gms.auth.firstparty.shared.PACLConfig;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<TokenWorkflowRequest> {
    static void a(TokenWorkflowRequest tokenWorkflowRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, tokenWorkflowRequest.version);
        b.a(parcel, 2, tokenWorkflowRequest.HE, false);
        b.a(parcel, 3, tokenWorkflowRequest.accountName, false);
        b.a(parcel, 4, tokenWorkflowRequest.Ho, false);
        b.a(parcel, 5, tokenWorkflowRequest.HF, i, false);
        b.a(parcel, 6, tokenWorkflowRequest.HG, i, false);
        b.a(parcel, 7, tokenWorkflowRequest.Ib);
        b.a(parcel, 8, tokenWorkflowRequest.callingAppDescription, i, false);
        b.J(parcel, bU);
    }

    public TokenWorkflowRequest aT(Parcel parcel) {
        boolean z = false;
        AppDescription appDescription = null;
        int bT = a.bT(parcel);
        Bundle bundle = new Bundle();
        PACLConfig pACLConfig = null;
        FACLConfig fACLConfig = null;
        String str = null;
        String str2 = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    fACLConfig = (FACLConfig) a.a(parcel, bS, FACLConfig.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    pACLConfig = (PACLConfig) a.a(parcel, bS, PACLConfig.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    appDescription = (AppDescription) a.a(parcel, bS, AppDescription.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new TokenWorkflowRequest(i, str2, str, bundle, fACLConfig, pACLConfig, z, appDescription);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public TokenWorkflowRequest[] bx(int i) {
        return new TokenWorkflowRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aT(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bx(x0);
    }
}
