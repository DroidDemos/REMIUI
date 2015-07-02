package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;

public interface Player extends Parcelable, Freezable<Player> {
    String getDisplayName();

    Uri getHiResImageUri();

    @Deprecated
    String getHiResImageUrl();

    Uri getIconImageUri();

    @Deprecated
    String getIconImageUrl();

    long getLastPlayedWithTimestamp();

    PlayerLevelInfo getLevelInfo();

    String getPlayerId();

    long getRetrievedTimestamp();

    String getTitle();

    boolean isProfileVisible();

    int mt();

    MostRecentGameInfo mu();
}
