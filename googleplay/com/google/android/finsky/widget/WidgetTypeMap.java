package com.google.android.finsky.widget;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.persistence.FileBasedKeyValueStore;
import com.google.android.finsky.utils.persistence.KeyValueStore;
import com.google.android.finsky.utils.persistence.WriteThroughKeyValueStore;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WidgetTypeMap {
    private static WidgetTypeMap sInstance;
    private final WriteThroughKeyValueStore mKeyValueStore;

    public static WidgetTypeMap get(Context context) {
        if (sInstance == null) {
            sInstance = new WidgetTypeMap(context);
        }
        return sInstance;
    }

    private WidgetTypeMap(Context context) {
        this(new FileBasedKeyValueStore(context.getDir("widgets", 0), WidgetTypeMap.class.getSimpleName()));
    }

    WidgetTypeMap(KeyValueStore backingStore) {
        this.mKeyValueStore = new WriteThroughKeyValueStore(backingStore);
        this.mKeyValueStore.forceSynchronousLoad();
    }

    private static String buildKey(Class<? extends BaseWidgetProvider> cls, int appWidgetId) {
        return cls.getSimpleName() + "#" + String.valueOf(appWidgetId);
    }

    public void put(Class<? extends BaseWidgetProvider> cls, int appWidgetId, String data) {
        if (TextUtils.isEmpty(data)) {
            throw new IllegalArgumentException("data cannot be null or empty");
        }
        String key = buildKey(cls, appWidgetId);
        Map<String, String> currentData = this.mKeyValueStore.get(key);
        if (currentData == null) {
            currentData = Collections.emptyMap();
        }
        Map<String, String> updatedData = new HashMap(currentData);
        updatedData.put("type", data);
        updatedData.put("appWidgetId", String.valueOf(appWidgetId));
        updatedData.put("widgetProvider", cls.getSimpleName());
        this.mKeyValueStore.put(key, updatedData);
    }

    public String get(Class<? extends BaseWidgetProvider> cls, int appWidgetId) {
        Map<String, String> data = this.mKeyValueStore.get(buildKey(cls, appWidgetId));
        return data != null ? (String) data.get("type") : null;
    }

    public void delete(Class<? extends BaseWidgetProvider> cls, int appWidgetId) {
        this.mKeyValueStore.delete(buildKey(cls, appWidgetId));
    }

    public int[] getWidgets(Class<? extends BaseWidgetProvider> cls, String type) {
        return getWidgets(cls, type, true);
    }

    public int[] getWidgetsOfExactType(Class<? extends BaseWidgetProvider> cls, String type) {
        return getWidgets(cls, type, false);
    }

    private int[] getWidgets(Class<? extends BaseWidgetProvider> cls, String type, boolean considerAllAffectedWidgets) {
        List<String> appWidgetIdStrings = Lists.newArrayList();
        String desiredCls = cls.getSimpleName();
        for (Map<String, String> entryValues : this.mKeyValueStore.fetchAll().values()) {
            String storedType = (String) entryValues.get("type");
            if ((type.equals(storedType) || (considerAllAffectedWidgets && "all".equals(storedType))) && desiredCls.equals((String) entryValues.get("widgetProvider"))) {
                appWidgetIdStrings.add(entryValues.get("appWidgetId"));
            }
        }
        int N = appWidgetIdStrings.size();
        int[] appWidgetIds = new int[N];
        for (int i = 0; i < N; i++) {
            appWidgetIds[i] = Integer.valueOf((String) appWidgetIdStrings.get(i)).intValue();
        }
        return appWidgetIds;
    }
}
