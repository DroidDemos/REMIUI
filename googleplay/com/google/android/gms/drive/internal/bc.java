package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.wallet.instrumentmanager.R;

public class bc implements Creator<RemoveEventListenerRequest> {
    static void a(RemoveEventListenerRequest removeEventListenerRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, removeEventListenerRequest.mVersionCode);
        b.a(parcel, 2, removeEventListenerRequest.Yb, i, false);
        b.c(parcel, 3, removeEventListenerRequest.YZ);
        b.J(parcel, bU);
    }

    public RemoveEventListenerRequest cV(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        DriveId driveId = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            DriveId driveId2;
            int g;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    int i3 = i;
                    driveId2 = driveId;
                    g = a.g(parcel, bS);
                    bS = i3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i2;
                    DriveId driveId3 = (DriveId) a.a(parcel, bS, DriveId.CREATOR);
                    bS = i;
                    driveId2 = driveId3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bS = a.g(parcel, bS);
                    driveId2 = driveId;
                    g = i2;
                    break;
                default:
                    a.b(parcel, bS);
                    bS = i;
                    driveId2 = driveId;
                    g = i2;
                    break;
            }
            i2 = g;
            driveId = driveId2;
            i = bS;
        }
        if (parcel.dataPosition() == bT) {
            return new RemoveEventListenerRequest(i2, driveId, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cV(x0);
    }

    public RemoveEventListenerRequest[] eD(int i) {
        return new RemoveEventListenerRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eD(x0);
    }
}
