package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class n implements Creator<CheckRealNameRequest> {
    static void a(CheckRealNameRequest checkRealNameRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, checkRealNameRequest.version);
        b.a(parcel, 2, checkRealNameRequest.callingAppDescription, i, false);
        b.a(parcel, 3, checkRealNameRequest.firstName, false);
        b.a(parcel, 4, checkRealNameRequest.lastName, false);
        b.J(parcel, bU);
    }

    public CheckRealNameRequest[] aX(int i) {
        return new CheckRealNameRequest[i];
    }

    public CheckRealNameRequest at(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        AppDescription appDescription = null;
        while (parcel.dataPosition() < bT) {
            AppDescription appDescription2;
            int g;
            String str3;
            int bS = a.bS(parcel);
            String str4;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str4 = str;
                    str = str2;
                    appDescription2 = appDescription;
                    g = a.g(parcel, bS);
                    str3 = str4;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    str4 = str2;
                    appDescription2 = (AppDescription) a.a(parcel, bS, AppDescription.CREATOR);
                    str3 = str;
                    str = str4;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    appDescription2 = appDescription;
                    g = i;
                    str4 = str;
                    str = a.p(parcel, bS);
                    str3 = str4;
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str3 = a.p(parcel, bS);
                    str = str2;
                    appDescription2 = appDescription;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
                    str3 = str;
                    str = str2;
                    appDescription2 = appDescription;
                    g = i;
                    break;
            }
            i = g;
            appDescription = appDescription2;
            str2 = str;
            str = str3;
        }
        if (parcel.dataPosition() == bT) {
            return new CheckRealNameRequest(i, appDescription, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return at(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aX(x0);
    }
}
