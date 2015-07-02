package com.google.android.gms.games.request;

import android.os.Parcel;
import com.google.android.gms.common.data.c;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import java.util.ArrayList;
import java.util.List;

public final class GameRequestRef extends c implements GameRequest {
    private final int akf;

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return GameRequestEntity.a(this, obj);
    }

    public GameRequest freeze() {
        return new GameRequestEntity(this);
    }

    public long getCreationTimestamp() {
        return getLong("creation_timestamp");
    }

    public byte[] getData() {
        return getByteArray("data");
    }

    public long getExpirationTimestamp() {
        return getLong("expiration_timestamp");
    }

    public Game getGame() {
        return new GameRef(this.mDataHolder, this.TX);
    }

    public int getRecipientStatus(String playerId) {
        for (int i = this.TX; i < this.TX + this.akf; i++) {
            int cW = this.mDataHolder.cW(i);
            if (this.mDataHolder.c("recipient_external_player_id", i, cW).equals(playerId)) {
                return this.mDataHolder.b("recipient_status", i, cW);
            }
        }
        return -1;
    }

    public List<Player> getRecipients() {
        List arrayList = new ArrayList(this.akf);
        for (int i = 0; i < this.akf; i++) {
            arrayList.add(new PlayerRef(this.mDataHolder, this.TX + i, "recipient_"));
        }
        return arrayList;
    }

    public String getRequestId() {
        return getString("external_request_id");
    }

    public Player getSender() {
        return new PlayerRef(this.mDataHolder, iq(), "sender_");
    }

    public int getStatus() {
        return getInteger("status");
    }

    public int getType() {
        return getInteger("type");
    }

    public int hashCode() {
        return GameRequestEntity.a(this);
    }

    public String toString() {
        return GameRequestEntity.c(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((GameRequestEntity) freeze()).writeToParcel(dest, flags);
    }
}
