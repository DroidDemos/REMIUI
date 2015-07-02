package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class QuerySpecification implements SafeParcelable {
    public static final w CREATOR;
    public final int debugLevel;
    final int mVersionCode;
    public final boolean prefixMatch;
    public final int queryTokenizer;
    public final int rankingStrategy;
    public final boolean semanticSectionNames;
    public final boolean wantUris;
    public final List<Section> wantedSections;
    public final List<String> wantedTags;

    static {
        CREATOR = new w();
    }

    QuerySpecification(int versionCode, boolean wantUris, List<String> wantedTags, List<Section> wantedSections, boolean prefixMatch, int debugLevel, int rankingStrategy, boolean semanticSectionNames, int queryTokenizer) {
        this.mVersionCode = versionCode;
        this.wantUris = wantUris;
        this.wantedTags = wantedTags;
        this.wantedSections = wantedSections;
        this.prefixMatch = prefixMatch;
        this.debugLevel = debugLevel;
        this.rankingStrategy = rankingStrategy;
        this.semanticSectionNames = semanticSectionNames;
        this.queryTokenizer = queryTokenizer;
    }

    public int describeContents() {
        w wVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        w wVar = CREATOR;
        w.a(this, out, flags);
    }
}
