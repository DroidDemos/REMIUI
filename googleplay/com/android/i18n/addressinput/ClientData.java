package com.android.i18n.addressinput;

import android.util.Log;
import com.android.i18n.addressinput.AddressData.Builder;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

public class ClientData implements DataSource {
    private final Map<String, JsoMap> mBootstrapMap;
    private CacheData mCacheData;

    public ClientData(CacheData cacheData) {
        this.mBootstrapMap = new HashMap();
        this.mCacheData = cacheData;
        buildRegionalData();
    }

    public AddressVerificationNodeData get(String key) {
        JsoMap jso = this.mCacheData.getObj(key);
        if (jso == null) {
            fetchDataIfNotAvailable(key);
            jso = this.mCacheData.getObj(key);
        }
        if (jso == null || !isValidDataKey(key)) {
            return null;
        }
        return createNodeData(jso);
    }

    public AddressVerificationNodeData getDefaultData(String key) {
        JsoMap jso;
        if (key.split("/").length == 1) {
            jso = (JsoMap) this.mBootstrapMap.get(key);
            if (jso != null && isValidDataKey(key)) {
                return createNodeData(jso);
            }
            throw new RuntimeException("key " + key + " does not have bootstrap data");
        }
        key = getCountryKey(key);
        jso = (JsoMap) this.mBootstrapMap.get(key);
        if (jso != null && isValidDataKey(key)) {
            return createNodeData(jso);
        }
        throw new RuntimeException("key " + key + " does not have bootstrap data");
    }

    private String getCountryKey(String hierarchyKey) {
        if (hierarchyKey.split("/").length <= 1) {
            throw new RuntimeException("Cannot get country key with key '" + hierarchyKey + "'");
        } else if (isCountryKey(hierarchyKey)) {
            return hierarchyKey;
        } else {
            String[] parts = hierarchyKey.split("/");
            return parts[0] + "/" + parts[1];
        }
    }

    private boolean isCountryKey(String hierarchyKey) {
        Util.checkNotNull(hierarchyKey, "Cannot use null as a key");
        return hierarchyKey.split("/").length == 2;
    }

    protected AddressVerificationNodeData createNodeData(JsoMap jso) {
        Map<AddressDataKey, String> map = new EnumMap(AddressDataKey.class);
        JSONArray arr = jso.getKeys();
        for (int i = 0; i < arr.length(); i++) {
            try {
                AddressDataKey key = AddressDataKey.get(arr.getString(i));
                if (key != null) {
                    map.put(key, jso.get(key.toString().toLowerCase()));
                }
            } catch (JSONException e) {
            }
        }
        return new AddressVerificationNodeData(map);
    }

    private boolean isValidDataKey(String key) {
        return key.startsWith("data");
    }

    private void buildRegionalData() {
        StringBuilder countries = new StringBuilder();
        for (String countryCode : RegionDataConstants.getCountryFormatMap().keySet()) {
            countries.append(countryCode + "~");
            JsoMap jso = null;
            try {
                jso = JsoMap.buildJsoMap((String) RegionDataConstants.getCountryFormatMap().get(countryCode));
            } catch (JSONException e) {
            }
            this.mBootstrapMap.put(new Builder(KeyType.DATA).setAddressData(new Builder().setCountry(countryCode).build()).build().toString(), jso);
        }
        countries.setLength(countries.length() - 1);
        JsoMap jsoData = null;
        try {
            jsoData = JsoMap.buildJsoMap("{\"id\":\"data\",\"" + AddressDataKey.COUNTRIES.toString().toLowerCase() + "\": \"" + countries.toString() + "\"}");
        } catch (JSONException e2) {
        }
        this.mBootstrapMap.put("data", jsoData);
    }

    private void fetchDataIfNotAvailable(String key) {
        if (this.mCacheData.getObj(key) == null) {
            JsoMap regionalData = (JsoMap) this.mBootstrapMap.get(key);
            NotifyingListener listener = new NotifyingListener(this);
            if (LookupKey.hasValidKeyPrefix(key)) {
                LookupKey lookupKey = new Builder(key).build();
                this.mCacheData.fetchDynamicData(lookupKey, regionalData, listener);
                try {
                    listener.waitLoadingEnd();
                    if (this.mCacheData.getObj(key) == null && isCountryKey(key)) {
                        Log.i("ClientData", "Server failure: looking up key in region data constants.");
                        this.mCacheData.getFromRegionDataConstants(lookupKey);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void requestData(LookupKey key, DataLoadListener listener) {
        Util.checkNotNull(key, "Null lookup key not allowed");
        this.mCacheData.fetchDynamicData(key, (JsoMap) this.mBootstrapMap.get(key.toString()), listener);
    }
}
