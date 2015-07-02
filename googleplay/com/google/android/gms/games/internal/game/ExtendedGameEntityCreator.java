package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class ExtendedGameEntityCreator implements Creator<ExtendedGameEntity> {
    static void a(ExtendedGameEntity extendedGameEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, extendedGameEntity.nX(), i, false);
        b.c(parcel, 1000, extendedGameEntity.getVersionCode());
        b.c(parcel, 2, extendedGameEntity.nO());
        b.a(parcel, 3, extendedGameEntity.nP());
        b.c(parcel, 4, extendedGameEntity.nQ());
        b.a(parcel, 5, extendedGameEntity.nR());
        b.a(parcel, 6, extendedGameEntity.nS());
        b.a(parcel, 7, extendedGameEntity.nT(), false);
        b.a(parcel, 8, extendedGameEntity.nU());
        b.a(parcel, 9, extendedGameEntity.nV(), false);
        b.d(parcel, 10, extendedGameEntity.nN(), false);
        b.a(parcel, 11, extendedGameEntity.nW(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eM(x0);
    }

    public ExtendedGameEntity eM(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        GameEntity gameEntity = null;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        long j = 0;
        long j2 = 0;
        String str = null;
        long j3 = 0;
        String str2 = null;
        ArrayList arrayList = null;
        SnapshotMetadataEntity snapshotMetadataEntity = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    gameEntity = (GameEntity) a.a(parcel, bS, GameEntity.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    j3 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    arrayList = a.c(parcel, bS, GameBadgeEntity.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    snapshotMetadataEntity = (SnapshotMetadataEntity) a.a(parcel, bS, SnapshotMetadataEntity.CREATOR);
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
            return new ExtendedGameEntity(i, gameEntity, i2, z, i3, j, j2, str, j3, str2, arrayList, snapshotMetadataEntity);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ExtendedGameEntity[] gN(int i) {
        return new ExtendedGameEntity[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gN(x0);
    }
}
