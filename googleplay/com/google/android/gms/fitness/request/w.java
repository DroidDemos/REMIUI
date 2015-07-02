package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.Session;
import com.google.android.wallet.instrumentmanager.R;

public class w implements Creator<v> {
    static void a(v vVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, vVar.getSession(), i, false);
        b.c(parcel, 1000, vVar.getVersionCode());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return en(x0);
    }

    public v en(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        Session session = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    session = (Session) a.a(parcel, bS, Session.CREATOR);
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
            return new v(i, session);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public v[] gc(int i) {
        return new v[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gc(x0);
    }
}
