package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class po implements Creator<pn> {
    static void a(pn pnVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, pnVar.name, false);
        b.c(parcel, 1000, pnVar.versionCode);
        b.a(parcel, 2, pnVar.axk, false);
        b.a(parcel, 3, pnVar.axl, false);
        b.a(parcel, 4, pnVar.axm, false);
        b.c(parcel, 5, pnVar.axn, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fW(x0);
    }

    public pn fW(Parcel parcel) {
        List list = null;
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
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    list = a.E(parcel, bS);
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
            return new pn(i, str4, str3, str2, str, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public pn[] ir(int i) {
        return new pn[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ir(x0);
    }
}
