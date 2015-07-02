package com.google.android.gms.games.event;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Player;

public interface Event extends Parcelable, Freezable<Event> {
    String getDescription();

    String getEventId();

    String getFormattedValue();

    Uri getIconImageUri();

    @Deprecated
    String getIconImageUrl();

    String getName();

    Player getPlayer();

    long getValue();

    boolean isVisible();
}
