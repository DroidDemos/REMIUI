package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<CustomPropertyKey> {
    static void a(CustomPropertyKey customPropertyKey, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, customPropertyKey.mVersionCode);
        b.a(parcel, 2, customPropertyKey.TN, false);
        b.c(parcel, 3, customPropertyKey.mVisibility);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return db(x0);
    }

    public CustomPropertyKey db(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CustomPropertyKey(i2, str, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CustomPropertyKey[] eJ(int i) {
        return new CustomPropertyKey[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eJ(x0);
    }
}
