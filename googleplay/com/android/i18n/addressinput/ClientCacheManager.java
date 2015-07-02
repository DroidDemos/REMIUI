package com.android.i18n.addressinput;

public interface ClientCacheManager {
    String get(String str);

    String getAddressServerUrl();

    void put(String str, String str2);
}
