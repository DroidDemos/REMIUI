package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.wallet.instrumentmanager.R;

public class ba implements Creator<OpenFileIntentSenderRequest> {
    static void a(OpenFileIntentSenderRequest openFileIntentSenderRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, openFileIntentSenderRequest.mVersionCode);
        b.a(parcel, 2, openFileIntentSenderRequest.Yv, false);
        b.a(parcel, 3, openFileIntentSenderRequest.Yw, false);
        b.a(parcel, 4, openFileIntentSenderRequest.Yx, i, false);
        b.J(parcel, bU);
    }

    public OpenFileIntentSenderRequest cT(Parcel parcel) {
        DriveId driveId = null;
        int bT = a.bT(parcel);
        int i = 0;
        String[] strArr = null;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    strArr = a.B(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    driveId = (DriveId) a.a(parcel, bS, DriveId.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new OpenFileIntentSenderRequest(i, str, strArr, driveId);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cT(x0);
    }

    public OpenFileIntentSenderRequest[] eB(int i) {
        return new OpenFileIntentSenderRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eB(x0);
    }
}
