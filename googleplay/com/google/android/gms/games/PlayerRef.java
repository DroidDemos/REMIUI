package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.c;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoRef;
import com.google.android.gms.games.internal.player.PlayerColumnNames;

public final class PlayerRef extends c implements Player {
    private final PlayerColumnNames ajA;
    private final MostRecentGameInfoRef ajB;
    private final PlayerLevelInfo ajr;

    public PlayerRef(DataHolder holder, int dataRow) {
        this(holder, dataRow, null);
    }

    public PlayerRef(DataHolder holder, int dataRow, String prefix) {
        super(holder, dataRow);
        this.ajA = new PlayerColumnNames(prefix);
        this.ajB = new MostRecentGameInfoRef(holder, dataRow, this.ajA);
        if (mv()) {
            PlayerLevel playerLevel;
            int integer = getInteger(this.ajA.aoW);
            int integer2 = getInteger(this.ajA.aoZ);
            PlayerLevel playerLevel2 = new PlayerLevel(integer, getLong(this.ajA.aoX), getLong(this.ajA.aoY));
            if (integer != integer2) {
                playerLevel = new PlayerLevel(integer2, getLong(this.ajA.aoY), getLong(this.ajA.apa));
            } else {
                playerLevel = playerLevel2;
            }
            this.ajr = new PlayerLevelInfo(getLong(this.ajA.aoV), getLong(this.ajA.apb), playerLevel2, playerLevel);
            return;
        }
        this.ajr = null;
    }

    private boolean mv() {
        return (bb(this.ajA.aoV) || getLong(this.ajA.aoV) == -1) ? false : true;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return PlayerEntity.a(this, obj);
    }

    public Player freeze() {
        return new PlayerEntity(this);
    }

    public String getDisplayName() {
        return getString(this.ajA.aoN);
    }

    public Uri getHiResImageUri() {
        return ba(this.ajA.aoQ);
    }

    public String getHiResImageUrl() {
        return getString(this.ajA.aoR);
    }

    public Uri getIconImageUri() {
        return ba(this.ajA.aoO);
    }

    public String getIconImageUrl() {
        return getString(this.ajA.aoP);
    }

    public long getLastPlayedWithTimestamp() {
        return (!aZ(this.ajA.aoU) || bb(this.ajA.aoU)) ? -1 : getLong(this.ajA.aoU);
    }

    public PlayerLevelInfo getLevelInfo() {
        return this.ajr;
    }

    public String getPlayerId() {
        return getString(this.ajA.aoM);
    }

    public long getRetrievedTimestamp() {
        return getLong(this.ajA.aoS);
    }

    public String getTitle() {
        return getString(this.ajA.apc);
    }

    public int hashCode() {
        return PlayerEntity.b((Player) this);
    }

    public boolean isProfileVisible() {
        return getBoolean(this.ajA.ape);
    }

    public int mt() {
        return getInteger(this.ajA.aoT);
    }

    public MostRecentGameInfo mu() {
        return bb(this.ajA.apf) ? null : this.ajB;
    }

    public String toString() {
        return PlayerEntity.c(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((PlayerEntity) freeze()).writeToParcel(dest, flags);
    }
}
