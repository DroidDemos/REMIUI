package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class b implements Creator<BleDevice> {
    static void a(BleDevice bleDevice, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, bleDevice.getAddress(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, bleDevice.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, bleDevice.getName(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, bleDevice.getSupportedProfiles(), false);
        com.google.android.gms.common.internal.safeparcel.b.d(parcel, 4, bleDevice.getDataTypes(), false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dJ(x0);
    }

    public BleDevice dJ(Parcel parcel) {
        List list = null;
        int bT = a.bT(parcel);
        int i = 0;
        List list2 = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    list2 = a.E(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    list = a.c(parcel, bS, DataType.CREATOR);
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
            return new BleDevice(i, str2, str, list2, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public BleDevice[] fw(int i) {
        return new BleDevice[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fw(x0);
    }
}
