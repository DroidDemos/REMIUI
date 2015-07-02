package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.jy;
import com.google.android.gms.internal.kl;
import java.util.ArrayList;

public final class RoomEntity extends GamesDowngradeableSafeParcel implements Room {
    public static final Creator<RoomEntity> CREATOR;
    private final String akM;
    private final long apT;
    private final ArrayList<ParticipantEntity> apW;
    private final int apX;
    private final Bundle aqm;
    private final String aqp;
    private final int aqq;
    private final int aqr;
    private final String mDescription;
    private final int mVersionCode;

    static final class RoomEntityCreatorCompat extends RoomEntityCreator {
        RoomEntityCreatorCompat() {
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return eU(x0);
        }

        public RoomEntity eU(Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(jy.iM()) || jy.bf(RoomEntity.class.getCanonicalName())) {
                return super.eU(parcel);
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            long readLong = parcel.readLong();
            int readInt = parcel.readInt();
            String readString3 = parcel.readString();
            int readInt2 = parcel.readInt();
            Bundle readBundle = parcel.readBundle();
            int readInt3 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt3);
            for (int i = 0; i < readInt3; i++) {
                arrayList.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new RoomEntity(2, readString, readString2, readLong, readInt, readString3, readInt2, readBundle, arrayList, -1);
        }
    }

    static {
        CREATOR = new RoomEntityCreatorCompat();
    }

    RoomEntity(int versionCode, String roomId, String creatorId, long creationTimestamp, int roomStatus, String description, int variant, Bundle autoMatchCriteria, ArrayList<ParticipantEntity> participants, int autoMatchWaitEstimateSeconds) {
        this.mVersionCode = versionCode;
        this.akM = roomId;
        this.aqp = creatorId;
        this.apT = creationTimestamp;
        this.aqq = roomStatus;
        this.mDescription = description;
        this.apX = variant;
        this.aqm = autoMatchCriteria;
        this.apW = participants;
        this.aqr = autoMatchWaitEstimateSeconds;
    }

    public RoomEntity(Room room) {
        this.mVersionCode = 2;
        this.akM = room.getRoomId();
        this.aqp = room.getCreatorId();
        this.apT = room.getCreationTimestamp();
        this.aqq = room.getStatus();
        this.mDescription = room.getDescription();
        this.apX = room.getVariant();
        this.aqm = room.getAutoMatchCriteria();
        ArrayList participants = room.getParticipants();
        int size = participants.size();
        this.apW = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            this.apW.add((ParticipantEntity) ((Participant) participants.get(i)).freeze());
        }
        this.aqr = room.getAutoMatchWaitEstimateSeconds();
    }

    static int a(Room room) {
        return kl.hashCode(room.getRoomId(), room.getCreatorId(), Long.valueOf(room.getCreationTimestamp()), Integer.valueOf(room.getStatus()), room.getDescription(), Integer.valueOf(room.getVariant()), room.getAutoMatchCriteria(), room.getParticipants(), Integer.valueOf(room.getAutoMatchWaitEstimateSeconds()));
    }

    static boolean a(Room room, Object obj) {
        if (!(obj instanceof Room)) {
            return false;
        }
        if (room == obj) {
            return true;
        }
        Room room2 = (Room) obj;
        return kl.equal(room2.getRoomId(), room.getRoomId()) && kl.equal(room2.getCreatorId(), room.getCreatorId()) && kl.equal(Long.valueOf(room2.getCreationTimestamp()), Long.valueOf(room.getCreationTimestamp())) && kl.equal(Integer.valueOf(room2.getStatus()), Integer.valueOf(room.getStatus())) && kl.equal(room2.getDescription(), room.getDescription()) && kl.equal(Integer.valueOf(room2.getVariant()), Integer.valueOf(room.getVariant())) && kl.equal(room2.getAutoMatchCriteria(), room.getAutoMatchCriteria()) && kl.equal(room2.getParticipants(), room.getParticipants()) && kl.equal(Integer.valueOf(room2.getAutoMatchWaitEstimateSeconds()), Integer.valueOf(room.getAutoMatchWaitEstimateSeconds()));
    }

    static String b(Room room) {
        return kl.j(room).a("RoomId", room.getRoomId()).a("CreatorId", room.getCreatorId()).a("CreationTimestamp", Long.valueOf(room.getCreationTimestamp())).a("RoomStatus", Integer.valueOf(room.getStatus())).a("Description", room.getDescription()).a("Variant", Integer.valueOf(room.getVariant())).a("AutoMatchCriteria", room.getAutoMatchCriteria()).a("Participants", room.getParticipants()).a("AutoMatchWaitEstimateSeconds", Integer.valueOf(room.getAutoMatchWaitEstimateSeconds())).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Room freeze() {
        return this;
    }

    public Bundle getAutoMatchCriteria() {
        return this.aqm;
    }

    public int getAutoMatchWaitEstimateSeconds() {
        return this.aqr;
    }

    public long getCreationTimestamp() {
        return this.apT;
    }

    public String getCreatorId() {
        return this.aqp;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public ArrayList<Participant> getParticipants() {
        return new ArrayList(this.apW);
    }

    public String getRoomId() {
        return this.akM;
    }

    public int getStatus() {
        return this.aqq;
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
        return b((Room) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (iN()) {
            dest.writeString(this.akM);
            dest.writeString(this.aqp);
            dest.writeLong(this.apT);
            dest.writeInt(this.aqq);
            dest.writeString(this.mDescription);
            dest.writeInt(this.apX);
            dest.writeBundle(this.aqm);
            int size = this.apW.size();
            dest.writeInt(size);
            for (int i = 0; i < size; i++) {
                ((ParticipantEntity) this.apW.get(i)).writeToParcel(dest, flags);
            }
            return;
        }
        RoomEntityCreator.a(this, dest, flags);
    }
}
