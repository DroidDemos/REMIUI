package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.wallet.instrumentmanager.R;

public class l implements Creator<CreateFolderRequest> {
    static void a(CreateFolderRequest createFolderRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, createFolderRequest.mVersionCode);
        b.a(parcel, 2, createFolderRequest.Zm, i, false);
        b.a(parcel, 3, createFolderRequest.Zk, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cx(x0);
    }

    public CreateFolderRequest cx(Parcel parcel) {
        MetadataBundle metadataBundle = null;
        int bT = a.bT(parcel);
        int i = 0;
        DriveId driveId = null;
        while (parcel.dataPosition() < bT) {
            DriveId driveId2;
            int g;
            MetadataBundle metadataBundle2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    MetadataBundle metadataBundle3 = metadataBundle;
                    driveId2 = driveId;
                    g = a.g(parcel, bS);
                    metadataBundle2 = metadataBundle3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    DriveId driveId3 = (DriveId) a.a(parcel, bS, DriveId.CREATOR);
                    metadataBundle2 = metadataBundle;
                    driveId2 = driveId3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    metadataBundle2 = (MetadataBundle) a.a(parcel, bS, MetadataBundle.CREATOR);
                    driveId2 = driveId;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
                    metadataBundle2 = metadataBundle;
                    driveId2 = driveId;
                    g = i;
                    break;
            }
            i = g;
            driveId = driveId2;
            metadataBundle = metadataBundle2;
        }
        if (parcel.dataPosition() == bT) {
            return new CreateFolderRequest(i, driveId, metadataBundle);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CreateFolderRequest[] eb(int i) {
        return new CreateFolderRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eb(x0);
    }
}
