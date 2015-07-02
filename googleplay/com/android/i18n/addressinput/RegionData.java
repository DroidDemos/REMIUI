package com.android.i18n.addressinput;

class RegionData {
    private String mKey;
    private String mName;

    static class Builder {
        RegionData mData;

        Builder() {
            this.mData = new RegionData();
        }

        RegionData build() {
            return new RegionData();
        }

        Builder setKey(String key) {
            Util.checkNotNull(key, "Key should not be null.");
            this.mData.mKey = key;
            return this;
        }

        Builder setName(String name) {
            this.mData.mName = Util.trimToNull(name);
            return this;
        }
    }

    private RegionData() {
    }

    private RegionData(RegionData data) {
        Util.checkNotNull(data);
        this.mKey = data.mKey;
        this.mName = data.mName;
    }

    String getKey() {
        return this.mKey;
    }

    public String getDisplayName() {
        return this.mName != null ? this.mName : this.mKey;
    }

    boolean isValidName(String subkey) {
        if (subkey == null) {
            return false;
        }
        if (subkey.equalsIgnoreCase(this.mKey) || subkey.equalsIgnoreCase(this.mName)) {
            return true;
        }
        return false;
    }
}
