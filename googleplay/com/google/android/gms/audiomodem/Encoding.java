package com.google.android.gms.audiomodem;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class Encoding implements SafeParcelable {
    public static final Creator<Encoding> CREATOR;
    private final int EB;
    private final DsssEncoding FL;
    private final DtmfEncoding FM;
    private final int mVersionCode;

    static {
        CREATOR = new d();
    }

    Encoding(int versionCode, int type, DsssEncoding dsssEncoding, DtmfEncoding dtmfEncoding) {
        this.mVersionCode = versionCode;
        this.EB = type;
        this.FL = dsssEncoding;
        this.FM = dtmfEncoding;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Encoding)) {
            return false;
        }
        Encoding encoding = (Encoding) obj;
        return this.mVersionCode == encoding.getVersionCode() && this.EB == encoding.getType() && ((this.EB != 0 || kl.equal(this.FL, encoding.getDsssEncoding())) && (this.EB != 1 || kl.equal(this.FM, encoding.getDtmfEncoding())));
    }

    public DsssEncoding getDsssEncoding() {
        return this.FL;
    }

    public DtmfEncoding getDtmfEncoding() {
        return this.FM;
    }

    public int getType() {
        return this.EB;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.EB == 0 ? this.FL.hashCode() : 0) + kl.hashCode(Integer.valueOf(this.mVersionCode), Integer.valueOf(this.EB));
        if (this.EB == 1) {
            i = this.FM.hashCode();
        }
        return hashCode + i;
    }

    public void writeToParcel(Parcel dest, int flags) {
        d.a(this, dest, flags);
    }
}
