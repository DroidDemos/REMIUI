package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AclResourceId implements SafeParcelable {
    public static final Creator<AclResourceId> CREATOR;
    private final String CB;
    private final String atQ;
    private final String atR;
    private final int mVersionCode;

    static {
        CREATOR = new d();
    }

    AclResourceId(int versionCode, String application, String id, String part) {
        this.mVersionCode = versionCode;
        this.atQ = application;
        this.CB = id;
        this.atR = part;
    }

    public int describeContents() {
        return 0;
    }

    public String getApplication() {
        return this.atQ;
    }

    public String getId() {
        return this.CB;
    }

    public String getPart() {
        return this.atR;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        return String.format("AclResourceId{application = %s, id = %s, part = %s}", new Object[]{this.atQ, this.CB, this.atR});
    }

    public void writeToParcel(Parcel dest, int flags) {
        d.a(this, dest, flags);
    }
}
