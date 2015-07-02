package com.google.android.gms.auth.firstparty.proximity.data;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;
import java.util.Arrays;

public class PermitAccess implements SafeParcelable {
    public static final c CREATOR;
    final String CB;
    final int Gf;
    final byte[] mData;
    final String vc;

    static {
        CREATOR = new c();
    }

    PermitAccess(int version, String id, String type, byte[] data) {
        this.Gf = version;
        this.CB = kn.bk(id);
        this.vc = kn.bk(type);
        this.mData = (byte[]) kn.k(data);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PermitAccess)) {
            return false;
        }
        PermitAccess permitAccess = (PermitAccess) obj;
        if (!(TextUtils.equals(this.CB, permitAccess.CB) && TextUtils.equals(this.vc, permitAccess.vc) && Arrays.equals(this.mData, permitAccess.mData))) {
            z = false;
        }
        return z;
    }

    public String getId() {
        return this.CB;
    }

    public int hashCode() {
        return (31 * (((this.CB.hashCode() + 527) * 31) + this.vc.hashCode())) + Arrays.hashCode(this.mData);
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
