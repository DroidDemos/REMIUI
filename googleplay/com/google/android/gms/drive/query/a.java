package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class a implements Creator<Query> {
    static void a(Query query, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, query.mVersionCode);
        b.a(parcel, 1, query.acg, i, false);
        b.a(parcel, 3, query.ach, false);
        b.a(parcel, 4, query.aci, i, false);
        b.c(parcel, 5, query.acj, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return df(x0);
    }

    public Query df(Parcel parcel) {
        List list = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        SortOrder sortOrder = null;
        String str = null;
        LogicalFilter logicalFilter = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    logicalFilter = (LogicalFilter) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, LogicalFilter.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    sortOrder = (SortOrder) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, SortOrder.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    list = com.google.android.gms.common.internal.safeparcel.a.E(parcel, bS);
                    break;
                case 1000:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Query(i, logicalFilter, str, sortOrder, list);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public Query[] eN(int i) {
        return new Query[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eN(x0);
    }
}
