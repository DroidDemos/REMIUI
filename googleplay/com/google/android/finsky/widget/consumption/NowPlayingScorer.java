package com.google.android.finsky.widget.consumption;

import android.util.SparseIntArray;
import com.google.android.finsky.config.G;
import com.google.android.finsky.services.ConsumptionAppDoc;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.widget.WidgetUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class NowPlayingScorer {
    private static final boolean ALWAYS_INCLUDE_MOST_RECENT;
    private static final SparseIntArray HAS_CONTENT_SCORE_MAP;
    private static final float IMPORTANT_CONTRIBUTION_FRACTION;
    private static final long INTERACTION_CLUSTER_MS;
    private static final long MIDTERM_INTERACTION_MS;
    private static final SparseIntArray MIDTERM_INTERACTION_SCORE_MAP;
    private static final float SHORTTERM_DECAY;
    private static final long SHORTTERM_INTERACTION_MS;
    private static final SparseIntArray SHORTTERM_INTERACTION_SCORE_MAP;
    private static final Comparator<ConsumptionAppDoc> UPDATE_TIME_COMPARATOR;

    private static class CorpusScore {
        private long lastInteractionMs;
        private int score;

        private CorpusScore() {
        }
    }

    static {
        INTERACTION_CLUSTER_MS = ((Long) G.myLibraryWidgetInteractionClusterMs.get()).longValue();
        SHORTTERM_INTERACTION_MS = ((Long) G.myLibraryWidgetShorttermDurationMs.get()).longValue();
        MIDTERM_INTERACTION_MS = ((Long) G.myLibraryWidgetMidtermDurationMs.get()).longValue();
        SHORTTERM_INTERACTION_SCORE_MAP = WidgetUtils.parseSparseIntArray((String) G.myLibraryWidgetShorttermScores.get());
        MIDTERM_INTERACTION_SCORE_MAP = WidgetUtils.parseSparseIntArray((String) G.myLibraryWidgeMidtermScores.get());
        SHORTTERM_DECAY = ((Float) G.myLibraryWidgetShorttermDecay.get()).floatValue();
        HAS_CONTENT_SCORE_MAP = WidgetUtils.parseSparseIntArray((String) G.myLibraryWidgetHasContentScoreMap.get());
        IMPORTANT_CONTRIBUTION_FRACTION = ((Float) G.myLibraryWidgetImportantContributionFraction.get()).floatValue();
        ALWAYS_INCLUDE_MOST_RECENT = ((Boolean) G.myLibraryWidgetAlwaysIncludeMostRecentBackend.get()).booleanValue();
        UPDATE_TIME_COMPARATOR = new Comparator<ConsumptionAppDoc>() {
            public int compare(ConsumptionAppDoc lhs, ConsumptionAppDoc rhs) {
                return Long.valueOf(lhs.getLastUpdateTimeMs()).compareTo(Long.valueOf(rhs.getLastUpdateTimeMs()));
            }
        };
    }

    public static List<ConsumptionAppDocList> score(List<ConsumptionAppDocList> docLists, int maxBackends, long nowMs) {
        maxBackends = Math.min(maxBackends, docLists.size());
        final Map<ConsumptionAppDocList, Integer> scores = Maps.newHashMap();
        ConsumptionAppDocList mostRecentDocList = computeScoresAndReturnMostRecent(docLists, nowMs, scores);
        List<ConsumptionAppDocList> result = new ArrayList(docLists);
        Collections.sort(result, new Comparator<ConsumptionAppDocList>() {
            public int compare(ConsumptionAppDocList lhs, ConsumptionAppDocList rhs) {
                return ((Integer) scores.get(rhs)).intValue() - ((Integer) scores.get(lhs)).intValue();
            }
        });
        return getImportantBackends(result, maxBackends, scores, mostRecentDocList);
    }

    private static ConsumptionAppDocList computeScoresAndReturnMostRecent(List<ConsumptionAppDocList> docLists, long nowMs, Map<ConsumptionAppDocList, Integer> scores) {
        ConsumptionAppDocList mostRecentDocList = null;
        long mostRecentInteractionMs = 0;
        for (ConsumptionAppDocList docList : docLists) {
            CorpusScore corpusScore = computeCorpusScore(docList, nowMs);
            scores.put(docList, Integer.valueOf(corpusScore.score));
            if (corpusScore.lastInteractionMs > mostRecentInteractionMs) {
                mostRecentDocList = docList;
                mostRecentInteractionMs = corpusScore.lastInteractionMs;
            }
        }
        return mostRecentDocList;
    }

    private static List<ConsumptionAppDocList> getImportantBackends(List<ConsumptionAppDocList> sortedDocLists, int maxCorpora, Map<ConsumptionAppDocList, Integer> scores, ConsumptionAppDocList mostRecentDocList) {
        List<ConsumptionAppDocList> result = Lists.newArrayList(maxCorpora);
        int scoreSum = 0;
        boolean hasMostRecentBackend = false;
        int i = 0;
        while (i < maxCorpora) {
            ConsumptionAppDocList docList = (ConsumptionAppDocList) sortedDocLists.get(i);
            int score = ((Integer) scores.get(docList)).intValue();
            int newScoreSum = scoreSum + score;
            if (newScoreSum == 0) {
                break;
            } else if (i <= 1 || ((float) score) / ((float) newScoreSum) >= IMPORTANT_CONTRIBUTION_FRACTION) {
                int i2;
                result.add(docList);
                scoreSum = newScoreSum;
                if (docList == mostRecentDocList) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                hasMostRecentBackend |= i2;
                i++;
            } else if (FinskyLog.DEBUG) {
                FinskyLog.v("Dropping backend=%d and after.", Integer.valueOf(docList.getBackend()));
            }
        }
        if (!(!ALWAYS_INCLUDE_MOST_RECENT || hasMostRecentBackend || mostRecentDocList == null)) {
            if (result.size() == maxCorpora) {
                result.remove(result.size() - 1);
            }
            result.add(mostRecentDocList);
        }
        return result;
    }

    private static CorpusScore computeCorpusScore(ConsumptionAppDocList consumptionAppDocList, long nowMs) {
        int backend = consumptionAppDocList.getBackend();
        List<ConsumptionAppDoc> arrayList = new ArrayList(consumptionAppDocList);
        Collections.sort(arrayList, UPDATE_TIME_COMPARATOR);
        long previousDocInteractionMs = 0;
        long latestInteractionMs = 0;
        int shorttermInteractions = 0;
        float shorttermScoreSum = 0.0f;
        float nextShorttermScore = (float) SHORTTERM_INTERACTION_SCORE_MAP.get(backend, SHORTTERM_INTERACTION_SCORE_MAP.get(0));
        int midtermInteractions = 0;
        long shorttermStartMs = nowMs - SHORTTERM_INTERACTION_MS;
        long midtermStartMs = nowMs - MIDTERM_INTERACTION_MS;
        for (ConsumptionAppDoc doc : arrayList) {
            long lastUpdateTimeMs = doc.getLastUpdateTimeMs();
            if (lastUpdateTimeMs - previousDocInteractionMs < INTERACTION_CLUSTER_MS) {
                if (FinskyLog.DEBUG) {
                    FinskyLog.v("Not scoring doc %s, since it's within %d ms of previous interaction", doc.getDocId(), Long.valueOf(INTERACTION_CLUSTER_MS));
                }
            } else if (lastUpdateTimeMs > shorttermStartMs) {
                shorttermInteractions++;
                shorttermScoreSum += nextShorttermScore;
                nextShorttermScore *= SHORTTERM_DECAY;
            } else if (lastUpdateTimeMs > midtermStartMs) {
                midtermInteractions++;
            }
            latestInteractionMs = Math.max(latestInteractionMs, lastUpdateTimeMs);
            previousDocInteractionMs = lastUpdateTimeMs;
        }
        int midtermScorePerDoc = MIDTERM_INTERACTION_SCORE_MAP.get(backend, MIDTERM_INTERACTION_SCORE_MAP.get(0));
        int hasContentScore = 0;
        if (consumptionAppDocList.size() > 0) {
            hasContentScore = HAS_CONTENT_SCORE_MAP.get(backend, HAS_CONTENT_SCORE_MAP.get(0));
        }
        int i = (int) shorttermScoreSum;
        int score = (r0 + (midtermInteractions * midtermScorePerDoc)) + hasContentScore;
        if (FinskyLog.DEBUG) {
            FinskyLog.d("Score for backend %d: %d (shorttermscore=%.3g,midtermscore=%d,hascontentscore=%d,shorttermcount=%d,midtermcount=%d,totalcount=%d)", Integer.valueOf(backend), Integer.valueOf(score), Float.valueOf(shorttermScoreSum), Integer.valueOf(midtermScore), Integer.valueOf(hasContentScore), Integer.valueOf(shorttermInteractions), Integer.valueOf(midtermInteractions), Integer.valueOf(consumptionAppDocList.size()));
        }
        CorpusScore corpusScore = new CorpusScore();
        corpusScore.score = score;
        corpusScore.lastInteractionMs = latestInteractionMs;
        return corpusScore;
    }
}
