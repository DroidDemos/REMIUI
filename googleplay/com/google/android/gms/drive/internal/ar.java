package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ar implements Creator<OnListEntriesResponse> {
    static void a(OnListEntriesResponse onListEntriesResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, onListEntriesResponse.mVersionCode);
        b.a(parcel, 2, onListEntriesResponse.aaZ, i, false);
        b.a(parcel, 3, onListEntriesResponse.Zy);
        b.J(parcel, bU);
    }

    public OnListEntriesResponse cL(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        DataHolder dataHolder = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            DataHolder dataHolder2;
            int g;
            boolean z2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    boolean z3 = z;
                    dataHolder2 = dataHolder;
                    g = a.g(parcel, bS);
                    z2 = z3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    DataHolder dataHolder3 = (DataHolder) a.a(parcel, bS, DataHolder.CREATOR);
                    z2 = z;
                    dataHolder2 = dataHolder3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z2 = a.c(parcel, bS);
                    dataHolder2 = dataHolder;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
                    z2 = z;
                    dataHolder2 = dataHolder;
                    g = i;
                    break;
            }
            i = g;
            dataHolder = dataHolder2;
            z = z2;
        }
        if (parcel.dataPosition() == bT) {
            return new OnListEntriesResponse(i, dataHolder, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cL(x0);
    }

    public OnListEntriesResponse[] et(int i) {
        return new OnListEntriesResponse[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return et(x0);
    }
}
