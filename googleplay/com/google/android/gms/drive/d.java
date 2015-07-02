package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class d implements Creator<RealtimeDocumentSyncRequest> {
    static void a(RealtimeDocumentSyncRequest realtimeDocumentSyncRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, realtimeDocumentSyncRequest.mVersionCode);
        b.c(parcel, 2, realtimeDocumentSyncRequest.Yy, false);
        b.c(parcel, 3, realtimeDocumentSyncRequest.Yz, false);
        b.J(parcel, bU);
    }

    public RealtimeDocumentSyncRequest cj(Parcel parcel) {
        List list = null;
        int bT = a.bT(parcel);
        int i = 0;
        List list2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list2 = a.E(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    list = a.E(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new RealtimeDocumentSyncRequest(i, list2, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cj(x0);
    }

    public RealtimeDocumentSyncRequest[] dK(int i) {
        return new RealtimeDocumentSyncRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dK(x0);
    }
}
