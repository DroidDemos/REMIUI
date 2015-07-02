package com.google.android.gms.games.achievement;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.wallet.instrumentmanager.R;

public class AchievementEntityCreator implements Creator<AchievementEntity> {
    static void a(AchievementEntity achievementEntity, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, achievementEntity.getAchievementId(), false);
        b.c(parcel, 2, achievementEntity.getType());
        b.a(parcel, 3, achievementEntity.getName(), false);
        b.a(parcel, 4, achievementEntity.getDescription(), false);
        b.a(parcel, 5, achievementEntity.getUnlockedImageUri(), i, false);
        b.a(parcel, 6, achievementEntity.getUnlockedImageUrl(), false);
        b.a(parcel, 7, achievementEntity.getRevealedImageUri(), i, false);
        b.a(parcel, 8, achievementEntity.getRevealedImageUrl(), false);
        b.c(parcel, 9, achievementEntity.getTotalSteps());
        b.a(parcel, 10, achievementEntity.getFormattedTotalSteps(), false);
        b.a(parcel, 11, achievementEntity.getPlayer(), i, false);
        b.c(parcel, 12, achievementEntity.getState());
        b.c(parcel, 13, achievementEntity.getCurrentSteps());
        b.a(parcel, 14, achievementEntity.getFormattedCurrentSteps(), false);
        b.a(parcel, 15, achievementEntity.getLastUpdatedTimestamp());
        b.a(parcel, 16, achievementEntity.getXpValue());
        b.c(parcel, 1000, achievementEntity.getVersionCode());
        b.J(parcel, bU);
    }

    public AchievementEntity createFromParcel(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        int i2 = 0;
        String str2 = null;
        String str3 = null;
        Uri uri = null;
        String str4 = null;
        Uri uri2 = null;
        String str5 = null;
        int i3 = 0;
        String str6 = null;
        PlayerEntity playerEntity = null;
        int i4 = 0;
        int i5 = 0;
        String str7 = null;
        long j = 0;
        long j2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    uri = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    uri2 = (Uri) a.a(parcel, bS, Uri.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    str6 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    playerEntity = (PlayerEntity) a.a(parcel, bS, PlayerEntity.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    i4 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    i5 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    str7 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    j = a.i(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    j2 = a.i(parcel, bS);
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
            return new AchievementEntity(i, str, i2, str2, str3, uri, str4, uri2, str5, i3, str6, playerEntity, i4, i5, str7, j, j2);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public AchievementEntity[] newArray(int size) {
        return new AchievementEntity[size];
    }
}
