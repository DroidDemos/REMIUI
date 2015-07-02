package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.server.response.FieldMappingDictionary.Entry;
import com.google.android.gms.common.server.response.FieldMappingDictionary.FieldMapPair;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class d implements Creator<Entry> {
    static void a(Entry entry, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, entry.versionCode);
        b.a(parcel, 2, entry.className, false);
        b.d(parcel, 3, entry.XB, false);
        b.J(parcel, bU);
    }

    public Entry ce(Parcel parcel) {
        ArrayList arrayList = null;
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
                    arrayList = a.c(parcel, bS, FieldMapPair.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Entry(i, str, arrayList);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ce(x0);
    }

    public Entry[] dx(int i) {
        return new Entry[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dx(x0);
    }
}
