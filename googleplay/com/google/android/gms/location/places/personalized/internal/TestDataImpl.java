package com.google.android.gms.location.places.personalized.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.location.places.personalized.TestData;

public class TestDataImpl extends TestData implements SafeParcelable {
    public static final a CREATOR;
    private final String axA;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    TestDataImpl(int versionCode, String testName) {
        this.mVersionCode = versionCode;
        this.axA = testName;
    }

    public int describeContents() {
        a aVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof TestDataImpl)) {
            return false;
        }
        return this.axA.equals(((TestDataImpl) object).axA);
    }

    public String getTestName() {
        return this.axA;
    }

    public int hashCode() {
        return this.axA.hashCode();
    }

    public String toString() {
        return kl.j(this).a("testName", this.axA).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        a aVar = CREATOR;
        a.a(this, parcel, flags);
    }
}
