package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class s implements Creator<GoogleAccountData> {
    static void a(GoogleAccountData googleAccountData, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, googleAccountData.version);
        b.a(parcel, 2, googleAccountData.accountName, false);
        b.a(parcel, 3, googleAccountData.GQ);
        b.c(parcel, 4, googleAccountData.services, false);
        b.a(parcel, 5, googleAccountData.firstName, false);
        b.a(parcel, 6, googleAccountData.lastName, false);
        b.J(parcel, bU);
    }

    public GoogleAccountData ay(Parcel parcel) {
        boolean z = false;
        String str = null;
        int bT = a.bT(parcel);
        String str2 = null;
        List list = null;
        String str3 = null;
        int i = 0;
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
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    list = a.E(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new GoogleAccountData(i, str3, z, list, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public GoogleAccountData[] bc(int i) {
        return new GoogleAccountData[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ay(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bc(x0);
    }
}
