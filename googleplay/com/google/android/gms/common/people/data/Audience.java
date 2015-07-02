package com.google.android.gms.common.people.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.Collections;
import java.util.List;

public final class Audience implements SafeParcelable {
    public static final a CREATOR;
    private final List<AudienceMember> WG;
    private final int WH;
    @Deprecated
    private final boolean WI;
    private final boolean WJ;
    private final int mVersionCode;

    static {
        CREATOR = new a();
    }

    Audience(int versionCode, List<AudienceMember> audienceMembers, int domainRestricted, boolean fullyUnderstood, boolean readOnly) {
        boolean z = true;
        if (versionCode == 1 && audienceMembers == null) {
            audienceMembers = Collections.emptyList();
        }
        this.mVersionCode = versionCode;
        this.WG = Collections.unmodifiableList(audienceMembers);
        this.WH = domainRestricted;
        if (versionCode == 1) {
            this.WI = fullyUnderstood;
            if (fullyUnderstood) {
                z = false;
            }
            this.WJ = z;
            return;
        }
        this.WJ = readOnly;
        if (readOnly) {
            z = false;
        }
        this.WI = z;
    }

    Audience(List<AudienceMember> audienceMembers, int domainRestricted, boolean readOnly) {
        this.mVersionCode = 2;
        this.WG = audienceMembers;
        this.WH = domainRestricted;
        this.WJ = readOnly;
        this.WI = !readOnly;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Audience)) {
            return false;
        }
        Audience audience = (Audience) obj;
        return this.mVersionCode == audience.mVersionCode && kl.equal(this.WG, audience.WG) && this.WH == audience.WH && this.WJ == audience.WJ;
    }

    public List<AudienceMember> getAudienceMemberList() {
        return this.WG;
    }

    public int getDomainRestricted() {
        return this.WH;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.mVersionCode), this.WG, Integer.valueOf(this.WH), Boolean.valueOf(this.WJ));
    }

    @Deprecated
    boolean iY() {
        return this.WI;
    }

    public boolean isReadOnly() {
        return this.WJ;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
