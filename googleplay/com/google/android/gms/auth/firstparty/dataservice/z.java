package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class z implements Creator<PasswordCheckRequest> {
    static void a(PasswordCheckRequest passwordCheckRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, passwordCheckRequest.version);
        b.a(parcel, 2, passwordCheckRequest.accountName, false);
        b.a(parcel, 3, passwordCheckRequest.HB, false);
        b.a(parcel, 4, passwordCheckRequest.Gs, false);
        b.a(parcel, 5, passwordCheckRequest.Gt, false);
        b.a(parcel, 6, passwordCheckRequest.HC, i, false);
        b.J(parcel, bU);
    }

    public PasswordCheckRequest aE(Parcel parcel) {
        AppDescription appDescription = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    appDescription = (AppDescription) a.a(parcel, bS, AppDescription.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new PasswordCheckRequest(i, str4, str3, str2, str, appDescription);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PasswordCheckRequest[] bi(int i) {
        return new PasswordCheckRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aE(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bi(x0);
    }
}
