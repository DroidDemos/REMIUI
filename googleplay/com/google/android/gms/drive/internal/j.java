package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.wallet.instrumentmanager.R;

public class j implements Creator<CreateFileIntentSenderRequest> {
    static void a(CreateFileIntentSenderRequest createFileIntentSenderRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, createFileIntentSenderRequest.mVersionCode);
        b.a(parcel, 2, createFileIntentSenderRequest.Zk, i, false);
        b.c(parcel, 3, createFileIntentSenderRequest.ve);
        b.a(parcel, 4, createFileIntentSenderRequest.Yv, false);
        b.a(parcel, 5, createFileIntentSenderRequest.Yx, i, false);
        b.a(parcel, 6, createFileIntentSenderRequest.Zl, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cv(x0);
    }

    public CreateFileIntentSenderRequest cv(Parcel parcel) {
        int i = 0;
        Integer num = null;
        int bT = a.bT(parcel);
        DriveId driveId = null;
        String str = null;
        MetadataBundle metadataBundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    metadataBundle = (MetadataBundle) a.a(parcel, bS, MetadataBundle.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    driveId = (DriveId) a.a(parcel, bS, DriveId.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    num = a.h(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CreateFileIntentSenderRequest(i2, metadataBundle, i, str, driveId, num);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CreateFileIntentSenderRequest[] dZ(int i) {
        return new CreateFileIntentSenderRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dZ(x0);
    }
}
