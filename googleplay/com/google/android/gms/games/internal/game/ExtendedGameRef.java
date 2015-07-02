package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.common.data.c;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataRef;
import java.util.ArrayList;

public class ExtendedGameRef extends c implements ExtendedGame {
    private final int akf;
    private final SnapshotMetadataRef aoE;
    private final GameRef aos;

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return ExtendedGameEntity.a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return nY();
    }

    public Game getGame() {
        return this.aos;
    }

    public int hashCode() {
        return ExtendedGameEntity.a(this);
    }

    public ArrayList<GameBadge> nN() {
        int i = 0;
        if (this.mDataHolder.c("badge_title", this.TX, this.mDataHolder.cW(this.TX)) == null) {
            return new ArrayList(0);
        }
        ArrayList<GameBadge> arrayList = new ArrayList(this.akf);
        while (i < this.akf) {
            arrayList.add(new GameBadgeRef(this.mDataHolder, this.TX + i));
            i++;
        }
        return arrayList;
    }

    public int nO() {
        return getInteger("availability");
    }

    public boolean nP() {
        return getBoolean("owned");
    }

    public int nQ() {
        return getInteger("achievement_unlocked_count");
    }

    public long nR() {
        return getLong("last_played_server_time");
    }

    public long nS() {
        return getLong("price_micros");
    }

    public String nT() {
        return getString("formatted_price");
    }

    public long nU() {
        return getLong("full_price_micros");
    }

    public String nV() {
        return getString("formatted_full_price");
    }

    public SnapshotMetadata nW() {
        return this.aoE;
    }

    public ExtendedGame nY() {
        return new ExtendedGameEntity(this);
    }

    public String toString() {
        return ExtendedGameEntity.b((ExtendedGame) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((ExtendedGameEntity) nY()).writeToParcel(dest, flags);
    }
}
