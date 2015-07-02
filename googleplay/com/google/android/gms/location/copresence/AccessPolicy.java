package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.people.data.Audience;
import com.google.android.gms.common.people.data.AudienceBuilder;
import com.google.android.gms.common.people.data.AudienceMember;
import java.util.Collections;

public class AccessPolicy implements SafeParcelable {
    public static final Creator<AccessPolicy> CREATOR;
    private static final Audience atJ;
    private final Audience TK;
    private final int atK;
    private final long atL;
    private final AccessLock atM;
    private final int atN;
    private final int atO;
    private final AclResourceId atP;
    private final String mName;
    private final int mVersionCode;

    static {
        CREATOR = new c();
        atJ = new AudienceBuilder().setAudienceMembers(Collections.singleton(AudienceMember.forGroup("public", "Public"))).build();
    }

    AccessPolicy(int versionCode, int fieldMask, String name, long timeToLiveMillis, AccessLock accessLock, Audience audience, int distanceType, int copresenceType, AclResourceId aclResourceId) {
        this.mVersionCode = versionCode;
        this.atK = fieldMask;
        this.mName = name;
        this.atL = timeToLiveMillis;
        this.atM = accessLock;
        this.TK = audience;
        this.atN = distanceType;
        this.atO = copresenceType;
        this.atP = aclResourceId;
    }

    public int describeContents() {
        return 0;
    }

    public AccessLock getAccessLock() {
        return this.atM;
    }

    public AclResourceId getAclResourceId() {
        return this.atP;
    }

    public Audience getAudience() {
        return this.TK;
    }

    public int getCopresenceType() {
        return this.atO;
    }

    public int getDistanceType() {
        return this.atN;
    }

    public String getName() {
        return this.mName;
    }

    public long getTimeToLiveMillis() {
        return this.atL;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    int pa() {
        return this.atK;
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
