package com.android.i18n.addressinput;

import java.util.Map;

public class AddressVerificationNodeData {
    private final Map<AddressDataKey, String> mMap;

    public AddressVerificationNodeData(Map<AddressDataKey, String> map) {
        Util.checkNotNull("Cannot construct StandardNodeData with null map");
        this.mMap = map;
    }

    public boolean containsKey(AddressDataKey key) {
        return this.mMap.containsKey(key);
    }

    public String get(AddressDataKey key) {
        return (String) this.mMap.get(key);
    }
}
