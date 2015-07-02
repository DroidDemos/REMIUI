package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.oh.a;
import com.google.android.gms.location.copresence.MessageFilter;
import com.google.android.wallet.instrumentmanager.R;

public class op implements SafeParcelable {
    public static final Creator<op> CREATOR;
    public final on avd;
    public final MessageFilter ave;
    public final PendingIntent avf;
    private final int mVersionCode;
    public final oh messageListener;
    public final int type;

    static {
        CREATOR = new oq();
    }

    op(int i, int i2, on onVar, MessageFilter messageFilter, IBinder iBinder, PendingIntent pendingIntent) {
        this.mVersionCode = i;
        this.type = i2;
        this.avd = onVar;
        this.ave = messageFilter;
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

    int getVersionCode() {
        return this.mVersionCode;
    }

    IBinder pg() {
        return this.messageListener == null ? null : this.messageListener.asBinder();
    }

    public String toString() {
        switch (this.type) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return "SubscribeOperation[listener=" + pg() + ", filter=" + this.ave + "]";
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return "SubscribeOperation[pendingIntent=" + this.avf + ", filter=" + this.ave + "]";
            default:
                return "SubscribeOperation[unknown type=" + this.type + ", filter=" + this.ave + "]";
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
        oq.a(this, dest, flags);
    }
}
