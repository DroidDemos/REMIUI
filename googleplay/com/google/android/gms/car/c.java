package com.google.android.gms.car;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.car.CarCall.Details;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<Details> {
    static void a(Details details, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, details.handle, i, false);
        b.c(parcel, 1000, details.getVersionCode());
        b.a(parcel, 2, details.callerDisplayName, false);
        b.a(parcel, 3, details.disconnectCause, false);
        b.a(parcel, 4, details.connectTimeMillis);
        b.a(parcel, 5, details.gatewayInfoOriginalAddress, i, false);
        b.a(parcel, 6, details.gatewayInfoGatewayAddress, i, false);
        b.J(parcel, bU);
    }

    public Details[] bZ(int i) {
        return new Details[i];
    }

    public Details bl(Parcel parcel) {
        Uri uri = null;
        int bT = a.bT(parcel);
        int i = 0;
        long j = 0;
        Uri uri2 = null;
        String str = null;
        String str2 = null;
        Uri uri3 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    uri3 = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    uri2 = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    uri = (Uri) a.a(parcel, bS, Uri.CREATOR);
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
            return new Details(i, uri3, str2, str, j, uri2, uri);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bl(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bZ(x0);
    }
}
