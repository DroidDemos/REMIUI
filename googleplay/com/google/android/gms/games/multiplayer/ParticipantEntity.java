package com.google.android.gms.games.multiplayer;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.jy;
import com.google.android.gms.internal.kl;

public final class ParticipantEntity extends GamesDowngradeableSafeParcel implements Participant {
    public static final Creator<ParticipantEntity> CREATOR;
    private final int Oa;
    private final int Ob;
    private final String WN;
    private final Uri aiK;
    private final Uri aiL;
    private final String aiV;
    private final String aiW;
    private final PlayerEntity ajJ;
    private final String akn;
    private final String alo;
    private final boolean aqa;
    private final ParticipantResult aqb;
    private final int mVersionCode;

    static final class ParticipantEntityCreatorCompat extends ParticipantEntityCreator {
        ParticipantEntityCreatorCompat() {
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return eS(x0);
        }

        public ParticipantEntity eS(Parcel parcel) {
            Object obj = 1;
            if (GamesDowngradeableSafeParcel.c(jy.iM()) || jy.bf(ParticipantEntity.class.getCanonicalName())) {
                return super.eS(parcel);
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            Uri parse = readString3 == null ? null : Uri.parse(readString3);
            String readString4 = parcel.readString();
            Uri parse2 = readString4 == null ? null : Uri.parse(readString4);
            int readInt = parcel.readInt();
            String readString5 = parcel.readString();
            boolean z = parcel.readInt() > 0;
            if (parcel.readInt() <= 0) {
                obj = null;
            }
            return new ParticipantEntity(3, readString, readString2, parse, parse2, readInt, readString5, z, obj != null ? (PlayerEntity) PlayerEntity.CREATOR.createFromParcel(parcel) : null, 7, null, null, null);
        }
    }

    static {
        CREATOR = new ParticipantEntityCreatorCompat();
    }

    ParticipantEntity(int versionCode, String participantId, String displayName, Uri iconImageUri, Uri hiResImageUri, int status, String clientAddress, boolean connectedToRoom, PlayerEntity player, int capabilities, ParticipantResult result, String iconImageUrl, String hiResImageUrl) {
        this.mVersionCode = versionCode;
        this.alo = participantId;
        this.WN = displayName;
        this.aiK = iconImageUri;
        this.aiL = hiResImageUri;
        this.Ob = status;
        this.akn = clientAddress;
        this.aqa = connectedToRoom;
        this.ajJ = player;
        this.Oa = capabilities;
        this.aqb = result;
        this.aiV = iconImageUrl;
        this.aiW = hiResImageUrl;
    }

    public ParticipantEntity(Participant participant) {
        this.mVersionCode = 3;
        this.alo = participant.getParticipantId();
        this.WN = participant.getDisplayName();
        this.aiK = participant.getIconImageUri();
        this.aiL = participant.getHiResImageUri();
        this.Ob = participant.getStatus();
        this.akn = participant.mT();
        this.aqa = participant.isConnectedToRoom();
        Player player = participant.getPlayer();
        this.ajJ = player == null ? null : new PlayerEntity(player);
        this.Oa = participant.getCapabilities();
        this.aqb = participant.getResult();
        this.aiV = participant.getIconImageUrl();
        this.aiW = participant.getHiResImageUrl();
    }

    static int a(Participant participant) {
        return kl.hashCode(participant.getPlayer(), Integer.valueOf(participant.getStatus()), participant.mT(), Boolean.valueOf(participant.isConnectedToRoom()), participant.getDisplayName(), participant.getIconImageUri(), participant.getHiResImageUri(), Integer.valueOf(participant.getCapabilities()), participant.getResult(), participant.getParticipantId());
    }

    static boolean a(Participant participant, Object obj) {
        if (!(obj instanceof Participant)) {
            return false;
        }
        if (participant == obj) {
            return true;
        }
        Participant participant2 = (Participant) obj;
        return kl.equal(participant2.getPlayer(), participant.getPlayer()) && kl.equal(Integer.valueOf(participant2.getStatus()), Integer.valueOf(participant.getStatus())) && kl.equal(participant2.mT(), participant.mT()) && kl.equal(Boolean.valueOf(participant2.isConnectedToRoom()), Boolean.valueOf(participant.isConnectedToRoom())) && kl.equal(participant2.getDisplayName(), participant.getDisplayName()) && kl.equal(participant2.getIconImageUri(), participant.getIconImageUri()) && kl.equal(participant2.getHiResImageUri(), participant.getHiResImageUri()) && kl.equal(Integer.valueOf(participant2.getCapabilities()), Integer.valueOf(participant.getCapabilities())) && kl.equal(participant2.getResult(), participant.getResult()) && kl.equal(participant2.getParticipantId(), participant.getParticipantId());
    }

    static String b(Participant participant) {
        return kl.j(participant).a("ParticipantId", participant.getParticipantId()).a("Player", participant.getPlayer()).a("Status", Integer.valueOf(participant.getStatus())).a("ClientAddress", participant.mT()).a("ConnectedToRoom", Boolean.valueOf(participant.isConnectedToRoom())).a("DisplayName", participant.getDisplayName()).a("IconImage", participant.getIconImageUri()).a("IconImageUrl", participant.getIconImageUrl()).a("HiResImage", participant.getHiResImageUri()).a("HiResImageUrl", participant.getHiResImageUrl()).a("Capabilities", Integer.valueOf(participant.getCapabilities())).a("Result", participant.getResult()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Participant freeze() {
        return this;
    }

    public int getCapabilities() {
        return this.Oa;
    }

    public String getDisplayName() {
        return this.ajJ == null ? this.WN : this.ajJ.getDisplayName();
    }

    public Uri getHiResImageUri() {
        return this.ajJ == null ? this.aiL : this.ajJ.getHiResImageUri();
    }

    public String getHiResImageUrl() {
        return this.ajJ == null ? this.aiW : this.ajJ.getHiResImageUrl();
    }

    public Uri getIconImageUri() {
        return this.ajJ == null ? this.aiK : this.ajJ.getIconImageUri();
    }

    public String getIconImageUrl() {
        return this.ajJ == null ? this.aiV : this.ajJ.getIconImageUrl();
    }

    public String getParticipantId() {
        return this.alo;
    }

    public Player getPlayer() {
        return this.ajJ;
    }

    public ParticipantResult getResult() {
        return this.aqb;
    }

    public int getStatus() {
        return this.Ob;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return a(this);
    }

    public boolean isConnectedToRoom() {
        return this.aqa;
    }

    public String mT() {
        return this.akn;
    }

    public String toString() {
        return b((Participant) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        String str = null;
        int i = 0;
        if (iN()) {
            dest.writeString(this.alo);
            dest.writeString(this.WN);
            dest.writeString(this.aiK == null ? null : this.aiK.toString());
            if (this.aiL != null) {
                str = this.aiL.toString();
            }
            dest.writeString(str);
            dest.writeInt(this.Ob);
            dest.writeString(this.akn);
            dest.writeInt(this.aqa ? 1 : 0);
            if (this.ajJ != null) {
                i = 1;
            }
            dest.writeInt(i);
            if (this.ajJ != null) {
                this.ajJ.writeToParcel(dest, flags);
                return;
            }
            return;
        }
        ParticipantEntityCreator.a(this, dest, flags);
    }
}
