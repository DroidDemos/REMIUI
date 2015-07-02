package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;
import com.google.android.wallet.instrumentmanager.R;

public class PlayerEntityCreator implements Creator<PlayerEntity> {
    static void a(PlayerEntity playerEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, playerEntity.getPlayerId(), false);
        b.a(parcel, 2, playerEntity.getDisplayName(), false);
        b.a(parcel, 3, playerEntity.getIconImageUri(), i, false);
        b.a(parcel, 4, playerEntity.getHiResImageUri(), i, false);
        b.a(parcel, 5, playerEntity.getRetrievedTimestamp());
        b.c(parcel, 6, playerEntity.mt());
        b.a(parcel, 7, playerEntity.getLastPlayedWithTimestamp());
        b.a(parcel, 8, playerEntity.getIconImageUrl(), false);
        b.a(parcel, 9, playerEntity.getHiResImageUrl(), false);
        b.a(parcel, 14, playerEntity.getTitle(), false);
        b.a(parcel, 15, playerEntity.mu(), i, false);
        b.a(parcel, 16, playerEntity.getLevelInfo(), i, false);
        b.c(parcel, 1000, playerEntity.getVersionCode());
        b.a(parcel, 18, playerEntity.isProfileVisible());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eE(x0);
    }

    public PlayerEntity eE(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        Uri uri = null;
        Uri uri2 = null;
        long j = 0;
        int i2 = 0;
        long j2 = 0;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        MostRecentGameInfoEntity mostRecentGameInfoEntity = null;
        PlayerLevelInfo playerLevelInfo = null;
        boolean z = false;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    uri = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    uri2 = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    mostRecentGameInfoEntity = (MostRecentGameInfoEntity) a.a(parcel, bS, MostRecentGameInfoEntity.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    playerLevelInfo = (PlayerLevelInfo) a.a(parcel, bS, PlayerLevelInfo.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
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
            return new PlayerEntity(i, str, str2, uri, uri2, j, i2, j2, str3, str4, str5, mostRecentGameInfoEntity, playerLevelInfo, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PlayerEntity[] gt(int i) {
        return new PlayerEntity[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gt(x0);
    }
}
