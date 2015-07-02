package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<b> {
    static void a(b bVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, bVar.getDeviceAddress(), false);
        b.c(parcel, 1000, bVar.getVersionCode());
        b.a(parcel, 2, bVar.lJ(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ea(x0);
    }

    public b ea(Parcel parcel) {
        BleDevice bleDevice = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bleDevice = (BleDevice) a.a(parcel, bS, BleDevice.CREATOR);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new b(i, str, bleDevice);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public b[] fO(int i) {
        return new b[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fO(x0);
    }
}
