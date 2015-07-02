package com.google.android.gms.appdatasearch;

import java.util.HashMap;
import java.util.Map;

public class GlobalSearchSections {
    private static final String[] Ds;
    private static final Map<String, Integer> Dt;

    static {
        int i = 0;
        Ds = new String[]{"text1", "text2", "icon", "intent_action", "intent_data", "intent_data_id", "intent_extra_data", "suggest_large_icon", "intent_activity"};
        Dt = new HashMap(Ds.length);
        while (i < Ds.length) {
            Dt.put(Ds[i], Integer.valueOf(i));
            i++;
        }
    }

    public static String getSectionName(int id) {
        return (id < 0 || id >= Ds.length) ? null : Ds[id];
    }
}
