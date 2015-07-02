package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ai implements Creator<VerifyPinRequest> {
    static void a(VerifyPinRequest verifyPinRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, verifyPinRequest.version);
        b.a(parcel, 2, verifyPinRequest.accountName, false);
        b.a(parcel, 3, verifyPinRequest.pin, false);
        b.J(parcel, bU);
    }

    public VerifyPinRequest aN(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
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
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new VerifyPinRequest(i, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public VerifyPinRequest[] br(int i) {
        return new VerifyPinRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aN(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return br(x0);
    }
}
