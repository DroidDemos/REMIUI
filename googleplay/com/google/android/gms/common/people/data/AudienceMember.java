package com.google.android.gms.common.people.data;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.rp;

public final class AudienceMember implements SafeParcelable {
    public static final b CREATOR;
    private final int EB;
    @Deprecated
    private final Bundle Ue;
    private final int WK;
    private final String WL;
    private final String WM;
    private final String WN;
    private final String WO;
    private final int mVersionCode;

    static {
        CREATOR = new b();
    }

    AudienceMember(int versionCode, int type, int circleType, String circleId, String peopleQualifiedId, String displayName, String avatarUrl, Bundle metadata) {
        this.mVersionCode = versionCode;
        this.EB = type;
        this.WK = circleType;
        this.WL = circleId;
        this.WM = peopleQualifiedId;
        this.WN = displayName;
        this.WO = avatarUrl;
        if (metadata == null) {
            metadata = new Bundle();
        }
        this.Ue = metadata;
    }

    private AudienceMember(int type, int circleType, String circleId, String qualifiedPersonId, String displayName, String avatarUrl) {
        this(1, type, circleType, circleId, qualifiedPersonId, displayName, avatarUrl, null);
    }

    public static AudienceMember forCircle(String circleId, String displayName) {
        return new AudienceMember(1, -1, circleId, null, displayName, null);
    }

    public static AudienceMember forGroup(String groupName, String displayName) {
        Integer num = (Integer) rp.aCX.get(groupName);
        return new AudienceMember(1, (num == null ? (Integer) rp.aCX.get(null) : num).intValue(), groupName, null, displayName, null);
    }

    public static AudienceMember forPersonWithPeopleQualifiedId(String peopleQualifiedId, String displayName, String avatarUrl) {
        return new AudienceMember(2, 0, null, peopleQualifiedId, displayName, avatarUrl);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudienceMember)) {
            return false;
        }
        AudienceMember audienceMember = (AudienceMember) obj;
        return this.mVersionCode == audienceMember.mVersionCode && this.EB == audienceMember.EB && this.WK == audienceMember.WK && kl.equal(this.WL, audienceMember.WL) && kl.equal(this.WM, audienceMember.WM);
    }

    public String getAvatarUrl() {
        return this.WO;
    }

    public String getCircleId() {
        return this.WL;
    }

    public int getCircleType() {
        return this.WK;
    }

    public String getDisplayName() {
        return this.WN;
    }

    @Deprecated
    public Bundle getMetadata() {
        return this.Ue;
    }

    public String getPeopleQualifiedId() {
        return this.WM;
    }

    public int getType() {
        return this.EB;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.mVersionCode), Integer.valueOf(this.EB), Integer.valueOf(this.WK), this.WL, this.WM);
    }

    public boolean isPerson() {
        return this.EB == 2;
    }

    public boolean isPersonalCircle() {
        return this.EB == 1 && this.WK == -1;
    }

    public String toString() {
        if (isPerson()) {
            return String.format("Person [%s] %s", new Object[]{getPeopleQualifiedId(), getDisplayName()});
        } else if (isPersonalCircle()) {
            return String.format("Circle [%s] %s", new Object[]{getCircleId(), getDisplayName()});
        } else {
            return String.format("Group [%s] %s", new Object[]{getCircleId(), getDisplayName()});
        }
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
