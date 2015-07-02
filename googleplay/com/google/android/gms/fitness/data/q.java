package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class q implements Creator<p> {
    static void a(p pVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, pVar.getSession(), i, false);
        b.c(parcel, 1000, pVar.mVersionCode);
        b.a(parcel, 2, pVar.lC(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dV(x0);
    }

    public p dV(Parcel parcel) {
        DataSet dataSet = null;
        int bT = a.bT(parcel);
        int i = 0;
        Session session = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            DataSet dataSet2;
            Session session2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    Session session3 = (Session) a.a(parcel, bS, Session.CREATOR);
                    dataSet2 = dataSet;
                    session2 = session3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    dataSet2 = (DataSet) a.a(parcel, bS, DataSet.CREATOR);
                    session2 = session;
                    i2 = i;
                    break;
                case 1000:
                    DataSet dataSet3 = dataSet;
                    session2 = session;
                    i2 = a.g(parcel, bS);
                    dataSet2 = dataSet3;
                    break;
                default:
                    a.b(parcel, bS);
                    dataSet2 = dataSet;
                    session2 = session;
                    i2 = i;
                    break;
            }
            i = i2;
            session = session2;
            dataSet = dataSet2;
        }
        if (parcel.dataPosition() == bT) {
            return new p(i, session, dataSet);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public p[] fJ(int i) {
        return new p[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fJ(x0);
    }
}
