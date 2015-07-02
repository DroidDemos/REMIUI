package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class Session implements SafeParcelable {
    public static final Creator<Session> CREATOR;
    private final long Va;
    private final String agV;
    private final long agb;
    private final int agk;
    private final Application agx;
    private final String mDescription;
    private final String mName;
    private final int mVersionCode;

    static {
        CREATOR = new o();
    }

    Session(int versionCode, long startTimeMillis, long endTimeMillis, String name, String identifier, String description, int activityType, Application application) {
        this.mVersionCode = versionCode;
        this.Va = startTimeMillis;
        this.agb = endTimeMillis;
        this.mName = name;
        this.agV = identifier;
        this.mDescription = description;
        this.agk = activityType;
        this.agx = application;
    }

    private boolean a(Session session) {
        return this.Va == session.Va && this.agb == session.agb && kl.equal(this.mName, session.mName) && kl.equal(this.agV, session.agV) && kl.equal(this.mDescription, session.mDescription) && kl.equal(this.agx, session.agx) && this.agk == session.agk;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof Session) && a((Session) o));
    }

    public Application getApplication() {
        return this.agx;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getIdentifier() {
        return this.agV;
    }

    public String getName() {
        return this.mName;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Long.valueOf(this.Va), Long.valueOf(this.agb), this.agV);
    }

    public int ln() {
        return this.agk;
    }

    public long lo() {
        return this.Va;
    }

    public long lp() {
        return this.agb;
    }

    public String toString() {
        return kl.j(this).a("startTime", Long.valueOf(this.Va)).a("endTime", Long.valueOf(this.agb)).a("name", this.mName).a("identifier", this.agV).a("description", this.mDescription).a("activity", Integer.valueOf(this.agk)).a("application", this.agx).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        o.a(this, dest, flags);
    }
}
