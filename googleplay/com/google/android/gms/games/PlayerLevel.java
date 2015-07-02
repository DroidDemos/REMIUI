package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kn;

public final class PlayerLevel implements SafeParcelable {
    public static final PlayerLevelCreator CREATOR;
    private final int ajt;
    private final long aju;
    private final long ajv;
    private final int mVersionCode;

    static {
        CREATOR = new PlayerLevelCreator();
    }

    PlayerLevel(int versionCode, int levelNumber, long minXp, long maxXp) {
        boolean z = true;
        kn.a(minXp >= 0, "Min XP must be positive!");
        if (maxXp <= minXp) {
            z = false;
        }
        kn.a(z, "Max XP must be more than min XP!");
        this.mVersionCode = versionCode;
        this.ajt = levelNumber;
        this.aju = minXp;
        this.ajv = maxXp;
    }

    public PlayerLevel(int value, long minXp, long maxXp) {
        this(1, value, minXp, maxXp);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlayerLevel)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PlayerLevel playerLevel = (PlayerLevel) obj;
        return kl.equal(Integer.valueOf(playerLevel.getLevelNumber()), Integer.valueOf(getLevelNumber())) && kl.equal(Long.valueOf(playerLevel.getMinXp()), Long.valueOf(getMinXp())) && kl.equal(Long.valueOf(playerLevel.getMaxXp()), Long.valueOf(getMaxXp()));
    }

    public int getLevelNumber() {
        return this.ajt;
    }

    public long getMaxXp() {
        return this.ajv;
    }

    public long getMinXp() {
        return this.aju;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.ajt), Long.valueOf(this.aju), Long.valueOf(this.ajv));
    }

    public String toString() {
        return kl.j(this).a("LevelNumber", Integer.valueOf(getLevelNumber())).a("MinXp", Long.valueOf(getMinXp())).a("MaxXp", Long.valueOf(getMaxXp())).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        PlayerLevelCreator.a(this, out, flags);
    }
}
