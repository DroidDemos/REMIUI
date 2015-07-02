package com.google.android.finsky.utils.persistence;

import android.os.Handler;
import android.os.Looper;
import com.google.android.finsky.utils.BackgroundThreadFactory;
import com.google.android.finsky.utils.Lists;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WriteThroughKeyValueStore implements KeyValueStore {
    private static final ExecutorService sWriteThread;
    private final KeyValueStore mBackingStore;
    private Map<String, Map<String, String>> mDataMap;
    private final Handler mHandler;
    private List<Runnable> mOnCompleteListeners;

    static {
        sWriteThread = Executors.newSingleThreadExecutor(new BackgroundThreadFactory());
    }

    public WriteThroughKeyValueStore(KeyValueStore backingStore) {
        this.mDataMap = null;
        this.mOnCompleteListeners = Lists.newArrayList();
        this.mBackingStore = backingStore;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    WriteThroughKeyValueStore(KeyValueStore backingStore, Handler postHandler) {
        this.mDataMap = null;
        this.mOnCompleteListeners = Lists.newArrayList();
        this.mBackingStore = backingStore;
        this.mHandler = postHandler;
    }

    public void load(Runnable onComplete) {
        ensureOnMainThread();
        if (this.mDataMap != null) {
            this.mHandler.post(onComplete);
            return;
        }
        this.mOnCompleteListeners.add(onComplete);
        if (this.mOnCompleteListeners.size() == 1) {
            fetchAllFromBackingStoreAsync();
        }
    }

    public void forceSynchronousLoad() {
        ensureOnMainThread();
        this.mDataMap = this.mBackingStore.fetchAll();
        this.mOnCompleteListeners.clear();
    }

    public Map<String, String> get(String key) {
        ensureReadyAndOnMainThread();
        Map<String, String> dataMap = (Map) this.mDataMap.get(key);
        return dataMap != null ? Collections.unmodifiableMap(dataMap) : null;
    }

    public void delete(final String key) {
        ensureReadyAndOnMainThread();
        this.mDataMap.remove(key);
        sWriteThread.submit(new Runnable() {
            public void run() {
                WriteThroughKeyValueStore.this.mBackingStore.delete(key);
            }
        });
    }

    public Map<String, Map<String, String>> fetchAll() {
        ensureReadyAndOnMainThread();
        if (this.mDataMap.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, Map<String, String>> result = new HashMap();
        for (String key : this.mDataMap.keySet()) {
            result.put(key, Collections.unmodifiableMap((Map) this.mDataMap.get(key)));
        }
        return Collections.unmodifiableMap(result);
    }

    public void put(final String key, Map<String, String> valueMap) {
        ensureReadyAndOnMainThread();
        this.mDataMap.put(key, valueMap);
        final HashMap<String, String> mapCopy = new HashMap(valueMap);
        sWriteThread.submit(new Runnable() {
            public void run() {
                WriteThroughKeyValueStore.this.mBackingStore.put(key, mapCopy);
            }
        });
    }

    private void ensureOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("Tried to access data off of the main thread.");
        }
    }

    private void ensureReadyAndOnMainThread() {
        if (this.mDataMap == null) {
            throw new IllegalStateException("Tried to access data before initializing.");
        }
        ensureOnMainThread();
    }

    private void fetchAllFromBackingStoreAsync() {
        sWriteThread.submit(new Runnable() {
            public void run() {
                final Map<String, Map<String, String>> backingData = WriteThroughKeyValueStore.this.mBackingStore.fetchAll();
                WriteThroughKeyValueStore.this.mHandler.post(new Runnable() {
                    public void run() {
                        WriteThroughKeyValueStore.this.handleDataLoaded(backingData);
                    }
                });
            }
        });
    }

    private void handleDataLoaded(Map<String, Map<String, String>> loadedBackingData) {
        this.mDataMap = loadedBackingData;
        int numListeners = this.mOnCompleteListeners.size();
        for (int i = 0; i < numListeners; i++) {
            ((Runnable) this.mOnCompleteListeners.get(i)).run();
        }
        this.mOnCompleteListeners.clear();
    }
}
