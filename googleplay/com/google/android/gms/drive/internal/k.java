package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.wallet.instrumentmanager.R;

public class k implements Creator<CreateFileRequest> {
    static void a(CreateFileRequest createFileRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, createFileRequest.mVersionCode);
        b.a(parcel, 2, createFileRequest.Zm, i, false);
        b.a(parcel, 3, createFileRequest.Zk, i, false);
        b.a(parcel, 4, createFileRequest.Ze, i, false);
        b.a(parcel, 5, createFileRequest.Zl, false);
        b.a(parcel, 6, createFileRequest.Zn);
        b.a(parcel, 7, createFileRequest.Ym, false);
        b.c(parcel, 8, createFileRequest.Zo);
        b.c(parcel, 9, createFileRequest.Zp);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cw(x0);
    }

    public CreateFileRequest cw(Parcel parcel) {
        int i = 0;
        String str = null;
        int bT = a.bT(parcel);
        int i2 = 0;
        boolean z = false;
        Integer num = null;
        Contents contents = null;
        MetadataBundle metadataBundle = null;
        DriveId driveId = null;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    driveId = (DriveId) a.a(parcel, bS, DriveId.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    metadataBundle = (MetadataBundle) a.a(parcel, bS, MetadataBundle.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    contents = (Contents) a.a(parcel, bS, Contents.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    num = a.h(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CreateFileRequest(i3, driveId, metadataBundle, contents, num, z, str, i2, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CreateFileRequest[] ea(int i) {
        return new CreateFileRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ea(x0);
    }
}
