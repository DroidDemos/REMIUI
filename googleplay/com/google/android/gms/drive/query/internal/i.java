package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;

public class i implements Creator<LogicalFilter> {
    static void a(LogicalFilter logicalFilter, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, logicalFilter.mVersionCode);
        b.a(parcel, 1, logicalFilter.acp, i, false);
        b.d(parcel, 2, logicalFilter.acC, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dn(x0);
    }

    public LogicalFilter dn(Parcel parcel) {
        List list = null;
        int bT = a.bT(parcel);
        int i = 0;
        Operator operator = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            Operator operator2;
            ArrayList c;
            int bS = a.bS(parcel);
            List list2;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    Operator operator3 = (Operator) a.a(parcel, bS, Operator.CREATOR);
                    list2 = list;
                    operator2 = operator3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    c = a.c(parcel, bS, FilterHolder.CREATOR);
                    operator2 = operator;
                    i2 = i;
                    break;
                case 1000:
                    List list3 = list;
                    operator2 = operator;
                    i2 = a.g(parcel, bS);
                    list2 = list3;
                    break;
                default:
                    a.b(parcel, bS);
                    c = list;
                    operator2 = operator;
                    i2 = i;
                    break;
            }
            i = i2;
            operator = operator2;
            Object obj = c;
        }
        if (parcel.dataPosition() == bT) {
            return new LogicalFilter(i, operator, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public LogicalFilter[] eV(int i) {
        return new LogicalFilter[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eV(x0);
    }
}
