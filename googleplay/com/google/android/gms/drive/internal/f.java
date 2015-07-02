package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.wallet.instrumentmanager.R;

public class f implements Creator<CloseContentsAndUpdateMetadataRequest> {
    static void a(CloseContentsAndUpdateMetadataRequest closeContentsAndUpdateMetadataRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, closeContentsAndUpdateMetadataRequest.mVersionCode);
        b.a(parcel, 2, closeContentsAndUpdateMetadataRequest.Zc, i, false);
        b.a(parcel, 3, closeContentsAndUpdateMetadataRequest.Zd, i, false);
        b.a(parcel, 4, closeContentsAndUpdateMetadataRequest.Ze, i, false);
        b.a(parcel, 5, closeContentsAndUpdateMetadataRequest.Yn);
        b.a(parcel, 6, closeContentsAndUpdateMetadataRequest.Ym, false);
        b.c(parcel, 7, closeContentsAndUpdateMetadataRequest.Zf);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cs(x0);
    }

    public CloseContentsAndUpdateMetadataRequest cs(Parcel parcel) {
        int i = 0;
        String str = null;
        int bT = a.bT(parcel);
        boolean z = false;
        Contents contents = null;
        MetadataBundle metadataBundle = null;
        DriveId driveId = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
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
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CloseContentsAndUpdateMetadataRequest(i2, driveId, metadataBundle, contents, z, str, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CloseContentsAndUpdateMetadataRequest[] dV(int i) {
        return new CloseContentsAndUpdateMetadataRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dV(x0);
    }
}
