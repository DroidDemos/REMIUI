package com.google.android.wallet.instrumentmanager.ui.tax;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.analytics.UiNode;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;
import com.google.android.wallet.instrumentmanager.ui.common.FormEditText;
import com.google.android.wallet.instrumentmanager.ui.common.FormFragment;
import com.google.android.wallet.instrumentmanager.ui.common.FormSpinner;
import com.google.android.wallet.instrumentmanager.ui.common.WalletUiUtils;
import com.google.commerce.payments.orchestration.proto.ui.common.UiErrorOuterClass.FormFieldMessage;
import com.google.commerce.payments.orchestration.proto.ui.common.components.tax.TaxInfoFormOuterClass.TaxInfoForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.tax.TaxInfoFormOuterClass.TaxInfoFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField;
import com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiFieldValue;
import java.util.List;

public class TaxInfoEntryFragment extends FormFragment<TaxInfoForm> implements OnItemSelectedListener {
    FormSpinner mTaxInfoFormSpinner;
    LinearLayout mTaxInfoTextFields;
    private final InstrumentManagerUiElement mUiElement;

    public TaxInfoEntryFragment() {
        this.mUiElement = new InstrumentManagerUiElement(1666);
    }

    public static TaxInfoEntryFragment newInstance(TaxInfoForm[] taxInfoForms, int initialTaxInfoFormIndex, int themeResourceId) {
        if (taxInfoForms == null || taxInfoForms.length == 0) {
            throw new IllegalArgumentException("At least one tax form should be provided");
        } else if (initialTaxInfoFormIndex < 0 || initialTaxInfoFormIndex >= taxInfoForms.length) {
            throw new IllegalArgumentException("Initial tax form index: " + initialTaxInfoFormIndex + " is outside of tax forms valid range: " + "[0," + taxInfoForms.length + ")");
        } else {
            TaxInfoEntryFragment fragment = new TaxInfoEntryFragment();
            fragment.setArguments(FormFragment.createArgsForFormFragment(themeResourceId, taxInfoForms, initialTaxInfoFormIndex, TaxInfoEntryFragment.class));
            return fragment;
        }
    }

    protected View onCreateThemedView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_tax_info_entry, null, false);
        this.mTaxInfoFormSpinner = (FormSpinner) content.findViewById(R.id.tax_info_forms_spinner);
        this.mTaxInfoTextFields = (LinearLayout) content.findViewById(R.id.tax_info_fields_container);
        showCurrentTaxFormFields();
        if (this.mFormProtos.size() > 1) {
            this.mTaxInfoFormSpinner.setVisibility(0);
            int taxFormsCount = this.mFormProtos.size();
            String[] taxFormsLabels = new String[taxFormsCount];
            for (int i = 0; i < taxFormsCount; i++) {
                taxFormsLabels[i] = ((TaxInfoForm) this.mFormProtos.get(i)).label;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), R.layout.view_row_spinner, taxFormsLabels);
            adapter.setDropDownViewResource(R.layout.view_spinner_dropdown);
            this.mTaxInfoFormSpinner.setAdapter(adapter);
            this.mTaxInfoFormSpinner.setSelection(this.mCurrentFormIndex);
            this.mTaxInfoFormSpinner.setOnItemSelectedListener(this);
        }
        doEnableUi();
        return content;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if (this.mCurrentFormIndex != position) {
            this.mCurrentFormIndex = position;
            showCurrentTaxFormFields();
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void showCurrentTaxFormFields() {
        this.mTaxInfoTextFields.removeAllViews();
        UiField[] uiFields = ((TaxInfoForm) this.mFormProtos.get(this.mCurrentFormIndex)).taxInfoField;
        int length = uiFields.length;
        for (int i = 0; i < length; i++) {
            this.mTaxInfoTextFields.addView(WalletUiUtils.createFormEditTextForTextUiField(uiFields[i], i + 1, getThemedLayoutInflater()));
        }
    }

    public boolean validate() {
        return validate(true);
    }

    public boolean isValid() {
        return validate(false);
    }

    private boolean validate(boolean showErrorIfInvalid) {
        boolean valid = true;
        int length = this.mTaxInfoTextFields.getChildCount();
        for (int i = 0; i < length; i++) {
            FormEditText editText = (FormEditText) this.mTaxInfoTextFields.getChildAt(i);
            if (showErrorIfInvalid) {
                if (editText.validate() && valid) {
                    valid = true;
                } else {
                    valid = false;
                }
            } else if (!editText.isValid()) {
                return false;
            }
        }
        return valid;
    }

    public boolean focusOnFirstInvalidFormField() {
        int i = 0;
        int length = this.mTaxInfoTextFields.getChildCount();
        while (i < length) {
            FormEditText editText = (FormEditText) this.mTaxInfoTextFields.getChildAt(i);
            if (TextUtils.isEmpty(editText.getError())) {
                i++;
            } else {
                WalletUiUtils.requestFocusAndAnnounceError(editText);
                return true;
            }
        }
        return false;
    }

    public boolean applyFormFieldMessage(FormFieldMessage formFieldMessage) {
        TaxInfoForm currentTaxInfoForm = (TaxInfoForm) this.mFormProtos.get(this.mCurrentFormIndex);
        if (!formFieldMessage.formFieldReference.formId.equals(currentTaxInfoForm.id)) {
            return false;
        }
        if (formFieldMessage.formFieldReference.fieldId != 1) {
            throw new IllegalArgumentException("TaxInfoForm does not support field with id: " + formFieldMessage.formFieldReference.fieldId);
        }
        int repeatedFieldIndex = formFieldMessage.formFieldReference.repeatedFieldIndex;
        if (repeatedFieldIndex < 0 || repeatedFieldIndex >= currentTaxInfoForm.taxInfoField.length) {
            throw new IllegalArgumentException("FormFieldMessage repeatedFieldIndex: " + repeatedFieldIndex + " is out of range [0," + currentTaxInfoForm.taxInfoField.length + ")");
        }
        ((FormEditText) this.mTaxInfoTextFields.getChildAt(repeatedFieldIndex)).setError(formFieldMessage.message);
        return true;
    }

    public void doEnableUi() {
        if (this.mTaxInfoFormSpinner != null) {
            boolean uiEnabled = isUiEnabled();
            this.mTaxInfoFormSpinner.setEnabled(uiEnabled);
            int length = this.mTaxInfoTextFields.getChildCount();
            for (int i = 0; i < length; i++) {
                this.mTaxInfoTextFields.getChildAt(i).setEnabled(uiEnabled);
            }
        }
    }

    public boolean isReadyToSubmit() {
        return true;
    }

    public TaxInfoFormValue getTaxInfoFormValue() {
        TaxInfoForm selectedTaxInfoForm = (TaxInfoForm) this.mFormProtos.get(this.mCurrentFormIndex);
        int textFieldsCount = selectedTaxInfoForm.taxInfoField.length;
        TaxInfoFormValue taxInfoFormValue = new TaxInfoFormValue();
        taxInfoFormValue.taxInfoFormId = selectedTaxInfoForm.id;
        taxInfoFormValue.taxInfoValue = new UiFieldValue[textFieldsCount];
        for (int i = 0; i < textFieldsCount; i++) {
            FormEditText editText = (FormEditText) this.mTaxInfoTextFields.getChildAt(i);
            taxInfoFormValue.taxInfoValue[i] = new UiFieldValue();
            taxInfoFormValue.taxInfoValue[i].name = selectedTaxInfoForm.taxInfoField[i].name;
            taxInfoFormValue.taxInfoValue[i].stringValue = editText.getText().toString();
        }
        return taxInfoFormValue;
    }

    public InstrumentManagerUiElement getUiElement() {
        return this.mUiElement;
    }

    public List<UiNode> getChildren() {
        return null;
    }
}
