package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class i implements Creator<MessageFilter> {
    static void a(MessageFilter messageFilter, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, messageFilter.getVersionCode());
        b.d(parcel, 2, messageFilter.getFilterPrimitives(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fr(x0);
    }

    public MessageFilter fr(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list = a.c(parcel, bS, MessageFilter.a.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new MessageFilter(i, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public MessageFilter[] hI(int i) {
        return new MessageFilter[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hI(x0);
    }
}
