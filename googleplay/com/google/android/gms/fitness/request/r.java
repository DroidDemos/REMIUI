package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;

public class r implements Creator<SessionInsertRequest> {
    static void a(SessionInsertRequest sessionInsertRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, sessionInsertRequest.getSession(), i, false);
        b.c(parcel, 1000, sessionInsertRequest.getVersionCode());
        b.d(parcel, 2, sessionInsertRequest.getDataSets(), false);
        b.d(parcel, 3, sessionInsertRequest.getAggregateDataPoints(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ek(x0);
    }

    public SessionInsertRequest ek(Parcel parcel) {
        List list = null;
        int bT = a.bT(parcel);
        int i = 0;
        List list2 = null;
        Session session = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            Session session2;
            Object c;
            ArrayList c2;
            int bS = a.bS(parcel);
            List list3;
            List list4;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    list3 = list2;
                    session2 = (Session) a.a(parcel, bS, Session.CREATOR);
                    list4 = list;
                    list = list3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    session2 = session;
                    i2 = i;
                    list3 = list;
                    c = a.c(parcel, bS, DataSet.CREATOR);
                    list4 = list3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    c2 = a.c(parcel, bS, DataPoint.CREATOR);
                    list = list2;
                    session2 = session;
                    i2 = i;
                    break;
                case 1000:
                    list3 = list;
                    list = list2;
                    session2 = session;
                    i2 = a.g(parcel, bS);
                    list4 = list3;
                    break;
                default:
                    a.b(parcel, bS);
                    c2 = list;
                    list = list2;
                    session2 = session;
                    i2 = i;
                    break;
            }
            i = i2;
            session = session2;
            list2 = list;
            c = c2;
        }
        if (parcel.dataPosition() == bT) {
            return new SessionInsertRequest(i, session, list2, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SessionInsertRequest[] fZ(int i) {
        return new SessionInsertRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fZ(x0);
    }
}
