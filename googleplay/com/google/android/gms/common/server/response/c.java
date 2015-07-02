package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.server.response.FieldMappingDictionary.Entry;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class c implements Creator<FieldMappingDictionary> {
    static void a(FieldMappingDictionary fieldMappingDictionary, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, fieldMappingDictionary.getVersionCode());
        b.d(parcel, 2, fieldMappingDictionary.je(), false);
        b.a(parcel, 3, fieldMappingDictionary.getRootClassName(), false);
        b.J(parcel, bU);
    }

    public FieldMappingDictionary cd(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    arrayList = a.c(parcel, bS, Entry.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new FieldMappingDictionary(i, arrayList, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cd(x0);
    }

    public FieldMappingDictionary[] dw(int i) {
        return new FieldMappingDictionary[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dw(x0);
    }
}
