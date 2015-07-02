package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.internal.constants.MatchResult;
import com.google.android.gms.internal.kn;

public final class ParticipantResult implements SafeParcelable {
    public static final ParticipantResultCreator CREATOR;
    private final int adZ;
    private final String alo;
    private final int aqd;
    private final int mVersionCode;

    static {
        CREATOR = new ParticipantResultCreator();
    }

    public ParticipantResult(int versionCode, String participantId, int result, int placing) {
        this.mVersionCode = versionCode;
        this.alo = (String) kn.k(participantId);
        kn.K(MatchResult.isValid(result));
        this.adZ = result;
        this.aqd = placing;
    }

    public ParticipantResult(String participantId, int result, int placing) {
        this(1, participantId, result, placing);
    }

    public int describeContents() {
        return 0;
    }

    public String getParticipantId() {
        return this.alo;
    }

    public int getPlacing() {
        return this.aqd;
    }

    public int getResult() {
        return this.adZ;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel out, int flags) {
        ParticipantResultCreator.a(this, out, flags);
    }
}
