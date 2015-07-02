package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;

public class People implements SafeParcelable {
    public static final People ALL;
    public static final Creator<People> CREATOR;
    public static final People NONE;
    private final List<String> aus;
    private final int aut;
    private final int mVersionCode;

    static {
        CREATOR = new l();
        NONE = new People(1);
        ALL = new People(2);
    }

    private People(int group) {
        this(1, null, group);
    }

    People(int versionCode, List<String> personIds, int group) {
        this.mVersionCode = versionCode;
        this.aus = personIds == null ? null : Collections.unmodifiableList(personIds);
        this.aut = group;
    }

    public int describeContents() {
        return 0;
    }

    public int getGroup() {
        return this.aut;
    }

    public List<String> getPersonIds() {
        return this.aus;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        l.a(this, dest, flags);
    }
}
