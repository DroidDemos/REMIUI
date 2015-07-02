package com.google.android.finsky.widget.consumption;

import android.util.SparseIntArray;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.widget.WidgetUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NowPlayingArranger {
    private static final int[][][] PERMUTATIONS;
    private static SparseIntArray sBottomAffinity;
    private static Map<String, int[]> sCachedArrangements;
    private static boolean sInitialized;
    private static SparseIntArray sLeftAffinity;
    private static SparseIntArray sRightAffinity;
    private static SparseIntArray sTopAffinity;

    public static class Arrangement {
        private static final byte[] LOCATION_FLAGS_2_HORIZONTAL;
        private static final byte[] LOCATION_FLAGS_2_VERTICAL;
        private static final byte[] LOCATION_FLAGS_3_STRETCH_FIRST;
        private static final byte[] LOCATION_FLAGS_3_STRETCH_SECOND;
        private static final byte[] LOCATION_FLAGS_4;
        public int layoutVariant;
        public final ConsumptionAppDocList[] quadrantToData;

        static {
            LOCATION_FLAGS_2_VERTICAL = new byte[]{(byte) 4, (byte) 8};
            LOCATION_FLAGS_2_HORIZONTAL = new byte[]{(byte) 1, (byte) 2};
            LOCATION_FLAGS_3_STRETCH_FIRST = new byte[]{(byte) 1, (byte) 6, (byte) 10};
            LOCATION_FLAGS_3_STRETCH_SECOND = new byte[]{(byte) 5, (byte) 2, (byte) 9};
            LOCATION_FLAGS_4 = new byte[]{(byte) 5, (byte) 6, (byte) 9, (byte) 10};
        }

        public Arrangement(ConsumptionAppDocList[] quadrantToData, int layoutVariant) {
            this.quadrantToData = quadrantToData;
            this.layoutVariant = layoutVariant;
        }

        public static byte getLocation(int numQuadrants, int quadrant, int layoutVariant) {
            if (numQuadrants == 1) {
                return (byte) 0;
            }
            if (numQuadrants == 2 && layoutVariant == 0) {
                return LOCATION_FLAGS_2_HORIZONTAL[quadrant];
            }
            if (numQuadrants == 2 && layoutVariant == 1) {
                return LOCATION_FLAGS_2_VERTICAL[quadrant];
            }
            if (numQuadrants == 3 && layoutVariant == 0) {
                return LOCATION_FLAGS_3_STRETCH_FIRST[quadrant];
            }
            if (numQuadrants == 3 && layoutVariant == 1) {
                return LOCATION_FLAGS_3_STRETCH_SECOND[quadrant];
            }
            if (numQuadrants == 4) {
                return LOCATION_FLAGS_4[quadrant];
            }
            return (byte) 0;
        }
    }

    static {
        r0 = new int[5][][];
        int[][] iArr = new int[1][];
        iArr[0] = new int[]{0};
        r0[1] = iArr;
        r0[2] = new int[][]{new int[]{0, 1}, new int[]{1, 0}};
        r0[3] = new int[][]{new int[]{0, 1, 2}, new int[]{0, 2, 1}, new int[]{1, 0, 2}, new int[]{2, 0, 1}};
        r0[4] = new int[][]{new int[]{0, 1, 2, 3}, new int[]{0, 1, 3, 2}, new int[]{0, 2, 1, 3}, new int[]{0, 2, 3, 1}, new int[]{0, 3, 1, 2}, new int[]{0, 3, 2, 1}, new int[]{1, 0, 3, 2}, new int[]{1, 0, 2, 3}, new int[]{1, 2, 3, 0}, new int[]{1, 2, 0, 3}, new int[]{1, 3, 2, 0}, new int[]{1, 3, 0, 2}, new int[]{2, 0, 1, 3}, new int[]{2, 0, 3, 1}, new int[]{2, 1, 0, 3}, new int[]{2, 1, 3, 0}, new int[]{2, 3, 0, 1}, new int[]{2, 3, 1, 0}, new int[]{3, 0, 2, 1}, new int[]{3, 0, 1, 2}, new int[]{3, 1, 2, 0}, new int[]{3, 1, 0, 2}, new int[]{3, 2, 1, 0}, new int[]{3, 2, 0, 1}};
        PERMUTATIONS = r0;
        sInitialized = false;
        sCachedArrangements = Maps.newHashMap();
    }

    private static synchronized void initialize() {
        synchronized (NowPlayingArranger.class) {
            if (!sInitialized) {
                sTopAffinity = WidgetUtils.parseSparseIntArray((String) FinskyPreferences.myLibraryWidgetTopAffinity.get());
                sBottomAffinity = WidgetUtils.parseSparseIntArray((String) FinskyPreferences.myLibraryWidgetBottomAffinity.get());
                sLeftAffinity = WidgetUtils.parseSparseIntArray((String) FinskyPreferences.myLibraryWidgetLeftAffinity.get());
                sRightAffinity = WidgetUtils.parseSparseIntArray((String) FinskyPreferences.myLibraryWidgetRightAffinity.get());
                sInitialized = true;
            }
        }
    }

    private static void writeAffinities() {
        FinskyPreferences.myLibraryWidgetTopAffinity.put(WidgetUtils.serializeSparseIntArray(sTopAffinity));
        FinskyPreferences.myLibraryWidgetBottomAffinity.put(WidgetUtils.serializeSparseIntArray(sBottomAffinity));
        FinskyPreferences.myLibraryWidgetLeftAffinity.put(WidgetUtils.serializeSparseIntArray(sLeftAffinity));
        FinskyPreferences.myLibraryWidgetRightAffinity.put(WidgetUtils.serializeSparseIntArray(sRightAffinity));
    }

    public static Arrangement arrange(List<ConsumptionAppDocList> scoredBackends, int layoutVariant) {
        int i;
        initialize();
        int numQuadrants = scoredBackends.size();
        int[] backends = new int[numQuadrants];
        for (i = 0; i < backends.length; i++) {
            backends[i] = ((ConsumptionAppDocList) scoredBackends.get(i)).getBackend();
        }
        if (numQuadrants == 0) {
            return new Arrangement(new ConsumptionAppDocList[0], layoutVariant);
        }
        if (numQuadrants == 1) {
            return new Arrangement((ConsumptionAppDocList[]) scoredBackends.toArray(new ConsumptionAppDocList[1]), layoutVariant);
        }
        int effectiveLayoutVariant;
        int[] result = getCachedCandidate(backends, layoutVariant);
        boolean cacheHit = result != null;
        if (!cacheHit) {
            if (FinskyLog.DEBUG) {
                FinskyLog.v("Arrangement cache miss, computing from scratch.", new Object[0]);
            }
            result = computeBestCandidate(backends, layoutVariant);
        }
        if (numQuadrants == 3) {
            effectiveLayoutVariant = determineLayoutVariant3(backends, result);
        } else {
            effectiveLayoutVariant = layoutVariant;
        }
        if (!cacheHit) {
            putCachedCandidate(backends, layoutVariant, result, effectiveLayoutVariant);
        }
        ConsumptionAppDocList[] quadrantToData = new ConsumptionAppDocList[numQuadrants];
        for (i = 0; i < result.length; i++) {
            int backend = result[i];
            for (ConsumptionAppDocList scoredBackend : scoredBackends) {
                if (scoredBackend.getBackend() == backend) {
                    quadrantToData[i] = scoredBackend;
                    break;
                }
            }
        }
        if (FinskyLog.DEBUG) {
            FinskyLog.v("Widget arrangement: quadrants=%s, layoutVariant=%d", Arrays.toString(result), Integer.valueOf(effectiveLayoutVariant));
        }
        return new Arrangement(quadrantToData, effectiveLayoutVariant);
    }

    private static int[] computeBestCandidate(int[] backends, int layoutVariant) {
        int maxScore = -1;
        int numQuadrants = backends.length;
        int[] candidate = new int[numQuadrants];
        int[] result = new int[numQuadrants];
        for (int[] permutation : PERMUTATIONS[numQuadrants]) {
            permute(backends, permutation, candidate);
            int candidateLayoutVariant = layoutVariant;
            if (numQuadrants == 3) {
                candidateLayoutVariant = determineLayoutVariant3(backends, candidate);
            }
            int score = getScore(candidate, candidateLayoutVariant);
            if (FinskyLog.DEBUG) {
                FinskyLog.v("Score for candidate %s: %d", Arrays.toString(candidate), Integer.valueOf(score));
            }
            if (score > maxScore) {
                System.arraycopy(candidate, 0, result, 0, result.length);
                maxScore = score;
            }
        }
        return result;
    }

    private static int determineLayoutVariant3(int[] backends, int[] result) {
        return backends[0] == result[0] ? 0 : 1;
    }

    private static synchronized int[] getCachedCandidate(int[] backends, int layoutVariant) {
        int[] iArr;
        synchronized (NowPlayingArranger.class) {
            iArr = (int[]) sCachedArrangements.get(getCacheKey(backends, layoutVariant));
        }
        return iArr;
    }

    private static synchronized void putCachedCandidate(int[] backends, int requestedLayoutVariant, int[] candidate, int candidateLayoutVariant) {
        synchronized (NowPlayingArranger.class) {
            sCachedArrangements.put(getCacheKey(backends, requestedLayoutVariant), candidate);
            for (int i = 0; i < candidate.length; i++) {
                updateAffinity(candidate[i], Arrangement.getLocation(backends.length, i, candidateLayoutVariant));
            }
            writeAffinities();
        }
    }

    private static String getCacheKey(int[] backends, int layoutVariant) {
        return Arrays.toString(backends) + "/" + layoutVariant;
    }

    private static int getScore(int[] backends, int layoutVariant) {
        int score = 0;
        int size = backends.length;
        for (int i = 0; i < size; i++) {
            score += getScore(backends[i], Arrangement.getLocation(size, i, layoutVariant));
        }
        return score;
    }

    private static synchronized int getScore(int backend, int location) {
        int score;
        synchronized (NowPlayingArranger.class) {
            score = 0;
            if ((location & 4) != 0) {
                score = 0 + sTopAffinity.get(backend, 0);
            }
            if ((location & 8) != 0) {
                score += sBottomAffinity.get(backend, 0);
            }
            if ((location & 1) != 0) {
                score += sLeftAffinity.get(backend, 0);
            }
            if ((location & 2) != 0) {
                score += sRightAffinity.get(backend, 0);
            }
        }
        return score;
    }

    private static synchronized void updateAffinity(int backend, int location) {
        synchronized (NowPlayingArranger.class) {
            if ((location & 4) != 0) {
                increaseAndDecrease(sTopAffinity, sBottomAffinity, backend);
            }
            if ((location & 8) != 0) {
                increaseAndDecrease(sBottomAffinity, sTopAffinity, backend);
            }
            if ((location & 1) != 0) {
                increaseAndDecrease(sLeftAffinity, sRightAffinity, backend);
            }
            if ((location & 2) != 0) {
                increaseAndDecrease(sRightAffinity, sLeftAffinity, backend);
            }
        }
    }

    private static void increaseAndDecrease(SparseIntArray increase, SparseIntArray decrease, int backend) {
        increase.put(backend, Math.min(increase.get(backend, 0) + 1, 10));
        decrease.put(backend, Math.max(decrease.get(backend, 0) - 1, 0));
    }

    private static void permute(int[] original, int[] permutation, int[] target) {
        for (int i = 0; i < permutation.length; i++) {
            target[i] = original[permutation[i]];
        }
    }
}
