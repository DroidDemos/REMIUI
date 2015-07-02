package com.google.android.gms.search.queries;

import android.os.Parcel;
import com.google.android.gms.appdatasearch.PhraseAffinityCorpusSpec;
import com.google.android.gms.appdatasearch.PhraseAffinityResponse;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GetPhraseAffinityCall {

    public static class Response implements Result, SafeParcelable {
        public static final d CREATOR;
        public PhraseAffinityResponse affinity;
        final int mVersionCode;
        public Status status;

        static {
            CREATOR = new d();
        }

        public Response() {
            this.mVersionCode = 1;
        }

        Response(int versionCode, Status status, PhraseAffinityResponse affinity) {
            this.mVersionCode = versionCode;
            this.status = status;
            this.affinity = affinity;
        }

        public int describeContents() {
            d dVar = CREATOR;
            return 0;
        }

        public Status getStatus() {
            return this.status;
        }

        public void writeToParcel(Parcel out, int flags) {
            d dVar = CREATOR;
            d.a(this, out, flags);
        }
    }

    public static class b implements SafeParcelable {
        public static final c CREATOR;
        public String[] aLW;
        public PhraseAffinityCorpusSpec[] aLX;
        final int mVersionCode;

        static {
            CREATOR = new c();
        }

        public b() {
            this.mVersionCode = 1;
        }

        b(int i, String[] strArr, PhraseAffinityCorpusSpec[] phraseAffinityCorpusSpecArr) {
            this.mVersionCode = i;
            this.aLW = strArr;
            this.aLX = phraseAffinityCorpusSpecArr;
        }

        public int describeContents() {
            c cVar = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            c cVar = CREATOR;
            c.a(this, out, flags);
        }
    }
}
