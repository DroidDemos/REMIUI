package com.google.android.gms.appdatasearch;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.HashMap;
import java.util.Map;

public class CorpusStatus implements SafeParcelable {
    public static final c CREATOR;
    final boolean CI;
    final long CJ;
    final long CK;
    final long CL;
    final Bundle CM;
    final String CN;
    final int mVersionCode;

    static {
        CREATOR = new c();
    }

    CorpusStatus() {
        this(2, false, 0, 0, 0, null, null);
    }

    CorpusStatus(int versionCode, boolean found, long lastIndexedSeqno, long lastCommittedSeqno, long committedNumDocuments, Bundle counters, String version) {
        this.mVersionCode = versionCode;
        this.CI = found;
        this.CJ = lastIndexedSeqno;
        this.CK = lastCommittedSeqno;
        this.CL = committedNumDocuments;
        if (counters == null) {
            counters = new Bundle();
        }
        this.CM = counters;
        this.CN = version;
    }

    public int describeContents() {
        c cVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof CorpusStatus)) {
            return false;
        }
        CorpusStatus corpusStatus = (CorpusStatus) object;
        return kl.equal(Boolean.valueOf(this.CI), Boolean.valueOf(corpusStatus.CI)) && kl.equal(Long.valueOf(this.CJ), Long.valueOf(corpusStatus.CJ)) && kl.equal(Long.valueOf(this.CK), Long.valueOf(corpusStatus.CK)) && kl.equal(Long.valueOf(this.CL), Long.valueOf(corpusStatus.CL)) && kl.equal(getCounters(), corpusStatus.getCounters());
    }

    public Map<String, Integer> getCounters() {
        Map<String, Integer> hashMap = new HashMap();
        for (String str : this.CM.keySet()) {
            int i = this.CM.getInt(str, -1);
            if (i != -1) {
                hashMap.put(str, Integer.valueOf(i));
            }
        }
        return hashMap;
    }

    public int hashCode() {
        return kl.hashCode(Boolean.valueOf(this.CI), Long.valueOf(this.CJ), Long.valueOf(this.CK), Long.valueOf(this.CL), getCounters());
    }

    public String toString() {
        return "CorpusStatus{found=" + this.CI + ", lastIndexedSeqno=" + this.CJ + ", lastCommittedSeqno=" + this.CK + ", committedNumDocuments=" + this.CL + ", counters=" + this.CM + "}";
    }

    public void writeToParcel(Parcel out, int flags) {
        c cVar = CREATOR;
        c.a(this, out, flags);
    }
}
