package com.google.android.gms.games.internal.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestEntity;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.kl;
import java.util.ArrayList;
import java.util.List;

public final class GameRequestCluster implements SafeParcelable, GameRequest {
    public static final GameRequestClusterCreator CREATOR;
    private final ArrayList<GameRequestEntity> apl;
    private final int mVersionCode;

    static {
        CREATOR = new GameRequestClusterCreator();
    }

    GameRequestCluster(int versionCode, ArrayList<GameRequestEntity> requestList) {
        this.mVersionCode = versionCode;
        this.apl = requestList;
        oe();
    }

    private void oe() {
        ju.K(!this.apl.isEmpty());
        GameRequest gameRequest = (GameRequest) this.apl.get(0);
        int size = this.apl.size();
        for (int i = 1; i < size; i++) {
            GameRequest gameRequest2 = (GameRequest) this.apl.get(i);
            ju.a(gameRequest.getType() == gameRequest2.getType(), "All the requests must be of the same type");
            ju.a(gameRequest.getSender().equals(gameRequest2.getSender()), "All the requests must be from the same sender");
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GameRequestCluster)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        GameRequestCluster gameRequestCluster = (GameRequestCluster) obj;
        if (gameRequestCluster.apl.size() != this.apl.size()) {
            return false;
        }
        int size = this.apl.size();
        for (int i = 0; i < size; i++) {
            if (!((GameRequest) this.apl.get(i)).equals((GameRequest) gameRequestCluster.apl.get(i))) {
                return false;
            }
        }
        return true;
    }

    public GameRequest freeze() {
        return this;
    }

    public long getCreationTimestamp() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public byte[] getData() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public long getExpirationTimestamp() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public Game getGame() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public int getRecipientStatus(String playerId) {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public /* synthetic */ List getRecipients() {
        return ot();
    }

    public String getRequestId() {
        return ((GameRequestEntity) this.apl.get(0)).getRequestId();
    }

    public Player getSender() {
        return ((GameRequestEntity) this.apl.get(0)).getSender();
    }

    public int getStatus() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public int getType() {
        return ((GameRequestEntity) this.apl.get(0)).getType();
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.apl.toArray());
    }

    public ArrayList<GameRequest> os() {
        return new ArrayList(this.apl);
    }

    public ArrayList<Player> ot() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public void writeToParcel(Parcel dest, int flags) {
        GameRequestClusterCreator.a(this, dest, flags);
    }
}
