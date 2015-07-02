package com.google.android.finsky.billing;

import android.content.Context;
import android.graphics.Rect;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import com.android.i18n.addressinput.AddressData;
import com.android.i18n.addressinput.AddressField;
import com.android.i18n.addressinput.FormOptions;
import com.android.i18n.addressinput.FormOptions.Builder;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.protos.ChallengeProto.InputValidationError;
import com.google.android.finsky.protos.CommonDevice.CarrierBillingInstrumentStatus;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Sha1Util;
import com.google.android.wallet.instrumentmanager.R;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class BillingUtils {
    private static final Pattern PATTERN_EM_TAG;
    private static final Pattern PATTERN_END_EM_TAG;

    public enum AddressMode {
        FULL_ADDRESS,
        REDUCED_ADDRESS
    }

    static {
        PATTERN_EM_TAG = Pattern.compile("<em>");
        PATTERN_END_EM_TAG = Pattern.compile("</em>");
    }

    public static Spanned parseHtmlAndColorizeEm(String html, int highlightColor) {
        return Html.fromHtml(parseHtmlAndColorizeEmAsString(html, highlightColor));
    }

    static String parseHtmlAndColorizeEmAsString(String html, int highlightColor) {
        return PATTERN_END_EM_TAG.matcher(PATTERN_EM_TAG.matcher(html).replaceAll("<b><font color=\"" + String.format("#%06X", new Object[]{Integer.valueOf(16777215 & highlightColor)}) + "\">")).replaceAll("</font></b>");
    }

    public static String getDefaultCountry(Context context, String country) {
        if (TextUtils.isEmpty(country)) {
            country = getSimCountry(context);
        }
        if (TextUtils.isEmpty(country)) {
            return "US";
        }
        return country;
    }

    public static String getSimCountry(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimCountryIso().toUpperCase();
    }

    public static String replaceLocale(String str) {
        if (!str.contains("%locale%")) {
            return str;
        }
        Locale locale = Locale.getDefault();
        return str.replace("%locale%", locale.getLanguage() + "_" + locale.getCountry().toLowerCase());
    }

    public static String replaceLanguageAndRegion(String str) {
        if (!str.contains("%lang%") && !str.contains("%region%")) {
            return str;
        }
        Locale locale = Locale.getDefault();
        return str.replace("%lang%", locale.getLanguage().toLowerCase()).replace("%region%", locale.getCountry().toLowerCase());
    }

    public static int getViewOffsetToChild(ViewGroup parent, View child) {
        Rect rect = new Rect();
        parent.offsetDescendantRectToMyCoords(child, rect);
        return rect.top;
    }

    public static <T extends View> T getTopMostView(ViewGroup parent, Collection<T> views) {
        Pair<Integer, T> topMost = null;
        for (T view : views) {
            int yOffset = getViewOffsetToChild(parent, view);
            if (topMost == null || yOffset < ((Integer) topMost.first).intValue()) {
                topMost = Pair.create(Integer.valueOf(yOffset), view);
            }
        }
        if (topMost != null) {
            return (View) topMost.second;
        }
        return null;
    }

    public static String getLine1Number(Context ctx) {
        TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService("phone");
        boolean isVoiceCapable = true;
        try {
            isVoiceCapable = ((Boolean) TelephonyManager.class.getMethod("isVoiceCapable", new Class[0]).invoke(telephonyManager, new Object[0])).booleanValue();
        } catch (Throwable th) {
        }
        return isVoiceCapable ? telephonyManager.getLine1Number() : null;
    }

    public static String getRiskHeader() {
        return Sha1Util.secureHash(String.valueOf(DfeApiConfig.androidId.get()));
    }

    public static Country findCountry(String isoCode, List<Country> countries) {
        if (!TextUtils.isEmpty(isoCode)) {
            for (Country country : countries) {
                if (isoCode.equals(country.countryCode)) {
                    return country;
                }
            }
        }
        if (countries.size() > 0) {
            return (Country) countries.get(0);
        }
        return null;
    }

    public static FormOptions getAddressFormOptions(AddressMode mode) {
        Builder builder = new Builder().hide(AddressField.COUNTRY).hide(AddressField.RECIPIENT).hide(AddressField.ORGANIZATION).hide(AddressField.DEPENDENT_LOCALITY).hide(AddressField.SORTING_CODE);
        if (mode == AddressMode.REDUCED_ADDRESS) {
            builder.hide(AddressField.ADDRESS_LINE_1).hide(AddressField.ADDRESS_LINE_2);
            builder.hide(AddressField.LOCALITY);
            builder.hide(AddressField.ADMIN_AREA).hide(AddressField.STREET_ADDRESS);
        }
        return builder.build();
    }

    public static AddressData addressDataFromInstrumentAddress(Address address) {
        AddressData.Builder builder = new AddressData.Builder();
        if (address.postalCountry != null) {
            builder.setCountry(address.postalCountry);
        }
        if (address.addressLine1 != null) {
            builder.setAddressLine1(address.addressLine1);
        }
        if (address.addressLine2 != null) {
            builder.setAddressLine2(address.addressLine2);
        }
        if (address.state != null) {
            builder.setAdminArea(address.state);
        }
        if (address.city != null) {
            builder.setLocality(address.city);
        }
        if (address.dependentLocality != null) {
            builder.setDependentLocality(address.dependentLocality);
        }
        if (address.postalCode != null) {
            builder.setPostalCode(address.postalCode);
        }
        if (address.sortingCode != null) {
            builder.setSortingCode(address.sortingCode);
        }
        if (address.name != null) {
            builder.setRecipient(address.name);
        }
        if (address.languageCode != null) {
            builder.setLanguageCode(address.languageCode);
        }
        return builder.build();
    }

    public static Address instrumentAddressFromAddressData(AddressData addressData, int[] requiredFields) {
        Address result = new Address();
        for (int i : requiredFields) {
            switch (i) {
                case R.styleable.WalletImFormEditText_required /*4*/:
                    if (addressData.getRecipient() == null) {
                        break;
                    }
                    result.name = addressData.getRecipient();
                    result.hasName = true;
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    if (addressData.getAddressLine1() == null) {
                        break;
                    }
                    result.addressLine1 = addressData.getAddressLine1();
                    result.hasAddressLine1 = true;
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    if (addressData.getAddressLine2() == null) {
                        break;
                    }
                    result.addressLine2 = addressData.getAddressLine2();
                    result.hasAddressLine2 = true;
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    if (addressData.getLocality() == null) {
                        break;
                    }
                    result.city = addressData.getLocality();
                    result.hasCity = true;
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    if (addressData.getAdministrativeArea() == null) {
                        break;
                    }
                    result.state = addressData.getAdministrativeArea();
                    result.hasState = true;
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    if (addressData.getPostalCode() == null) {
                        break;
                    }
                    result.postalCode = addressData.getPostalCode();
                    result.hasPostalCode = true;
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    if (addressData.getPostalCountry() == null) {
                        break;
                    }
                    result.postalCountry = addressData.getPostalCountry();
                    result.hasPostalCountry = true;
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    if (addressData.getDependentLocality() == null) {
                        break;
                    }
                    result.dependentLocality = addressData.getDependentLocality();
                    result.hasDependentLocality = true;
                    break;
                default:
                    break;
            }
        }
        if (addressData.getSortingCode() != null) {
            result.sortingCode = addressData.getSortingCode();
            result.hasSortingCode = true;
        }
        if (addressData.getLanguageCode() != null) {
            result.languageCode = addressData.getLanguageCode();
            result.hasLanguageCode = true;
        }
        return result;
    }

    public static int getFopVersion(Instrument instrument) {
        if (instrument.instrumentFamily != 2) {
            return 0;
        }
        if (instrument.carrierBillingStatus != null) {
            CarrierBillingInstrumentStatus status = instrument.carrierBillingStatus;
            if (status.hasApiVersion) {
                return status.apiVersion;
            }
        }
        if (!FinskyLog.DEBUG) {
            return 2;
        }
        FinskyLog.v("No api version in CarrierBillingInstrumentStatus. Return DCB_VERSION_2", new Object[0]);
        return 2;
    }

    public static InputValidationError createInputValidationError(int inputField) {
        return createInputValidationError(inputField, null);
    }

    public static InputValidationError createInputValidationError(int inputField, String errorMessage) {
        InputValidationError error = new InputValidationError();
        error.inputField = inputField;
        error.hasInputField = true;
        if (!TextUtils.isEmpty(errorMessage)) {
            error.errorMessage = errorMessage;
            error.hasErrorMessage = true;
        }
        return error;
    }
}
