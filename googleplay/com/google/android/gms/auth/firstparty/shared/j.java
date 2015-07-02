package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;

public class j implements Creator<ScopeDetail> {
    static void a(ScopeDetail scopeDetail, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, scopeDetail.version);
        b.a(parcel, 2, scopeDetail.description, false);
        b.a(parcel, 3, scopeDetail.Gx, false);
        b.a(parcel, 4, scopeDetail.IL, false);
        b.a(parcel, 5, scopeDetail.IM, false);
        b.a(parcel, 6, scopeDetail.HE, false);
        b.c(parcel, 7, scopeDetail.IN, false);
        b.a(parcel, 8, scopeDetail.friendPickerData, i, false);
        b.J(parcel, bU);
    }

    public ScopeDetail[] bM(int i) {
        return new ScopeDetail[i];
    }

    public ScopeDetail bi(Parcel parcel) {
        FACLData fACLData = null;
        int bT = a.bT(parcel);
        int i = 0;
        List arrayList = new ArrayList();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    arrayList = a.E(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    fACLData = (FACLData) a.a(parcel, bS, FACLData.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ScopeDetail(i, str5, str4, str3, str2, str, arrayList, fACLData);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bi(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bM(x0);
    }
}
