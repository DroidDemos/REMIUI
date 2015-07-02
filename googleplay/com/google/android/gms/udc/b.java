package com.google.android.gms.udc;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<SettingState> {
    static void a(SettingState settingState, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, settingState.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, settingState.getSettingId());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, settingState.getSettingValue());
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hU(x0);
    }

    public SettingState hU(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = a.g(parcel, bS);
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
            return new SettingState(i3, i2, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SettingState[] kP(int i) {
        return new SettingState[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kP(x0);
    }
}
