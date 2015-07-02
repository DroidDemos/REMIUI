package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class SnapshotEntityCreator implements Creator<SnapshotEntity> {
    static void a(SnapshotEntity snapshotEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, snapshotEntity.getMetadata(), i, false);
        b.c(parcel, 1000, snapshotEntity.getVersionCode());
        b.a(parcel, 3, snapshotEntity.getSnapshotContents(), i, false);
        b.J(parcel, bU);
    }

    public SnapshotEntity createFromParcel(Parcel parcel) {
        SnapshotContentsEntity snapshotContentsEntity = null;
        int bT = a.bT(parcel);
        int i = 0;
        SnapshotMetadata snapshotMetadata = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            SnapshotContentsEntity snapshotContentsEntity2;
            SnapshotMetadata snapshotMetadata2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    SnapshotMetadataEntity snapshotMetadataEntity = (SnapshotMetadataEntity) a.a(parcel, bS, SnapshotMetadataEntity.CREATOR);
                    snapshotContentsEntity2 = snapshotContentsEntity;
                    Object obj = snapshotMetadataEntity;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    snapshotContentsEntity2 = (SnapshotContentsEntity) a.a(parcel, bS, SnapshotContentsEntity.CREATOR);
                    snapshotMetadata2 = snapshotMetadata;
                    i2 = i;
                    break;
                case 1000:
                    SnapshotContentsEntity snapshotContentsEntity3 = snapshotContentsEntity;
                    snapshotMetadata2 = snapshotMetadata;
                    i2 = a.g(parcel, bS);
                    snapshotContentsEntity2 = snapshotContentsEntity3;
                    break;
                default:
                    a.b(parcel, bS);
                    snapshotContentsEntity2 = snapshotContentsEntity;
                    snapshotMetadata2 = snapshotMetadata;
                    i2 = i;
                    break;
            }
            i = i2;
            snapshotMetadata = snapshotMetadata2;
            snapshotContentsEntity = snapshotContentsEntity2;
        }
        if (parcel.dataPosition() == bT) {
            return new SnapshotEntity(i, snapshotMetadata, snapshotContentsEntity);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SnapshotEntity[] newArray(int size) {
        return new SnapshotEntity[size];
    }
}
