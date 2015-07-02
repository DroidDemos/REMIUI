package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.car.CarPhoneStatus.CarCall;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class r implements Creator<CarPhoneStatus> {
    static void a(CarPhoneStatus carPhoneStatus, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, carPhoneStatus.calls, i, false);
        b.c(parcel, 1000, carPhoneStatus.getVersionCode());
        b.c(parcel, 2, carPhoneStatus.signalStrength);
        b.J(parcel, bU);
    }

    public CarPhoneStatus bx(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        CarCall[] carCallArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int i3;
            CarCall[] carCallArr2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = i2;
                    CarCall[] carCallArr3 = (CarCall[]) a.b(parcel, bS, CarCall.CREATOR);
                    bS = i;
                    carCallArr2 = carCallArr3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bS = a.g(parcel, bS);
                    carCallArr2 = carCallArr;
                    i3 = i2;
                    break;
                case 1000:
                    int i4 = i;
                    carCallArr2 = carCallArr;
                    i3 = a.g(parcel, bS);
                    bS = i4;
                    break;
                default:
                    a.b(parcel, bS);
                    bS = i;
                    carCallArr2 = carCallArr;
                    i3 = i2;
                    break;
            }
            i2 = i3;
            carCallArr = carCallArr2;
            i = bS;
        }
        if (parcel.dataPosition() == bT) {
            return new CarPhoneStatus(i2, carCallArr, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CarPhoneStatus[] cm(int i) {
        return new CarPhoneStatus[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bx(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cm(x0);
    }
}
