package com.google.android.finsky.billing.carrierbilling;

import android.text.TextUtils;
import com.android.i18n.addressinput.AddressData;
import com.android.i18n.addressinput.AddressData.Builder;
import com.android.i18n.addressinput.AddressField;
import com.android.i18n.addressinput.AddressProblemType;
import com.android.i18n.addressinput.AddressProblems;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingUtils.AddressMode;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.utils.Utils;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map.Entry;

public class PhoneCarrierBillingUtils {
    private static final EnumMap<AddressField, AddressInputField> addressMap;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$i18n$addressinput$AddressField;

        static {
            $SwitchMap$com$android$i18n$addressinput$AddressField = new int[AddressField.values().length];
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.ADDRESS_LINE_1.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.ADDRESS_LINE_2.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.STREET_ADDRESS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.DEPENDENT_LOCALITY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.LOCALITY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.ADMIN_AREA.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.POSTAL_CODE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public enum AddressInputField {
        PERSON_NAME,
        ADDR_COUNTRY_CODE,
        ADDR_POSTAL_CODE,
        ADDR_ADDRESS1,
        ADDR_ADDRESS2,
        ADDR_CITY,
        ADDR_STATE,
        ADDR_PHONE
    }

    static {
        addressMap = new EnumMap(AddressField.class);
        addressMap.put(AddressField.STREET_ADDRESS, AddressInputField.ADDR_ADDRESS1);
        addressMap.put(AddressField.ADDRESS_LINE_2, AddressInputField.ADDR_ADDRESS2);
        addressMap.put(AddressField.LOCALITY, AddressInputField.ADDR_CITY);
        addressMap.put(AddressField.ADMIN_AREA, AddressInputField.ADDR_STATE);
        addressMap.put(AddressField.POSTAL_CODE, AddressInputField.ADDR_POSTAL_CODE);
        addressMap.put(AddressField.COUNTRY, AddressInputField.ADDR_COUNTRY_CODE);
    }

    private static AddressInputField convertAddressFieldToInputField(AddressField address) {
        return (AddressInputField) addressMap.get(address);
    }

    public static Collection<AddressInputField> getErrors(String name, String phoneNumber, AddressProblems addressProblems, AddressMode mode) {
        Collection<AddressInputField> errors = new ArrayList();
        if (Utils.isEmptyOrSpaces(name)) {
            errors.add(AddressInputField.PERSON_NAME);
        }
        if (isPhoneNumberRequired(mode, BillingLocator.getCarrierBillingStorage()) && Utils.isEmptyOrSpaces(phoneNumber)) {
            errors.add(AddressInputField.ADDR_PHONE);
        }
        for (Entry<AddressField, AddressProblemType> addressError : addressProblems.getProblems().entrySet()) {
            if (AddressMode.REDUCED_ADDRESS.equals(mode)) {
                switch (AnonymousClass1.$SwitchMap$com$android$i18n$addressinput$AddressField[((AddressField) addressError.getKey()).ordinal()]) {
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    case R.styleable.WalletImFormEditText_required /*4*/:
                    case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                        break;
                }
            }
            AddressInputField errorField = convertAddressFieldToInputField((AddressField) addressError.getKey());
            if (errorField != null) {
                errors.add(errorField);
            }
        }
        return errors;
    }

    public static boolean isPhoneNumberRequired(AddressMode mode, CarrierBillingStorage storage) {
        if (!CarrierBillingUtils.isDcb30(storage) && mode == AddressMode.REDUCED_ADDRESS) {
            return ((Boolean) G.reducedBillingAddressRequiresPhonenumber.get()).booleanValue();
        }
        return true;
    }

    public static AddressData subscriberInfoToAddressData(SubscriberInfo info) {
        return new Builder().setAddressLine1(info.getAddress1()).setAddressLine2(info.getAddress2()).setLocality(info.getCity()).setAdminArea(info.getState()).setPostalCode(info.getPostalCode()).setCountry(info.getCountry()).build();
    }

    public static Address subscriberInfoToAddress(SubscriberInfo info, AddressMode mode) {
        Address address = new Address();
        if (!TextUtils.isEmpty(info.getName())) {
            address.name = info.getName();
            address.hasName = true;
        }
        if (!TextUtils.isEmpty(info.getIdentifier())) {
            address.phoneNumber = info.getIdentifier();
            address.hasPhoneNumber = true;
        }
        if (AddressMode.FULL_ADDRESS == mode) {
            if (!TextUtils.isEmpty(info.getAddress1())) {
                address.addressLine1 = info.getAddress1();
                address.hasAddressLine1 = true;
            }
            if (!TextUtils.isEmpty(info.getAddress2())) {
                address.addressLine2 = info.getAddress2();
                address.hasAddressLine2 = true;
            }
            if (!TextUtils.isEmpty(info.getCity())) {
                address.city = info.getCity();
                address.hasCity = true;
            }
            if (!TextUtils.isEmpty(info.getState())) {
                address.state = info.getState();
                address.hasState = true;
            }
            address.deprecatedIsReduced = false;
        } else {
            address.deprecatedIsReduced = true;
        }
        address.hasDeprecatedIsReduced = true;
        if (!TextUtils.isEmpty(info.getPostalCode())) {
            address.postalCode = info.getPostalCode();
            address.hasPostalCode = true;
        }
        if (!TextUtils.isEmpty(info.getCountry())) {
            address.postalCountry = info.getCountry();
            address.hasPostalCountry = true;
        }
        return address;
    }

    public static SubscriberInfo getSubscriberInfo(Address address) {
        if (address == null) {
            return null;
        }
        SubscriberInfo.Builder builder = new SubscriberInfo.Builder();
        builder.setName(address.name);
        builder.setAddress1(address.addressLine1);
        builder.setAddress2(address.addressLine2);
        builder.setCity(address.city);
        builder.setState(address.state);
        builder.setPostalCode(address.postalCode);
        builder.setIdentifier(address.phoneNumber);
        return builder.build();
    }
}
