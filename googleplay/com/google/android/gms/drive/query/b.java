package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.query.internal.FieldWithSortOrder;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class b implements Creator<SortOrder> {
    static void a(SortOrder sortOrder, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, sortOrder.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.d(parcel, 1, sortOrder.acn, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, sortOrder.aco);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dg(x0);
    }

    public SortOrder dg(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        List list = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    list = a.c(parcel, bS, FieldWithSortOrder.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z = a.c(parcel, bS);
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
            return new SortOrder(i, list, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SortOrder[] eO(int i) {
        return new SortOrder[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eO(x0);
    }
}
