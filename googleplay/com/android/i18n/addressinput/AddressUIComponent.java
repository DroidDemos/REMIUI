package com.android.i18n.addressinput;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;

class AddressUIComponent {
    private List<RegionData> mCandidatesList;
    private String mFieldName;
    private AddressField mId;
    private AddressField mParentId;
    private UIComponent mUiType;
    private View mView;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$i18n$addressinput$AddressField;
        static final /* synthetic */ int[] $SwitchMap$com$android$i18n$addressinput$AddressUIComponent$UIComponent;

        static {
            $SwitchMap$com$android$i18n$addressinput$AddressUIComponent$UIComponent = new int[UIComponent.values().length];
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressUIComponent$UIComponent[UIComponent.SPINNER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressUIComponent$UIComponent[UIComponent.EDIT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SwitchMap$com$android$i18n$addressinput$AddressField = new int[AddressField.values().length];
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.DEPENDENT_LOCALITY.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.LOCALITY.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressField[AddressField.ADMIN_AREA.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    enum UIComponent {
        EDIT,
        SPINNER
    }

    AddressUIComponent(AddressField id) {
        this.mCandidatesList = new ArrayList();
        this.mId = id;
        this.mParentId = null;
        this.mUiType = UIComponent.EDIT;
    }

    void initializeCandidatesList(List<RegionData> candidatesList) {
        this.mCandidatesList = candidatesList;
        if (candidatesList.size() > 1) {
            this.mUiType = UIComponent.SPINNER;
            switch (AnonymousClass1.$SwitchMap$com$android$i18n$addressinput$AddressField[this.mId.ordinal()]) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    this.mParentId = AddressField.LOCALITY;
                    return;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    this.mParentId = AddressField.ADMIN_AREA;
                    return;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    this.mParentId = AddressField.COUNTRY;
                    return;
                default:
                    return;
            }
        }
    }

    String getValue() {
        if (this.mView != null) {
            switch (AnonymousClass1.$SwitchMap$com$android$i18n$addressinput$AddressUIComponent$UIComponent[this.mUiType.ordinal()]) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    Object selectedItem = ((Spinner) this.mView).getSelectedItem();
                    if (selectedItem == null) {
                        return "";
                    }
                    return selectedItem.toString();
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return ((EditText) this.mView).getText().toString();
                default:
                    return "";
            }
        } else if (this.mCandidatesList.size() == 0) {
            return "";
        } else {
            return ((RegionData) this.mCandidatesList.get(0)).getDisplayName();
        }
    }

    String getFieldName() {
        return this.mFieldName;
    }

    void setFieldName(String fieldName) {
        this.mFieldName = fieldName;
    }

    UIComponent getUIType() {
        return this.mUiType;
    }

    List<RegionData> getCandidatesList() {
        return this.mCandidatesList;
    }

    AddressField getId() {
        return this.mId;
    }

    AddressField getParentId() {
        return this.mParentId;
    }

    void setView(View view) {
        this.mView = view;
    }

    View getView() {
        return this.mView;
    }
}
