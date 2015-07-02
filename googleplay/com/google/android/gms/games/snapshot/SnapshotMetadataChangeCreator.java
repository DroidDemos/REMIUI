package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class SnapshotMetadataChangeCreator implements Creator<SnapshotMetadataChangeEntity> {
    static void a(SnapshotMetadataChangeEntity snapshotMetadataChangeEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, snapshotMetadataChangeEntity.getDescription(), false);
        b.c(parcel, 1000, snapshotMetadataChangeEntity.getVersionCode());
        b.a(parcel, 2, snapshotMetadataChangeEntity.getPlayedTimeMillis(), false);
        b.a(parcel, 4, snapshotMetadataChangeEntity.getCoverImageUri(), i, false);
        b.a(parcel, 5, snapshotMetadataChangeEntity.oI(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eW(x0);
    }

    public SnapshotMetadataChangeEntity eW(Parcel parcel) {
        Uri uri = null;
        int bT = a.bT(parcel);
        int i = 0;
        BitmapTeleporter bitmapTeleporter = null;
        Long l = null;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    l = a.j(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    uri = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    bitmapTeleporter = (BitmapTeleporter) a.a(parcel, bS, BitmapTeleporter.CREATOR);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new SnapshotMetadataChangeEntity(i, str, l, bitmapTeleporter, uri);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SnapshotMetadataChangeEntity[] hd(int i) {
        return new SnapshotMetadataChangeEntity[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hd(x0);
    }
}
