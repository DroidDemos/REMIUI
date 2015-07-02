package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.kl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GameRequestEntity implements SafeParcelable, GameRequest {
    public static final GameRequestEntityCreator CREATOR;
    private final int EB;
    private final int Ob;
    private final String alx;
    private final GameEntity aot;
    private final long apT;
    private final PlayerEntity aqT;
    private final ArrayList<PlayerEntity> aqU;
    private final long aqV;
    private final Bundle aqW;
    private final byte[] mData;
    private final int mVersionCode;

    static {
        CREATOR = new GameRequestEntityCreator();
    }

    GameRequestEntity(int versionCode, GameEntity game, PlayerEntity sender, byte[] data, String requestId, ArrayList<PlayerEntity> recipients, int type, long creationTimestamp, long expirationTimestamp, Bundle recipientStatuses, int status) {
        this.mVersionCode = versionCode;
        this.aot = game;
        this.aqT = sender;
        this.mData = data;
        this.alx = requestId;
        this.aqU = recipients;
        this.EB = type;
        this.apT = creationTimestamp;
        this.aqV = expirationTimestamp;
        this.aqW = recipientStatuses;
        this.Ob = status;
    }

    public GameRequestEntity(GameRequest request) {
        this.mVersionCode = 2;
        this.aot = new GameEntity(request.getGame());
        this.aqT = new PlayerEntity(request.getSender());
        this.alx = request.getRequestId();
        this.EB = request.getType();
        this.apT = request.getCreationTimestamp();
        this.aqV = request.getExpirationTimestamp();
        this.Ob = request.getStatus();
        Object data = request.getData();
        if (data == null) {
            this.mData = null;
        } else {
            this.mData = new byte[data.length];
            System.arraycopy(data, 0, this.mData, 0, data.length);
        }
        List recipients = request.getRecipients();
        int size = recipients.size();
        this.aqU = new ArrayList(size);
        this.aqW = new Bundle();
        for (int i = 0; i < size; i++) {
            Player player = (Player) ((Player) recipients.get(i)).freeze();
            String playerId = player.getPlayerId();
            this.aqU.add((PlayerEntity) player);
            this.aqW.putInt(playerId, request.getRecipientStatus(playerId));
        }
    }

    static int a(GameRequest gameRequest) {
        return kl.hashCode(gameRequest.getGame(), gameRequest.getRecipients(), gameRequest.getRequestId(), gameRequest.getSender(), b(gameRequest), Integer.valueOf(gameRequest.getType()), Long.valueOf(gameRequest.getCreationTimestamp()), Long.valueOf(gameRequest.getExpirationTimestamp()));
    }

    static boolean a(GameRequest gameRequest, Object obj) {
        if (!(obj instanceof GameRequest)) {
            return false;
        }
        if (gameRequest == obj) {
            return true;
        }
        GameRequest gameRequest2 = (GameRequest) obj;
        return kl.equal(gameRequest2.getGame(), gameRequest.getGame()) && kl.equal(gameRequest2.getRecipients(), gameRequest.getRecipients()) && kl.equal(gameRequest2.getRequestId(), gameRequest.getRequestId()) && kl.equal(gameRequest2.getSender(), gameRequest.getSender()) && Arrays.equals(b(gameRequest2), b(gameRequest)) && kl.equal(Integer.valueOf(gameRequest2.getType()), Integer.valueOf(gameRequest.getType())) && kl.equal(Long.valueOf(gameRequest2.getCreationTimestamp()), Long.valueOf(gameRequest.getCreationTimestamp())) && kl.equal(Long.valueOf(gameRequest2.getExpirationTimestamp()), Long.valueOf(gameRequest.getExpirationTimestamp()));
    }

    private static int[] b(GameRequest gameRequest) {
        List recipients = gameRequest.getRecipients();
        int size = recipients.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = gameRequest.getRecipientStatus(((Player) recipients.get(i)).getPlayerId());
        }
        return iArr;
    }

    static String c(GameRequest gameRequest) {
        return kl.j(gameRequest).a("Game", gameRequest.getGame()).a("Sender", gameRequest.getSender()).a("Recipients", gameRequest.getRecipients()).a("Data", gameRequest.getData()).a("RequestId", gameRequest.getRequestId()).a("Type", Integer.valueOf(gameRequest.getType())).a("CreationTimestamp", Long.valueOf(gameRequest.getCreationTimestamp())).a("ExpirationTimestamp", Long.valueOf(gameRequest.getExpirationTimestamp())).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public GameRequest freeze() {
        return this;
    }

    public long getCreationTimestamp() {
        return this.apT;
    }

    public byte[] getData() {
        return this.mData;
    }

    public long getExpirationTimestamp() {
        return this.aqV;
    }

    public Game getGame() {
        return this.aot;
    }

    public int getRecipientStatus(String playerId) {
        return this.aqW.getInt(playerId, 0);
    }

    public List<Player> getRecipients() {
        return new ArrayList(this.aqU);
    }

    public String getRequestId() {
        return this.alx;
    }

    public Player getSender() {
        return this.aqT;
    }

    public int getStatus() {
        return this.Ob;
    }

    public int getType() {
        return this.EB;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return a(this);
    }

    public Bundle oH() {
        return this.aqW;
    }

    public String toString() {
        return c(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        GameRequestEntityCreator.a(this, dest, flags);
    }
}
