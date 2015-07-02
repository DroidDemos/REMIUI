package com.google.android.gms.games.internal.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.kl;
import java.util.ArrayList;

public final class ZInvitationCluster implements SafeParcelable, Invitation {
    public static final InvitationClusterCreator CREATOR;
    private final ArrayList<InvitationEntity> aoF;
    private final int mVersionCode;

    static {
        CREATOR = new InvitationClusterCreator();
    }

    ZInvitationCluster(int versionCode, ArrayList<InvitationEntity> invitationList) {
        this.mVersionCode = versionCode;
        this.aoF = invitationList;
        oe();
    }

    private void oe() {
        ju.K(!this.aoF.isEmpty());
        Invitation invitation = (Invitation) this.aoF.get(0);
        int size = this.aoF.size();
        for (int i = 1; i < size; i++) {
            ju.a(invitation.getInviter().equals(((Invitation) this.aoF.get(i)).getInviter()), "All the invitations must be from the same inviter");
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ZInvitationCluster)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ZInvitationCluster zInvitationCluster = (ZInvitationCluster) obj;
        if (zInvitationCluster.aoF.size() != this.aoF.size()) {
            return false;
        }
        int size = this.aoF.size();
        for (int i = 0; i < size; i++) {
            if (!((Invitation) this.aoF.get(i)).equals((Invitation) zInvitationCluster.aoF.get(i))) {
                return false;
            }
        }
        return true;
    }

    public Invitation freeze() {
        return this;
    }

    public int getAvailableAutoMatchSlots() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public long getCreationTimestamp() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public Game getGame() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public String getInvitationId() {
        return ((InvitationEntity) this.aoF.get(0)).getInvitationId();
    }

    public int getInvitationType() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public Participant getInviter() {
        return ((InvitationEntity) this.aoF.get(0)).getInviter();
    }

    public ArrayList<Participant> getParticipants() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public int getVariant() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.aoF.toArray());
    }

    public ArrayList<Invitation> of() {
        return new ArrayList(this.aoF);
    }

    public void writeToParcel(Parcel dest, int flags) {
        InvitationClusterCreator.a(this, dest, flags);
    }
}
