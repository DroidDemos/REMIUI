package com.android.i18n.addressinput;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class FormOptions {
    private final String mBaseId;
    private final EnumMap<AddressField, String> mCustomLabels;
    private final EnumSet<AddressField> mHiddenFields;
    private final EnumMap<AddressField, Integer> mMaxLengths;
    private final Map<String, AddressField[]> mOverrideFieldOrder;
    private final EnumSet<AddressField> mReadonlyFields;
    private final EnumSet<AddressField> mRequiredFields;
    private final String mServerUrl;

    public static class Builder {
        private String mBaseId;
        private final EnumMap<AddressField, String> mCustomLabels;
        private final EnumSet<AddressField> mHiddenFields;
        private final EnumMap<AddressField, Integer> mMaxLengths;
        private final Map<String, AddressField[]> mOverrideFieldOrder;
        private final EnumSet<AddressField> mReadonlyFields;
        private final EnumSet<AddressField> mRequiredFields;
        private String mServerUrl;

        public Builder() {
            this.mBaseId = "addressform";
            this.mRequiredFields = EnumSet.noneOf(AddressField.class);
            this.mHiddenFields = EnumSet.noneOf(AddressField.class);
            this.mReadonlyFields = EnumSet.noneOf(AddressField.class);
            this.mCustomLabels = new EnumMap(AddressField.class);
            this.mOverrideFieldOrder = new HashMap();
            this.mMaxLengths = new EnumMap(AddressField.class);
            this.mServerUrl = new CacheData().getUrl();
        }

        public Builder hide(AddressField field) {
            if (field == null) {
                throw new RuntimeException("AddressField field cannot be null.");
            }
            this.mHiddenFields.add(field);
            this.mRequiredFields.remove(field);
            return this;
        }

        public FormOptions build() {
            return new FormOptions();
        }
    }

    private FormOptions(Builder builder) {
        this.mCustomLabels = new EnumMap(AddressField.class);
        this.mOverrideFieldOrder = new HashMap();
        this.mMaxLengths = new EnumMap(AddressField.class);
        this.mBaseId = builder.mBaseId;
        this.mHiddenFields = EnumSet.copyOf(builder.mHiddenFields);
        this.mReadonlyFields = EnumSet.copyOf(builder.mReadonlyFields);
        this.mRequiredFields = EnumSet.copyOf(builder.mRequiredFields);
        this.mCustomLabels.putAll(builder.mCustomLabels);
        this.mOverrideFieldOrder.putAll(builder.mOverrideFieldOrder);
        this.mMaxLengths.putAll(builder.mMaxLengths);
        this.mServerUrl = builder.mServerUrl;
    }

    boolean isHidden(AddressField field) {
        return this.mHiddenFields.contains(field);
    }

    boolean isReadonly(AddressField field) {
        return this.mReadonlyFields.contains(field);
    }

    EnumSet<AddressField> getHiddenFields() {
        return this.mHiddenFields;
    }

    AddressField[] getCustomFieldOrder(String regionCode) {
        if (regionCode != null) {
            return (AddressField[]) this.mOverrideFieldOrder.get(regionCode);
        }
        throw new RuntimeException("regionCode cannot be null.");
    }
}
