package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Operator implements SafeParcelable {
    public static final Creator<Operator> CREATOR;
    public static final Operator acE;
    public static final Operator acF;
    public static final Operator acG;
    public static final Operator acH;
    public static final Operator acI;
    public static final Operator acJ;
    public static final Operator acK;
    public static final Operator acL;
    public static final Operator acM;
    final String mTag;
    final int mVersionCode;

    static {
        CREATOR = new l();
        acE = new Operator("=");
        acF = new Operator("<");
        acG = new Operator("<=");
        acH = new Operator(">");
        acI = new Operator(">=");
        acJ = new Operator("and");
        acK = new Operator("or");
        acL = new Operator("not");
        acM = new Operator("contains");
    }

    Operator(int versionCode, String tag) {
        this.mVersionCode = versionCode;
        this.mTag = tag;
    }

    private Operator(String tag) {
        this(1, tag);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Operator operator = (Operator) obj;
        return this.mTag == null ? operator.mTag == null : this.mTag.equals(operator.mTag);
    }

    public String getTag() {
        return this.mTag;
    }

    public int hashCode() {
        return (this.mTag == null ? 0 : this.mTag.hashCode()) + 31;
    }

    public void writeToParcel(Parcel out, int flags) {
        l.a(this, out, flags);
    }
}
