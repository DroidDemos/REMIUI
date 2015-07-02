package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.c;

public final class GameBadgeRef extends c implements GameBadge {
    GameBadgeRef(DataHolder holder, int dataRow) {
        super(holder, dataRow);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return GameBadgeEntity.a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return nZ();
    }

    public String getDescription() {
        return getString("badge_description");
    }

    public Uri getIconImageUri() {
        return ba("badge_icon_image_uri");
    }

    public String getTitle() {
        return getString("badge_title");
    }

    public int getType() {
        return getInteger("badge_type");
    }

    public int hashCode() {
        return GameBadgeEntity.a(this);
    }

    public GameBadge nZ() {
        return new GameBadgeEntity(this);
    }

    public String toString() {
        return GameBadgeEntity.b((GameBadge) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((GameBadgeEntity) nZ()).writeToParcel(dest, flags);
    }
}
