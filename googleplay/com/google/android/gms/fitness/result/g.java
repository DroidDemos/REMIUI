package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.Session;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;

public class g implements Creator<SessionStopResult> {
    static void a(SessionStopResult sessionStopResult, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, sessionStopResult.getVersionCode());
        b.a(parcel, 2, sessionStopResult.getStatus(), i, false);
        b.d(parcel, 3, sessionStopResult.getSessions(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eB(x0);
    }

    public SessionStopResult eB(Parcel parcel) {
        List list = null;
        int bT = a.bT(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            Status status2;
            ArrayList c;
            int bS = a.bS(parcel);
            List list2;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = i;
                    Status status3 = (Status) a.a(parcel, bS, Status.CREATOR);
                    list2 = list;
                    status2 = status3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    c = a.c(parcel, bS, Session.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    List list3 = list;
                    status2 = status;
                    i2 = a.g(parcel, bS);
                    list2 = list3;
                    break;
                default:
                    a.b(parcel, bS);
                    c = list;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            Object obj = c;
        }
        if (parcel.dataPosition() == bT) {
            return new SessionStopResult(i, status, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SessionStopResult[] gq(int i) {
        return new SessionStopResult[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gq(x0);
    }
}
