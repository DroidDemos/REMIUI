package com.google.android.gms.games.appcontent;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public final class AppContentTupleEntity implements SafeParcelable, AppContentTuple {
    public static final AppContentTupleEntityCreator CREATOR;
    private final String mName;
    private final String mValue;
    private final int mVersionCode;

    static {
        CREATOR = new AppContentTupleEntityCreator();
    }

    AppContentTupleEntity(int versionCode, String name, String value) {
        this.mVersionCode = versionCode;
        this.mName = name;
        this.mValue = value;
    }

    public AppContentTupleEntity(AppContentTuple tuple) {
        this.mVersionCode = 1;
        this.mName = tuple.getName();
        this.mValue = tuple.getValue();
    }

    static int a(AppContentTuple appContentTuple) {
        return kl.hashCode(appContentTuple.getName(), appContentTuple.getValue());
    }

    static boolean a(AppContentTuple appContentTuple, Object obj) {
        if (!(obj instanceof AppContentTuple)) {
            return false;
        }
        if (appContentTuple == obj) {
            return true;
        }
        AppContentTuple appContentTuple2 = (AppContentTuple) obj;
        return kl.equal(appContentTuple2.getName(), appContentTuple.getName()) && kl.equal(appContentTuple2.getValue(), appContentTuple.getValue());
    }

    static String b(AppContentTuple appContentTuple) {
        return kl.j(appContentTuple).a("Name", appContentTuple.getName()).a("Value", appContentTuple.getValue()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return mS();
    }

    public String getName() {
        return this.mName;
    }

    public String getValue() {
        return this.mValue;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return a(this);
    }

    public AppContentTuple mS() {
        return this;
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        AppContentTupleEntityCreator.a(this, out, flags);
    }
}
