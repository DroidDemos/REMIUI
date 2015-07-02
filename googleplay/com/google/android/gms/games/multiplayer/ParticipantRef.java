package com.google.android.gms.games.multiplayer;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.c;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class ParticipantRef extends c implements Participant {
    private final PlayerRef aqc;

    public ParticipantRef(DataHolder holder, int dataRow) {
        super(holder, dataRow);
        this.aqc = new PlayerRef(holder, dataRow);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return ParticipantEntity.a(this, obj);
    }

    public Participant freeze() {
        return new ParticipantEntity(this);
    }

    public int getCapabilities() {
        return getInteger("capabilities");
    }

    public String getDisplayName() {
        return bb("external_player_id") ? getString("default_display_name") : this.aqc.getDisplayName();
    }

    public Uri getHiResImageUri() {
        return bb("external_player_id") ? ba("default_display_hi_res_image_uri") : this.aqc.getHiResImageUri();
    }

    public String getHiResImageUrl() {
        return bb("external_player_id") ? getString("default_display_hi_res_image_url") : this.aqc.getHiResImageUrl();
    }

    public Uri getIconImageUri() {
        return bb("external_player_id") ? ba("default_display_image_uri") : this.aqc.getIconImageUri();
    }

    public String getIconImageUrl() {
        return bb("external_player_id") ? getString("default_display_image_url") : this.aqc.getIconImageUrl();
    }

    public String getParticipantId() {
        return getString("external_participant_id");
    }

    public Player getPlayer() {
        return bb("external_player_id") ? null : this.aqc;
    }

    public ParticipantResult getResult() {
        if (bb("result_type")) {
            return null;
        }
        return new ParticipantResult(getParticipantId(), getInteger("result_type"), getInteger("placing"));
    }

    public int getStatus() {
        return getInteger("player_status");
    }

    public int hashCode() {
        return ParticipantEntity.a(this);
    }

    public boolean isConnectedToRoom() {
        return getInteger("connected") > 0;
    }

    public String mT() {
        return getString("client_address");
    }

    public String toString() {
        return ParticipantEntity.b((Participant) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((ParticipantEntity) freeze()).writeToParcel(dest, flags);
    }
}
