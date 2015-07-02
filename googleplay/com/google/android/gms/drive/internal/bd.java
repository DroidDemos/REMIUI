package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DrivePreferences;
import com.google.android.wallet.instrumentmanager.R;

public class bd implements Creator<SetDrivePreferencesRequest> {
    static void a(SetDrivePreferencesRequest setDrivePreferencesRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, setDrivePreferencesRequest.mVersionCode);
        b.a(parcel, 2, setDrivePreferencesRequest.aaE, i, false);
        b.J(parcel, bU);
    }

    public SetDrivePreferencesRequest cW(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        DrivePreferences drivePreferences = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    drivePreferences = (DrivePreferences) a.a(parcel, bS, DrivePreferences.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new SetDrivePreferencesRequest(i, drivePreferences);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cW(x0);
    }

    public SetDrivePreferencesRequest[] eE(int i) {
        return new SetDrivePreferencesRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eE(x0);
    }
}
