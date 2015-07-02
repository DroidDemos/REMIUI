package com.google.android.finsky.utils;

import com.google.android.finsky.api.DfeApi;
import java.util.List;

public class MyPeoplePageHelper {
    private static long sLastMutationTimeMs;
    private static List<String> sListUrls;

    static {
        sListUrls = Lists.newArrayList();
    }

    public static void addPeoplePageListUrls(List<String> listUrls) {
        sListUrls.addAll(listUrls);
    }

    public static boolean hasMutationOccurredSince(long timestampMs) {
        return timestampMs < sLastMutationTimeMs;
    }

    public static void onMutationOccurred(DfeApi dfeApi) {
        sLastMutationTimeMs = System.currentTimeMillis();
        for (String listUrl : sListUrls) {
            dfeApi.invalidateListCache(listUrl, true);
        }
        sListUrls.clear();
    }
}
