package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface Game extends Parcelable, Freezable<Game> {
    boolean areSnapshotsEnabled();

    int getAchievementTotalCount();

    String getApplicationId();

    String getDescription();

    String getDeveloperName();

    String getDisplayName();

    Uri getFeaturedImageUri();

    @Deprecated
    String getFeaturedImageUrl();

    Uri getHiResImageUri();

    @Deprecated
    String getHiResImageUrl();

    Uri getIconImageUri();

    @Deprecated
    String getIconImageUrl();

    int getLeaderboardCount();

    String getPrimaryCategory();

    String getSecondaryCategory();

    String getThemeColor();

    boolean isMuted();

    boolean isRealTimeMultiplayerEnabled();

    boolean isTurnBasedMultiplayerEnabled();

    boolean mn();

    boolean mo();

    boolean mp();

    String mq();

    int mr();
}
