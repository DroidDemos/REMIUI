package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.kl;

public final class SnapshotMetadataEntity implements SafeParcelable, SnapshotMetadata {
    public static final SnapshotMetadataEntityCreator CREATOR;
    private final String Yv;
    private final String akF;
    private final GameEntity aot;
    private final Uri arc;
    private final PlayerEntity arf;
    private final String arg;
    private final long arh;
    private final long ari;
    private final float arj;
    private final String ark;
    private final boolean arl;
    private final String mDescription;
    private final int mVersionCode;

    static {
        CREATOR = new SnapshotMetadataEntityCreator();
    }

    SnapshotMetadataEntity(int versionCode, GameEntity game, PlayerEntity owner, String snapshotId, Uri coverImageUri, String coverImageUrl, String title, String description, long lastModifiedTimestamp, long playedTime, float coverImageAspectRatio, String uniqueName, boolean changePending) {
        this.mVersionCode = versionCode;
        this.aot = game;
        this.arf = owner;
        this.akF = snapshotId;
        this.arc = coverImageUri;
        this.arg = coverImageUrl;
        this.arj = coverImageAspectRatio;
        this.Yv = title;
        this.mDescription = description;
        this.arh = lastModifiedTimestamp;
        this.ari = playedTime;
        this.ark = uniqueName;
        this.arl = changePending;
    }

    public SnapshotMetadataEntity(SnapshotMetadata snapshotMetadata) {
        this.mVersionCode = 4;
        this.aot = new GameEntity(snapshotMetadata.getGame());
        this.arf = new PlayerEntity(snapshotMetadata.getOwner());
        this.akF = snapshotMetadata.getSnapshotId();
        this.arc = snapshotMetadata.getCoverImageUri();
        this.arg = snapshotMetadata.getCoverImageUrl();
        this.arj = snapshotMetadata.getCoverImageAspectRatio();
        this.Yv = snapshotMetadata.getTitle();
        this.mDescription = snapshotMetadata.getDescription();
        this.arh = snapshotMetadata.getLastModifiedTimestamp();
        this.ari = snapshotMetadata.getPlayedTime();
        this.ark = snapshotMetadata.getUniqueName();
        this.arl = snapshotMetadata.hasChangePending();
    }

    static int a(SnapshotMetadata snapshotMetadata) {
        return kl.hashCode(snapshotMetadata.getGame(), snapshotMetadata.getOwner(), snapshotMetadata.getSnapshotId(), snapshotMetadata.getCoverImageUri(), Float.valueOf(snapshotMetadata.getCoverImageAspectRatio()), snapshotMetadata.getTitle(), snapshotMetadata.getDescription(), Long.valueOf(snapshotMetadata.getLastModifiedTimestamp()), Long.valueOf(snapshotMetadata.getPlayedTime()), snapshotMetadata.getUniqueName(), Boolean.valueOf(snapshotMetadata.hasChangePending()));
    }

    static boolean a(SnapshotMetadata snapshotMetadata, Object obj) {
        if (!(obj instanceof SnapshotMetadata)) {
            return false;
        }
        if (snapshotMetadata == obj) {
            return true;
        }
        SnapshotMetadata snapshotMetadata2 = (SnapshotMetadata) obj;
        return kl.equal(snapshotMetadata2.getGame(), snapshotMetadata.getGame()) && kl.equal(snapshotMetadata2.getOwner(), snapshotMetadata.getOwner()) && kl.equal(snapshotMetadata2.getSnapshotId(), snapshotMetadata.getSnapshotId()) && kl.equal(snapshotMetadata2.getCoverImageUri(), snapshotMetadata.getCoverImageUri()) && kl.equal(Float.valueOf(snapshotMetadata2.getCoverImageAspectRatio()), Float.valueOf(snapshotMetadata.getCoverImageAspectRatio())) && kl.equal(snapshotMetadata2.getTitle(), snapshotMetadata.getTitle()) && kl.equal(snapshotMetadata2.getDescription(), snapshotMetadata.getDescription()) && kl.equal(Long.valueOf(snapshotMetadata2.getLastModifiedTimestamp()), Long.valueOf(snapshotMetadata.getLastModifiedTimestamp())) && kl.equal(Long.valueOf(snapshotMetadata2.getPlayedTime()), Long.valueOf(snapshotMetadata.getPlayedTime())) && kl.equal(snapshotMetadata2.getUniqueName(), snapshotMetadata.getUniqueName()) && kl.equal(Boolean.valueOf(snapshotMetadata2.hasChangePending()), Boolean.valueOf(snapshotMetadata.hasChangePending()));
    }

    static String b(SnapshotMetadata snapshotMetadata) {
        return kl.j(snapshotMetadata).a("Game", snapshotMetadata.getGame()).a("Owner", snapshotMetadata.getOwner()).a("SnapshotId", snapshotMetadata.getSnapshotId()).a("CoverImageUri", snapshotMetadata.getCoverImageUri()).a("CoverImageUrl", snapshotMetadata.getCoverImageUrl()).a("CoverImageAspectRatio", Float.valueOf(snapshotMetadata.getCoverImageAspectRatio())).a("Description", snapshotMetadata.getDescription()).a("LastModifiedTimestamp", Long.valueOf(snapshotMetadata.getLastModifiedTimestamp())).a("PlayedTime", Long.valueOf(snapshotMetadata.getPlayedTime())).a("UniqueName", snapshotMetadata.getUniqueName()).a("ChangePending", Boolean.valueOf(snapshotMetadata.hasChangePending())).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public SnapshotMetadata freeze() {
        return this;
    }

    public float getCoverImageAspectRatio() {
        return this.arj;
    }

    public Uri getCoverImageUri() {
        return this.arc;
    }

    public String getCoverImageUrl() {
        return this.arg;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public Game getGame() {
        return this.aot;
    }

    public long getLastModifiedTimestamp() {
        return this.arh;
    }

    public Player getOwner() {
        return this.arf;
    }

    public long getPlayedTime() {
        return this.ari;
    }

    public String getSnapshotId() {
        return this.akF;
    }

    public String getTitle() {
        return this.Yv;
    }

    public String getUniqueName() {
        return this.ark;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean hasChangePending() {
        return this.arl;
    }

    public int hashCode() {
        return a(this);
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        SnapshotMetadataEntityCreator.a(this, out, flags);
    }
}
