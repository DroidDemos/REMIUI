package com.google.android.gms.fitness.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<FitnessSensorServiceRequest> {
    static void a(FitnessSensorServiceRequest fitnessSensorServiceRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, fitnessSensorServiceRequest.getDataSource(), i, false);
        b.c(parcel, 1000, fitnessSensorServiceRequest.getVersionCode());
        b.a(parcel, 2, fitnessSensorServiceRequest.lZ(), false);
        b.a(parcel, 3, fitnessSensorServiceRequest.getSamplingRateMicros());
        b.a(parcel, 4, fitnessSensorServiceRequest.mm());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eC(x0);
    }

    public FitnessSensorServiceRequest eC(Parcel parcel) {
        long j = 0;
        IBinder iBinder = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        long j2 = 0;
        DataSource dataSource = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    dataSource = (DataSource) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, DataSource.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    iBinder = com.google.android.gms.common.internal.safeparcel.a.q(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    j = com.google.android.gms.common.internal.safeparcel.a.i(parcel, bS);
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
            return new FitnessSensorServiceRequest(i, dataSource, iBinder, j2, j);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public FitnessSensorServiceRequest[] gr(int i) {
        return new FitnessSensorServiceRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gr(x0);
    }
}
