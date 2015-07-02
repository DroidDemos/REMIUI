package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class LatencyTracker implements SafeParcelable {
    public static final g CREATOR;
    final int Gf;
    final long IH;
    final String mName;
    public final LatencyTracker parent;

    static {
        CREATOR = new g();
    }

    LatencyTracker(int version, String name, long startRealtimeMillis, LatencyTracker parent) {
        this.Gf = version;
        this.mName = name;
        this.IH = startRealtimeMillis;
        this.parent = parent;
        log(name, "constructed");
    }

    public int describeContents() {
        return 0;
    }

    public void log(String prefix, String eventDescription) {
        if (Log.isLoggable("GLSLogging", 2)) {
            String str = "GLSLogging";
            Log.println(2, str, prefix + " " + m(SystemClock.elapsedRealtime()) + " > " + eventDescription);
        }
    }

    String m(long j) {
        Iterable linkedList = new LinkedList();
        while (this != null) {
            long j2 = j - this.IH;
            j2 -= TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(j2));
            linkedList.addFirst(String.format("[%s, %,d.%03ds]", new Object[]{this.mName, Long.valueOf(r4), Long.valueOf(j2)}));
            this = this.parent;
        }
        return TextUtils.join(" > ", linkedList);
    }

    public void writeToParcel(Parcel dest, int flags) {
        log(this.mName, "writing to parcel");
        g.a(this, dest, flags);
    }
}
