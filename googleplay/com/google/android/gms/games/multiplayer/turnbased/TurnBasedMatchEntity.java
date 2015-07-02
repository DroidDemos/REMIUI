package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.kl;
import java.util.ArrayList;

public final class TurnBasedMatchEntity implements SafeParcelable, TurnBasedMatch {
    public static final TurnBasedMatchEntityCreator CREATOR;
    private final int Gf;
    private final long ajM;
    private final String alf;
    private final GameEntity aot;
    private final long apT;
    private final ArrayList<ParticipantEntity> apW;
    private final int apX;
    private final String aqA;
    private final byte[] aqB;
    private final int aqC;
    private final int aqD;
    private final boolean aqE;
    private final String aqF;
    private final Bundle aqm;
    private final String aqp;
    private final String aqx;
    private final String aqy;
    private final int aqz;
    private final byte[] mData;
    private final String mDescription;
    private final int mVersionCode;

    static {
        CREATOR = new TurnBasedMatchEntityCreator();
    }

    TurnBasedMatchEntity(int versionCode, GameEntity game, String matchId, String creatorId, long creationTimestamp, String lastUpdaterId, long lastUpdatedTimestamp, String pendingParticipantId, int matchStatus, int variant, int version, byte[] data, ArrayList<ParticipantEntity> participants, String rematchId, byte[] previousData, int matchNumber, Bundle autoMatchCriteria, int turnStatus, boolean isLocallyModified, String description, String descriptionParticipantId) {
        this.mVersionCode = versionCode;
        this.aot = game;
        this.alf = matchId;
        this.aqp = creatorId;
        this.apT = creationTimestamp;
        this.aqx = lastUpdaterId;
        this.ajM = lastUpdatedTimestamp;
        this.aqy = pendingParticipantId;
        this.aqz = matchStatus;
        this.aqD = turnStatus;
        this.apX = variant;
        this.Gf = version;
        this.mData = data;
        this.apW = participants;
        this.aqA = rematchId;
        this.aqB = previousData;
        this.aqC = matchNumber;
        this.aqm = autoMatchCriteria;
        this.aqE = isLocallyModified;
        this.mDescription = description;
        this.aqF = descriptionParticipantId;
    }

    public TurnBasedMatchEntity(TurnBasedMatch match) {
        this.mVersionCode = 2;
        this.aot = new GameEntity(match.getGame());
        this.alf = match.getMatchId();
        this.aqp = match.getCreatorId();
        this.apT = match.getCreationTimestamp();
        this.aqx = match.getLastUpdaterId();
        this.ajM = match.getLastUpdatedTimestamp();
        this.aqy = match.getPendingParticipantId();
        this.aqz = match.getStatus();
        this.aqD = match.getTurnStatus();
        this.apX = match.getVariant();
        this.Gf = match.getVersion();
        this.aqA = match.getRematchId();
        this.aqC = match.getMatchNumber();
        this.aqm = match.getAutoMatchCriteria();
        this.aqE = match.isLocallyModified();
        this.mDescription = match.getDescription();
        this.aqF = match.getDescriptionParticipantId();
        Object data = match.getData();
        if (data == null) {
            this.mData = null;
        } else {
            this.mData = new byte[data.length];
            System.arraycopy(data, 0, this.mData, 0, data.length);
        }
        data = match.getPreviousMatchData();
        if (data == null) {
            this.aqB = null;
        } else {
            this.aqB = new byte[data.length];
            System.arraycopy(data, 0, this.aqB, 0, data.length);
        }
        ArrayList participants = match.getParticipants();
        int size = participants.size();
        this.apW = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            this.apW.add((ParticipantEntity) ((Participant) participants.get(i)).freeze());
        }
    }

    static int a(TurnBasedMatch turnBasedMatch) {
        return kl.hashCode(turnBasedMatch.getGame(), turnBasedMatch.getMatchId(), turnBasedMatch.getCreatorId(), Long.valueOf(turnBasedMatch.getCreationTimestamp()), turnBasedMatch.getLastUpdaterId(), Long.valueOf(turnBasedMatch.getLastUpdatedTimestamp()), turnBasedMatch.getPendingParticipantId(), Integer.valueOf(turnBasedMatch.getStatus()), Integer.valueOf(turnBasedMatch.getTurnStatus()), turnBasedMatch.getDescription(), Integer.valueOf(turnBasedMatch.getVariant()), Integer.valueOf(turnBasedMatch.getVersion()), turnBasedMatch.getParticipants(), turnBasedMatch.getRematchId(), Integer.valueOf(turnBasedMatch.getMatchNumber()), turnBasedMatch.getAutoMatchCriteria(), Integer.valueOf(turnBasedMatch.getAvailableAutoMatchSlots()), Boolean.valueOf(turnBasedMatch.isLocallyModified()));
    }

    static boolean a(TurnBasedMatch turnBasedMatch, Object obj) {
        if (!(obj instanceof TurnBasedMatch)) {
            return false;
        }
        if (turnBasedMatch == obj) {
            return true;
        }
        TurnBasedMatch turnBasedMatch2 = (TurnBasedMatch) obj;
        return kl.equal(turnBasedMatch2.getGame(), turnBasedMatch.getGame()) && kl.equal(turnBasedMatch2.getMatchId(), turnBasedMatch.getMatchId()) && kl.equal(turnBasedMatch2.getCreatorId(), turnBasedMatch.getCreatorId()) && kl.equal(Long.valueOf(turnBasedMatch2.getCreationTimestamp()), Long.valueOf(turnBasedMatch.getCreationTimestamp())) && kl.equal(turnBasedMatch2.getLastUpdaterId(), turnBasedMatch.getLastUpdaterId()) && kl.equal(Long.valueOf(turnBasedMatch2.getLastUpdatedTimestamp()), Long.valueOf(turnBasedMatch.getLastUpdatedTimestamp())) && kl.equal(turnBasedMatch2.getPendingParticipantId(), turnBasedMatch.getPendingParticipantId()) && kl.equal(Integer.valueOf(turnBasedMatch2.getStatus()), Integer.valueOf(turnBasedMatch.getStatus())) && kl.equal(Integer.valueOf(turnBasedMatch2.getTurnStatus()), Integer.valueOf(turnBasedMatch.getTurnStatus())) && kl.equal(turnBasedMatch2.getDescription(), turnBasedMatch.getDescription()) && kl.equal(Integer.valueOf(turnBasedMatch2.getVariant()), Integer.valueOf(turnBasedMatch.getVariant())) && kl.equal(Integer.valueOf(turnBasedMatch2.getVersion()), Integer.valueOf(turnBasedMatch.getVersion())) && kl.equal(turnBasedMatch2.getParticipants(), turnBasedMatch.getParticipants()) && kl.equal(turnBasedMatch2.getRematchId(), turnBasedMatch.getRematchId()) && kl.equal(Integer.valueOf(turnBasedMatch2.getMatchNumber()), Integer.valueOf(turnBasedMatch.getMatchNumber())) && kl.equal(turnBasedMatch2.getAutoMatchCriteria(), turnBasedMatch.getAutoMatchCriteria()) && kl.equal(Integer.valueOf(turnBasedMatch2.getAvailableAutoMatchSlots()), Integer.valueOf(turnBasedMatch.getAvailableAutoMatchSlots())) && kl.equal(Boolean.valueOf(turnBasedMatch2.isLocallyModified()), Boolean.valueOf(turnBasedMatch.isLocallyModified()));
    }

    static String b(TurnBasedMatch turnBasedMatch) {
        return kl.j(turnBasedMatch).a("Game", turnBasedMatch.getGame()).a("MatchId", turnBasedMatch.getMatchId()).a("CreatorId", turnBasedMatch.getCreatorId()).a("CreationTimestamp", Long.valueOf(turnBasedMatch.getCreationTimestamp())).a("LastUpdaterId", turnBasedMatch.getLastUpdaterId()).a("LastUpdatedTimestamp", Long.valueOf(turnBasedMatch.getLastUpdatedTimestamp())).a("PendingParticipantId", turnBasedMatch.getPendingParticipantId()).a("MatchStatus", Integer.valueOf(turnBasedMatch.getStatus())).a("TurnStatus", Integer.valueOf(turnBasedMatch.getTurnStatus())).a("Description", turnBasedMatch.getDescription()).a("Variant", Integer.valueOf(turnBasedMatch.getVariant())).a("Data", turnBasedMatch.getData()).a("Version", Integer.valueOf(turnBasedMatch.getVersion())).a("Participants", turnBasedMatch.getParticipants()).a("RematchId", turnBasedMatch.getRematchId()).a("PreviousData", turnBasedMatch.getPreviousMatchData()).a("MatchNumber", Integer.valueOf(turnBasedMatch.getMatchNumber())).a("AutoMatchCriteria", turnBasedMatch.getAutoMatchCriteria()).a("AvailableAutoMatchSlots", Integer.valueOf(turnBasedMatch.getAvailableAutoMatchSlots())).a("LocallyModified", Boolean.valueOf(turnBasedMatch.isLocallyModified())).a("DescriptionParticipantId", turnBasedMatch.getDescriptionParticipantId()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public TurnBasedMatch freeze() {
        return this;
    }

    public Bundle getAutoMatchCriteria() {
        return this.aqm;
    }

    public int getAvailableAutoMatchSlots() {
        return this.aqm == null ? 0 : this.aqm.getInt("max_automatch_players");
    }

    public long getCreationTimestamp() {
        return this.apT;
    }

    public String getCreatorId() {
        return this.aqp;
    }

    public byte[] getData() {
        return this.mData;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getDescriptionParticipantId() {
        return this.aqF;
    }

    public Game getGame() {
        return this.aot;
    }

    public long getLastUpdatedTimestamp() {
        return this.ajM;
    }

    public String getLastUpdaterId() {
        return this.aqx;
    }

    public String getMatchId() {
        return this.alf;
    }

    public int getMatchNumber() {
        return this.aqC;
    }

    public ArrayList<Participant> getParticipants() {
        return new ArrayList(this.apW);
    }

    public String getPendingParticipantId() {
        return this.aqy;
    }

    public byte[] getPreviousMatchData() {
        return this.aqB;
    }

    public String getRematchId() {
        return this.aqA;
    }

    public int getStatus() {
        return this.aqz;
    }

    public int getTurnStatus() {
        return this.aqD;
    }

    public int getVariant() {
        return this.apX;
    }

    public int getVersion() {
        return this.Gf;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return a(this);
    }

    public boolean isLocallyModified() {
        return this.aqE;
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        TurnBasedMatchEntityCreator.a(this, out, flags);
    }
}
