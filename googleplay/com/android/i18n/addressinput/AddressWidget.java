package com.android.i18n.addressinput;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.i18n.addressinput.FormOptions.Builder;
import com.android.vending.R;
import com.google.android.finsky.layout.AddressAutoComplete;
import com.google.android.finsky.layout.AddressFieldsLayout;
import com.google.android.finsky.layout.AddressSuggestionProvider;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.UiUtils;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AddressWidget implements OnItemSelectedListener {
    private static final Map<String, Integer> ADMIN_ERROR_MESSAGES;
    private static final Map<String, Integer> ADMIN_LABELS;
    private static final FormOptions SHOW_ALL_FIELDS;
    private String mAdminLabel;
    private CacheData mCacheData;
    private Context mContext;
    private String mCurrentRegion;
    private FormController mFormController;
    private FormOptions mFormOptions;
    private FormatInterpreter mFormatInterpreter;
    final Handler mHandler;
    private LayoutInflater mInflater;
    private final EnumMap<AddressField, AddressUIComponent> mInputWidgets;
    private Listener mListener;
    private AddressFieldsLayout mRootView;
    private AddressData mSavedAddress;
    private Map<AddressField, String> mSavedErrors;
    private ScriptType mScript;
    private ArrayList<AddressSpinnerInfo> mSpinners;
    private AddressSuggestionProvider mSuggestionProvider;
    private StandardAddressVerifier mVerifier;
    private String mWidgetLocale;
    private ZipLabel mZipLabel;

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$android$i18n$addressinput$AddressField;

        static {
            $SwitchMap$com$android$i18n$addressinput$AddressField = new int[AddressField.values().length];
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.ADMIN_AREA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.LOCALITY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.DEPENDENT_LOCALITY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.POSTAL_CODE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private static class AddressSpinnerInfo {
        private ArrayAdapter<String> mAdapter;
        private List<RegionData> mCurrentRegions;
        private AddressField mId;
        private AddressField mParentId;
        private Spinner mView;

        public AddressSpinnerInfo(Spinner view, AddressField id, AddressField parentId) {
            this.mView = view;
            this.mId = id;
            this.mParentId = parentId;
        }

        public void initAdapter(Context context, int textViewId, int dropDownId) {
            this.mAdapter = new ArrayAdapter(context, textViewId);
            this.mAdapter.setDropDownViewResource(dropDownId);
        }

        public void setSpinnerList(List<RegionData> list, String defaultKey) {
            this.mCurrentRegions = list;
            this.mAdapter.clear();
            for (RegionData item : list) {
                this.mAdapter.add(item.getDisplayName());
            }
            this.mAdapter.sort(Collator.getInstance(Locale.getDefault()));
            if (defaultKey.length() == 0) {
                this.mView.setSelection(0);
                return;
            }
            this.mView.setSelection(this.mAdapter.getPosition(defaultKey));
        }

        public String getRegionDataKeyForValue(String value) {
            for (RegionData data : this.mCurrentRegions) {
                if (data.getDisplayName().endsWith(value)) {
                    return data.getKey();
                }
            }
            return "";
        }

        private RegionData findRegionByKeyIgnoreCase(String key) {
            for (RegionData data : this.mCurrentRegions) {
                if (data.getKey().equalsIgnoreCase(key)) {
                    return data;
                }
            }
            return null;
        }

        private RegionData findRegionByDisplayName(String displayName) {
            for (RegionData data : this.mCurrentRegions) {
                if (data.getDisplayName().equalsIgnoreCase(displayName)) {
                    return data;
                }
            }
            return null;
        }

        public void setSelectionByKey(String key) {
            RegionData data = findRegionByKeyIgnoreCase(key);
            if (data != null) {
                int position = this.mAdapter.getPosition(data.getDisplayName());
                if (position >= 0) {
                    this.mView.setSelection(position);
                }
            }
        }

        public void setSelectionByKeyOrDisplayName(String keyOrDisplayName) {
            RegionData data = findRegionByKeyIgnoreCase(keyOrDisplayName);
            if (data == null) {
                data = findRegionByDisplayName(keyOrDisplayName);
                if (data == null) {
                    return;
                }
            }
            int position = this.mAdapter.getPosition(data.getDisplayName());
            if (position >= 0) {
                this.mView.setSelection(position);
            }
        }
    }

    public interface Listener {
        void onInitialized();
    }

    private class UpdateRunnable implements Runnable {
        private AddressField myId;

        public UpdateRunnable(AddressField id) {
            this.myId = id;
        }

        public void run() {
            AddressWidget.this.updateInputWidget(this.myId);
        }
    }

    private enum ZipLabel {
        ZIP,
        POSTAL
    }

    static {
        SHOW_ALL_FIELDS = new Builder().build();
        Map<String, Integer> adminLabelMap = new HashMap(15);
        adminLabelMap.put("area", Integer.valueOf(R.string.i18n_area));
        adminLabelMap.put("county", Integer.valueOf(R.string.i18n_county_label));
        adminLabelMap.put("department", Integer.valueOf(R.string.i18n_department));
        adminLabelMap.put("district", Integer.valueOf(R.string.i18n_dependent_locality_label));
        adminLabelMap.put("do_si", Integer.valueOf(R.string.i18n_do_si));
        adminLabelMap.put("emirate", Integer.valueOf(R.string.i18n_emirate));
        adminLabelMap.put("island", Integer.valueOf(R.string.i18n_island));
        adminLabelMap.put("parish", Integer.valueOf(R.string.i18n_parish));
        adminLabelMap.put("prefecture", Integer.valueOf(R.string.i18n_prefecture));
        adminLabelMap.put("province", Integer.valueOf(R.string.i18n_province));
        adminLabelMap.put("state", Integer.valueOf(R.string.i18n_state_label));
        ADMIN_LABELS = Collections.unmodifiableMap(adminLabelMap);
        Map<String, Integer> adminErrorMap = new HashMap(15);
        adminErrorMap.put("area", Integer.valueOf(R.string.invalid_area));
        adminErrorMap.put("county", Integer.valueOf(R.string.invalid_county_label));
        adminErrorMap.put("department", Integer.valueOf(R.string.invalid_department));
        adminErrorMap.put("district", Integer.valueOf(R.string.invalid_dependent_locality_label));
        adminErrorMap.put("do_si", Integer.valueOf(R.string.invalid_do_si));
        adminErrorMap.put("emirate", Integer.valueOf(R.string.invalid_emirate));
        adminErrorMap.put("island", Integer.valueOf(R.string.invalid_island));
        adminErrorMap.put("parish", Integer.valueOf(R.string.invalid_parish));
        adminErrorMap.put("prefecture", Integer.valueOf(R.string.invalid_prefecture));
        adminErrorMap.put("province", Integer.valueOf(R.string.invalid_province));
        adminErrorMap.put("state", Integer.valueOf(R.string.invalid_state_label));
        ADMIN_ERROR_MESSAGES = Collections.unmodifiableMap(adminErrorMap);
    }

    private View createView(AddressFieldsLayout rootView, AddressUIComponent field, String defaultKey, boolean readOnly, boolean isFirstField) {
        boolean z = false;
        String fieldText = field.getFieldName();
        if (isFirstField && !readOnly && field.getUIType().equals(UIComponent.EDIT) && field.getId() == AddressField.ADDRESS_LINE_1 && this.mSuggestionProvider != null) {
            AddressAutoComplete view = (AddressAutoComplete) this.mInflater.inflate(R.layout.address_autocomplete, rootView, false);
            field.setView(view);
            view.setHint(fieldText);
            view.setSuggestionProvider(this.mSuggestionProvider);
            return view;
        } else if (field.getUIType().equals(UIComponent.EDIT)) {
            view = this.mInflater.inflate(R.layout.address_edittext, rootView, false);
            field.setView(view);
            EditText editText = (EditText) view;
            if (!readOnly) {
                z = true;
            }
            editText.setEnabled(z);
            if (fieldText.length() > 0) {
                editText.setHint(fieldText);
            }
            if (field.getId() == AddressField.POSTAL_CODE) {
                editText.setInputType(editText.getInputType() | 4096);
            }
            return editText;
        } else if (!field.getUIType().equals(UIComponent.SPINNER)) {
            return null;
        } else {
            view = this.mInflater.inflate(R.layout.address_spinner, rootView, false);
            field.setView(view);
            View spinner = (Spinner) view;
            AddressSpinnerInfo spinnerInfo = new AddressSpinnerInfo(spinner, field.getId(), field.getParentId());
            spinnerInfo.initAdapter(this.mContext, 17367048, 17367049);
            spinner.setAdapter(spinnerInfo.mAdapter);
            spinnerInfo.setSpinnerList(field.getCandidatesList(), defaultKey);
            if (fieldText.length() > 0) {
                spinner.setPrompt(fieldText);
            }
            spinner.setOnItemSelectedListener(this);
            this.mSpinners.add(spinnerInfo);
            return spinner;
        }
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void setEnabled(boolean enabled) {
        for (AddressUIComponent addressField : this.mInputWidgets.values()) {
            if (addressField.getView() != null) {
                addressField.getView().setEnabled(enabled);
            }
        }
    }

    private void buildFieldWidgets() {
        AddressVerificationNodeData countryNode = new ClientData(this.mCacheData).getDefaultData(new Builder(KeyType.DATA).setAddressData(new AddressData.Builder().setCountry(this.mCurrentRegion).build()).build().toString());
        AddressUIComponent adminAreaUI = new AddressUIComponent(AddressField.ADMIN_AREA);
        adminAreaUI.setFieldName(getAdminAreaFieldName(countryNode));
        this.mInputWidgets.put(AddressField.ADMIN_AREA, adminAreaUI);
        AddressUIComponent localityUI = new AddressUIComponent(AddressField.LOCALITY);
        localityUI.setFieldName(this.mContext.getString(R.string.i18n_locality_label));
        this.mInputWidgets.put(AddressField.LOCALITY, localityUI);
        AddressUIComponent subLocalityUI = new AddressUIComponent(AddressField.DEPENDENT_LOCALITY);
        subLocalityUI.setFieldName(this.mContext.getString(R.string.i18n_dependent_locality_label));
        this.mInputWidgets.put(AddressField.DEPENDENT_LOCALITY, subLocalityUI);
        AddressUIComponent addressLine1UI = new AddressUIComponent(AddressField.ADDRESS_LINE_1);
        addressLine1UI.setFieldName(this.mContext.getString(R.string.i18n_address_line1_label));
        this.mInputWidgets.put(AddressField.ADDRESS_LINE_1, addressLine1UI);
        this.mInputWidgets.put(AddressField.STREET_ADDRESS, addressLine1UI);
        AddressUIComponent addressLine2UI = new AddressUIComponent(AddressField.ADDRESS_LINE_2);
        addressLine2UI.setFieldName("");
        this.mInputWidgets.put(AddressField.ADDRESS_LINE_2, addressLine2UI);
        AddressUIComponent organizationUI = new AddressUIComponent(AddressField.ORGANIZATION);
        organizationUI.setFieldName(this.mContext.getString(R.string.i18n_organization_label));
        this.mInputWidgets.put(AddressField.ORGANIZATION, organizationUI);
        AddressUIComponent recipientUI = new AddressUIComponent(AddressField.RECIPIENT);
        recipientUI.setFieldName(this.mContext.getString(R.string.i18n_recipient_label));
        this.mInputWidgets.put(AddressField.RECIPIENT, recipientUI);
        AddressUIComponent postalCodeUI = new AddressUIComponent(AddressField.POSTAL_CODE);
        postalCodeUI.setFieldName(getZipFieldName(countryNode));
        this.mInputWidgets.put(AddressField.POSTAL_CODE, postalCodeUI);
        AddressUIComponent sortingCodeUI = new AddressUIComponent(AddressField.SORTING_CODE);
        sortingCodeUI.setFieldName("CEDEX");
        this.mInputWidgets.put(AddressField.SORTING_CODE, sortingCodeUI);
    }

    private void initializeDropDowns() {
        ((AddressUIComponent) this.mInputWidgets.get(AddressField.ADMIN_AREA)).initializeCandidatesList(getRegionData(AddressField.COUNTRY));
        ((AddressUIComponent) this.mInputWidgets.get(AddressField.LOCALITY)).initializeCandidatesList(getRegionData(AddressField.ADMIN_AREA));
    }

    private String getZipFieldName(AddressVerificationNodeData countryNode) {
        if (countryNode.get(AddressDataKey.ZIP_NAME_TYPE) == null) {
            this.mZipLabel = ZipLabel.POSTAL;
            return this.mContext.getString(R.string.i18n_postal_code_label);
        }
        this.mZipLabel = ZipLabel.ZIP;
        return this.mContext.getString(R.string.i18n_zip_code_label);
    }

    private String getAdminAreaFieldName(AddressVerificationNodeData countryNode) {
        String adminLabelType = countryNode.get(AddressDataKey.STATE_NAME_TYPE);
        this.mAdminLabel = adminLabelType;
        Integer result = (Integer) ADMIN_LABELS.get(adminLabelType);
        if (result == null) {
            result = Integer.valueOf(R.string.i18n_province);
        }
        return this.mContext.getString(result.intValue());
    }

    private AddressSpinnerInfo findSpinnerByView(View view) {
        Iterator i$ = this.mSpinners.iterator();
        while (i$.hasNext()) {
            AddressSpinnerInfo spinnerInfo = (AddressSpinnerInfo) i$.next();
            if (spinnerInfo.mView == view) {
                return spinnerInfo;
            }
        }
        return null;
    }

    private void updateFields() {
        buildFieldWidgets();
        initializeDropDowns();
        layoutAddressFields();
        if (this.mSavedAddress != null) {
            initializeFieldsWithAddress(this.mSavedAddress, true);
        }
        if (this.mSavedErrors != null) {
            setViewErrors(this.mSavedErrors);
        }
        this.mRootView.showFields();
    }

    private void layoutAddressFields() {
        ArrayList<View> fields = Lists.newArrayList();
        boolean first = true;
        for (AddressField field : this.mFormatInterpreter.getAddressFieldOrder(this.mScript, this.mCurrentRegion)) {
            if (!this.mFormOptions.isHidden(field)) {
                View v = createView(this.mRootView, (AddressUIComponent) this.mInputWidgets.get(field), "", this.mFormOptions.isReadonly(field), first);
                if (v != null) {
                    fields.add(v);
                    first = false;
                }
            }
        }
        this.mRootView.setFields(fields);
    }

    private void updateChildNodes(AdapterView<?> parent, int position) {
        AddressSpinnerInfo spinnerInfo = findSpinnerByView(parent);
        if (spinnerInfo != null) {
            final AddressField myId = spinnerInfo.mId;
            if (myId == AddressField.ADMIN_AREA || myId == AddressField.LOCALITY) {
                this.mFormController.requestDataForAddress(getAddressData(), new DataLoadListener() {
                    public void dataLoadingBegin() {
                    }

                    public void dataLoadingEnd() {
                        AddressWidget.this.mHandler.post(new UpdateRunnable(myId));
                    }
                });
            }
        }
    }

    public void updateWidgetOnCountryChange(String regionCode) {
        if (!this.mCurrentRegion.equalsIgnoreCase(regionCode)) {
            this.mSavedAddress = null;
            this.mCurrentRegion = regionCode;
            this.mFormController.setCurrentCountry(this.mCurrentRegion);
            renderForm();
        }
    }

    private void updateInputWidget(AddressField myId) {
        Iterator i$ = this.mSpinners.iterator();
        while (i$.hasNext()) {
            AddressSpinnerInfo child = (AddressSpinnerInfo) i$.next();
            if (child.mParentId == myId) {
                child.setSpinnerList(getRegionData(child.mParentId), "");
            }
        }
    }

    public void renderForm() {
        setWidgetLocaleAndScript();
        this.mFormController.requestDataForAddress(new AddressData.Builder().setCountry(this.mCurrentRegion).setLanguageCode(this.mWidgetLocale).build(), new DataLoadListener() {
            public void dataLoadingBegin() {
            }

            public void dataLoadingEnd() {
                Log.d(toString(), "Data loading completed.");
                AddressWidget.this.mHandler.post(new Runnable() {
                    public void run() {
                        AddressWidget.this.updateFields();
                        if (AddressWidget.this.mListener != null) {
                            AddressWidget.this.mListener.onInitialized();
                        }
                    }
                });
            }
        });
        this.mRootView.showProgressBar();
    }

    private void setWidgetLocaleAndScript() {
        this.mWidgetLocale = Util.getWidgetCompatibleLanguageCode(Locale.getDefault(), this.mCurrentRegion);
        this.mFormController.setLanguageCode(this.mWidgetLocale);
        this.mScript = Util.isExplicitLatinScript(this.mWidgetLocale) ? ScriptType.LATIN : ScriptType.LOCAL;
    }

    private List<RegionData> getRegionData(AddressField parentField) {
        AddressData address = getAddressData();
        if (this.mFormController.isDefaultLanguage(address.getLanguageCode())) {
            address = new AddressData.Builder(address).setLanguageCode(null).build();
        }
        LookupKey parentKey = this.mFormController.getDataKeyFor(address).getKeyForUpperLevelField(parentField);
        if (parentKey != null) {
            return this.mFormController.getRegionData(parentKey);
        }
        Log.w(toString(), "Can't build key with parent field " + parentField + ". One of" + " the ancestor fields might be empty");
        return new ArrayList(1);
    }

    public AddressWidget(Context context, AddressFieldsLayout rootView, FormOptions formOptions, ClientCacheManager cacheManager, String region) {
        this.mInputWidgets = new EnumMap(AddressField.class);
        this.mHandler = new Handler();
        this.mSpinners = new ArrayList();
        if (region == null || !isValidRegionCode(region)) {
            this.mCurrentRegion = getDefaultRegionCode(context);
        } else {
            this.mCurrentRegion = region;
        }
        init(context, rootView, formOptions, cacheManager);
    }

    public void renderFormWithSavedAddress(AddressData savedAddress) {
        if (savedAddress != null) {
            this.mSavedAddress = savedAddress;
            this.mRootView.disableOneFieldMode();
        }
        renderForm();
    }

    private void initializeFieldsWithAddress(AddressData savedAddress, boolean exactAddress) {
        for (AddressField field : this.mFormatInterpreter.getAddressFieldOrder(this.mScript, this.mCurrentRegion)) {
            AddressUIComponent uiComponent = (AddressUIComponent) this.mInputWidgets.get(field);
            if (uiComponent != null) {
                String value = savedAddress.getFieldValue(field);
                if (value == null) {
                    value = "";
                }
                View view = uiComponent.getView();
                if (view != null) {
                    if (uiComponent.getUIType() == UIComponent.SPINNER) {
                        AddressSpinnerInfo spinnerInfo = findSpinnerByView(view);
                        if (spinnerInfo != null) {
                            if (exactAddress) {
                                spinnerInfo.setSelectionByKey(value);
                            } else {
                                spinnerInfo.setSelectionByKeyOrDisplayName(value);
                            }
                        }
                    } else {
                        ((EditText) view).setText(value);
                    }
                }
            }
        }
    }

    private Map<AddressField, String> getViewErrors() {
        HashMap<AddressField, String> errors = new HashMap();
        for (AddressField field : this.mFormatInterpreter.getAddressFieldOrder(this.mScript, this.mCurrentRegion)) {
            AddressUIComponent uiComponent = (AddressUIComponent) this.mInputWidgets.get(field);
            if (uiComponent != null) {
                View view = uiComponent.getView();
                if (view != null && uiComponent.getUIType() == UIComponent.EDIT) {
                    CharSequence text = ((EditText) view).getError();
                    if (text != null) {
                        errors.put(field, text.toString());
                    }
                }
            }
        }
        return errors;
    }

    private void setViewErrors(Map<AddressField, String> errors) {
        for (AddressField field : errors.keySet()) {
            AddressUIComponent uiComponent = (AddressUIComponent) this.mInputWidgets.get(field);
            if (uiComponent != null) {
                View view = uiComponent.getView();
                if (view != null && uiComponent.getUIType() == UIComponent.EDIT) {
                    String errorText = (String) errors.get(field);
                    if (errorText != null) {
                        UiUtils.setErrorOnTextView((EditText) view, uiComponent.getFieldName(), errorText);
                    }
                }
            }
        }
    }

    private void init(Context context, AddressFieldsLayout rootView, FormOptions formOptions, ClientCacheManager cacheManager) {
        this.mContext = context;
        this.mRootView = rootView;
        this.mFormOptions = formOptions;
        this.mCacheData = new CacheData(cacheManager);
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mFormController = new FormController(new ClientData(this.mCacheData), this.mWidgetLocale, this.mCurrentRegion);
        this.mFormatInterpreter = new FormatInterpreter(this.mFormOptions);
        this.mVerifier = new StandardAddressVerifier(new FieldVerifier(new ClientData(this.mCacheData)));
    }

    public void setFormOptions(FormOptions formOptions) {
        this.mFormOptions = formOptions;
    }

    public AddressData getAddressData() {
        AddressData.Builder builder = new AddressData.Builder();
        builder.setCountry(this.mCurrentRegion);
        for (AddressField field : this.mFormatInterpreter.getAddressFieldOrder(this.mScript, this.mCurrentRegion)) {
            AddressUIComponent addressUIComponent = (AddressUIComponent) this.mInputWidgets.get(field);
            if (addressUIComponent != null) {
                String value = addressUIComponent.getValue();
                if (addressUIComponent.getUIType() == UIComponent.SPINNER) {
                    AddressSpinnerInfo spinnerInfo = findSpinnerByView(getViewForField(field));
                    if (spinnerInfo != null) {
                        value = spinnerInfo.getRegionDataKeyForValue(value);
                    }
                }
                builder.set(field, value);
            }
        }
        builder.setLanguageCode(this.mWidgetLocale);
        return builder.build();
    }

    public static List<String> getFullEnvelopeAddress(AddressData address, Context context) {
        return new FormatInterpreter(SHOW_ALL_FIELDS).getEnvelopeAddress(address, getDefaultRegionCode(context));
    }

    public AddressProblems getAddressProblems() {
        AddressProblems problems = new AddressProblems();
        this.mVerifier.verify(getAddressData(), problems);
        problems.getProblems().keySet().removeAll(this.mFormOptions.getHiddenFields());
        if (this.mFormOptions.isHidden(AddressField.ADMIN_AREA) && problems.getProblem(AddressField.POSTAL_CODE) != AddressProblemType.MISSING_REQUIRED_FIELD) {
            problems.getProblems().remove(AddressField.POSTAL_CODE);
        }
        return problems;
    }

    public TextView displayErrorMessageForInvalidEntryIn(AddressField field) {
        AddressUIComponent addressUIComponent = (AddressUIComponent) this.mInputWidgets.get(field);
        if (addressUIComponent == null || addressUIComponent.getUIType() != UIComponent.EDIT) {
            return null;
        }
        EditText view = (EditText) addressUIComponent.getView();
        UiUtils.setErrorOnTextView(view, addressUIComponent.getFieldName(), this.mContext.getString(getErrorMessageIdForInvalidEntryIn(field)));
        return view;
    }

    private int getErrorMessageIdForInvalidEntryIn(AddressField field) {
        switch (AnonymousClass3.$SwitchMap$com$android$i18n$addressinput$AddressField[field.ordinal()]) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return ((Integer) ADMIN_ERROR_MESSAGES.get(this.mAdminLabel)).intValue();
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return R.string.invalid_locality_label;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return R.string.invalid_dependent_locality_label;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                return this.mZipLabel == ZipLabel.POSTAL ? R.string.invalid_postal_code_label : R.string.invalid_zip_code_label;
            default:
                return R.string.invalid_entry;
        }
    }

    public void clearErrorMessage() {
        for (AddressField field : this.mFormatInterpreter.getAddressFieldOrder(this.mScript, this.mCurrentRegion)) {
            AddressUIComponent addressUIComponent = (AddressUIComponent) this.mInputWidgets.get(field);
            if (addressUIComponent != null && addressUIComponent.getUIType() == UIComponent.EDIT) {
                EditText view = (EditText) addressUIComponent.getView();
                if (view != null) {
                    view.setError(null);
                }
            }
        }
    }

    public View getViewForField(AddressField field) {
        AddressUIComponent component = (AddressUIComponent) this.mInputWidgets.get(field);
        if (component == null) {
            return null;
        }
        return component.getView();
    }

    public String getNameForField(AddressField field) {
        AddressUIComponent component = (AddressUIComponent) this.mInputWidgets.get(field);
        if (component == null) {
            return null;
        }
        return component.getFieldName();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        updateChildNodes(parent, position);
    }

    public void setSuggestionProvider(AddressSuggestionProvider suggestionProvider) {
        this.mSuggestionProvider = suggestionProvider;
        View view = getViewForField(AddressField.ADDRESS_LINE_1);
        if (view != null && (view instanceof AddressAutoComplete)) {
            ((AddressAutoComplete) view).setSuggestionProvider(this.mSuggestionProvider);
        }
    }

    public void setAddressFromSuggestion(AddressData address) {
        View view = getViewForField(AddressField.ADDRESS_LINE_1);
        AddressAutoComplete autoCompleteView = view instanceof AddressAutoComplete ? (AddressAutoComplete) view : null;
        if (autoCompleteView != null) {
            autoCompleteView.blockNextSuggestion();
        }
        initializeFieldsWithAddress(address, false);
        if (autoCompleteView != null) {
            autoCompleteView.setSelection(autoCompleteView.getText().length());
        }
    }

    public void showUpperRightProgressBar() {
        this.mRootView.showUpperRightProgressBar();
    }

    public void hideUpperRightProgressBar() {
        this.mRootView.hideUpperRightProgressBar();
    }

    public static boolean isValidRegionCode(String regionCode) {
        return RegionDataConstants.getCountryFormatMap().containsKey(regionCode);
    }

    private static String getDefaultRegionCode(Context context) {
        String defaultRegionCode = "US";
        if (context == null) {
            return defaultRegionCode;
        }
        String region = ((TelephonyManager) context.getSystemService("phone")).getSimCountryIso().toUpperCase();
        if (region == null || region.length() != 2) {
            return defaultRegionCode;
        }
        return region;
    }

    public void saveInstanceState(Bundle outState) {
        outState.putSerializable("address_data", getAddressData());
        Map<AddressField, String> errors = getViewErrors();
        ArrayList<Integer> keys = new ArrayList();
        ArrayList<String> values = new ArrayList();
        for (AddressField field : errors.keySet()) {
            keys.add(Integer.valueOf(field.ordinal()));
            values.add(errors.get(field));
        }
        outState.putIntegerArrayList("address_error_fields", keys);
        outState.putStringArrayList("address_error_values", values);
    }

    public void restoreInstanceState(Bundle inState) {
        this.mSavedAddress = (AddressData) inState.getSerializable("address_data");
        initializeFieldsWithAddress(this.mSavedAddress, true);
        ArrayList<Integer> keys = inState.getIntegerArrayList("address_error_fields");
        ArrayList<String> values = inState.getStringArrayList("address_error_values");
        if (keys != null && values != null) {
            HashMap<AddressField, String> errors = new HashMap();
            for (int i = 0; i < keys.size(); i++) {
                String value = (String) values.get(i);
                errors.put(AddressField.values()[((Integer) keys.get(i)).intValue()], value);
            }
            this.mSavedErrors = errors;
            setViewErrors(errors);
        }
    }
}
