package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.im;
import com.google.android.gms.internal.kl;
import java.util.Locale;

public class LaunchOptions implements SafeParcelable {
    public static final Creator<LaunchOptions> CREATOR;
    private boolean Od;
    private String Oe;
    private final int mVersionCode;

    static {
        CREATOR = new c();
    }

    public LaunchOptions() {
        this(1, false, im.b(Locale.getDefault()));
    }

    LaunchOptions(int versionCode, boolean relaunchIfRunning, String language) {
        this.mVersionCode = versionCode;
        this.Od = relaunchIfRunning;
        this.Oe = language;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LaunchOptions)) {
            return false;
        }
        LaunchOptions launchOptions = (LaunchOptions) obj;
        return this.Od == launchOptions.Od && im.a(this.Oe, launchOptions.Oe);
    }

    public String getLanguage() {
        return this.Oe;
    }

    public boolean getRelaunchIfRunning() {
        return this.Od;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Boolean.valueOf(this.Od), this.Oe);
    }

    public String toString() {
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", new Object[]{Boolean.valueOf(this.Od), this.Oe});
    }

    public void writeToParcel(Parcel out, int flags) {
        c.a(this, out, flags);
    }
}
