package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class te implements SafeParcelable {
    public static final tf CREATOR;
    public final int aHl;
    public final int aHm;
    public final String aHn;
    public final String aHo;
    public final boolean aHp;
    public final String aHq;
    public final String packageName;
    public final int versionCode;

    static {
        CREATOR = new tf();
    }

    public te(int i, String str, int i2, int i3, String str2, String str3, boolean z, String str4) {
        this.versionCode = i;
        this.packageName = str;
        this.aHl = i2;
        this.aHm = i3;
        this.aHn = str2;
        this.aHo = str3;
        this.aHp = z;
        this.aHq = str4;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof te)) {
            return false;
        }
        te teVar = (te) object;
        return this.packageName.equals(teVar.packageName) && this.aHl == teVar.aHl && this.aHm == teVar.aHm && kl.equal(this.aHq, teVar.aHq) && kl.equal(this.aHn, teVar.aHn) && kl.equal(this.aHo, teVar.aHo) && this.aHp == teVar.aHp;
    }

    public int hashCode() {
        return kl.hashCode(this.packageName, Integer.valueOf(this.aHl), Integer.valueOf(this.aHm), this.aHn, this.aHo, Boolean.valueOf(this.aHp));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PlayLoggerContext[");
        stringBuilder.append("package=").append(this.packageName).append(',');
        stringBuilder.append("versionCode=").append(this.versionCode).append(',');
        stringBuilder.append("logSource=").append(this.aHm).append(',');
        stringBuilder.append("logSourceName=").append(this.aHq).append(',');
        stringBuilder.append("uploadAccount=").append(this.aHn).append(',');
        stringBuilder.append("loggingId=").append(this.aHo).append(',');
        stringBuilder.append("logAndroidId=").append(this.aHp);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        tf.a(this, out, flags);
    }
}
