package com.android.i18n.addressinput;

import java.util.EnumMap;
import java.util.Map;

final class LookupKey {
    private static final AddressField[] HIERARCHY;
    private final String mKeyString;
    private final KeyType mKeyType;
    private final String mLanguageCode;
    private final Map<AddressField, String> mNodes;
    private final ScriptType mScriptType;

    static class Builder {
        private KeyType keyType;
        private String languageCode;
        private Map<AddressField, String> nodes;
        private ScriptType script;

        Builder(KeyType keyType) {
            this.script = ScriptType.LOCAL;
            this.nodes = new EnumMap(AddressField.class);
            this.keyType = keyType;
        }

        Builder(LookupKey oldKey) {
            this.script = ScriptType.LOCAL;
            this.nodes = new EnumMap(AddressField.class);
            this.keyType = oldKey.mKeyType;
            this.script = oldKey.mScriptType;
            this.languageCode = oldKey.mLanguageCode;
            AddressField[] arr$ = LookupKey.HIERARCHY;
            int len$ = arr$.length;
            int i$ = 0;
            while (i$ < len$) {
                AddressField field = arr$[i$];
                if (oldKey.mNodes.containsKey(field)) {
                    this.nodes.put(field, oldKey.mNodes.get(field));
                    i$++;
                } else {
                    return;
                }
            }
        }

        Builder(String keyString) {
            this.script = ScriptType.LOCAL;
            this.nodes = new EnumMap(AddressField.class);
            String[] parts = keyString.split("/");
            if (!parts[0].equals(KeyType.DATA.name().toLowerCase()) && !parts[0].equals(KeyType.EXAMPLES.name().toLowerCase())) {
                throw new RuntimeException("Wrong key type: " + parts[0]);
            } else if (parts.length > LookupKey.HIERARCHY.length + 1) {
                throw new RuntimeException("input key '" + keyString + "' deeper than supported hierarchy");
            } else if (parts[0].equals("data")) {
                String substr;
                this.keyType = KeyType.DATA;
                if (parts.length > 1) {
                    substr = Util.trimToNull(parts[1]);
                    if (substr.contains("--")) {
                        String[] s = substr.split("--");
                        if (s.length != 2) {
                            throw new RuntimeException("Wrong format: Substring should be country code--language code");
                        }
                        substr = s[0];
                        this.languageCode = s[1];
                    }
                    this.nodes.put(LookupKey.HIERARCHY[0], substr);
                }
                if (parts.length > 2) {
                    int i = 2;
                    while (i < parts.length) {
                        substr = Util.trimToNull(parts[i]);
                        if (substr != null) {
                            this.nodes.put(LookupKey.HIERARCHY[i - 1], substr);
                            i++;
                        } else {
                            return;
                        }
                    }
                }
            } else if (parts[0].equals("examples")) {
                this.keyType = KeyType.EXAMPLES;
                if (parts.length > 1) {
                    this.nodes.put(AddressField.COUNTRY, parts[1]);
                }
                if (parts.length > 2) {
                    String scriptStr = parts[2];
                    if (scriptStr.equals("local")) {
                        this.script = ScriptType.LOCAL;
                    } else if (scriptStr.equals("latin")) {
                        this.script = ScriptType.LATIN;
                    } else {
                        throw new RuntimeException("Script type has to be either latin or local.");
                    }
                }
                if (parts.length > 3 && !parts[3].equals("_default")) {
                    this.languageCode = parts[3];
                }
            }
        }

        Builder setAddressData(AddressData data) {
            this.languageCode = data.getLanguageCode();
            if (this.languageCode != null && Util.isExplicitLatinScript(this.languageCode)) {
                this.script = ScriptType.LATIN;
            }
            if (data.getPostalCountry() != null) {
                this.nodes.put(AddressField.COUNTRY, data.getPostalCountry());
                if (data.getAdministrativeArea() != null) {
                    this.nodes.put(AddressField.ADMIN_AREA, data.getAdministrativeArea());
                    if (data.getLocality() != null) {
                        this.nodes.put(AddressField.LOCALITY, data.getLocality());
                        if (data.getDependentLocality() != null) {
                            this.nodes.put(AddressField.DEPENDENT_LOCALITY, data.getDependentLocality());
                        }
                    }
                }
            }
            return this;
        }

        LookupKey build() {
            return new LookupKey();
        }
    }

    enum KeyType {
        DATA,
        EXAMPLES
    }

    enum ScriptType {
        LATIN,
        LOCAL
    }

    static {
        HIERARCHY = new AddressField[]{AddressField.COUNTRY, AddressField.ADMIN_AREA, AddressField.LOCALITY, AddressField.DEPENDENT_LOCALITY};
    }

    private LookupKey(Builder builder) {
        this.mKeyType = builder.keyType;
        this.mScriptType = builder.script;
        this.mNodes = builder.nodes;
        this.mLanguageCode = builder.languageCode;
        this.mKeyString = getKeyString();
    }

    LookupKey getKeyForUpperLevelField(AddressField field) {
        if (this.mKeyType != KeyType.DATA) {
            throw new RuntimeException("Only support getting parent keys for the data key type.");
        }
        Builder newKeyBuilder = new Builder(this);
        boolean removeNode = false;
        boolean fieldInHierarchy = false;
        for (AddressField hierarchyField : HIERARCHY) {
            if (removeNode && newKeyBuilder.nodes.containsKey(hierarchyField)) {
                newKeyBuilder.nodes.remove(hierarchyField);
            }
            if (hierarchyField == field) {
                if (!newKeyBuilder.nodes.containsKey(hierarchyField)) {
                    return null;
                }
                removeNode = true;
                fieldInHierarchy = true;
            }
        }
        if (!fieldInHierarchy) {
            return null;
        }
        newKeyBuilder.languageCode = this.mLanguageCode;
        newKeyBuilder.script = this.mScriptType;
        return newKeyBuilder.build();
    }

    String getValueForUpperLevelField(AddressField field) {
        LookupKey key = getKeyForUpperLevelField(field);
        if (key != null) {
            String keyString = key.toString();
            int lastSlashPosition = keyString.lastIndexOf("/");
            if (lastSlashPosition > 0 && lastSlashPosition != keyString.length()) {
                return keyString.substring(lastSlashPosition + 1);
            }
        }
        return "";
    }

    KeyType getKeyType() {
        return this.mKeyType;
    }

    private String getKeyString() {
        StringBuilder keyBuilder = new StringBuilder(this.mKeyType.name().toLowerCase());
        if (this.mKeyType == KeyType.DATA) {
            for (AddressField field : HIERARCHY) {
                if (!this.mNodes.containsKey(field)) {
                    break;
                }
                if (field != AddressField.COUNTRY || this.mLanguageCode == null) {
                    keyBuilder.append("/").append((String) this.mNodes.get(field));
                } else {
                    keyBuilder.append("/").append((String) this.mNodes.get(field)).append("--").append(this.mLanguageCode);
                }
            }
        } else if (this.mNodes.containsKey(AddressField.COUNTRY)) {
            keyBuilder.append("/").append((String) this.mNodes.get(AddressField.COUNTRY)).append("/").append(this.mScriptType.name().toLowerCase()).append("/").append("_default");
        }
        return keyBuilder.toString();
    }

    public String toString() {
        return this.mKeyString;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((LookupKey) obj).toString().equals(this.mKeyString);
    }

    public int hashCode() {
        return this.mKeyString.hashCode();
    }

    static boolean hasValidKeyPrefix(String key) {
        for (KeyType type : KeyType.values()) {
            if (key.startsWith(type.name().toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
