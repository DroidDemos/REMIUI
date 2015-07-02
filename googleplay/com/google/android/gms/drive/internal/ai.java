package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.wallet.instrumentmanager.R;

public class ai implements Creator<ListParentsRequest> {
    static void a(ListParentsRequest listParentsRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, listParentsRequest.mVersionCode);
        b.a(parcel, 2, listParentsRequest.aaO, i, false);
        b.J(parcel, bU);
    }

    public ListParentsRequest cD(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        DriveId driveId = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    driveId = (DriveId) a.a(parcel, bS, DriveId.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ListParentsRequest(i, driveId);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cD(x0);
    }

    public ListParentsRequest[] el(int i) {
        return new ListParentsRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return el(x0);
    }
}
