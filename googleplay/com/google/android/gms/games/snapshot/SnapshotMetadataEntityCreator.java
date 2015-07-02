package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.wallet.instrumentmanager.R;

public class SnapshotMetadataEntityCreator implements Creator<SnapshotMetadataEntity> {
    static void a(SnapshotMetadataEntity snapshotMetadataEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, snapshotMetadataEntity.getGame(), i, false);
        b.c(parcel, 1000, snapshotMetadataEntity.getVersionCode());
        b.a(parcel, 2, snapshotMetadataEntity.getOwner(), i, false);
        b.a(parcel, 3, snapshotMetadataEntity.getSnapshotId(), false);
        b.a(parcel, 5, snapshotMetadataEntity.getCoverImageUri(), i, false);
        b.a(parcel, 6, snapshotMetadataEntity.getCoverImageUrl(), false);
        b.a(parcel, 7, snapshotMetadataEntity.getTitle(), false);
        b.a(parcel, 8, snapshotMetadataEntity.getDescription(), false);
        b.a(parcel, 9, snapshotMetadataEntity.getLastModifiedTimestamp());
        b.a(parcel, 10, snapshotMetadataEntity.getPlayedTime());
        b.a(parcel, 11, snapshotMetadataEntity.getCoverImageAspectRatio());
        b.a(parcel, 12, snapshotMetadataEntity.getUniqueName(), false);
        b.a(parcel, 13, snapshotMetadataEntity.hasChangePending());
        b.J(parcel, bU);
    }

    public SnapshotMetadataEntity createFromParcel(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        GameEntity gameEntity = null;
        PlayerEntity playerEntity = null;
        String str = null;
        Uri uri = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        long j = 0;
        long j2 = 0;
        float f = 0.0f;
        String str5 = null;
        boolean z = false;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    gameEntity = (GameEntity) a.a(parcel, bS, GameEntity.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    playerEntity = (PlayerEntity) a.a(parcel, bS, PlayerEntity.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    uri = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    f = a.l(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    z = a.c(parcel, bS);
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
            return new SnapshotMetadataEntity(i, gameEntity, playerEntity, str, uri, str2, str3, str4, j, j2, f, str5, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public SnapshotMetadataEntity[] newArray(int size) {
        return new SnapshotMetadataEntity[size];
    }
}
