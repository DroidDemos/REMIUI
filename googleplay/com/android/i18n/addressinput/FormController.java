package com.android.i18n.addressinput;

import com.android.i18n.addressinput.AddressData.Builder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class FormController {
    private static final AddressField[] ADDRESS_HIERARCHY;
    private static final LookupKey ROOT_KEY;
    private String mCurrentCountry;
    private ClientData mIntegratedData;
    private String mLanguageCode;

    static {
        ROOT_KEY = getDataKeyForRoot();
        ADDRESS_HIERARCHY = new AddressField[]{AddressField.COUNTRY, AddressField.ADMIN_AREA, AddressField.LOCALITY, AddressField.DEPENDENT_LOCALITY};
    }

    FormController(ClientData integratedData, String languageCode, String currentCountry) {
        Util.checkNotNull(integratedData, "null data not allowed");
        this.mLanguageCode = languageCode;
        this.mCurrentCountry = currentCountry;
        LookupKey defaultCountryKey = getDataKeyFor(new Builder().setCountry("ZZ").build());
        Util.checkNotNull(integratedData.getDefaultData(defaultCountryKey.toString()), "require data for default country key: " + defaultCountryKey);
        this.mIntegratedData = integratedData;
    }

    void setLanguageCode(String languageCode) {
        this.mLanguageCode = languageCode;
    }

    void setCurrentCountry(String currentCountry) {
        this.mCurrentCountry = currentCountry;
    }

    private ScriptType getScriptType() {
        if (this.mLanguageCode == null || !Util.isExplicitLatinScript(this.mLanguageCode)) {
            return ScriptType.LOCAL;
        }
        return ScriptType.LATIN;
    }

    private static LookupKey getDataKeyForRoot() {
        return new Builder(KeyType.DATA).setAddressData(new Builder().build()).build();
    }

    LookupKey getDataKeyFor(AddressData address) {
        return new Builder(KeyType.DATA).setAddressData(address).build();
    }

    void requestDataForAddress(AddressData address, DataLoadListener listener) {
        Util.checkNotNull(address.getPostalCountry(), "null country not allowed");
        Queue<String> subkeys = new LinkedList();
        for (AddressField field : ADDRESS_HIERARCHY) {
            String value = address.getFieldValue(field);
            if (value == null) {
                break;
            }
            subkeys.add(value);
        }
        if (subkeys.size() == 0) {
            throw new RuntimeException("Need at least country level info");
        }
        if (listener != null) {
            listener.dataLoadingBegin();
        }
        requestDataRecursively(ROOT_KEY, subkeys, listener);
    }

    private void requestDataRecursively(final LookupKey key, final Queue<String> subkeys, final DataLoadListener listener) {
        Util.checkNotNull(key, "Null key not allowed");
        Util.checkNotNull(subkeys, "Null subkeys not allowed");
        this.mIntegratedData.requestData(key, new DataLoadListener() {
            public void dataLoadingBegin() {
            }

            public void dataLoadingEnd() {
                List<RegionData> subregions = FormController.this.getRegionData(key);
                if (!subregions.isEmpty()) {
                    if (subkeys.size() > 0) {
                        String subkey = (String) subkeys.remove();
                        for (RegionData subregion : subregions) {
                            if (subregion.isValidName(subkey)) {
                                FormController.this.requestDataRecursively(FormController.this.buildDataLookupKey(key, subregion.getKey()), subkeys, listener);
                                return;
                            }
                        }
                    }
                    FormController.this.requestDataRecursively(FormController.this.buildDataLookupKey(key, ((RegionData) subregions.get(0)).getKey()), new LinkedList(), listener);
                } else if (listener != null) {
                    listener.dataLoadingEnd();
                }
            }
        });
    }

    private LookupKey buildDataLookupKey(LookupKey lookupKey, String subKey) {
        String[] subKeys = lookupKey.toString().split("/");
        String languageCodeSubTag = this.mLanguageCode == null ? null : Util.getLanguageSubtag(this.mLanguageCode);
        String key = lookupKey.toString() + "/" + subKey;
        if (!(subKeys.length != 1 || languageCodeSubTag == null || isDefaultLanguage(languageCodeSubTag))) {
            key = key + "--" + languageCodeSubTag.toString();
        }
        return new Builder(key).build();
    }

    boolean isDefaultLanguage(String languageCode) {
        if (languageCode == null) {
            return true;
        }
        if (Util.trimToNull(this.mIntegratedData.getDefaultData(getDataKeyFor(new Builder().setCountry(this.mCurrentCountry).build()).toString()).get(AddressDataKey.LANG)) == null || Util.getLanguageSubtag(languageCode).equals(Util.getLanguageSubtag(languageCode))) {
            return true;
        }
        return false;
    }

    List<RegionData> getRegionData(LookupKey key) {
        if (key.getKeyType() == KeyType.EXAMPLES) {
            throw new RuntimeException("example key not allowed for getting region data");
        }
        Util.checkNotNull(key, "null regionKey not allowed");
        LookupKey normalizedKey = normalizeLookupKey(key);
        List<RegionData> results = new ArrayList();
        int i;
        if (normalizedKey.equals(ROOT_KEY)) {
            String[] countries = splitData(this.mIntegratedData.getDefaultData(normalizedKey.toString()).get(AddressDataKey.COUNTRIES));
            for (i = 0; i < countries.length; i++) {
                results.add(new Builder().setKey(countries[i]).setName(countries[i]).build());
            }
        } else {
            AddressVerificationNodeData data = this.mIntegratedData.get(normalizedKey.toString());
            if (data != null) {
                String[] keys = splitData(data.get(AddressDataKey.SUB_KEYS));
                String[] names = getScriptType() == ScriptType.LOCAL ? splitData(data.get(AddressDataKey.SUB_NAMES)) : splitData(data.get(AddressDataKey.SUB_LNAMES));
                i = 0;
                while (i < keys.length) {
                    results.add(new Builder().setKey(keys[i]).setName(i < names.length ? names[i] : keys[i]).build());
                    i++;
                }
            }
        }
        return results;
    }

    private String[] splitData(String raw) {
        if (raw == null || raw.length() == 0) {
            return new String[0];
        }
        return raw.split("~");
    }

    private String getSubKey(LookupKey parentKey, String name) {
        for (RegionData subRegion : getRegionData(parentKey)) {
            if (subRegion.isValidName(name)) {
                return subRegion.getKey();
            }
        }
        return null;
    }

    private LookupKey normalizeLookupKey(LookupKey key) {
        Util.checkNotNull(key);
        if (key.getKeyType() != KeyType.DATA) {
            throw new RuntimeException("Only DATA keyType is supported");
        }
        String[] subStr = key.toString().split("/");
        if (subStr.length < 2) {
            return key;
        }
        StringBuilder sb = new StringBuilder(subStr[0]);
        int i = 1;
        while (i < subStr.length) {
            String languageCode = null;
            if (i == 1 && subStr[i].contains("--")) {
                String[] s = subStr[i].split("--");
                subStr[i] = s[0];
                languageCode = s[1];
            }
            String normalizedSubKey = getSubKey(new Builder(sb.toString()).build(), subStr[i]);
            if (normalizedSubKey == null) {
                while (i < subStr.length) {
                    sb.append("/").append(subStr[i]);
                    i++;
                }
                return new Builder(sb.toString()).build();
            }
            sb.append("/").append(normalizedSubKey);
            if (languageCode != null) {
                sb.append("--").append(languageCode);
            }
            i++;
        }
        return new Builder(sb.toString()).build();
    }
}
