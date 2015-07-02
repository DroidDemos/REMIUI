package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<ChangeEvent> {
    static void a(ChangeEvent changeEvent, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, changeEvent.mVersionCode);
        b.a(parcel, 2, changeEvent.Yb, i, false);
        b.c(parcel, 3, changeEvent.YK);
        b.J(parcel, bU);
    }

    public ChangeEvent cm(Parcel parcel) {
        int i = 0;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        DriveId driveId = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            DriveId driveId2;
            int g;
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    int i3 = i;
                    driveId2 = driveId;
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    bS = i3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i2;
                    DriveId driveId3 = (DriveId) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, DriveId.CREATOR);
                    bS = i;
                    driveId2 = driveId3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bS = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    driveId2 = driveId;
                    g = i2;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
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
            return new ChangeEvent(i2, driveId, i);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cm(x0);
    }

    public ChangeEvent[] dN(int i) {
        return new ChangeEvent[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dN(x0);
    }
}
