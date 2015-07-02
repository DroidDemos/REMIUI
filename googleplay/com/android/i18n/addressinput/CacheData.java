package com.android.i18n.addressinput;

import android.util.Log;
import java.util.EventListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public final class CacheData {
    private final HashSet<String> mBadKeys;
    private final JsoMap mCache;
    private final ClientCacheManager mClientCacheManager;
    private final HashSet<String> mRequestedKeys;
    private String mServiceUrl;
    private final HashMap<LookupKey, HashSet<CacheListener>> mTemporaryListenerStore;

    private interface CacheListener extends EventListener {
        void onAdd(String str);
    }

    private class JsonHandler {
        private final JSONObject mExistingJso;
        private final String mKey;
        private final DataLoadListener mListener;

        private JsonHandler(String key, JSONObject oldJso, DataLoadListener listener) {
            Util.checkNotNull(key);
            this.mKey = key;
            this.mExistingJso = oldJso;
            this.mListener = listener;
        }

        private void handleJson(JsoMap map) {
            if (map == null) {
                Log.w("CacheData", "server returns null for key:" + this.mKey);
                CacheData.this.mBadKeys.add(this.mKey);
                CacheData.this.notifyListenersAfterJobDone(this.mKey);
                CacheData.this.triggerDataLoadingEndIfNotNull(this.mListener);
            } else if (map.has(AddressDataKey.ID.name().toLowerCase())) {
                if (this.mExistingJso != null) {
                    map.mergeData((JsoMap) this.mExistingJso);
                }
                CacheData.this.mCache.putObj(this.mKey, map);
                CacheData.this.notifyListenersAfterJobDone(this.mKey);
                CacheData.this.triggerDataLoadingEndIfNotNull(this.mListener);
            } else {
                Log.w("CacheData", "invalid or empty data returned for key: " + this.mKey);
                CacheData.this.mBadKeys.add(this.mKey);
                CacheData.this.notifyListenersAfterJobDone(this.mKey);
                CacheData.this.triggerDataLoadingEndIfNotNull(this.mListener);
            }
        }
    }

    public CacheData() {
        this(new SimpleClientCacheManager());
    }

    public CacheData(ClientCacheManager clientCacheManager) {
        this.mRequestedKeys = new HashSet();
        this.mBadKeys = new HashSet();
        this.mTemporaryListenerStore = new HashMap();
        this.mClientCacheManager = clientCacheManager;
        setUrl(this.mClientCacheManager.getAddressServerUrl());
        this.mCache = JsoMap.createEmptyJsoMap();
    }

    public void setUrl(String url) {
        Util.checkNotNull(url, "Cannot set URL of address data server to null.");
        this.mServiceUrl = url;
    }

    public String getUrl() {
        return this.mServiceUrl;
    }

    private void triggerDataLoadingEndIfNotNull(DataLoadListener listener) {
        if (listener != null) {
            listener.dataLoadingEnd();
        }
    }

    void fetchDynamicData(final LookupKey key, JSONObject existingJso, final DataLoadListener listener) {
        Util.checkNotNull(key, "null key not allowed.");
        if (listener != null) {
            listener.dataLoadingBegin();
        }
        if (this.mCache.containsKey(key.toString())) {
            triggerDataLoadingEndIfNotNull(listener);
        } else if (this.mBadKeys.contains(key.toString())) {
            triggerDataLoadingEndIfNotNull(listener);
        } else if (this.mRequestedKeys.add(key.toString())) {
            String dataFromClientCache = this.mClientCacheManager.get(key.toString());
            if (dataFromClientCache != null && dataFromClientCache.length() > 0) {
                try {
                    new JsonHandler(key.toString(), existingJso, listener).handleJson(JsoMap.buildJsoMap(dataFromClientCache));
                    return;
                } catch (JSONException e) {
                    Log.w("CacheData", "Data from client's cache is in the wrong format: " + dataFromClientCache);
                }
            }
            JsonpRequestBuilder jsonp = new JsonpRequestBuilder();
            jsonp.setTimeout(5000);
            final JsonHandler handler = new JsonHandler(key.toString(), existingJso, listener);
            jsonp.requestObject(this.mServiceUrl + "/" + key.toString(), new AsyncCallback<JsoMap>() {
                public void onFailure(Throwable caught) {
                    Log.w("CacheData", "Request for key " + key + " failed");
                    CacheData.this.mRequestedKeys.remove(key.toString());
                    CacheData.this.notifyListenersAfterJobDone(key.toString());
                    CacheData.this.triggerDataLoadingEndIfNotNull(listener);
                }

                public void onSuccess(JsoMap result) {
                    handler.handleJson(result);
                    CacheData.this.mClientCacheManager.put(key.toString(), result.toString());
                }
            });
        } else {
            Log.d("CacheData", "data for key " + key + " requested but not cached yet");
            addListenerToTempStore(key, new CacheListener() {
                public void onAdd(String myKey) {
                    CacheData.this.triggerDataLoadingEndIfNotNull(listener);
                }
            });
        }
    }

    void getFromRegionDataConstants(LookupKey key) {
        Util.checkNotNull(key, "null key not allowed.");
        String data = (String) RegionDataConstants.getCountryFormatMap().get(key.getValueForUpperLevelField(AddressField.COUNTRY));
        if (data != null) {
            try {
                this.mCache.putObj(key.toString(), JsoMap.buildJsoMap(data));
            } catch (JSONException e) {
                Log.w("CacheData", "Failed to parse data for key " + key + " from RegionDataConstants");
            }
        }
    }

    public JsoMap getObj(String key) {
        Util.checkNotNull(key, "null key not allowed");
        return this.mCache.getObj(key);
    }

    private void notifyListenersAfterJobDone(String key) {
        HashSet<CacheListener> listeners = (HashSet) this.mTemporaryListenerStore.get(new Builder(key).build());
        if (listeners != null) {
            Iterator i$ = listeners.iterator();
            while (i$.hasNext()) {
                ((CacheListener) i$.next()).onAdd(key.toString());
            }
            listeners.clear();
        }
    }

    private void addListenerToTempStore(LookupKey key, CacheListener listener) {
        Util.checkNotNull(key);
        Util.checkNotNull(listener);
        HashSet<CacheListener> listeners = (HashSet) this.mTemporaryListenerStore.get(key);
        if (listeners == null) {
            listeners = new HashSet();
            this.mTemporaryListenerStore.put(key, listeners);
        }
        listeners.add(listener);
    }
}
