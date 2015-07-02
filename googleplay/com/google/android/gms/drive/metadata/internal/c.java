package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<CustomProperty> {
    static void a(CustomProperty customProperty, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, customProperty.mVersionCode);
        b.a(parcel, 2, customProperty.abo, i, false);
        b.a(parcel, 3, customProperty.mValue, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dd(x0);
    }

    public CustomProperty dd(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        CustomPropertyKey customPropertyKey = null;
        while (parcel.dataPosition() < bT) {
            CustomPropertyKey customPropertyKey2;
            int g;
            String str2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    String str3 = str;
                    customPropertyKey2 = customPropertyKey;
                    g = a.g(parcel, bS);
                    str2 = str3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    CustomPropertyKey customPropertyKey3 = (CustomPropertyKey) a.a(parcel, bS, CustomPropertyKey.CREATOR);
                    str2 = str;
                    customPropertyKey2 = customPropertyKey3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    customPropertyKey2 = customPropertyKey;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
                    str2 = str;
                    customPropertyKey2 = customPropertyKey;
                    g = i;
                    break;
            }
            i = g;
            customPropertyKey = customPropertyKey2;
            str = str2;
        }
        if (parcel.dataPosition() == bT) {
            return new CustomProperty(i, customPropertyKey, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CustomProperty[] eL(int i) {
        return new CustomProperty[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eL(x0);
    }
}
