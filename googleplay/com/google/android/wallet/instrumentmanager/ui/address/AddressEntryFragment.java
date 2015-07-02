package com.google.android.wallet.instrumentmanager.ui.address;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue.RequestFilter;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.analytics.UiNode;
import com.google.android.wallet.instrumentmanager.api.InstrumentManagerRequestQueue;
import com.google.android.wallet.instrumentmanager.common.address.AddressFormatter;
import com.google.android.wallet.instrumentmanager.common.address.AddressSource;
import com.google.android.wallet.instrumentmanager.common.address.AddressSourceResult;
import com.google.android.wallet.instrumentmanager.common.address.AddressUtils;
import com.google.android.wallet.instrumentmanager.common.address.CountryMetadataRetrievalTask;
import com.google.android.wallet.instrumentmanager.common.address.CountryMetadataRetrievalTask.AdminAreaMetadataRetrievalRequest;
import com.google.android.wallet.instrumentmanager.common.address.CountryMetadataRetrievalTask.CountryMetadataRetrievalRequest;
import com.google.android.wallet.instrumentmanager.common.address.DeviceAddressSource;
import com.google.android.wallet.instrumentmanager.common.address.GooglePlacesAddressSource;
import com.google.android.wallet.instrumentmanager.common.address.LocalAddressSource;
import com.google.android.wallet.instrumentmanager.common.address.RegionCode;
import com.google.android.wallet.instrumentmanager.common.util.ArrayUtils;
import com.google.android.wallet.instrumentmanager.common.util.ErrorUtils;
import com.google.android.wallet.instrumentmanager.common.util.Objects;
import com.google.android.wallet.instrumentmanager.common.util.ParcelableProto;
import com.google.android.wallet.instrumentmanager.common.util.PaymentUtils;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;
import com.google.android.wallet.instrumentmanager.ui.address.DynamicAddressFieldsLayout.OnHeightOffsetChangedListener;
import com.google.android.wallet.instrumentmanager.ui.common.FormEditText;
import com.google.android.wallet.instrumentmanager.ui.common.FormEventListener;
import com.google.android.wallet.instrumentmanager.ui.common.FormFragment;
import com.google.android.wallet.instrumentmanager.ui.common.FormSpinner;
import com.google.android.wallet.instrumentmanager.ui.common.RegionCodeSelector.OnRegionCodeSelectedListener;
import com.google.android.wallet.instrumentmanager.ui.common.RegionCodeView;
import com.google.android.wallet.instrumentmanager.ui.common.Validatable;
import com.google.android.wallet.instrumentmanager.ui.common.ValidatableComponent;
import com.google.android.wallet.instrumentmanager.ui.common.WalletUiUtils;
import com.google.android.wallet.instrumentmanager.ui.common.validator.AbstractValidator;
import com.google.android.wallet.instrumentmanager.ui.common.validator.AndValidator;
import com.google.android.wallet.instrumentmanager.ui.common.validator.ComposedValidator;
import com.google.android.wallet.instrumentmanager.ui.common.validator.PatternValidator;
import com.google.commerce.payments.orchestration.proto.ui.common.UiErrorOuterClass.FormFieldMessage;
import com.google.commerce.payments.orchestration.proto.ui.common.components.AddressFormOuterClass.AddressForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.AddressFormOuterClass.AddressFormValue;
import com.google.location.country.Postaladdress.PostalAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class AddressEntryFragment extends FormFragment<AddressForm> implements OnCheckedChangeListener, OnHeightOffsetChangedListener {
    private static final SparseIntArray ADDRESS_FORM_FIELD_ID_TO_VIEW_ID;
    private static final SparseBooleanArray ALL_DYNAMIC_ADDRESS_FIELDS;
    private static final Comparator<Object> CASE_INSENSITIVE_COMPARATOR;
    private static final SparseIntArray I18N_FIELD_ID_TO_ADDRESS_FORM_FIELD_ID;
    private static final SparseBooleanArray POSTAL_CODE_ONLY_DYNAMIC_ADDRESS_FIELDS;
    ArrayList<View> mAddressFields;
    private ArrayList<PostalAddress> mAddressHints;
    ArrayList<AddressSource> mAddressSources;
    private JSONObject mAdminAreaData;
    private AdminAreaMetadataRetrievalRequest mAdminAreaMetadataRetrievalRequest;
    private LinearLayout mContainer;
    private JSONObject mCountryData;
    DynamicAddressFieldsLayout mDynamicAddressFieldsLayout;
    CheckBox mHideAddressCheckbox;
    String mLanguageCode;
    private OnAddressFieldsShownListener mOnAddressFieldsShownListener;
    private OnHeightOffsetChangedListener mOnHeightOffsetChangedListener;
    private OnRegionCodeSelectedListener mOnRegionCodeSelectedListener;
    private PostalAddress mPendingAddress;
    private int mPendingRequestCounter;
    TextView mPhoneNumberText;
    private ComposedValidator mPostalCodeValidator;
    TextView mRecipientNameText;
    RegionCodeView mRegionCodeView;
    int[] mRegionCodes;
    int mSelectedCountry;
    private final InstrumentManagerUiElement mUiElement;

    static class AdminAdapter extends ArrayAdapter<AdminArea> {
        private View mHiddenView;
        private TextView mPromptView;

        public AdminAdapter(ContextThemeWrapper context, int textViewResourceId, List<AdminArea> adminAreas, String hint) {
            super(context, textViewResourceId, adminAreas);
            insert(new AdminArea(null, false, null, hint, null), 0);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (isEnabled(position)) {
                if (convertView == this.mPromptView) {
                    convertView = null;
                }
                return super.getView(position, convertView, parent);
            }
            if (this.mPromptView == null) {
                this.mPromptView = (TextView) super.getView(0, null, parent);
                this.mPromptView.setHint(this.mPromptView.getText());
                this.mPromptView.setText(null);
            }
            return this.mPromptView;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            if (isEnabled(position)) {
                if (convertView == this.mHiddenView) {
                    convertView = null;
                }
                return super.getDropDownView(position, convertView, parent);
            }
            if (this.mHiddenView == null) {
                this.mHiddenView = new View(getContext());
                this.mHiddenView.setVisibility(8);
                this.mHiddenView.setLayoutParams(new LayoutParams(0, 0));
            }
            return this.mHiddenView;
        }

        public boolean areAllItemsEnabled() {
            return false;
        }

        public boolean isEnabled(int position) {
            return position != 0;
        }
    }

    public interface SelectableItem {
        String getValue();
    }

    static class AdminArea implements SelectableItem {
        JSONObject mData;
        final boolean mHasLocalities;
        final String mI18nKey;
        final String mLabel;
        final String mValue;
        final String mZipStartsWithRegEx;

        AdminArea(String i18nKey, boolean hasLocalities, String value, String label, String zipStartsWithRegEx) {
            this.mI18nKey = i18nKey;
            this.mHasLocalities = hasLocalities;
            this.mValue = value;
            this.mLabel = label;
            this.mZipStartsWithRegEx = zipStartsWithRegEx;
        }

        public String getValue() {
            return this.mValue;
        }

        public String toString() {
            return this.mLabel;
        }
    }

    class FetchAddressTask extends AsyncTask<AddressSourceResult, Void, PostalAddress> {
        private final ArrayList<AddressSource> mCopyOfAddressSources;
        private final String mCurrentLanguageCode;
        private final View mInput;

        public FetchAddressTask(View input) {
            this.mInput = input;
            this.mCurrentLanguageCode = AddressEntryFragment.this.getAddressLanguageCode();
            this.mCopyOfAddressSources = AddressEntryFragment.this.mAddressSources != null ? new ArrayList(AddressEntryFragment.this.mAddressSources) : new ArrayList();
        }

        protected PostalAddress doInBackground(AddressSourceResult... results) {
            AddressSourceResult result = results[0];
            if (result == null || result.sourceName == null) {
                return null;
            }
            int length = this.mCopyOfAddressSources.size();
            for (int i = 0; i < length; i++) {
                AddressSource source = (AddressSource) this.mCopyOfAddressSources.get(i);
                if (result.sourceName.equals(source.getName())) {
                    return source.getAddress(result.reference, this.mCurrentLanguageCode);
                }
            }
            return null;
        }

        protected void onPostExecute(PostalAddress result) {
            if (AddressEntryFragment.this.getActivity() != null) {
                super.onPostExecute(result);
                if (result != null) {
                    AddressEntryFragment.this.fillAddressFieldsAndUpdateFocus(this.mInput, result);
                }
            }
        }
    }

    static class Locality implements SelectableItem {
        final String mLabel;
        final String mValue;

        Locality(String value, String label) {
            this.mValue = value;
            this.mLabel = label;
        }

        public String getValue() {
            return this.mValue;
        }

        public String toString() {
            return this.mLabel;
        }
    }

    public interface OnAddressFieldsShownListener {
        void onAddressFieldsShown(boolean z);
    }

    public AddressEntryFragment() {
        this.mAddressFields = new ArrayList();
        this.mUiElement = new InstrumentManagerUiElement(1667);
        this.mLanguageCode = Locale.getDefault().toString();
    }

    static {
        ALL_DYNAMIC_ADDRESS_FIELDS = new SparseBooleanArray(7);
        ALL_DYNAMIC_ADDRESS_FIELDS.put(83, true);
        ALL_DYNAMIC_ADDRESS_FIELDS.put(82, true);
        ALL_DYNAMIC_ADDRESS_FIELDS.put(67, true);
        ALL_DYNAMIC_ADDRESS_FIELDS.put(49, true);
        ALL_DYNAMIC_ADDRESS_FIELDS.put(50, true);
        ALL_DYNAMIC_ADDRESS_FIELDS.put(90, true);
        ALL_DYNAMIC_ADDRESS_FIELDS.put(88, true);
        POSTAL_CODE_ONLY_DYNAMIC_ADDRESS_FIELDS = new SparseBooleanArray(2);
        POSTAL_CODE_ONLY_DYNAMIC_ADDRESS_FIELDS.put(82, true);
        POSTAL_CODE_ONLY_DYNAMIC_ADDRESS_FIELDS.put(90, true);
        ADDRESS_FORM_FIELD_ID_TO_VIEW_ID = new SparseIntArray(11);
        ADDRESS_FORM_FIELD_ID_TO_VIEW_ID.put(1, R.id.address_field_country);
        ADDRESS_FORM_FIELD_ID_TO_VIEW_ID.put(2, R.id.address_field_recipient);
        ADDRESS_FORM_FIELD_ID_TO_VIEW_ID.put(3, R.id.address_field_address_line_1);
        ADDRESS_FORM_FIELD_ID_TO_VIEW_ID.put(4, R.id.address_field_address_line_2);
        ADDRESS_FORM_FIELD_ID_TO_VIEW_ID.put(5, R.id.address_field_locality);
        ADDRESS_FORM_FIELD_ID_TO_VIEW_ID.put(6, R.id.address_field_admin_area);
        ADDRESS_FORM_FIELD_ID_TO_VIEW_ID.put(7, R.id.address_field_postal_code);
        ADDRESS_FORM_FIELD_ID_TO_VIEW_ID.put(9, R.id.address_field_sorting_code);
        ADDRESS_FORM_FIELD_ID_TO_VIEW_ID.put(10, R.id.address_field_dependent_locality);
        ADDRESS_FORM_FIELD_ID_TO_VIEW_ID.put(11, R.id.address_field_organization);
        ADDRESS_FORM_FIELD_ID_TO_VIEW_ID.put(8, R.id.address_field_phone_number);
        I18N_FIELD_ID_TO_ADDRESS_FORM_FIELD_ID = new SparseIntArray(10);
        I18N_FIELD_ID_TO_ADDRESS_FORM_FIELD_ID.put(82, 1);
        I18N_FIELD_ID_TO_ADDRESS_FORM_FIELD_ID.put(78, 2);
        I18N_FIELD_ID_TO_ADDRESS_FORM_FIELD_ID.put(49, 3);
        I18N_FIELD_ID_TO_ADDRESS_FORM_FIELD_ID.put(50, 4);
        I18N_FIELD_ID_TO_ADDRESS_FORM_FIELD_ID.put(67, 5);
        I18N_FIELD_ID_TO_ADDRESS_FORM_FIELD_ID.put(83, 6);
        I18N_FIELD_ID_TO_ADDRESS_FORM_FIELD_ID.put(90, 7);
        I18N_FIELD_ID_TO_ADDRESS_FORM_FIELD_ID.put(88, 9);
        I18N_FIELD_ID_TO_ADDRESS_FORM_FIELD_ID.put(68, 10);
        I18N_FIELD_ID_TO_ADDRESS_FORM_FIELD_ID.put(79, 11);
        CASE_INSENSITIVE_COMPARATOR = new Comparator<Object>() {
            public int compare(Object lhs, Object rhs) {
                return String.CASE_INSENSITIVE_ORDER.compare(lhs.toString(), rhs.toString());
            }
        };
    }

    public static AddressEntryFragment newInstance(AddressForm addressForm, int themeResourceId) {
        AddressEntryFragment fragment = new AddressEntryFragment();
        fragment.setArguments(FormFragment.createArgsForFormFragment(themeResourceId, addressForm, AddressEntryFragment.class));
        return fragment;
    }

    static String getInputValue(View input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        } else if (input instanceof TextView) {
            return ((TextView) input).getText().toString();
        } else {
            if (input instanceof Spinner) {
                Object selectedItem = ((Spinner) input).getSelectedItem();
                if (selectedItem instanceof SelectableItem) {
                    return ((SelectableItem) selectedItem).getValue();
                }
                if (selectedItem != null) {
                    return String.valueOf(selectedItem);
                }
                return null;
            }
            throw new IllegalArgumentException("Unknown input type: " + input.getClass());
        }
    }

    static void setInputValue(View input, String value) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        } else if (input instanceof FormEditText) {
            ((FormEditText) input).replaceTextAndPreventFilter(value);
        } else if (input instanceof TextView) {
            ((TextView) input).setText(value);
        } else if (input instanceof Spinner) {
            Spinner spinner = (Spinner) input;
            if (value == null) {
                spinner.setSelection(0);
            } else if (spinner.getAdapter() instanceof ArrayAdapter) {
                ArrayAdapter<?> adapter = (ArrayAdapter) spinner.getAdapter();
                if (!adapter.isEmpty()) {
                    boolean found = false;
                    int length = adapter.getCount();
                    for (int i = 0; i < length; i++) {
                        Object item = adapter.getItem(i);
                        if ((item instanceof SelectableItem) && value.equalsIgnoreCase(((SelectableItem) item).getValue())) {
                            found = true;
                        } else if (item != null && value.equalsIgnoreCase(item.toString())) {
                            found = true;
                        }
                        if (found) {
                            spinner.setSelection(i);
                            break;
                        }
                    }
                    if (!found) {
                        spinner.setSelection(0);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Unknown input type: " + input.getClass());
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            try {
                this.mCountryData = new JSONObject(((AddressForm) this.mFormProto).initialCountryI18NDataJson);
                setAddress(((AddressForm) this.mFormProto).initialValue.address);
                this.mRegionCodes = AddressUtils.scrubAndSortRegionCodes(AddressUtils.stringArrayToRegionCodeArray(((AddressForm) this.mFormProto).allowedCountryCode));
                if (this.mRegionCodes.length <= 0) {
                    throw new IllegalArgumentException("Array length of allowedCountryCodes must be > 0");
                } else if (TextUtils.isEmpty(((AddressForm) this.mFormProto).recipientLabel)) {
                    throw new IllegalArgumentException("Recipient field hint must be specified!");
                } else {
                    return;
                }
            } catch (JSONException e) {
                throw new RuntimeException("Could not construct JSONObject from mFormProto.initialCountryI18NDataJson", e);
            }
        }
        this.mRegionCodes = savedInstanceState.getIntArray("regionCodes");
    }

    protected View onCreateThemedView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mContainer = (LinearLayout) inflater.inflate(R.layout.fragment_address_entry, container, false);
        if (!TextUtils.isEmpty(((AddressForm) this.mFormProto).title)) {
            TextView titleText = (TextView) this.mContainer.findViewById(R.id.address_title);
            titleText.setText(((AddressForm) this.mFormProto).title);
            titleText.setVisibility(0);
        }
        this.mHideAddressCheckbox = (CheckBox) this.mContainer.findViewById(R.id.hide_address_checkbox);
        if (!TextUtils.isEmpty(((AddressForm) this.mFormProto).hideFormFieldsToggleLabel)) {
            this.mHideAddressCheckbox.setText(((AddressForm) this.mFormProto).hideFormFieldsToggleLabel);
            this.mHideAddressCheckbox.setVisibility(0);
            this.mHideAddressCheckbox.setOnCheckedChangeListener(this);
        }
        if (ArrayUtils.contains(((AddressForm) this.mFormProto).readOnlyField, 2)) {
            this.mRecipientNameText = (TextView) inflater.inflate(R.layout.view_form_non_editable_text, this.mContainer, false);
        } else {
            this.mRecipientNameText = (TextView) inflater.inflate(R.layout.view_form_edit_text, this.mContainer, false);
            this.mRecipientNameText.setHint(getFieldLabel('N'));
            this.mRecipientNameText.setInputType(8289);
        }
        if (ArrayUtils.contains(((AddressForm) this.mFormProto).hiddenField, 2)) {
            this.mRecipientNameText.setVisibility(8);
        }
        this.mRecipientNameText.setTag(Character.valueOf('N'));
        this.mRecipientNameText.setId(R.id.address_field_recipient);
        this.mContainer.addView(this.mRecipientNameText, this.mContainer.indexOfChild(this.mHideAddressCheckbox) + 1);
        this.mRegionCodeView = (RegionCodeView) this.mContainer.findViewById(R.id.region_code_view);
        if (ArrayUtils.contains(((AddressForm) this.mFormProto).hiddenField, 1)) {
            this.mRegionCodeView.setVisibility(8);
        }
        this.mDynamicAddressFieldsLayout = (DynamicAddressFieldsLayout) this.mContainer.findViewById(R.id.dynamic_address_fields_layout);
        if (((AddressForm) this.mFormProto).phoneNumberForm) {
            if (ArrayUtils.contains(((AddressForm) this.mFormProto).readOnlyField, 8)) {
                this.mPhoneNumberText = (TextView) inflater.inflate(R.layout.view_form_non_editable_text, this.mContainer, false);
            } else {
                this.mPhoneNumberText = (TextView) inflater.inflate(R.layout.view_form_edit_text, this.mContainer, false);
                this.mPhoneNumberText.setHint(R.string.wallet_im_phone_number);
                this.mPhoneNumberText.setInputType(3);
            }
            this.mPhoneNumberText.setId(R.id.address_field_phone_number);
            if (VERSION.SDK_INT >= 11) {
                this.mPhoneNumberText.setLayerType(2, null);
            }
            if (ArrayUtils.contains(((AddressForm) this.mFormProto).hiddenField, 8)) {
                this.mPhoneNumberText.setVisibility(8);
            }
            this.mContainer.addView(this.mPhoneNumberText, this.mContainer.indexOfChild(this.mDynamicAddressFieldsLayout) + 1);
            if (savedInstanceState == null && TextUtils.isEmpty(this.mPhoneNumberText.getText())) {
                if (TextUtils.isEmpty(((AddressForm) this.mFormProto).initialValue.phoneNumber)) {
                    TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService("phone");
                    if (telephonyManager != null) {
                        setPhoneNumber(telephonyManager.getLine1Number());
                    }
                } else {
                    setPhoneNumber(((AddressForm) this.mFormProto).initialValue.phoneNumber);
                }
            }
        }
        this.mDynamicAddressFieldsLayout.setOnHeightOffsetChangedListener(this);
        return this.mContainer;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            phoneNumber = PhoneNumberUtils.formatNumber(phoneNumber);
        }
        this.mPhoneNumberText.setText(phoneNumber);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("pendingAddress")) {
                setAddress((PostalAddress) ParcelableProto.getProtoFromBundle(savedInstanceState, "pendingAddress"));
            }
            if (this.mSelectedCountry == 0) {
                this.mSelectedCountry = savedInstanceState.getInt("selectedCountry");
            }
            if (savedInstanceState.containsKey("countryData")) {
                try {
                    this.mCountryData = new JSONObject(savedInstanceState.getString("countryData"));
                    int country = AddressUtils.getRegionCodeFromAddressData(this.mCountryData);
                    if (!(country == 0 || country == RegionCode.getUnknown() || country == this.mSelectedCountry)) {
                        int trulySelectedCountry = this.mSelectedCountry;
                        this.mSelectedCountry = country;
                        onReceivedCountryData(this.mCountryData);
                        this.mSelectedCountry = trulySelectedCountry;
                    }
                } catch (JSONException e) {
                    throw new RuntimeException("Could not construct JSONObject from KEY_COUNTRY_DATA json string", e);
                }
            }
            if (savedInstanceState.containsKey("languageCode")) {
                this.mLanguageCode = savedInstanceState.getString("languageCode");
            }
            if (savedInstanceState.containsKey("adminAreaData")) {
                try {
                    this.mAdminAreaData = new JSONObject(savedInstanceState.getString("adminAreaData"));
                } catch (JSONException e2) {
                    throw new RuntimeException("Could not construct JSONObject from KEY_ADMIN_AREA_DATA json string", e2);
                }
            }
        }
        doEnableUi();
        displayRegionCodeViewWithSelectedCountry();
        if (this.mHideAddressCheckbox.getVisibility() == 0) {
            onCheckedChanged(this.mHideAddressCheckbox, this.mHideAddressCheckbox.isChecked());
        }
        if (this.mOnRegionCodeSelectedListener != null && this.mSelectedCountry != 0) {
            this.mOnRegionCodeSelectedListener.onRegionCodeSelected(this.mSelectedCountry, getId());
        }
    }

    private void fireCountryDataRequest(int regionCode, String desiredLanguageCode, String languageCodeForRequest, final ArrayList<PostalAddress> addressesToPotentiallyFillAdminArea) {
        this.mAdminAreaData = null;
        cancelPendingAdminAreaMetadataRetrievalRequest();
        CountryMetadataRetrievalTask task = new CountryMetadataRetrievalTask(getRequestQueue(), regionCode, desiredLanguageCode, new Listener<JSONObject>() {
            public void onResponse(JSONObject countryData) {
                AddressEntryFragment.this.indicatePendingRequest(false);
                int regionCode = AddressUtils.getRegionCodeFromAddressData(countryData);
                String countryNameCode = RegionCode.toCountryCode(regionCode);
                if (regionCode != 0) {
                    Iterator i$ = addressesToPotentiallyFillAdminArea.iterator();
                    while (i$.hasNext()) {
                        PostalAddress address = (PostalAddress) i$.next();
                        if (address != null && countryNameCode.equalsIgnoreCase(address.countryNameCode) && TextUtils.isEmpty(address.administrativeAreaName) && !TextUtils.isEmpty(address.postalCodeNumber)) {
                            String postalCode = address.postalCodeNumber;
                            if (!TextUtils.isEmpty(postalCode)) {
                                String adminArea = AddressUtils.getAdminAreaForPostalCode(countryData, postalCode);
                                if (!TextUtils.isEmpty(adminArea)) {
                                    address.administrativeAreaName = adminArea;
                                }
                            }
                        }
                    }
                }
                String defaultPostalCode = AddressUtils.getDefaultPostalCodeForCountry(countryData);
                if (!(TextUtils.isEmpty(defaultPostalCode) || TextUtils.isEmpty(countryNameCode))) {
                    if (AddressEntryFragment.this.mPendingAddress == null) {
                        AddressEntryFragment.this.mPendingAddress = AddressEntryFragment.getPostalAddressFromFieldValues(AddressEntryFragment.this.getAddressFieldValuesForPrefilling());
                        AddressEntryFragment.this.mPendingAddress.postalCodeNumber = defaultPostalCode;
                        AddressEntryFragment.this.mPendingAddress.countryNameCode = countryNameCode;
                    } else if (TextUtils.isEmpty(AddressEntryFragment.this.mPendingAddress.postalCodeNumber) && (countryNameCode.equalsIgnoreCase(AddressEntryFragment.this.mPendingAddress.countryNameCode) || TextUtils.isEmpty(AddressEntryFragment.this.mPendingAddress.countryNameCode))) {
                        AddressEntryFragment.this.mPendingAddress.postalCodeNumber = defaultPostalCode;
                    }
                }
                AddressEntryFragment.this.onReceivedCountryData(countryData);
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                AddressEntryFragment.this.indicatePendingRequest(false);
                Bundle eventDetails = new Bundle();
                eventDetails.putString("FormEventListener.EXTRA_FORM_ID", ((AddressForm) AddressEntryFragment.this.mFormProto).id);
                AddressEntryFragment.this.notifyFormEvent(5, ErrorUtils.addErrorDetailsToBundle(eventDetails, 1000, AddressEntryFragment.this.getString(R.string.wallet_im_network_error_title), AddressEntryFragment.this.getString(R.string.wallet_im_network_error_message), null, AddressEntryFragment.this.getString(R.string.wallet_im_retry)));
            }
        });
        indicatePendingRequest(true);
        this.mDynamicAddressFieldsLayout.switchToShowingProgressBar();
        task.run(languageCodeForRequest);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedCountry", this.mSelectedCountry);
        outState.putIntArray("regionCodes", this.mRegionCodes);
        if (this.mPendingAddress != null) {
            outState.putParcelable("pendingAddress", ParcelableProto.forProto(this.mPendingAddress));
        }
        if (this.mCountryData != null) {
            outState.putString("countryData", this.mCountryData.toString());
        }
        outState.putString("languageCode", this.mLanguageCode);
        if (this.mAdminAreaData != null) {
            outState.putString("adminAreaData", this.mAdminAreaData.toString());
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        resetPendingRequestStatus();
    }

    public void onDetach() {
        super.onDetach();
        this.mOnRegionCodeSelectedListener = null;
        cancelPendingAdminAreaMetadataRetrievalRequest();
        getRequestQueue().cancelAll(new RequestFilter() {
            public boolean apply(Request<?> request) {
                return request instanceof CountryMetadataRetrievalRequest;
            }
        });
    }

    public AddressFormValue getAddressFormValue() {
        AddressFormValue addressFormValue = new AddressFormValue();
        if (areFormFieldsHidden()) {
            addressFormValue.isHidden = true;
        } else {
            addressFormValue.address = getPostalAddressFromFieldValues(getAddressFieldValues());
            String addressLanguageCode = getAddressLanguageCode();
            if (!TextUtils.isEmpty(addressLanguageCode)) {
                addressFormValue.address.languageCode = addressLanguageCode;
            }
            if (!(this.mPhoneNumberText == null || TextUtils.isEmpty(this.mPhoneNumberText.getText()))) {
                addressFormValue.phoneNumber = this.mPhoneNumberText.getText().toString();
            }
        }
        return addressFormValue;
    }

    private static PostalAddress getPostalAddressFromFieldValues(SparseArray<String> fieldValues) {
        PostalAddress postalAddress = new PostalAddress();
        int length = fieldValues.size();
        for (int i = 0; i < length; i++) {
            char field = (char) fieldValues.keyAt(i);
            String value = (String) fieldValues.valueAt(i);
            if (value != null) {
                switch (field) {
                    case com.google.android.play.R.styleable.Theme_dividerVertical /*49*/:
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        postalAddress.addressLine = (String[]) ArrayUtils.appendToArray(postalAddress.addressLine, value);
                        break;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingRight /*67*/:
                        postalAddress.localityName = value;
                        break;
                    case com.google.android.play.R.styleable.Theme_colorAccent /*78*/:
                        postalAddress.recipientName = value;
                        break;
                    case com.google.android.play.R.styleable.Theme_colorControlNormal /*79*/:
                        postalAddress.firmName = value;
                        break;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        postalAddress.countryNameCode = value;
                        break;
                    case com.google.android.play.R.styleable.Theme_colorSwitchThumbNormal /*83*/:
                        postalAddress.administrativeAreaName = value;
                        break;
                    case 'X':
                        postalAddress.sortingCode = value;
                        break;
                    case 'Z':
                        postalAddress.postalCodeNumber = value.toUpperCase();
                        break;
                    default:
                        break;
                }
            }
        }
        return postalAddress;
    }

    public void setAddress(PostalAddress address) {
        if (address == null) {
            if (this.mContainer == null) {
                this.mPendingAddress = null;
                this.mSelectedCountry = 0;
                this.mLanguageCode = Locale.getDefault().toString();
                return;
            }
            address = new PostalAddress();
        }
        int country = RegionCode.toRegionCode(address.countryNameCode);
        this.mPendingAddress = address;
        if (!TextUtils.isEmpty(this.mPendingAddress.languageCode)) {
            this.mLanguageCode = this.mPendingAddress.languageCode;
        }
        if (this.mContainer == null) {
            this.mSelectedCountry = country;
        } else if (country == this.mSelectedCountry) {
            if (!hasPendingRequests() && !requestAlternativeCountryDataIfAvailable()) {
                setPostalAddressValues(this.mPendingAddress);
                this.mPendingAddress = null;
            }
        } else if (this.mSelectedCountry == 0) {
            this.mSelectedCountry = country;
        } else {
            setSelectedCountry(country);
        }
    }

    private SparseArray<String> getAddressFieldValues() {
        if (this.mContainer == null) {
            return null;
        }
        int numInputs = this.mAddressFields.size();
        SparseArray<String> fieldValues = new SparseArray(numInputs);
        for (int i = 0; i < numInputs; i++) {
            View input = (View) this.mAddressFields.get(i);
            Character field = (Character) input.getTag();
            fieldValues.put(field.charValue(), getInputValue(input));
        }
        if (this.mSelectedCountry == 0) {
            return fieldValues;
        }
        fieldValues.put(82, RegionCode.toCountryCode(this.mSelectedCountry));
        return fieldValues;
    }

    private void setAddressFieldValues(SparseArray<String> fieldValues) {
        if (this.mContainer != null && fieldValues != null) {
            int length = this.mAddressFields.size();
            for (int i = 0; i < length; i++) {
                View input = (View) this.mAddressFields.get(i);
                String value = (String) fieldValues.get(((Character) input.getTag()).charValue());
                if (value != null) {
                    setInputValue(input, value);
                }
            }
        }
    }

    void setPostalAddressValues(PostalAddress address) {
        if (this.mContainer != null) {
            int length = this.mAddressFields.size();
            for (int i = 0; i < length; i++) {
                View input = (View) this.mAddressFields.get(i);
                setInputValue(input, AddressFormatter.formatAddressValue(address, ((Character) input.getTag()).charValue()));
            }
        }
    }

    public boolean applyFormFieldMessage(FormFieldMessage formFieldMessage) {
        if (!formFieldMessage.formFieldReference.formId.equals(((AddressForm) this.mFormProto).id)) {
            return false;
        }
        View view = this.mContainer.findViewById(ADDRESS_FORM_FIELD_ID_TO_VIEW_ID.get(formFieldMessage.formFieldReference.fieldId, -1));
        if (view == null || view.getVisibility() != 0) {
            throw new IllegalArgumentException("FormFieldMessage received for invalid field: " + formFieldMessage.formFieldReference.fieldId + " view: " + view);
        } else if (view instanceof EditText) {
            ((EditText) view).setError(formFieldMessage.message);
            return true;
        } else {
            throw new IllegalArgumentException("FormFieldMessage received for non-EditText field: " + formFieldMessage.formFieldReference.fieldId);
        }
    }

    public boolean handleErrorMessageDismissed(String formId, int errorType) {
        if (!formId.equals(((AddressForm) this.mFormProto).id)) {
            return false;
        }
        if (errorType == 1000) {
            String languageCodeForRequest = null;
            if (this.mCountryData != null) {
                languageCodeForRequest = AddressUtils.getAlternativeLanguageCode(this.mCountryData, this.mLanguageCode);
            }
            fireCountryDataRequest(this.mSelectedCountry, this.mLanguageCode, languageCodeForRequest, constructAddressesToFetchAdminAreaFor());
            return true;
        }
        throw new IllegalArgumentException("Unrecognized errorType: " + errorType);
    }

    public boolean validate() {
        return validate(true);
    }

    public boolean isValid() {
        return validate(false);
    }

    private boolean validate(boolean showErrorIfInvalid) {
        if (isHidden()) {
            return true;
        }
        if (!isUiEnabled() || hasPendingRequests() || this.mContainer == null) {
            return false;
        }
        if (areFormFieldsHidden()) {
            return true;
        }
        if (this.mSelectedCountry == 0) {
            return false;
        }
        boolean valid = true;
        int length = this.mAddressFields.size();
        for (int i = 0; i < length; i++) {
            View child = (View) this.mAddressFields.get(i);
            if (child instanceof Validatable) {
                if (showErrorIfInvalid) {
                    if (((Validatable) child).validate() && valid) {
                        valid = true;
                    } else {
                        valid = false;
                    }
                } else if (!((Validatable) child).isValid()) {
                    return false;
                }
            }
        }
        if (this.mPhoneNumberText instanceof Validatable) {
            Validatable validatablePhoneNumber = this.mPhoneNumberText;
            if (showErrorIfInvalid) {
                if (validatablePhoneNumber.validate() && valid) {
                    valid = true;
                } else {
                    valid = false;
                }
            } else if (!validatablePhoneNumber.isValid()) {
                return false;
            }
        }
        return valid;
    }

    public boolean focusOnFirstInvalidFormField() {
        int i = 0;
        int length = this.mAddressFields.size();
        while (i < length) {
            View child = (View) this.mAddressFields.get(i);
            if ((child instanceof TextView) && !TextUtils.isEmpty(((TextView) child).getError())) {
                WalletUiUtils.requestFocusAndAnnounceError(child);
                return true;
            } else if (!(child instanceof Validatable) || ((Validatable) child).isValid()) {
                i++;
            } else {
                WalletUiUtils.requestFocusAndAnnounceError(child);
                return true;
            }
        }
        if (this.mPhoneNumberText == null || TextUtils.isEmpty(this.mPhoneNumberText.getError())) {
            return false;
        }
        WalletUiUtils.requestFocusAndAnnounceError(this.mPhoneNumberText);
        return true;
    }

    public void setSelectedCountry(int selectedCountry) {
        if (selectedCountry != this.mSelectedCountry) {
            this.mSelectedCountry = selectedCountry;
            onSelectedCountryChange();
            if (this.mOnRegionCodeSelectedListener != null && selectedCountry != 0) {
                this.mOnRegionCodeSelectedListener.onRegionCodeSelected(selectedCountry, getId());
            }
        }
    }

    public void setOnRegionCodeSelectedListener(OnRegionCodeSelectedListener listener) {
        this.mOnRegionCodeSelectedListener = listener;
    }

    public void setOnHeightOffsetChangedListener(OnHeightOffsetChangedListener listener) {
        this.mOnHeightOffsetChangedListener = listener;
    }

    public void setOnAddressFieldsShownListener(OnAddressFieldsShownListener listener) {
        this.mOnAddressFieldsShownListener = listener;
    }

    private void displayRegionCodeViewWithSelectedCountry() {
        this.mRegionCodeView.setRegionCodes(this.mRegionCodes);
        this.mRegionCodeView.setRegionCodeSelectedListener(new OnRegionCodeSelectedListener() {
            public void onRegionCodeSelected(int regionCode, int senderId) {
                AddressEntryFragment.this.setSelectedCountry(regionCode);
            }
        });
        onSelectedCountryChange();
    }

    private void onSelectedCountryChange() {
        if (!Objects.equals(RegionCode.toCountryCode(this.mSelectedCountry), ((AddressForm) this.mFormProto).initialValue.address.countryNameCode)) {
            Bundle eventDetails = new Bundle();
            eventDetails.putString("FormEventListener.EXTRA_FORM_ID", ((AddressForm) this.mFormProto).id);
            eventDetails.putInt("FormEventListener.EXTRA_FIELD_ID", 1);
            ((FormEventListener) getParentFragment()).notifyFormEvent(3, eventDetails);
        }
        this.mRegionCodeView.setSelectedRegionCode(this.mSelectedCountry);
        updateCountryData();
    }

    private void updateCountryData() {
        if (AddressUtils.getRegionCodeFromAddressData(this.mCountryData) == this.mSelectedCountry) {
            onReceivedCountryData(this.mCountryData);
        } else {
            fireCountryDataRequest(this.mSelectedCountry, this.mLanguageCode, null, constructAddressesToFetchAdminAreaFor());
        }
    }

    private boolean requestAlternativeCountryDataIfAvailable() {
        if (this.mCountryData == null) {
            return false;
        }
        String alternativeLang = AddressUtils.getAlternativeLanguageCode(this.mCountryData, this.mLanguageCode);
        if (TextUtils.isEmpty(alternativeLang)) {
            String id = AddressUtils.getAddressData(this.mCountryData, "id");
            if (TextUtils.isEmpty(id) || !id.contains("--") || AddressUtils.isSameLanguage(AddressUtils.getAddressData(this.mCountryData, "lang"), this.mLanguageCode)) {
                return false;
            }
            fireCountryDataRequest(this.mSelectedCountry, this.mLanguageCode, null, constructAddressesToFetchAdminAreaFor());
            return true;
        }
        fireCountryDataRequest(this.mSelectedCountry, this.mLanguageCode, alternativeLang, constructAddressesToFetchAdminAreaFor());
        return true;
    }

    private void onReceivedCountryData(JSONObject countryData) {
        if (AddressUtils.getRegionCodeFromAddressData(countryData) == this.mSelectedCountry) {
            SparseArray<String> oldFieldValues = null;
            if (this.mPendingAddress == null) {
                oldFieldValues = getAddressFieldValuesForPrefilling();
            }
            this.mCountryData = countryData;
            updateAddressSources();
            createAndConfigureAddressFields();
            setAddressFieldValues(oldFieldValues);
            if (this.mPendingAddress != null) {
                setPostalAddressValues(this.mPendingAddress);
                this.mPendingAddress = null;
            }
            selectFirstAdminAreaIfHasLocalities();
            this.mDynamicAddressFieldsLayout.switchToShowingFields();
            updatePostalCodeValidation();
        }
    }

    private SparseArray<String> getAddressFieldValuesForPrefilling() {
        SparseArray<String> fieldValues = getAddressFieldValues();
        for (int i = fieldValues.size() - 1; i >= 0; i--) {
            char field = (char) fieldValues.keyAt(i);
            if (findInputWithAddressField(field) instanceof Spinner) {
                fieldValues.remove(field);
            }
        }
        return fieldValues;
    }

    private void updateAddressSources() {
        this.mAddressSources = new ArrayList();
        if (!(this.mAddressHints == null || this.mAddressHints.isEmpty())) {
            this.mAddressSources.add(new LocalAddressSource(this.mAddressHints));
        }
        this.mAddressSources.add(new DeviceAddressSource(getActivity()));
        if (GooglePlacesAddressSource.isCountrySupported(this.mSelectedCountry)) {
            this.mAddressSources.add(new GooglePlacesAddressSource(getActivity()));
        }
    }

    private char[] getCountryDataAddressFields() {
        String format = null;
        if (AddressUtils.shouldUseLatinScript(this.mCountryData, this.mLanguageCode)) {
            format = AddressUtils.getAddressData(this.mCountryData, "lfmt");
        }
        if (TextUtils.isEmpty(format)) {
            format = AddressUtils.getAddressData(this.mCountryData, "fmt");
        }
        return AddressUtils.determineAddressFieldsToDisplay(format);
    }

    private String getActiveCountryDataAddressFields() {
        char[] allAddressFields = getCountryDataAddressFields();
        boolean inPostalCodeOnlyMode = ArrayUtils.contains(((AddressForm) this.mFormProto).postalCodeOnlyCountryCode, RegionCode.toCountryCode(this.mSelectedCountry));
        StringBuilder activeAddressFields = new StringBuilder(allAddressFields.length);
        activeAddressFields.append('N');
        for (char addressField : allAddressFields) {
            if (ALL_DYNAMIC_ADDRESS_FIELDS.get(addressField) && (!inPostalCodeOnlyMode || POSTAL_CODE_ONLY_DYNAMIC_ADDRESS_FIELDS.get(addressField))) {
                activeAddressFields.append(addressField);
            }
        }
        return activeAddressFields.toString();
    }

    private ArrayList<AdminArea> getCountryAdminAreas() {
        boolean latin = AddressUtils.shouldUseLatinScript(this.mCountryData, this.mLanguageCode);
        String[] i18nKeys = AddressUtils.getAddressDataArray(this.mCountryData, "sub_keys");
        String[] localityPresenceIndicators = AddressUtils.getAddressDataArray(this.mCountryData, "sub_mores");
        String[] valuesToStore = null;
        if (latin) {
            valuesToStore = AddressUtils.getAddressDataArray(this.mCountryData, "sub_lnames");
        }
        if (valuesToStore == null) {
            valuesToStore = i18nKeys;
        }
        if (valuesToStore == null || valuesToStore.length == 0) {
            return null;
        }
        String[] valuesToDisplay;
        if (latin) {
            valuesToDisplay = valuesToStore;
        } else {
            valuesToDisplay = AddressUtils.getAddressDataArray(this.mCountryData, "sub_names");
        }
        if (valuesToDisplay == null || valuesToDisplay.length != valuesToStore.length) {
            valuesToDisplay = valuesToStore;
        }
        String[] zipCodes = AddressUtils.getAddressDataArray(this.mCountryData, "sub_zips");
        if (!(zipCodes == null || zipCodes.length == valuesToStore.length)) {
            zipCodes = null;
        }
        if (!(i18nKeys == null || i18nKeys.length == valuesToStore.length)) {
            i18nKeys = null;
            localityPresenceIndicators = null;
        }
        if (!(localityPresenceIndicators == null || localityPresenceIndicators.length == valuesToStore.length)) {
            localityPresenceIndicators = null;
        }
        ArrayList<AdminArea> adminAreas = new ArrayList(valuesToStore.length);
        int i = 0;
        while (i < valuesToStore.length) {
            String str;
            boolean hasLocality = localityPresenceIndicators != null && Boolean.parseBoolean(localityPresenceIndicators[i]);
            if (i18nKeys != null) {
                str = i18nKeys[i];
            } else {
                str = null;
            }
            adminAreas.add(new AdminArea(str, hasLocality, valuesToStore[i], valuesToDisplay[i], zipCodes != null ? zipCodes[i] : null));
            i++;
        }
        if (!latin) {
            return adminAreas;
        }
        Collections.sort(adminAreas, CASE_INSENSITIVE_COMPARATOR);
        return adminAreas;
    }

    private ArrayList<Locality> getAdminAreaLocalities() {
        boolean latin = AddressUtils.shouldUseLatinScript(this.mCountryData, this.mLanguageCode);
        String[] valuesToStore = null;
        if (latin) {
            valuesToStore = AddressUtils.getAddressDataArray(this.mAdminAreaData, "sub_lnames");
        }
        if (valuesToStore == null) {
            valuesToStore = AddressUtils.getAddressDataArray(this.mAdminAreaData, "sub_keys");
        }
        if (valuesToStore == null || valuesToStore.length == 0) {
            return null;
        }
        String[] valuesToDisplay;
        if (latin) {
            valuesToDisplay = valuesToStore;
        } else {
            valuesToDisplay = AddressUtils.getAddressDataArray(this.mAdminAreaData, "sub_names");
        }
        if (valuesToDisplay == null || valuesToDisplay.length != valuesToStore.length) {
            valuesToDisplay = valuesToStore;
        }
        ArrayList<Locality> localities = new ArrayList(valuesToStore.length);
        for (int i = 0; i < valuesToStore.length; i++) {
            localities.add(new Locality(valuesToStore[i], valuesToDisplay[i]));
        }
        if (!latin) {
            return localities;
        }
        Collections.sort(localities, CASE_INSENSITIVE_COMPARATOR);
        return localities;
    }

    private void createAndConfigureAddressFields() {
        String addressFields = getActiveCountryDataAddressFields();
        String requiredFields = AddressUtils.getAddressData(this.mCountryData, "require");
        ArrayList<View> dynamicViews = new ArrayList(addressFields.length());
        this.mAddressFields = new ArrayList(addressFields.length());
        int length = addressFields.length();
        for (int i = 0; i < length; i++) {
            char field = addressFields.charAt(i);
            char[] remainingFields = addressFields.substring(i).toCharArray();
            if (field == 'N') {
                if (this.mRecipientNameText instanceof FormEditText) {
                    configureAddressFormEditTextAutocomplete('N', remainingFields, requiredFields, (FormEditText) this.mRecipientNameText);
                    this.mRecipientNameText.setEnabled(isUiEnabled());
                }
                this.mAddressFields.add(this.mRecipientNameText);
            } else {
                View input = newAddressFieldInput(field, remainingFields, requiredFields);
                dynamicViews.add(input);
                this.mAddressFields.add(input);
            }
        }
        this.mDynamicAddressFieldsLayout.setFields(dynamicViews);
        updatePostalCodeValidation();
    }

    private boolean isFieldAutoCompletable(char field) {
        if (this.mAddressSources == null || this.mAddressSources.isEmpty()) {
            return false;
        }
        switch (field) {
            case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
            case com.google.android.play.R.styleable.Theme_activityChooserViewStyle /*51*/:
            case 'X':
                return false;
            default:
                return true;
        }
    }

    private View newAddressFieldInput(char field, char[] remainingFields, String requiredFields) {
        return newAddressFieldInput(field, remainingFields, requiredFields, null);
    }

    private View newAddressFieldInput(char field, char[] remainingFields, String requiredFields, View convertView) {
        if (!(convertView == null || convertView.getTag() == Character.valueOf(field))) {
            convertView = null;
        }
        if (field == 'N') {
            throw new IllegalArgumentException("Recipient name should not be created dynamically");
        }
        boolean required = AddressUtils.isAddressFieldRequired(field, this.mCountryData);
        String fieldLabel = getFieldLabel(field);
        LayoutInflater themedInflater = getThemedLayoutInflater();
        View input = null;
        int addressFormFieldId = I18N_FIELD_ID_TO_ADDRESS_FORM_FIELD_ID.get(field);
        if (ArrayUtils.contains(((AddressForm) this.mFormProto).readOnlyField, addressFormFieldId)) {
            input = themedInflater.inflate(R.layout.view_form_non_editable_text, null);
        } else if (field == 'S') {
            ArrayList<AdminArea> adminAreas = getCountryAdminAreas();
            if (!(adminAreas == null || adminAreas.isEmpty())) {
                if (convertView instanceof FormSpinner) {
                    spinner = (FormSpinner) convertView;
                } else {
                    spinner = (FormSpinner) themedInflater.inflate(R.layout.view_default_spinner, null);
                }
                spinner.setRequired(required);
                spinner.setPrompt(fieldLabel);
                spinner.setLabel(fieldLabel);
                ArrayAdapter<AdminArea> adminAdapter = new AdminAdapter(getThemedContext(), R.layout.view_row_spinner, adminAreas, fieldLabel);
                adminAdapter.setDropDownViewResource(R.layout.view_spinner_dropdown);
                spinner.setAdapter(adminAdapter);
                spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                        AddressEntryFragment.this.onSelectedAdminChange();
                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
                input = spinner;
            }
        } else if (field == 'C') {
            ArrayList<Locality> localities = getAdminAreaLocalities();
            if (!(localities == null || localities.isEmpty())) {
                ArrayAdapter<Locality> adapter = new ArrayAdapter(getThemedContext(), R.layout.view_row_spinner, localities);
                adapter.setDropDownViewResource(R.layout.view_spinner_dropdown);
                if (convertView instanceof FormSpinner) {
                    spinner = (FormSpinner) convertView;
                } else {
                    spinner = (FormSpinner) themedInflater.inflate(R.layout.view_default_spinner, null);
                }
                spinner.setRequired(required);
                spinner.setPrompt(fieldLabel);
                spinner.setAdapter(adapter);
                input = spinner;
            }
        }
        if (input == null) {
            View editText;
            if (convertView instanceof FormEditText) {
                editText = (FormEditText) convertView;
            } else {
                FormEditText editText2 = (FormEditText) themedInflater.inflate(R.layout.view_form_edit_text, null);
            }
            configureAddressFormEditTextAutocomplete(field, remainingFields, requiredFields, editText);
            editText.setRequired(required);
            editText.setHint(fieldLabel);
            int inputType = 1;
            switch (field) {
                case com.google.android.play.R.styleable.Theme_dividerVertical /*49*/:
                case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                case com.google.android.play.R.styleable.Theme_activityChooserViewStyle /*51*/:
                case com.google.android.play.R.styleable.Theme_listPreferredItemHeightLarge /*65*/:
                    inputType = (1 | 112) | 8192;
                    break;
                case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingRight /*67*/:
                    inputType = 1 | 8192;
                    break;
                case com.google.android.play.R.styleable.Theme_colorSwitchThumbNormal /*83*/:
                    inputType = 1 | 8192;
                    break;
                case 'Z':
                    if (!AddressUtils.doesCountryUseNumericPostalCode(this.mCountryData)) {
                        inputType = 1 | 4096;
                        inputType = 524288 | 4097;
                        break;
                    }
                    inputType = 1 | 2;
                    break;
            }
            editText.setInputType(inputType);
            input = editText;
        }
        if (ArrayUtils.contains(((AddressForm) this.mFormProto).hiddenField, addressFormFieldId)) {
            input.setVisibility(8);
        }
        input.setId(AddressUtils.getAddressFieldViewId(field));
        input.setTag(Character.valueOf(field));
        input.setEnabled(isUiEnabled());
        return input;
    }

    private void configureAddressFormEditTextAutocomplete(char field, char[] remainingFields, String requiredFields, final FormEditText editText) {
        if (isFieldAutoCompletable(field) && PaymentUtils.shouldAutoCompleteBeEnabled(getActivity())) {
            editText.setAdapter(new AddressSourceResultAdapter(getThemedContext(), R.layout.view_row_address_hint_spinner, this.mSelectedCountry, getAddressLanguageCode(), field, remainingFields, requiredFields, this.mAddressSources));
            editText.setThreshold(1);
            editText.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AddressSourceResult result = (AddressSourceResult) parent.getItemAtPosition(position);
                    PostalAddress address = result.address;
                    if (address != null) {
                        AddressEntryFragment.this.fillAddressFieldsAndUpdateFocus(editText, address);
                    } else if (!TextUtils.isEmpty(result.reference)) {
                        new FetchAddressTask(editText).execute(new AddressSourceResult[]{result});
                    } else if (editText.validate()) {
                        AddressEntryFragment.this.focusToNextFieldOrHideKeyboard(editText);
                    }
                }
            });
        }
    }

    private String getFieldLabel(char field) {
        switch (field) {
            case com.google.android.play.R.styleable.Theme_colorAccent /*78*/:
                return ((AddressForm) this.mFormProto).recipientLabel;
            default:
                return AddressUtils.makeAddressFieldLabel(getActivity(), field, this.mCountryData);
        }
    }

    private void fillAddressFieldsAndUpdateFocus(View autoCompleteInput, PostalAddress address) {
        int i;
        boolean foundInput = false;
        ArrayList<Integer> viewIndicesToValidate = new ArrayList();
        int numInputs = this.mAddressFields.size();
        for (i = 0; i < numInputs; i++) {
            View input = (View) this.mAddressFields.get(i);
            if (!foundInput && input == autoCompleteInput) {
                foundInput = true;
            }
            if (foundInput) {
                char field = ((Character) input.getTag()).charValue();
                String value = AddressFormatter.formatAddressValue(address, field);
                if (!TextUtils.isEmpty(value) || !AddressUtils.isAddressFieldRequired(field, this.mCountryData) || TextUtils.isEmpty(getInputValue(input))) {
                    setInputValue(input, value);
                    viewIndicesToValidate.add(Integer.valueOf(i));
                }
            }
        }
        if (foundInput) {
            View firstInvalidOrEmptyInput = null;
            int length = viewIndicesToValidate.size();
            for (i = 0; i < length; i++) {
                input = (View) this.mAddressFields.get(((Integer) viewIndicesToValidate.get(i)).intValue());
                boolean isValid = !(input instanceof Validatable) || ((Validatable) input).validate();
                if (firstInvalidOrEmptyInput == null && (!isValid || TextUtils.isEmpty(getInputValue(input)))) {
                    firstInvalidOrEmptyInput = input;
                }
            }
            if (firstInvalidOrEmptyInput == null) {
                advanceFocusOutOfFragmentOrHideKeyboard();
                return;
            }
            firstInvalidOrEmptyInput.requestFocus();
            if (firstInvalidOrEmptyInput instanceof EditText) {
                EditText editText = (EditText) firstInvalidOrEmptyInput;
                editText.setSelection(editText.getText().length());
            }
        }
    }

    private boolean focusToNextField(View currentFocus) {
        if (currentFocus == null) {
            return false;
        }
        View nextView = currentFocus.focusSearch(130);
        if (nextView == null) {
            return false;
        }
        nextView.requestFocus();
        return true;
    }

    private boolean focusOutOfFragment() {
        View nextView = this.mDynamicAddressFieldsLayout.focusSearch(130);
        if (nextView == null) {
            return false;
        }
        nextView.requestFocus();
        return true;
    }

    private boolean hideKeyboard() {
        View input = this.mDynamicAddressFieldsLayout.findFocus();
        if (input == null) {
            return false;
        }
        WalletUiUtils.hideSoftKeyboard(getActivity(), input);
        return true;
    }

    private void focusToNextFieldOrHideKeyboard(View currentFocus) {
        if (!focusToNextField(currentFocus)) {
            hideKeyboard();
        }
    }

    private void advanceFocusOutOfFragmentOrHideKeyboard() {
        if (!focusOutOfFragment()) {
            hideKeyboard();
        }
    }

    private void updatePostalCodeValidation() {
        View input = findInputWithAddressField('Z');
        if (input instanceof Validatable) {
            ValidatableComponent postalCodeInput = (ValidatableComponent) input;
            if (this.mPostalCodeValidator != null) {
                postalCodeInput.removeValidator(this.mPostalCodeValidator);
                this.mPostalCodeValidator = null;
            }
            this.mPostalCodeValidator = new AndValidator(new AbstractValidator[0]);
            String errorMessage = getString(R.string.wallet_im_error_address_field_invalid, getFieldLabel('Z'));
            Pattern postalCodeRegexpPattern = AddressUtils.getPostalCodeRegexpPattern(this.mCountryData);
            if (postalCodeRegexpPattern != null) {
                this.mPostalCodeValidator.add(new PatternValidator(errorMessage, postalCodeRegexpPattern));
            }
            AdminArea admin = getSelectedAdmin();
            if (admin != null) {
                postalCodeRegexpPattern = AddressUtils.getPostalCodeRegexpPatternForAdminArea(admin.mZipStartsWithRegEx);
                if (postalCodeRegexpPattern != null) {
                    this.mPostalCodeValidator.add(new PatternValidator(errorMessage, postalCodeRegexpPattern));
                }
            }
            if (this.mPostalCodeValidator.isEmpty()) {
                this.mPostalCodeValidator = null;
                return;
            }
            postalCodeInput.addValidator(this.mPostalCodeValidator);
            if (postalCodeInput instanceof TextView) {
                TextView postalCodeText = (TextView) postalCodeInput;
                if (!TextUtils.isEmpty(postalCodeText.getText()) || !TextUtils.isEmpty(postalCodeText.getError())) {
                    postalCodeInput.validate();
                    return;
                }
                return;
            }
            postalCodeInput.validate();
            return;
        }
        this.mPostalCodeValidator = null;
    }

    private void onSelectedAdminChange() {
        if (this.mSelectedCountry != 0) {
            updatePostalCodeValidation();
            AdminArea adminArea = getSelectedAdmin();
            if (this.mAdminAreaData == null || !Objects.equals(AddressUtils.getAddressData(this.mAdminAreaData, "key"), adminArea.mI18nKey)) {
                this.mAdminAreaData = adminArea.mData;
                cancelPendingAdminAreaMetadataRetrievalRequest();
                updateLocalities();
                return;
            }
            adminArea.mData = this.mAdminAreaData;
        }
    }

    private void cancelPendingAdminAreaMetadataRetrievalRequest() {
        if (this.mAdminAreaMetadataRetrievalRequest != null) {
            this.mAdminAreaMetadataRetrievalRequest.cancel();
            this.mAdminAreaMetadataRetrievalRequest = null;
        }
    }

    private void updateLocalities() {
        if (findInputWithAddressField('C') != null) {
            final AdminArea adminArea = getSelectedAdmin();
            if (adminArea.mHasLocalities && adminArea.mData == null) {
                this.mAdminAreaMetadataRetrievalRequest = new AdminAreaMetadataRetrievalRequest(this.mSelectedCountry, adminArea.mI18nKey, new Listener<JSONObject>() {
                    public void onResponse(JSONObject adminAreaData) {
                        adminArea.mData = adminAreaData;
                        AddressEntryFragment.this.mAdminAreaData = adminAreaData;
                        AddressEntryFragment.this.updateLocalityInput();
                        AddressEntryFragment.this.mAdminAreaMetadataRetrievalRequest = null;
                    }
                }, new ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        AddressEntryFragment.this.mAdminAreaData = null;
                        AddressEntryFragment.this.updateLocalityInput();
                        AddressEntryFragment.this.mAdminAreaMetadataRetrievalRequest = null;
                    }
                });
                getRequestQueue().add(this.mAdminAreaMetadataRetrievalRequest);
                return;
            }
            updateLocalityInput();
        }
    }

    private void updateLocalityInput() {
        int addressFieldIndex = findInputIndexWithAddressField('C');
        View localityInput = (View) this.mAddressFields.get(addressFieldIndex);
        String inputValue = getInputValue(localityInput);
        View newInput = newAddressFieldInput('C', getActiveCountryDataAddressFields().substring(addressFieldIndex).toCharArray(), AddressUtils.getAddressData(this.mCountryData, "require"), localityInput);
        if (newInput != localityInput) {
            this.mDynamicAddressFieldsLayout.replaceView(localityInput, newInput);
            this.mAddressFields.set(addressFieldIndex, newInput);
        }
        setInputValue(newInput, inputValue);
    }

    private void selectFirstAdminAreaIfHasLocalities() {
        View input = findInputWithAddressField('S');
        if (input instanceof Spinner) {
            Spinner adminAreaSpinner = (Spinner) input;
            if (adminAreaSpinner.getSelectedItemPosition() == 0 && ((AdminArea) adminAreaSpinner.getItemAtPosition(1)).mHasLocalities) {
                adminAreaSpinner.setSelection(1);
            }
        }
    }

    private AdminArea getSelectedAdmin() {
        View adminAreaInput = findInputWithAddressField('S');
        if (adminAreaInput != null && (adminAreaInput instanceof Spinner)) {
            return (AdminArea) ((Spinner) adminAreaInput).getSelectedItem();
        }
        return null;
    }

    private String getAddressLanguageCode() {
        if (AddressUtils.shouldUseLatinScript(this.mCountryData, this.mLanguageCode)) {
            return this.mLanguageCode;
        }
        return AddressUtils.getAddressData(this.mCountryData, "lang");
    }

    protected void doEnableUi() {
        if (this.mContainer != null) {
            boolean enabled = isUiEnabled() && !hasPendingRequests();
            this.mHideAddressCheckbox.setEnabled(enabled);
            this.mRegionCodeView.setEnabled(enabled);
            int length = this.mAddressFields.size();
            for (int i = 0; i < length; i++) {
                ((View) this.mAddressFields.get(i)).setEnabled(enabled);
            }
            if (this.mPhoneNumberText != null) {
                this.mPhoneNumberText.setEnabled(enabled);
            }
        }
    }

    private boolean areFormFieldsHidden() {
        return this.mHideAddressCheckbox.getVisibility() == 0 && this.mHideAddressCheckbox.isChecked();
    }

    public boolean isReadyToSubmit() {
        return true;
    }

    boolean hasPendingRequests() {
        return this.mPendingRequestCounter > 0;
    }

    void indicatePendingRequest(boolean pendingRequest) {
        this.mPendingRequestCounter = Math.max(0, (pendingRequest ? 1 : -1) + this.mPendingRequestCounter);
        if ((pendingRequest && this.mPendingRequestCounter == 1) || (!pendingRequest && this.mPendingRequestCounter == 0)) {
            doEnableUi();
        }
    }

    private void resetPendingRequestStatus() {
        this.mPendingRequestCounter = 0;
        doEnableUi();
    }

    View findInputWithAddressField(char addressField) {
        int addressFieldIndex = findInputIndexWithAddressField(addressField);
        if (addressFieldIndex >= 0) {
            return (View) this.mAddressFields.get(addressFieldIndex);
        }
        return null;
    }

    private int findInputIndexWithAddressField(char addressField) {
        int length = this.mAddressFields.size();
        for (int i = 0; i < length; i++) {
            Character field = (Character) ((View) this.mAddressFields.get(i)).getTag();
            if (field != null && field.charValue() == addressField) {
                return i;
            }
        }
        return -1;
    }

    private ArrayList<PostalAddress> constructAddressesToFetchAdminAreaFor() {
        ArrayList<PostalAddress> postalAddresses = new ArrayList();
        postalAddresses.add(this.mPendingAddress);
        if (this.mAddressHints != null) {
            postalAddresses.addAll(this.mAddressHints);
        }
        return postalAddresses;
    }

    RequestQueue getRequestQueue() {
        return InstrumentManagerRequestQueue.getApiRequestQueue(getActivity());
    }

    public void onHeightOffsetChanged(float heightOffset) {
        if (this.mPhoneNumberText != null && this.mPhoneNumberText.getVisibility() == 0) {
            this.mPhoneNumberText.setTranslationY(heightOffset);
        }
        if (this.mOnHeightOffsetChangedListener != null) {
            this.mOnHeightOffsetChangedListener.onHeightOffsetChanged(heightOffset);
        }
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int visibility;
        boolean z = false;
        if (isChecked) {
            visibility = 8;
        } else {
            visibility = 0;
        }
        this.mRecipientNameText.setVisibility(visibility);
        this.mRegionCodeView.setVisibility(visibility);
        this.mDynamicAddressFieldsLayout.setVisibility(visibility);
        if (this.mPhoneNumberText != null) {
            this.mPhoneNumberText.setVisibility(visibility);
        }
        if (this.mOnAddressFieldsShownListener != null) {
            OnAddressFieldsShownListener onAddressFieldsShownListener = this.mOnAddressFieldsShownListener;
            if (!isChecked) {
                z = true;
            }
            onAddressFieldsShownListener.onAddressFieldsShown(z);
        }
    }

    public InstrumentManagerUiElement getUiElement() {
        return this.mUiElement;
    }

    public List<UiNode> getChildren() {
        return null;
    }
}
