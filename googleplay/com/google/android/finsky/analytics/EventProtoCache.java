package com.google.android.finsky.analytics;

import com.google.android.finsky.analytics.PlayStore.PlayStoreBackgroundActionEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreClickEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreImpressionEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreLogEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreSearchEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.analytics.PlayStore.SearchSuggestionReport;
import com.google.android.finsky.utils.FinskyLog;
import java.lang.reflect.Array;

public class EventProtoCache {
    private static EventProtoCache INSTANCE;
    private final ElementCache<PlayStoreBackgroundActionEvent> mCachePlayStoreBackgroundAction;
    private final ElementCache<PlayStoreClickEvent> mCachePlayStoreClick;
    private final ElementCache<PlayStoreImpressionEvent> mCachePlayStoreImpression;
    private final ElementCache<PlayStoreLogEvent> mCachePlayStoreLogEvent;
    private final ElementCache<PlayStoreSearchEvent> mCachePlayStoreSearch;
    private final ElementCache<SearchSuggestionReport> mCachePlayStoreSearchSuggestionReport;
    private final ElementCache<PlayStoreUiElement> mCachePlayStoreUIElement;

    private static class ElementCache<T> {
        private T[] mCache;
        Class<?> mClazz;
        private int mCount;
        private int mHighWater;
        private final int mLimit;

        public ElementCache(Class<?> clazz, int limit) {
            this.mLimit = limit;
            this.mCount = 0;
            this.mHighWater = 0;
            this.mCache = (Object[]) Array.newInstance(clazz, limit);
            this.mClazz = clazz;
        }

        public T obtain() {
            T result = null;
            synchronized (this) {
                if (this.mCount > 0) {
                    this.mCount--;
                    result = this.mCache[this.mCount];
                    this.mCache[this.mCount] = null;
                } else {
                    try {
                        result = this.mClazz.newInstance();
                    } catch (Exception e) {
                        FinskyLog.wtf("Exception from mClazz.newInstance ", e);
                    }
                }
            }
            return result;
        }

        public void recycle(T element) {
            synchronized (this) {
                if (this.mCount < this.mLimit) {
                    this.mCache[this.mCount] = element;
                    this.mCount++;
                    if (this.mCount > this.mHighWater) {
                        this.mHighWater = this.mCount;
                    }
                }
            }
        }
    }

    static {
        INSTANCE = null;
    }

    public static synchronized EventProtoCache getInstance() {
        EventProtoCache eventProtoCache;
        synchronized (EventProtoCache.class) {
            if (INSTANCE == null) {
                INSTANCE = new EventProtoCache();
            }
            eventProtoCache = INSTANCE;
        }
        return eventProtoCache;
    }

    private EventProtoCache() {
        this.mCachePlayStoreLogEvent = new ElementCache(PlayStoreLogEvent.class, 40);
        this.mCachePlayStoreImpression = new ElementCache(PlayStoreImpressionEvent.class, 10);
        this.mCachePlayStoreClick = new ElementCache(PlayStoreClickEvent.class, 10);
        this.mCachePlayStoreBackgroundAction = new ElementCache(PlayStoreBackgroundActionEvent.class, 10);
        this.mCachePlayStoreSearch = new ElementCache(PlayStoreSearchEvent.class, 10);
        this.mCachePlayStoreUIElement = new ElementCache(PlayStoreUiElement.class, 50);
        this.mCachePlayStoreSearchSuggestionReport = new ElementCache(SearchSuggestionReport.class, 10);
    }

    public PlayStoreLogEvent obtainPlayStoreLogEvent() {
        return (PlayStoreLogEvent) this.mCachePlayStoreLogEvent.obtain();
    }

    public void recycle(PlayStoreLogEvent event) {
        if (event.impression != null) {
            recycle(event.impression);
        }
        if (event.click != null) {
            recycle(event.click);
        }
        if (event.backgroundAction != null) {
            recycle(event.backgroundAction);
        }
        if (event.search != null) {
            recycle(event.search);
        }
        event.clear();
        this.mCachePlayStoreLogEvent.recycle(event);
    }

    public PlayStoreImpressionEvent obtainPlayStoreImpressionEvent() {
        return (PlayStoreImpressionEvent) this.mCachePlayStoreImpression.obtain();
    }

    public void recycle(PlayStoreImpressionEvent event) {
        if (event.tree != null) {
            recycle(event.tree);
        }
        for (PlayStoreUiElement element : event.referrerPath) {
            recycle(element);
        }
        event.clear();
        this.mCachePlayStoreImpression.recycle(event);
    }

    public PlayStoreClickEvent obtainPlayStoreClickEvent() {
        return (PlayStoreClickEvent) this.mCachePlayStoreClick.obtain();
    }

    public void recycle(PlayStoreClickEvent event) {
        for (PlayStoreUiElement element : event.elementPath) {
            recycle(element);
        }
        event.clear();
        this.mCachePlayStoreClick.recycle(event);
    }

    public PlayStoreBackgroundActionEvent obtainPlayStoreBackgroundActionEvent() {
        return (PlayStoreBackgroundActionEvent) this.mCachePlayStoreBackgroundAction.obtain();
    }

    public void recycle(PlayStoreBackgroundActionEvent event) {
        if (event.searchSuggestionReport != null) {
            recycle(event.searchSuggestionReport);
        }
        event.clear();
        this.mCachePlayStoreBackgroundAction.recycle(event);
    }

    public PlayStoreSearchEvent obtainPlayStoreSearchEvent() {
        return (PlayStoreSearchEvent) this.mCachePlayStoreSearch.obtain();
    }

    public void recycle(PlayStoreSearchEvent event) {
        event.clear();
        this.mCachePlayStoreSearch.recycle(event);
    }

    public PlayStoreUiElement obtainPlayStoreUiElement() {
        return (PlayStoreUiElement) this.mCachePlayStoreUIElement.obtain();
    }

    public void recycle(PlayStoreUiElement element) {
        for (PlayStoreUiElement child : element.child) {
            recycle(child);
        }
        element.clear();
        this.mCachePlayStoreUIElement.recycle(element);
    }

    public SearchSuggestionReport obtainPlayStoreSearchSuggestionReport() {
        return (SearchSuggestionReport) this.mCachePlayStoreSearchSuggestionReport.obtain();
    }

    public void recycle(SearchSuggestionReport searchSuggestionReport) {
        searchSuggestionReport.clear();
        this.mCachePlayStoreSearchSuggestionReport.recycle(searchSuggestionReport);
    }
}
