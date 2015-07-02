package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class g implements Creator<f> {
    static void a(f fVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, fVar.getVersionCode());
        b.a(parcel, 2, fVar.jg(), false);
        b.a(parcel, 3, fVar.jh(), i, false);
        b.J(parcel, bU);
    }

    public f cf(Parcel parcel) {
        FieldMappingDictionary fieldMappingDictionary = null;
        int bT = a.bT(parcel);
        int i = 0;
        Parcel parcel2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    parcel2 = a.F(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    fieldMappingDictionary = (FieldMappingDictionary) a.a(parcel, bS, FieldMappingDictionary.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new f(i, parcel2, fieldMappingDictionary);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cf(x0);
    }

    public f[] dy(int i) {
        return new f[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dy(x0);
    }
}
