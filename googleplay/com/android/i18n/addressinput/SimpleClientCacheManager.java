package com.android.i18n.addressinput;

public class SimpleClientCacheManager implements ClientCacheManager {
    public String get(String key) {
        return "";
    }

    public void put(String key, String data) {
    }

    public String getAddressServerUrl() {
        return "http://i18napis.appspot.com/address";
    }
}
