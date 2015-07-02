package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.wallet.instrumentmanager.R;

public class n implements Creator<DeleteResourceRequest> {
    static void a(DeleteResourceRequest deleteResourceRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, deleteResourceRequest.mVersionCode);
        b.a(parcel, 2, deleteResourceRequest.Zc, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cy(x0);
    }

    public DeleteResourceRequest cy(Parcel parcel) {
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
            return new DeleteResourceRequest(i, driveId);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public DeleteResourceRequest[] ec(int i) {
        return new DeleteResourceRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ec(x0);
    }
}
