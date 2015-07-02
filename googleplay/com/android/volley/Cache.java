package com.android.volley;

import java.util.Collections;
import java.util.Map;

public interface Cache {

    public static class Entry {
        public byte[] data;
        public String etag;
        public Map<String, String> responseHeaders;
        public long serverDate;
        public long softTtl;
        public long ttl;

        public Entry() {
            this.responseHeaders = Collections.emptyMap();
        }

        public boolean isExpired() {
            return this.ttl < System.currentTimeMillis();
        }

        public boolean refreshNeeded() {
            return this.softTtl < System.currentTimeMillis();
        }
    }

    void clear();

    Entry get(String str);

    void initialize();

    void invalidate(String str, boolean z);

    void put(String str, Entry entry);
}
