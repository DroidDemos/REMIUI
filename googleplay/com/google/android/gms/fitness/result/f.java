package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.p;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class f implements Creator<SessionReadResult> {
    static void a(SessionReadResult sessionReadResult, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, sessionReadResult.getSessions(), false);
        b.c(parcel, 1000, sessionReadResult.getVersionCode());
        b.d(parcel, 2, sessionReadResult.mk(), false);
        b.a(parcel, 3, sessionReadResult.getStatus(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eA(x0);
    }

    public SessionReadResult eA(Parcel parcel) {
        Status status = null;
        int bT = a.bT(parcel);
        int i = 0;
        List list = null;
        List list2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    list2 = a.c(parcel, bS, Session.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list = a.c(parcel, bS, p.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    status = (Status) a.a(parcel, bS, Status.CREATOR);
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
            return new SessionReadResult(i, list2, list, status);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SessionReadResult[] gp(int i) {
        return new SessionReadResult[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gp(x0);
    }
}
