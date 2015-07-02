package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<LabelValue> {
    static void a(LabelValue labelValue, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, labelValue.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, labelValue.label, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, labelValue.value, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iv(x0);
    }

    public LabelValue iv(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
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
            return new LabelValue(i, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public LabelValue[] lv(int i) {
        return new LabelValue[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lv(x0);
    }
}
