package com.google.android.gms.appdatasearch;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class r implements Creator<PIMEUpdate> {
    static void a(PIMEUpdate pIMEUpdate, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, pIMEUpdate.Dv, false);
        b.c(parcel, 1000, pIMEUpdate.mVersionCode);
        b.a(parcel, 2, pIMEUpdate.Dw, false);
        b.c(parcel, 3, pIMEUpdate.sourceClass);
        b.a(parcel, 4, pIMEUpdate.sourcePackageName, false);
        b.a(parcel, 5, pIMEUpdate.sourceCorpusHandle, false);
        b.a(parcel, 6, pIMEUpdate.inputByUser);
        b.a(parcel, 8, pIMEUpdate.Dx, false);
        b.a(parcel, 9, pIMEUpdate.score);
        b.a(parcel, 10, pIMEUpdate.createdTimestamp);
        b.a(parcel, 11, pIMEUpdate.account, i, false);
        b.J(parcel, bU);
    }

    public PIMEUpdate B(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        byte[] bArr = null;
        byte[] bArr2 = null;
        int i2 = 0;
        String str = null;
        String str2 = null;
        boolean z = false;
        Bundle bundle = null;
        long j = 0;
        long j2 = 0;
        Account account = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    bArr = a.s(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bArr2 = a.s(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    account = (Account) a.a(parcel, bS, Account.CREATOR);
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
            return new PIMEUpdate(i, bArr, bArr2, i2, str, str2, z, bundle, j, j2, account);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PIMEUpdate[] V(int i) {
        return new PIMEUpdate[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return B(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return V(x0);
    }
}
