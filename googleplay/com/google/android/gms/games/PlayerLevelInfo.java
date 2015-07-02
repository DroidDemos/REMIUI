package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kn;

public final class PlayerLevelInfo implements SafeParcelable {
    public static final PlayerLevelInfoCreator CREATOR;
    private final long ajw;
    private final long ajx;
    private final PlayerLevel ajy;
    private final PlayerLevel ajz;
    private final int mVersionCode;

    static {
        CREATOR = new PlayerLevelInfoCreator();
    }

    PlayerLevelInfo(int versionCode, long currentXpTotal, long lastLevelUpTimestamp, PlayerLevel currentLevel, PlayerLevel nextLevel) {
        kn.K(currentXpTotal != -1);
        kn.k(currentLevel);
        kn.k(nextLevel);
        this.mVersionCode = versionCode;
        this.ajw = currentXpTotal;
        this.ajx = lastLevelUpTimestamp;
        this.ajy = currentLevel;
        this.ajz = nextLevel;
    }

    public PlayerLevelInfo(long currentXpTotal, long lastLevelUpTimestamp, PlayerLevel currentLevel, PlayerLevel nextLevel) {
        this(1, currentXpTotal, lastLevelUpTimestamp, currentLevel, nextLevel);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlayerLevelInfo)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        PlayerLevelInfo playerLevelInfo = (PlayerLevelInfo) obj;
        return kl.equal(Long.valueOf(this.ajw), Long.valueOf(playerLevelInfo.ajw)) && kl.equal(Long.valueOf(this.ajx), Long.valueOf(playerLevelInfo.ajx)) && kl.equal(this.ajy, playerLevelInfo.ajy) && kl.equal(this.ajz, playerLevelInfo.ajz);
    }

    public PlayerLevel getCurrentLevel() {
        return this.ajy;
    }

    public long getCurrentXpTotal() {
        return this.ajw;
    }

    public long getLastLevelUpTimestamp() {
        return this.ajx;
    }

    public PlayerLevel getNextLevel() {
        return this.ajz;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Long.valueOf(this.ajw), Long.valueOf(this.ajx), this.ajy, this.ajz);
    }

    public void writeToParcel(Parcel out, int flags) {
        PlayerLevelInfoCreator.a(this, out, flags);
    }
}
