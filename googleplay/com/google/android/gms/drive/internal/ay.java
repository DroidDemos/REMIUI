package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.wallet.instrumentmanager.R;

public class ay implements Creator<OpenContentsRequest> {
    static void a(OpenContentsRequest openContentsRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, openContentsRequest.mVersionCode);
        b.a(parcel, 2, openContentsRequest.Zc, i, false);
        b.c(parcel, 3, openContentsRequest.Ya);
        b.c(parcel, 4, openContentsRequest.abb);
        b.J(parcel, bU);
    }

    public OpenContentsRequest cS(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        DriveId driveId = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            DriveId driveId2;
            int g;
            int bS = a.bS(parcel);
            int i4;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i4 = i;
                    i = i2;
                    driveId2 = driveId;
                    g = a.g(parcel, bS);
                    bS = i4;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i3;
                    i4 = i2;
                    driveId2 = (DriveId) a.a(parcel, bS, DriveId.CREATOR);
                    bS = i;
                    i = i4;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    driveId2 = driveId;
                    g = i3;
                    i4 = i;
                    i = a.g(parcel, bS);
                    bS = i4;
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bS = a.g(parcel, bS);
                    i = i2;
                    driveId2 = driveId;
                    g = i3;
                    break;
                default:
                    a.b(parcel, bS);
                    bS = i;
                    i = i2;
                    driveId2 = driveId;
                    g = i3;
                    break;
            }
            i3 = g;
            driveId = driveId2;
            i2 = i;
            i = bS;
        }
        if (parcel.dataPosition() == bT) {
            return new OpenContentsRequest(i3, driveId, i2, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cS(x0);
    }

    public OpenContentsRequest[] eA(int i) {
        return new OpenContentsRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eA(x0);
    }
}
