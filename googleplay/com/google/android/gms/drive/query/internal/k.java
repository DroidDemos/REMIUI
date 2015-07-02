package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class k implements Creator<NotFilter> {
    static void a(NotFilter notFilter, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, notFilter.mVersionCode);
        b.a(parcel, 1, notFilter.acD, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dp(x0);
    }

    public NotFilter dp(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        FilterHolder filterHolder = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    filterHolder = (FilterHolder) a.a(parcel, bS, FilterHolder.CREATOR);
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
            return new NotFilter(i, filterHolder);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public NotFilter[] eX(int i) {
        return new NotFilter[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eX(x0);
    }
}
