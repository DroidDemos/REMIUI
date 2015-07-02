package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class UserDataType implements SafeParcelable {
    public static final UserDataType ALIASES;
    public static final l CREATOR;
    public static final UserDataType TEST_TYPE;
    public static final Set<UserDataType> VALUES;
    final int awS;
    final int mVersionCode;
    final String vc;

    static {
        TEST_TYPE = y("test_type", 1);
        ALIASES = y("labeled_place", 6);
        VALUES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new UserDataType[]{TEST_TYPE, ALIASES})));
        CREATOR = new l();
    }

    UserDataType(int versionCode, String type, int enumValue) {
        kn.bk(type);
        this.mVersionCode = versionCode;
        this.vc = type;
        this.awS = enumValue;
    }

    private static UserDataType y(String str, int i) {
        return new UserDataType(0, str, i);
    }

    public int describeContents() {
        l lVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof UserDataType)) {
            return false;
        }
        UserDataType userDataType = (UserDataType) object;
        return this.vc.equals(userDataType.vc) && this.awS == userDataType.awS;
    }

    public int hashCode() {
        return this.vc.hashCode();
    }

    public String toString() {
        return this.vc;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        l lVar = CREATOR;
        l.a(this, parcel, flags);
    }
}
