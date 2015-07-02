package com.google.android.gms.games.request;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import java.util.List;

public interface GameRequest extends Parcelable, Freezable<GameRequest> {
    long getCreationTimestamp();

    byte[] getData();

    long getExpirationTimestamp();

    Game getGame();

    int getRecipientStatus(String str);

    List<Player> getRecipients();

    String getRequestId();

    Player getSender();

    int getStatus();

    int getType();
}
