package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;

public class bf implements Creator<SetResourceParentsRequest> {
    static void a(SetResourceParentsRequest setResourceParentsRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, setResourceParentsRequest.mVersionCode);
        b.a(parcel, 2, setResourceParentsRequest.abe, i, false);
        b.d(parcel, 3, setResourceParentsRequest.abf, false);
        b.J(parcel, bU);
    }

    public SetResourceParentsRequest cY(Parcel parcel) {
        List list = null;
        int bT = a.bT(parcel);
        int i = 0;
        DriveId driveId = null;
        while (parcel.dataPosition() < bT) {
            DriveId driveId2;
            int g;
            ArrayList c;
            int bS = a.bS(parcel);
            List list2;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    List list3 = list;
                    driveId2 = driveId;
                    g = a.g(parcel, bS);
                    list2 = list3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    DriveId driveId3 = (DriveId) a.a(parcel, bS, DriveId.CREATOR);
                    list2 = list;
                    driveId2 = driveId3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    c = a.c(parcel, bS, DriveId.CREATOR);
                    driveId2 = driveId;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
                    c = list;
                    driveId2 = driveId;
                    g = i;
                    break;
            }
            i = g;
            driveId = driveId2;
            Object obj = c;
        }
        if (parcel.dataPosition() == bT) {
            return new SetResourceParentsRequest(i, driveId, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cY(x0);
    }

    public SetResourceParentsRequest[] eG(int i) {
        return new SetResourceParentsRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eG(x0);
    }
}
