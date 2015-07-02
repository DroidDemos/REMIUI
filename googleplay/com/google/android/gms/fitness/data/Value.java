package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kn;
import com.google.android.wallet.instrumentmanager.R;

public final class Value implements SafeParcelable {
    public static final Creator<Value> CREATOR;
    private final int agM;
    private boolean agZ;
    private float aha;
    private final int mVersionCode;

    static {
        CREATOR = new t();
    }

    Value(int versionCode, int format, boolean isSet, float value) {
        this.mVersionCode = versionCode;
        this.agM = format;
        this.agZ = isSet;
        this.aha = value;
    }

    private boolean a(Value value) {
        if (this.agM != value.agM || this.agZ != value.agZ) {
            return false;
        }
        switch (this.agM) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return asInt() == value.asInt();
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return asFloat() == value.asFloat();
            default:
                return this.aha == value.aha;
        }
    }

    public float asFloat() {
        kn.a(this.agM == 2, "Value is not in float format");
        return this.aha;
    }

    public int asInt() {
        boolean z = true;
        if (this.agM != 1) {
            z = false;
        }
        kn.a(z, "Value is not in int format");
        return Float.floatToRawIntBits(this.aha);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return this == o || ((o instanceof Value) && a((Value) o));
    }

    public int getFormat() {
        return this.agM;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Float.valueOf(this.aha), Integer.valueOf(this.agM), Boolean.valueOf(this.agZ));
    }

    public boolean isSet() {
        return this.agZ;
    }

    float lE() {
        return this.aha;
    }

    public String toString() {
        if (!this.agZ) {
            return "unset";
        }
        switch (this.agM) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return Integer.toString(asInt());
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return Float.toString(asFloat());
            default:
                return "unknown";
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
        t.a(this, dest, flags);
    }
}
