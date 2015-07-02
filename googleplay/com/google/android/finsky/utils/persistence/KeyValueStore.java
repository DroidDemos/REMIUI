package com.google.android.finsky.utils.persistence;

import java.util.Map;

public interface KeyValueStore {
    void delete(String str);

    Map<String, Map<String, String>> fetchAll();

    void put(String str, Map<String, String> map);
}
