package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.location.LocationRequest;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class pg implements Creator<pf> {
    static void a(pf pfVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, pfVar.ail, i, false);
        b.c(parcel, 1000, pfVar.getVersionCode());
        b.a(parcel, 2, pfVar.avP);
        b.a(parcel, 3, pfVar.avQ);
        b.a(parcel, 4, pfVar.avR);
        b.d(parcel, 5, pfVar.avS, false);
        b.a(parcel, 6, pfVar.mTag, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fI(x0);
    }

    public pf fI(Parcel parcel) {
        String str = null;
        boolean z = true;
        boolean z2 = false;
        int bT = a.bT(parcel);
        List list = pf.avO;
        boolean z3 = true;
        LocationRequest locationRequest = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    locationRequest = (LocationRequest) a.a(parcel, bS, LocationRequest.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z3 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    list = a.c(parcel, bS, ox.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str = a.p(parcel, bS);
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
            return new pf(i, locationRequest, z2, z3, z, list, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public pf[] hZ(int i) {
        return new pf[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hZ(x0);
    }
}
