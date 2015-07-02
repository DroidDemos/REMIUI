package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.wallet.instrumentmanager.R;

public class at implements Creator<OnMetadataResponse> {
    static void a(OnMetadataResponse onMetadataResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, onMetadataResponse.mVersionCode);
        b.a(parcel, 2, onMetadataResponse.Zk, i, false);
        b.J(parcel, bU);
    }

    public OnMetadataResponse cN(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    metadataBundle = (MetadataBundle) a.a(parcel, bS, MetadataBundle.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new OnMetadataResponse(i, metadataBundle);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cN(x0);
    }

    public OnMetadataResponse[] ev(int i) {
        return new OnMetadataResponse[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ev(x0);
    }
}
