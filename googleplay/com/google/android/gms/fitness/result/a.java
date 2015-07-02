package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class a implements Creator<BleDevicesResult> {
    static void a(BleDevicesResult bleDevicesResult, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, bleDevicesResult.getClaimedBleDevices(), false);
        b.c(parcel, 1000, bleDevicesResult.getVersionCode());
        b.a(parcel, 2, bleDevicesResult.getStatus(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ev(x0);
    }

    public BleDevicesResult ev(Parcel parcel) {
        Status status = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    list = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS, BleDevice.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    status = (Status) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, Status.CREATOR);
                    break;
                case 1000:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new BleDevicesResult(i, list, status);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public BleDevicesResult[] gk(int i) {
        return new BleDevicesResult[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gk(x0);
    }
}
