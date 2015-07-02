package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.jy;
import com.google.android.gms.internal.kl;

public final class PlayerEntity extends GamesDowngradeableSafeParcel implements Player {
    public static final Creator<PlayerEntity> CREATOR;
    private final String WN;
    private final String Yv;
    private final Uri aiK;
    private final Uri aiL;
    private final String aiV;
    private final String aiW;
    private final String ajm;
    private final long ajn;
    private final int ajo;
    private final long ajp;
    private final MostRecentGameInfoEntity ajq;
    private final PlayerLevelInfo ajr;
    private final boolean ajs;
    private final int mVersionCode;

    static final class PlayerEntityCreatorCompat extends PlayerEntityCreator {
        PlayerEntityCreatorCompat() {
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return eE(x0);
        }

        public PlayerEntity eE(Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(jy.iM()) || jy.bf(PlayerEntity.class.getCanonicalName())) {
                return super.eE(parcel);
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            return new PlayerEntity(11, readString, readString2, readString3 == null ? null : Uri.parse(readString3), readString4 == null ? null : Uri.parse(readString4), parcel.readLong(), -1, -1, null, null, null, null, null, true);
        }
    }

    static {
        CREATOR = new PlayerEntityCreatorCompat();
    }

    PlayerEntity(int versionCode, String playerId, String displayName, Uri iconImageUri, Uri hiResImageUri, long retrievedTimestamp, int isInCircles, long lastPlayedWithTimestamp, String iconImageUrl, String hiResImageUrl, String title, MostRecentGameInfoEntity mostRecentGameInfo, PlayerLevelInfo playerLevelInfo, boolean isProfileVisible) {
        this.mVersionCode = versionCode;
        this.ajm = playerId;
        this.WN = displayName;
        this.aiK = iconImageUri;
        this.aiV = iconImageUrl;
        this.aiL = hiResImageUri;
        this.aiW = hiResImageUrl;
        this.ajn = retrievedTimestamp;
        this.ajo = isInCircles;
        this.ajp = lastPlayedWithTimestamp;
        this.Yv = title;
        this.ajs = isProfileVisible;
        this.ajq = mostRecentGameInfo;
        this.ajr = playerLevelInfo;
    }

    public PlayerEntity(Player player) {
        this.mVersionCode = 11;
        this.ajm = player.getPlayerId();
        this.WN = player.getDisplayName();
        this.aiK = player.getIconImageUri();
        this.aiV = player.getIconImageUrl();
        this.aiL = player.getHiResImageUri();
        this.aiW = player.getHiResImageUrl();
        this.ajn = player.getRetrievedTimestamp();
        this.ajo = player.mt();
        this.ajp = player.getLastPlayedWithTimestamp();
        this.Yv = player.getTitle();
        this.ajs = player.isProfileVisible();
        MostRecentGameInfo mu = player.mu();
        this.ajq = mu == null ? null : new MostRecentGameInfoEntity(mu);
        this.ajr = player.getLevelInfo();
        ju.h(this.ajm);
        ju.h(this.WN);
        ju.K(this.ajn > 0);
    }

    static boolean a(Player player, Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        if (player == obj) {
            return true;
        }
        Player player2 = (Player) obj;
        return kl.equal(player2.getPlayerId(), player.getPlayerId()) && kl.equal(player2.getDisplayName(), player.getDisplayName()) && kl.equal(player2.getIconImageUri(), player.getIconImageUri()) && kl.equal(player2.getHiResImageUri(), player.getHiResImageUri()) && kl.equal(Long.valueOf(player2.getRetrievedTimestamp()), Long.valueOf(player.getRetrievedTimestamp())) && kl.equal(player2.getTitle(), player.getTitle()) && kl.equal(player2.getLevelInfo(), player.getLevelInfo());
    }

    static int b(Player player) {
        return kl.hashCode(player.getPlayerId(), player.getDisplayName(), player.getIconImageUri(), player.getHiResImageUri(), Long.valueOf(player.getRetrievedTimestamp()), player.getTitle(), player.getLevelInfo());
    }

    static String c(Player player) {
        return kl.j(player).a("PlayerId", player.getPlayerId()).a("DisplayName", player.getDisplayName()).a("IconImageUri", player.getIconImageUri()).a("IconImageUrl", player.getIconImageUrl()).a("HiResImageUri", player.getHiResImageUri()).a("HiResImageUrl", player.getHiResImageUrl()).a("RetrievedTimestamp", Long.valueOf(player.getRetrievedTimestamp())).a("Title", player.getTitle()).a("LevelInfo", player.getLevelInfo()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Player freeze() {
        return this;
    }

    public String getDisplayName() {
        return this.WN;
    }

    public Uri getHiResImageUri() {
        return this.aiL;
    }

    public String getHiResImageUrl() {
        return this.aiW;
    }

    public Uri getIconImageUri() {
        return this.aiK;
    }

    public String getIconImageUrl() {
        return this.aiV;
    }

    public long getLastPlayedWithTimestamp() {
        return this.ajp;
    }

    public PlayerLevelInfo getLevelInfo() {
        return this.ajr;
    }

    public String getPlayerId() {
        return this.ajm;
    }

    public long getRetrievedTimestamp() {
        return this.ajn;
    }

    public String getTitle() {
        return this.Yv;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return b((Player) this);
    }

    public boolean isProfileVisible() {
        return this.ajs;
    }

    public int mt() {
        return this.ajo;
    }

    public MostRecentGameInfo mu() {
        return this.ajq;
    }

    public String toString() {
        return c(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        String str = null;
        if (iN()) {
            dest.writeString(this.ajm);
            dest.writeString(this.WN);
            dest.writeString(this.aiK == null ? null : this.aiK.toString());
            if (this.aiL != null) {
                str = this.aiL.toString();
            }
            dest.writeString(str);
            dest.writeLong(this.ajn);
            return;
        }
        PlayerEntityCreator.a(this, dest, flags);
    }
}
