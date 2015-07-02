package com.android.i18n.addressinput;

import com.google.android.wallet.instrumentmanager.R;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AddressData implements Serializable {
    private final String mAddressLine1;
    private final String mAddressLine2;
    private final String mAdministrativeArea;
    private final String mDependentLocality;
    private final String mLanguageCode;
    private final String mLocality;
    private final String mOrganization;
    private final String mPostalCode;
    private final String mPostalCountry;
    private final String mRecipient;
    private final String mSortingCode;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$i18n$addressinput$AddressField;

        static {
            $SwitchMap$com$android$i18n$addressinput$AddressField = new int[AddressField.values().length];
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.COUNTRY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.ADMIN_AREA.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.LOCALITY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.DEPENDENT_LOCALITY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.POSTAL_CODE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.SORTING_CODE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.ADDRESS_LINE_1.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.ADDRESS_LINE_2.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.ORGANIZATION.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.RECIPIENT.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    public static class Builder {
        private String mLanguage;
        private final Map<AddressField, String> mValues;

        public Builder() {
            this.mLanguage = null;
            this.mValues = new HashMap();
        }

        public Builder(AddressData addr) {
            this.mLanguage = null;
            this.mValues = new HashMap();
            set(addr);
        }

        public Builder setCountry(String value) {
            return set(AddressField.COUNTRY, value);
        }

        public Builder setAdminArea(String value) {
            return set(AddressField.ADMIN_AREA, value);
        }

        public Builder setLocality(String value) {
            return set(AddressField.LOCALITY, value);
        }

        public Builder setDependentLocality(String value) {
            return set(AddressField.DEPENDENT_LOCALITY, value);
        }

        public Builder setPostalCode(String value) {
            return set(AddressField.POSTAL_CODE, value);
        }

        public Builder setSortingCode(String value) {
            return set(AddressField.SORTING_CODE, value);
        }

        public Builder setLanguageCode(String languageCode) {
            this.mLanguage = languageCode;
            return this;
        }

        public Builder set(AddressData data) {
            this.mValues.clear();
            for (AddressField addressField : AddressField.values()) {
                if (addressField != AddressField.STREET_ADDRESS) {
                    set(addressField, data.getFieldValue(addressField));
                }
            }
            normalizeAddresses();
            setLanguageCode(data.getLanguageCode());
            return this;
        }

        public Builder setAddressLine1(String value) {
            return set(AddressField.ADDRESS_LINE_1, value);
        }

        public Builder setAddressLine2(String value) {
            return set(AddressField.ADDRESS_LINE_2, value);
        }

        public Builder setOrganization(String value) {
            return set(AddressField.ORGANIZATION, value);
        }

        public Builder setRecipient(String value) {
            return set(AddressField.RECIPIENT, value);
        }

        public Builder set(AddressField field, String value) {
            if (value == null || value.length() == 0) {
                this.mValues.remove(field);
            } else {
                this.mValues.put(field, value.trim());
            }
            normalizeAddresses();
            return this;
        }

        public AddressData build() {
            return new AddressData();
        }

        private void normalizeAddresses() {
            String address1 = (String) this.mValues.get(AddressField.ADDRESS_LINE_1);
            String address2 = (String) this.mValues.get(AddressField.ADDRESS_LINE_2);
            if (address1 == null || address1.trim().length() == 0) {
                address1 = address2;
                address2 = null;
            }
            if (address1 != null) {
                String[] addressLines = address1.split("\n");
                if (addressLines.length > 1) {
                    address1 = addressLines[0];
                    address2 = addressLines[1];
                }
            }
            this.mValues.put(AddressField.ADDRESS_LINE_1, address1);
            this.mValues.put(AddressField.ADDRESS_LINE_2, address2);
        }
    }

    private AddressData(Builder builder) {
        this.mPostalCountry = (String) builder.mValues.get(AddressField.COUNTRY);
        this.mAdministrativeArea = (String) builder.mValues.get(AddressField.ADMIN_AREA);
        this.mLocality = (String) builder.mValues.get(AddressField.LOCALITY);
        this.mDependentLocality = (String) builder.mValues.get(AddressField.DEPENDENT_LOCALITY);
        this.mPostalCode = (String) builder.mValues.get(AddressField.POSTAL_CODE);
        this.mSortingCode = (String) builder.mValues.get(AddressField.SORTING_CODE);
        this.mOrganization = (String) builder.mValues.get(AddressField.ORGANIZATION);
        this.mRecipient = (String) builder.mValues.get(AddressField.RECIPIENT);
        this.mAddressLine1 = (String) builder.mValues.get(AddressField.ADDRESS_LINE_1);
        this.mAddressLine2 = (String) builder.mValues.get(AddressField.ADDRESS_LINE_2);
        this.mLanguageCode = builder.mLanguage;
    }

    public String getPostalCountry() {
        return this.mPostalCountry;
    }

    public String getAddressLine1() {
        return this.mAddressLine1;
    }

    public String getAddressLine2() {
        return this.mAddressLine2;
    }

    public String getAdministrativeArea() {
        return this.mAdministrativeArea;
    }

    public String getLocality() {
        return this.mLocality;
    }

    public String getDependentLocality() {
        return this.mDependentLocality;
    }

    public String getOrganization() {
        return this.mOrganization;
    }

    public String getRecipient() {
        return this.mRecipient;
    }

    public String getPostalCode() {
        return this.mPostalCode;
    }

    public String getSortingCode() {
        return this.mSortingCode;
    }

    public String getFieldValue(AddressField field) {
        switch (AnonymousClass1.$SwitchMap$com$android$i18n$addressinput$AddressField[field.ordinal()]) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return this.mPostalCountry;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return this.mAdministrativeArea;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return this.mLocality;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return this.mDependentLocality;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return this.mPostalCode;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return this.mSortingCode;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return this.mAddressLine1;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                return this.mAddressLine2;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                return this.mOrganization;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                return this.mRecipient;
            default:
                throw new IllegalArgumentException("unrecognized key: " + field);
        }
    }

    public String getLanguageCode() {
        return this.mLanguageCode;
    }
}
