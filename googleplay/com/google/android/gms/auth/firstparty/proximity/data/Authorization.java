package com.google.android.gms.auth.firstparty.proximity.data;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;
import java.util.Arrays;

public class Authorization implements SafeParcelable {
    public static final a CREATOR;
    final int Gf;
    public final byte[] mData;
    public final String mPermitAccessId;
    public final String mPermitId;

    static {
        CREATOR = new a();
    }

    Authorization(int version, String permitId, String permitAccessId, byte[] data) {
        this.Gf = version;
        this.mPermitId = kn.bk(permitId);
        this.mPermitAccessId = kn.bk(permitAccessId);
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
        if (!(obj instanceof Authorization)) {
            return false;
        }
        Authorization authorization = (Authorization) obj;
        if (!(TextUtils.equals(this.mPermitId, authorization.mPermitId) && TextUtils.equals(this.mPermitAccessId, authorization.mPermitAccessId) && Arrays.equals(this.mData, authorization.mData))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (31 * (((this.mPermitId.hashCode() + 527) * 31) + this.mPermitAccessId.hashCode())) + Arrays.hashCode(this.mData);
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
