package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.oh.a;

public class ot implements SafeParcelable {
    public static final Creator<ot> CREATOR;
    public final PendingIntent avf;
    private final int mVersionCode;
    public final oh messageListener;
    public final int type;

    static {
        CREATOR = new ou();
    }

    ot(int i, int i2, IBinder iBinder, PendingIntent pendingIntent) {
        this.mVersionCode = i;
        this.type = i2;
        if (iBinder == null) {
            this.messageListener = null;
        } else {
            this.messageListener = a.cm(iBinder);
        }
        this.avf = pendingIntent;
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
        ot otVar = (ot) object;
        return this.type == otVar.type && kl.equal(this.messageListener, otVar.messageListener);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.type), this.messageListener);
    }

    IBinder pg() {
        return this.messageListener == null ? null : this.messageListener.asBinder();
    }

    public void writeToParcel(Parcel dest, int flags) {
        ou.a(this, dest, flags);
    }
}
