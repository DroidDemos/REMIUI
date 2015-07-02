package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class ab implements Creator<StartBleScanRequest> {
    static void a(StartBleScanRequest startBleScanRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, startBleScanRequest.getDataTypes(), false);
        b.c(parcel, 1000, startBleScanRequest.getVersionCode());
        b.a(parcel, 2, startBleScanRequest.mf(), false);
        b.c(parcel, 3, startBleScanRequest.getTimeoutSecs());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eq(x0);
    }

    public StartBleScanRequest eq(Parcel parcel) {
        IBinder iBinder = null;
        int i = 0;
        int bT = a.bT(parcel);
        List list = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    list = a.c(parcel, bS, DataType.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    iBinder = a.q(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
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
            return new StartBleScanRequest(i2, list, iBinder, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public StartBleScanRequest[] gf(int i) {
        return new StartBleScanRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gf(x0);
    }
}
