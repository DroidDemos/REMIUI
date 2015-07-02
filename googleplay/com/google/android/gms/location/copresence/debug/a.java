package com.google.android.gms.location.copresence.debug;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<CopresenceDebugPokeRequest> {
    static void a(CopresenceDebugPokeRequest copresenceDebugPokeRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, copresenceDebugPokeRequest.getCommand());
        b.c(parcel, 1000, copresenceDebugPokeRequest.getVersionCode());
        b.a(parcel, 4, copresenceDebugPokeRequest.getProtoData(), false);
        b.a(parcel, 5, copresenceDebugPokeRequest.pg(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fw(x0);
    }

    public CopresenceDebugPokeRequest fw(Parcel parcel) {
        IBinder iBinder = null;
        int i = 0;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        byte[] bArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bArr = com.google.android.gms.common.internal.safeparcel.a.s(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    iBinder = com.google.android.gms.common.internal.safeparcel.a.q(parcel, bS);
                    break;
                case 1000:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CopresenceDebugPokeRequest(i2, i, bArr, iBinder);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public CopresenceDebugPokeRequest[] hN(int i) {
        return new CopresenceDebugPokeRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hN(x0);
    }
}
