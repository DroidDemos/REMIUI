package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class t implements Creator<CarSensorEvent> {
    static void a(CarSensorEvent carSensorEvent, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, carSensorEvent.sensorType);
        b.c(parcel, 1000, carSensorEvent.getVersionCode());
        b.a(parcel, 2, carSensorEvent.timeStampNs);
        b.a(parcel, 3, carSensorEvent.floatValues, false);
        b.a(parcel, 4, carSensorEvent.byteValues, false);
        b.J(parcel, bU);
    }

    public CarSensorEvent by(Parcel parcel) {
        byte[] bArr = null;
        int i = 0;
        int bT = a.bT(parcel);
        long j = 0;
        float[] fArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    fArr = a.y(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bArr = a.s(parcel, bS);
                    break;
                case 1000:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CarSensorEvent(i2, i, j, fArr, bArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CarSensorEvent[] cn(int i) {
        return new CarSensorEvent[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return by(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cn(x0);
    }
}
