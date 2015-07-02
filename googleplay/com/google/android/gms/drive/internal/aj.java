package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.wallet.instrumentmanager.R;

public class aj implements Creator<LoadRealtimeRequest> {
    static void a(LoadRealtimeRequest loadRealtimeRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, loadRealtimeRequest.mVersionCode);
        b.a(parcel, 2, loadRealtimeRequest.Yb, i, false);
        b.a(parcel, 3, loadRealtimeRequest.aaP);
        b.J(parcel, bU);
    }

    public LoadRealtimeRequest cE(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        DriveId driveId = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            DriveId driveId2;
            int g;
            boolean z2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    boolean z3 = z;
                    driveId2 = driveId;
                    g = a.g(parcel, bS);
                    z2 = z3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    DriveId driveId3 = (DriveId) a.a(parcel, bS, DriveId.CREATOR);
                    z2 = z;
                    driveId2 = driveId3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z2 = a.c(parcel, bS);
                    driveId2 = driveId;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
                    z2 = z;
                    driveId2 = driveId;
                    g = i;
                    break;
            }
            i = g;
            driveId = driveId2;
            z = z2;
        }
        if (parcel.dataPosition() == bT) {
            return new LoadRealtimeRequest(i, driveId, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cE(x0);
    }

    public LoadRealtimeRequest[] em(int i) {
        return new LoadRealtimeRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return em(x0);
    }
}
