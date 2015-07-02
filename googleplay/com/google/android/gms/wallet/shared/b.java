package com.google.android.gms.wallet.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<BrokerAndRelationships> {
    static void a(BrokerAndRelationships brokerAndRelationships, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, brokerAndRelationships.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, brokerAndRelationships.aVy, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, brokerAndRelationships.aVz, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ir(x0);
    }

    public BrokerAndRelationships ir(Parcel parcel) {
        String[] strArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    strArr = a.B(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new BrokerAndRelationships(i, str, strArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public BrokerAndRelationships[] lq(int i) {
        return new BrokerAndRelationships[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lq(x0);
    }
}
