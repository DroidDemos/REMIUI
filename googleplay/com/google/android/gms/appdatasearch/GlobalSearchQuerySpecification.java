package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GlobalSearchQuerySpecification implements SafeParcelable {
    public static final p CREATOR;
    final CorpusId[] Dh;
    final CorpusScoringInfo[] Di;
    private final transient Map<String, Set<String>> Dj;
    private final transient Map<CorpusId, CorpusScoringInfo> Dk;
    public final String context;
    public final int debugLevel;
    final int mVersionCode;
    public final int queryTokenizer;
    public final int rankingStrategy;
    public final int scoringVerbosityLevel;

    static {
        CREATOR = new p();
    }

    GlobalSearchQuerySpecification(int versionCode, CorpusId[] filters, int scoringVerbosityLevel, CorpusScoringInfo[] corpusScoringInfo, int debugLevel, int rankingStrategy, int queryTokenizer, String context) {
        int i = 0;
        this.mVersionCode = versionCode;
        this.Dh = filters;
        this.scoringVerbosityLevel = scoringVerbosityLevel;
        this.debugLevel = debugLevel;
        this.rankingStrategy = rankingStrategy;
        this.queryTokenizer = queryTokenizer;
        this.context = context;
        this.Di = corpusScoringInfo;
        if (filters == null || filters.length == 0) {
            this.Dj = null;
        } else {
            this.Dj = new HashMap();
            for (int i2 = 0; i2 < filters.length; i2++) {
                Set set = (Set) this.Dj.get(filters[i2].packageName);
                if (set == null) {
                    set = new HashSet();
                    this.Dj.put(filters[i2].packageName, set);
                }
                if (filters[i2].corpusName != null) {
                    set.add(filters[i2].corpusName);
                }
            }
        }
        if (corpusScoringInfo == null || corpusScoringInfo.length == 0) {
            this.Dk = null;
            return;
        }
        this.Dk = new HashMap(corpusScoringInfo.length);
        while (i < corpusScoringInfo.length) {
            this.Dk.put(corpusScoringInfo[i].corpus, corpusScoringInfo[i]);
            i++;
        }
    }

    public int describeContents() {
        p pVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        p pVar = CREATOR;
        p.a(this, out, flags);
    }
}
