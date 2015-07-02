package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class u implements Creator<GplusInfoRequest> {
    static void a(GplusInfoRequest gplusInfoRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, gplusInfoRequest.version);
        b.a(parcel, 2, gplusInfoRequest.accountName, false);
        b.a(parcel, 3, gplusInfoRequest.Gu, i, false);
        b.J(parcel, bU);
    }

    public GplusInfoRequest aA(Parcel parcel) {
        CaptchaSolution captchaSolution = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    captchaSolution = (CaptchaSolution) a.a(parcel, bS, CaptchaSolution.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new GplusInfoRequest(i, str, captchaSolution);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public GplusInfoRequest[] be(int i) {
        return new GplusInfoRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aA(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return be(x0);
    }
}
