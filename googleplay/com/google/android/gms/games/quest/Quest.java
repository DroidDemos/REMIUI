package com.google.android.gms.games.quest;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import java.util.List;

public interface Quest extends Parcelable, Freezable<Quest> {
    public static final int[] QUEST_STATE_ALL;
    public static final String[] QUEST_STATE_NON_TERMINAL;

    static {
        QUEST_STATE_ALL = new int[]{1, 2, 3, 4, 6, 5};
        QUEST_STATE_NON_TERMINAL = new String[]{Integer.toString(1), Integer.toString(2), Integer.toString(3)};
    }

    long getAcceptedTimestamp();

    Uri getBannerImageUri();

    @Deprecated
    String getBannerImageUrl();

    String getDescription();

    long getEndTimestamp();

    Game getGame();

    Uri getIconImageUri();

    @Deprecated
    String getIconImageUrl();

    long getLastUpdatedTimestamp();

    String getName();

    String getQuestId();

    long getStartTimestamp();

    int getState();

    int getType();

    List<Milestone> oF();

    long oG();
}
