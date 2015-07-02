package com.google.android.finsky.utils;

import android.os.Bundle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectMap {
    private Bundle mBundle;
    private Map<String, Object> mHashmap;

    public ObjectMap() {
        this.mHashmap = new HashMap();
        this.mBundle = new Bundle();
    }

    public Bundle getBundle() {
        return this.mBundle;
    }

    public boolean containsKey(String key) {
        return this.mHashmap.containsKey(key) || this.mBundle.containsKey(key);
    }

    public void put(String key, Object value) {
        this.mHashmap.put(key, value);
    }

    public Object get(String key) {
        if (this.mHashmap.containsKey(key)) {
            return this.mHashmap.get(key);
        }
        return this.mBundle.get(key);
    }

    public void putBoolean(String key, boolean value) {
        this.mHashmap.put(key, Boolean.valueOf(value));
    }

    public boolean getBoolean(String key) {
        if (this.mHashmap.containsKey(key)) {
            return ((Boolean) this.mHashmap.get(key)).booleanValue();
        }
        return this.mBundle.getBoolean(key);
    }

    public void putInt(String key, int value) {
        this.mHashmap.put(key, Integer.valueOf(value));
    }

    public int getInt(String key) {
        if (this.mHashmap.containsKey(key)) {
            return ((Integer) this.mHashmap.get(key)).intValue();
        }
        return this.mBundle.getInt(key);
    }

    public void putFloat(String key, float value) {
        this.mHashmap.put(key, Float.valueOf(value));
    }

    public float getFloat(String key) {
        if (this.mHashmap.containsKey(key)) {
            return ((Float) this.mHashmap.get(key)).floatValue();
        }
        return this.mBundle.getFloat(key);
    }

    public void putString(String key, String value) {
        this.mHashmap.put(key, value);
    }

    public String getString(String key) {
        if (this.mHashmap.containsKey(key)) {
            return (String) this.mHashmap.get(key);
        }
        return this.mBundle.getString(key);
    }

    public <T> List<T> getList(String key) {
        if (this.mHashmap.containsKey(key)) {
            return (List) this.mHashmap.get(key);
        }
        return (List) this.mBundle.getParcelable(key);
    }

    public void remove(String key) {
        this.mHashmap.remove(key);
        this.mBundle.remove(key);
    }

    public void putAll(ObjectMap objectMap) {
        this.mHashmap.putAll(objectMap.mHashmap);
        this.mBundle.putAll(objectMap.getBundle());
    }

    public void clear() {
        this.mHashmap.clear();
        this.mBundle.clear();
    }
}
