package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.jy;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kn;
import java.util.ArrayList;

public final class InvitationEntity extends GamesDowngradeableSafeParcel implements Invitation {
    public static final Creator<InvitationEntity> CREATOR;
    private final String akK;
    private final GameEntity aot;
    private final long apT;
    private final int apU;
    private final ParticipantEntity apV;
    private final ArrayList<ParticipantEntity> apW;
    private final int apX;
    private final int apY;
    private final int mVersionCode;

    static final class InvitationEntityCreatorCompat extends InvitationEntityCreator {
        InvitationEntityCreatorCompat() {
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return eR(x0);
        }

        public InvitationEntity eR(Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(jy.iM()) || jy.bf(InvitationEntity.class.getCanonicalName())) {
                return super.eR(parcel);
            }
            GameEntity gameEntity = (GameEntity) GameEntity.CREATOR.createFromParcel(parcel);
            String readString = parcel.readString();
            long readLong = parcel.readLong();
            int readInt = parcel.readInt();
            ParticipantEntity participantEntity = (ParticipantEntity) ParticipantEntity.CREATOR.createFromParcel(parcel);
            int readInt2 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt2);
            for (int i = 0; i < readInt2; i++) {
                arrayList.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new InvitationEntity(2, gameEntity, readString, readLong, readInt, participantEntity, arrayList, -1, 0);
        }
    }

    static {
        CREATOR = new InvitationEntityCreatorCompat();
    }

    InvitationEntity(int versionCode, GameEntity game, String invitationId, long creationTimestamp, int invitationType, ParticipantEntity inviter, ArrayList<ParticipantEntity> participants, int variant, int availableAutoMatchSlots) {
        this.mVersionCode = versionCode;
        this.aot = game;
        this.akK = invitationId;
        this.apT = creationTimestamp;
        this.apU = invitationType;
        this.apV = inviter;
        this.apW = participants;
        this.apX = variant;
        this.apY = availableAutoMatchSlots;
    }

    InvitationEntity(Invitation invitation) {
        this.mVersionCode = 2;
        this.aot = new GameEntity(invitation.getGame());
        this.akK = invitation.getInvitationId();
        this.apT = invitation.getCreationTimestamp();
        this.apU = invitation.getInvitationType();
        this.apX = invitation.getVariant();
        this.apY = invitation.getAvailableAutoMatchSlots();
        String participantId = invitation.getInviter().getParticipantId();
        Object obj = null;
        ArrayList participants = invitation.getParticipants();
        int size = participants.size();
        this.apW = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            Participant participant = (Participant) participants.get(i);
            if (participant.getParticipantId().equals(participantId)) {
                obj = participant;
            }
            this.apW.add((ParticipantEntity) participant.freeze());
        }
        kn.b(obj, (Object) "Must have a valid inviter!");
        this.apV = (ParticipantEntity) obj.freeze();
    }

    static int a(Invitation invitation) {
        return kl.hashCode(invitation.getGame(), invitation.getInvitationId(), Long.valueOf(invitation.getCreationTimestamp()), Integer.valueOf(invitation.getInvitationType()), invitation.getInviter(), invitation.getParticipants(), Integer.valueOf(invitation.getVariant()), Integer.valueOf(invitation.getAvailableAutoMatchSlots()));
    }

    static boolean a(Invitation invitation, Object obj) {
        if (!(obj instanceof Invitation)) {
            return false;
        }
        if (invitation == obj) {
            return true;
        }
        Invitation invitation2 = (Invitation) obj;
        return kl.equal(invitation2.getGame(), invitation.getGame()) && kl.equal(invitation2.getInvitationId(), invitation.getInvitationId()) && kl.equal(Long.valueOf(invitation2.getCreationTimestamp()), Long.valueOf(invitation.getCreationTimestamp())) && kl.equal(Integer.valueOf(invitation2.getInvitationType()), Integer.valueOf(invitation.getInvitationType())) && kl.equal(invitation2.getInviter(), invitation.getInviter()) && kl.equal(invitation2.getParticipants(), invitation.getParticipants()) && kl.equal(Integer.valueOf(invitation2.getVariant()), Integer.valueOf(invitation.getVariant())) && kl.equal(Integer.valueOf(invitation2.getAvailableAutoMatchSlots()), Integer.valueOf(invitation.getAvailableAutoMatchSlots()));
    }

    static String b(Invitation invitation) {
        return kl.j(invitation).a("Game", invitation.getGame()).a("InvitationId", invitation.getInvitationId()).a("CreationTimestamp", Long.valueOf(invitation.getCreationTimestamp())).a("InvitationType", Integer.valueOf(invitation.getInvitationType())).a("Inviter", invitation.getInviter()).a("Participants", invitation.getParticipants()).a("Variant", Integer.valueOf(invitation.getVariant())).a("AvailableAutoMatchSlots", Integer.valueOf(invitation.getAvailableAutoMatchSlots())).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Invitation freeze() {
        return this;
    }

    public int getAvailableAutoMatchSlots() {
        return this.apY;
    }

    public long getCreationTimestamp() {
        return this.apT;
    }

    public Game getGame() {
        return this.aot;
    }

    public String getInvitationId() {
        return this.akK;
    }

    public int getInvitationType() {
        return this.apU;
    }

    public Participant getInviter() {
        return this.apV;
    }

    public ArrayList<Participant> getParticipants() {
        return new ArrayList(this.apW);
    }

    public int getVariant() {
        return this.apX;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return a(this);
    }

    public String toString() {
        return b((Invitation) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (iN()) {
            this.aot.writeToParcel(dest, flags);
            dest.writeString(this.akK);
            dest.writeLong(this.apT);
            dest.writeInt(this.apU);
            this.apV.writeToParcel(dest, flags);
            int size = this.apW.size();
            dest.writeInt(size);
            for (int i = 0; i < size; i++) {
                ((ParticipantEntity) this.apW.get(i)).writeToParcel(dest, flags);
            }
            return;
        }
        InvitationEntityCreator.a(this, dest, flags);
    }
}
