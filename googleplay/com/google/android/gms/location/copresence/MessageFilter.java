package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;

public class MessageFilter implements SafeParcelable {
    public static final Creator<MessageFilter> CREATOR;
    private final List<a> aui;
    private final int mVersionCode;

    public static class a implements SafeParcelable {
        public static final Creator<a> CREATOR;
        @Deprecated
        public final String WP;
        public final AccessKey auj;
        public final long auk;
        public final People aul;
        private final int mVersionCode;
        public final String type;

        static {
            CREATOR = new j();
        }

        a(int i, String str, String str2, AccessKey accessKey, long j, People people) {
            this.mVersionCode = i;
            this.WP = str;
            this.type = str2;
            this.auj = accessKey;
            this.auk = j;
            this.aul = people;
        }

        public int describeContents() {
            return 0;
        }

        int getVersionCode() {
            return this.mVersionCode;
        }

        public String toString() {
            return "(namespace=" + this.WP + ", type=" + this.type + ", accessKey=" + (this.auj == null ? null : "REDACTED") + ", ttl=" + this.auk + ", senders=" + this.aul + ")";
        }

        public void writeToParcel(Parcel dest, int flags) {
            j.a(this, dest, flags);
        }
    }

    static {
        CREATOR = new i();
    }

    MessageFilter(int versionCode, List<a> filterPrimitives) {
        this.mVersionCode = versionCode;
        this.aui = filterPrimitives == null ? null : Collections.unmodifiableList(filterPrimitives);
    }

    public int describeContents() {
        return 0;
    }

    public List<a> getFilterPrimitives() {
        return this.aui;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MessageFilter(count=").append(this.aui.size()).append(")\n");
        for (a aVar : this.aui) {
            stringBuilder.append("[").append(0).append("]: ");
            stringBuilder.append(aVar.toString()).append('\n');
        }
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        i.a(this, dest, flags);
    }
}
