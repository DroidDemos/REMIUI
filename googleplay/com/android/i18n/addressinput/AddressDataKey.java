package com.android.i18n.addressinput;

import java.util.HashMap;
import java.util.Map;

enum AddressDataKey {
    COUNTRIES,
    FMT,
    ID,
    KEY,
    LANG,
    LFMT,
    REQUIRE,
    STATE_NAME_TYPE,
    SUB_KEYS,
    SUB_LNAMES,
    SUB_MORES,
    SUB_NAMES,
    XZIP,
    ZIP,
    ZIP_NAME_TYPE;
    
    private static final Map<String, AddressDataKey> ADDRESS_KEY_NAME_MAP;

    static {
        ADDRESS_KEY_NAME_MAP = new HashMap();
        for (AddressDataKey field : values()) {
            ADDRESS_KEY_NAME_MAP.put(field.toString().toLowerCase(), field);
        }
    }

    static AddressDataKey get(String keyname) {
        return (AddressDataKey) ADDRESS_KEY_NAME_MAP.get(keyname.toLowerCase());
    }
}
