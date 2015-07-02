package com.google.android.finsky.billing;

import com.android.i18n.addressinput.ClientCacheManager;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.google.android.finsky.config.G;
import java.io.UnsupportedEncodingException;

public class AddressMetadataCacheManager implements ClientCacheManager {
    private final Cache mCache;

    public AddressMetadataCacheManager(Cache cache) {
        this.mCache = cache;
    }

    public String get(String key) {
        Entry entry = this.mCache.get("AddressMetadataCacheManager-" + key);
        if (entry == null || entry.isExpired()) {
            return "";
        }
        try {
            return new String(entry.data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    public void put(String key, String data) {
        Entry entry = new Entry();
        try {
            entry.data = data.getBytes("UTF-8");
            entry.serverDate = System.currentTimeMillis();
            entry.ttl = entry.serverDate + 604800000;
            this.mCache.put("AddressMetadataCacheManager-" + key, entry);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    public String getAddressServerUrl() {
        return (String) G.vendingAddressServerUrl.get();
    }
}
