package com.google.android.gms.audiomodem;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<Encoding> {
    static void a(Encoding encoding, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, encoding.getVersionCode());
        b.c(parcel, 2, encoding.getType());
        b.a(parcel, 3, encoding.getDsssEncoding(), i, false);
        b.a(parcel, 4, encoding.getDtmfEncoding(), i, false);
        b.J(parcel, bU);
    }

    public Encoding W(Parcel parcel) {
        DtmfEncoding dtmfEncoding = null;
        int i = 0;
        int bT = a.bT(parcel);
        DsssEncoding dsssEncoding = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            DsssEncoding dsssEncoding2;
            int i3;
            DtmfEncoding dtmfEncoding2;
            int bS = a.bS(parcel);
            DtmfEncoding dtmfEncoding3;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    dtmfEncoding3 = dtmfEncoding;
                    dsssEncoding2 = dsssEncoding;
                    i3 = i;
                    i = a.g(parcel, bS);
                    dtmfEncoding2 = dtmfEncoding3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = i2;
                    DsssEncoding dsssEncoding3 = dsssEncoding;
                    i3 = a.g(parcel, bS);
                    dtmfEncoding2 = dtmfEncoding;
                    dsssEncoding2 = dsssEncoding3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i3 = i;
                    i = i2;
                    dtmfEncoding3 = dtmfEncoding;
                    dsssEncoding2 = (DsssEncoding) a.a(parcel, bS, DsssEncoding.CREATOR);
                    dtmfEncoding2 = dtmfEncoding3;
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    dtmfEncoding2 = (DtmfEncoding) a.a(parcel, bS, DtmfEncoding.CREATOR);
                    dsssEncoding2 = dsssEncoding;
                    i3 = i;
                    i = i2;
                    break;
                default:
                    a.b(parcel, bS);
                    dtmfEncoding2 = dtmfEncoding;
                    dsssEncoding2 = dsssEncoding;
                    i3 = i;
                    i = i2;
                    break;
            }
            i2 = i;
            i = i3;
            dsssEncoding = dsssEncoding2;
            dtmfEncoding = dtmfEncoding2;
        }
        if (parcel.dataPosition() == bT) {
            return new Encoding(i2, i, dsssEncoding, dtmfEncoding);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Encoding[] aA(int i) {
        return new Encoding[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return W(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aA(x0);
    }
}
