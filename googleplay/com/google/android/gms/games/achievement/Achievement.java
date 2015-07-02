package com.google.android.gms.games.achievement;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Player;

public interface Achievement extends Parcelable, Freezable<Achievement> {
    String getAchievementId();

    int getCurrentSteps();

    String getDescription();

    String getFormattedCurrentSteps();

    String getFormattedTotalSteps();

    long getLastUpdatedTimestamp();

    String getName();

    Player getPlayer();

    Uri getRevealedImageUri();

    @Deprecated
    String getRevealedImageUrl();

    int getState();

    int getTotalSteps();

    int getType();

    Uri getUnlockedImageUri();

    @Deprecated
    String getUnlockedImageUrl();

    long getXpValue();
}
