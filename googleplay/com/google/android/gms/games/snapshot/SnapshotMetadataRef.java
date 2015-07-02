package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.c;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;

public final class SnapshotMetadataRef extends c implements SnapshotMetadata {
    private final Game apr;
    private final Player arm;

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return SnapshotMetadataEntity.a(this, obj);
    }

    public SnapshotMetadata freeze() {
        return new SnapshotMetadataEntity(this);
    }

    public float getCoverImageAspectRatio() {
        float f = getFloat("cover_icon_image_height");
        return f == 0.0f ? 0.0f : getFloat("cover_icon_image_width") / f;
    }

    public Uri getCoverImageUri() {
        return ba("cover_icon_image_uri");
    }

    public String getCoverImageUrl() {
        return getString("cover_icon_image_url");
    }

    public String getDescription() {
        return getString("description");
    }

    public Game getGame() {
        return this.apr;
    }

    public long getLastModifiedTimestamp() {
        return getLong("last_modified_timestamp");
    }

    public Player getOwner() {
        return this.arm;
    }

    public long getPlayedTime() {
        return getLong("duration");
    }

    public String getSnapshotId() {
        return getString("external_snapshot_id");
    }

    public String getTitle() {
        return getString("title");
    }

    public String getUniqueName() {
        return getString("unique_name");
    }

    public boolean hasChangePending() {
        return getInteger("pending_change_count") > 0;
    }

    public int hashCode() {
        return SnapshotMetadataEntity.a(this);
    }

    public String toString() {
        return SnapshotMetadataEntity.b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((SnapshotMetadataEntity) freeze()).writeToParcel(dest, flags);
    }
}
