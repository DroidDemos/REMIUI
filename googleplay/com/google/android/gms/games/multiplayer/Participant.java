package com.google.android.gms.games.multiplayer;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Player;

public interface Participant extends Parcelable, Freezable<Participant> {
    int getCapabilities();

    String getDisplayName();

    Uri getHiResImageUri();

    @Deprecated
    String getHiResImageUrl();

    Uri getIconImageUri();

    @Deprecated
    String getIconImageUrl();

    String getParticipantId();

    Player getPlayer();

    ParticipantResult getResult();

    int getStatus();

    boolean isConnectedToRoom();

    String mT();
}
