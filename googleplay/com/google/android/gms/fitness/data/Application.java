package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kn;
import com.google.android.gms.internal.mz;

public final class Application implements SafeParcelable {
    public static final Creator<Application> CREATOR;
    public static final Application GOOGLE_PLAY_SERVICES;
    private final String CN;
    private final String agg;
    private final String mPackageName;
    private final int mVersionCode;

    static {
        GOOGLE_PLAY_SERVICES = new Application("com.google.android.gms", String.valueOf(6587000), null);
        CREATOR = new a();
    }

    Application(int versionCode, String packageName, String version, String domainName) {
        this.mVersionCode = versionCode;
        this.mPackageName = (String) kn.k(packageName);
        this.CN = "";
        this.agg = domainName;
    }

    public Application(String packageName, String version, String domainName) {
        this(1, packageName, "", domainName);
    }

    private boolean a(Application application) {
        return this.mPackageName.equals(application.mPackageName) && kl.equal(this.CN, application.CN) && kl.equal(this.agg, application.agg);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof Application) && a((Application) that));
    }

    public String getDomainName() {
        return this.agg;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getVersion() {
        return this.CN;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.mPackageName, this.CN, this.agg);
    }

    Application lm() {
        return new Application(mz.bP(this.mPackageName), mz.bP(this.CN), mz.bP(this.agg));
    }

    public String toString() {
        return String.format("Application{%s:%s:%s}", new Object[]{this.mPackageName, this.CN, this.agg});
    }

    public void writeToParcel(Parcel parcel, int flags) {
        a.a(this, parcel, flags);
    }
}
