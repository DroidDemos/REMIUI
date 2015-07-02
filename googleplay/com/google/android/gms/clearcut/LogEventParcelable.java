package com.google.android.gms.clearcut;

import android.os.Parcel;
import com.google.android.gms.clearcut.ClearcutLogger.MessageProducer;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.te;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogEvent;
import java.util.Arrays;

public class LogEventParcelable implements SafeParcelable {
    public static final a CREATOR;
    public final MessageProducer extensionProducer;
    public final LogEvent logEvent;
    public byte[] logEventBytes;
    public te playLoggerContext;
    public final int versionCode;

    static {
        CREATOR = new a();
    }

    LogEventParcelable(int versionCode, te playLoggerContext, byte[] logEventBytes) {
        this.versionCode = versionCode;
        this.playLoggerContext = playLoggerContext;
        this.logEventBytes = logEventBytes;
        this.logEvent = null;
        this.extensionProducer = null;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LogEventParcelable)) {
            return false;
        }
        LogEventParcelable logEventParcelable = (LogEventParcelable) other;
        return this.versionCode == logEventParcelable.versionCode && kl.equal(this.playLoggerContext, logEventParcelable.playLoggerContext) && Arrays.equals(this.logEventBytes, logEventParcelable.logEventBytes) && kl.equal(this.logEvent, logEventParcelable.logEvent) && kl.equal(this.extensionProducer, logEventParcelable.extensionProducer);
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.versionCode), this.playLoggerContext, this.logEventBytes, this.logEvent, this.extensionProducer);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("LogEventParcelable[");
        stringBuilder.append(this.versionCode);
        stringBuilder.append(", ");
        stringBuilder.append(this.playLoggerContext);
        stringBuilder.append(", ");
        stringBuilder.append(this.logEventBytes == null ? null : new String(this.logEventBytes));
        stringBuilder.append(", ");
        stringBuilder.append(this.logEvent);
        stringBuilder.append(", ");
        stringBuilder.append(this.extensionProducer);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
