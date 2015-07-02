package com.android.internal.os;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PkgUsageStats implements Parcelable {
    public static final Creator<PkgUsageStats> CREATOR;
    public Map<String, Long> componentResumeTimes;
    public int launchCount;
    public String packageName;
    public long usageTime;

    static {
        CREATOR = new Creator<PkgUsageStats>() {
            public PkgUsageStats createFromParcel(Parcel in) {
                return new PkgUsageStats(in);
            }

            public PkgUsageStats[] newArray(int size) {
                return new PkgUsageStats[size];
            }
        };
    }

    public String toString() {
        return "PkgUsageStats{" + Integer.toHexString(System.identityHashCode(this)) + " " + this.packageName + "}";
    }

    public PkgUsageStats(Parcel source) {
        this.packageName = source.readString();
        this.launchCount = source.readInt();
        this.usageTime = source.readLong();
        int N = source.readInt();
        this.componentResumeTimes = new HashMap(N);
        for (int i = 0; i < N; i++) {
            this.componentResumeTimes.put(source.readString(), Long.valueOf(source.readLong()));
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int parcelableFlags) {
        dest.writeString(this.packageName);
        dest.writeInt(this.launchCount);
        dest.writeLong(this.usageTime);
        dest.writeInt(this.componentResumeTimes.size());
        for (Entry<String, Long> ent : this.componentResumeTimes.entrySet()) {
            dest.writeString((String) ent.getKey());
            dest.writeLong(((Long) ent.getValue()).longValue());
        }
    }
}
