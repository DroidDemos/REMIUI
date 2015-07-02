package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.wallet.instrumentmanager.R;

public class or implements SafeParcelable {
    public static final Creator<or> CREATOR;
    public final String avg;
    private final int mVersionCode;
    public final int type;

    static {
        CREATOR = new os();
    }

    or(int i, int i2, String str) {
        this.mVersionCode = i;
        this.type = i2;
        this.avg = str;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        or orVar = (or) object;
        return this.type == orVar.type && TextUtils.equals(this.avg, orVar.avg);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.type), this.avg);
    }

    public String toString() {
        switch (this.type) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return "Unpublish(id=" + this.avg + ")";
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return "UnpublishAll";
            default:
                return null;
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
        os.a(this, dest, flags);
    }
}
