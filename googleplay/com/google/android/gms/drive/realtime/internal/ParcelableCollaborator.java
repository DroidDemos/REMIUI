package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ParcelableCollaborator implements SafeParcelable {
    public static final Creator<ParcelableCollaborator> CREATOR;
    final String WN;
    final String ada;
    final boolean aeh;
    final boolean aei;
    final String aej;
    final String aek;
    final int mVersionCode;
    final String vY;

    static {
        CREATOR = new ag();
    }

    ParcelableCollaborator(int versionCode, boolean isMe, boolean isAnonymous, String sessionId, String userId, String displayName, String color, String photoUrl) {
        this.mVersionCode = versionCode;
        this.aeh = isMe;
        this.aei = isAnonymous;
        this.vY = sessionId;
        this.ada = userId;
        this.WN = displayName;
        this.aej = color;
        this.aek = photoUrl;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ParcelableCollaborator)) {
            return false;
        }
        return this.vY.equals(((ParcelableCollaborator) obj).vY);
    }

    public int hashCode() {
        return this.vY.hashCode();
    }

    public String toString() {
        return "Collaborator [isMe=" + this.aeh + ", isAnonymous=" + this.aei + ", sessionId=" + this.vY + ", userId=" + this.ada + ", displayName=" + this.WN + ", color=" + this.aej + ", photoUrl=" + this.aek + "]";
    }

    public void writeToParcel(Parcel dest, int flags) {
        ag.a(this, dest, flags);
    }
}
