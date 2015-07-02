package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class au implements Creator<OnLoadRealtimeResponse> {
    static void a(OnLoadRealtimeResponse onLoadRealtimeResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, onLoadRealtimeResponse.mVersionCode);
        b.a(parcel, 2, onLoadRealtimeResponse.wf);
        b.J(parcel, bU);
    }

    public OnLoadRealtimeResponse cO(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new OnLoadRealtimeResponse(i, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cO(x0);
    }

    public OnLoadRealtimeResponse[] ew(int i) {
        return new OnLoadRealtimeResponse[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ew(x0);
    }
}
