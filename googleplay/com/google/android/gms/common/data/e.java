package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<DataHolder> {
    static void a(DataHolder dataHolder, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, dataHolder.is(), false);
        b.c(parcel, 1000, dataHolder.getVersionCode());
        b.a(parcel, 2, dataHolder.it(), i, false);
        b.c(parcel, 3, dataHolder.getStatusCode());
        b.a(parcel, 4, dataHolder.getMetadata(), false);
        b.J(parcel, bU);
    }

    public DataHolder bO(Parcel parcel) {
        int i = 0;
        Bundle bundle = null;
        int bT = a.bT(parcel);
        CursorWindow[] cursorWindowArr = null;
        String[] strArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    strArr = a.B(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    cursorWindowArr = (CursorWindow[]) a.b(parcel, bS, CursorWindow.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bundle = a.r(parcel, bS);
                    break;
                case 1000:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() != bT) {
            throw new a.a("Overread allowed size end=" + bT, parcel);
        }
        DataHolder dataHolder = new DataHolder(i2, strArr, cursorWindowArr, i, bundle);
        dataHolder.ir();
        return dataHolder;
    }

    public DataHolder[] cZ(int i) {
        return new DataHolder[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bO(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cZ(x0);
    }
}
