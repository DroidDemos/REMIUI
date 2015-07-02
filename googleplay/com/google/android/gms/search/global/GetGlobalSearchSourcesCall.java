package com.google.android.gms.search.global;

import android.os.Parcel;
import com.google.android.gms.appdatasearch.Feature;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GetGlobalSearchSourcesCall {

    public static class CorpusInfo implements SafeParcelable {
        public static final c CREATOR;
        public String corpusName;
        public Feature[] features;
        public boolean isAppHistoryCorpus;
        final int mVersionCode;

        static {
            CREATOR = new c();
        }

        public CorpusInfo() {
            this.mVersionCode = 1;
        }

        CorpusInfo(int versionCode, String corpusName, Feature[] features, boolean isAppHistoryCorpus) {
            this.mVersionCode = versionCode;
            this.corpusName = corpusName;
            this.features = features;
            this.isAppHistoryCorpus = isAppHistoryCorpus;
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

    public static class GlobalSearchSource implements SafeParcelable {
        public static final d CREATOR;
        public CorpusInfo[] corpora;
        public String defaultIntentAction;
        public String defaultIntentActivity;
        public String defaultIntentData;
        public boolean enabled;
        public int iconId;
        public int labelId;
        final int mVersionCode;
        public String packageName;
        public int settingsDescriptionId;

        static {
            CREATOR = new d();
        }

        public GlobalSearchSource() {
            this.mVersionCode = 1;
        }

        GlobalSearchSource(int versionCode, String packageName, int labelId, int settingsDescriptionId, int iconId, String defaultIntentAction, String defaultIntentData, String defaultIntentActivity, CorpusInfo[] corpora, boolean enabled) {
            this.mVersionCode = versionCode;
            this.packageName = packageName;
            this.labelId = labelId;
            this.settingsDescriptionId = settingsDescriptionId;
            this.iconId = iconId;
            this.defaultIntentAction = defaultIntentAction;
            this.defaultIntentData = defaultIntentData;
            this.defaultIntentActivity = defaultIntentActivity;
            this.corpora = corpora;
            this.enabled = enabled;
        }

        public int describeContents() {
            d dVar = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            d dVar = CREATOR;
            d.a(this, out, flags);
        }
    }

    public static class Request implements SafeParcelable {
        public static final e CREATOR;
        final int mVersionCode;
        public boolean wantDisabledSources;

        static {
            CREATOR = new e();
        }

        public Request() {
            this.mVersionCode = 1;
        }

        Request(int versionCode, boolean wantDisabledSources) {
            this.mVersionCode = versionCode;
            this.wantDisabledSources = wantDisabledSources;
        }

        public int describeContents() {
            e eVar = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            e eVar = CREATOR;
            e.a(this, out, flags);
        }
    }

    public static class Response implements Result, SafeParcelable {
        public static final f CREATOR;
        final int mVersionCode;
        public GlobalSearchSource[] sources;
        public Status status;

        static {
            CREATOR = new f();
        }

        public Response() {
            this.mVersionCode = 1;
        }

        Response(int versionCode, Status status, GlobalSearchSource[] sources) {
            this.mVersionCode = versionCode;
            this.status = status;
            this.sources = sources;
        }

        public int describeContents() {
            f fVar = CREATOR;
            return 0;
        }

        public Status getStatus() {
            return this.status;
        }

        public void writeToParcel(Parcel out, int flags) {
            f fVar = CREATOR;
            f.a(this, out, flags);
        }
    }
}
