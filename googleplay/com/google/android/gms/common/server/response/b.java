package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FieldMappingDictionary.FieldMapPair;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<FieldMapPair> {
    static void a(FieldMapPair fieldMapPair, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, fieldMapPair.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, fieldMapPair.key, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, fieldMapPair.XC, i, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public FieldMapPair cc(Parcel parcel) {
        Field field = null;
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
                    field = (Field) a.a(parcel, bS, Field.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new FieldMapPair(i, str, field);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cc(x0);
    }

    public FieldMapPair[] dv(int i) {
        return new FieldMapPair[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dv(x0);
    }
}
