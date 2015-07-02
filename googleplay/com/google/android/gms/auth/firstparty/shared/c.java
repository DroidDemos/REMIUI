package com.google.android.gms.auth.firstparty.shared;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<CaptchaChallenge> {
    static void a(CaptchaChallenge captchaChallenge, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, captchaChallenge.version);
        b.a(parcel, 2, captchaChallenge.Gv, false);
        b.a(parcel, 3, captchaChallenge.GP, false);
        b.a(parcel, 4, captchaChallenge.Iu, i, false);
        b.J(parcel, bU);
    }

    public CaptchaChallenge[] bF(int i) {
        return new CaptchaChallenge[i];
    }

    public CaptchaChallenge bb(Parcel parcel) {
        Bitmap bitmap = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
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
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bitmap = (Bitmap) a.a(parcel, bS, Bitmap.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CaptchaChallenge(i, str2, str, bitmap);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bb(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bF(x0);
    }
}
