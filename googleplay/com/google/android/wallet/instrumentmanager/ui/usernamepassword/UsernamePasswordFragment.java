package com.google.android.wallet.instrumentmanager.ui.usernamepassword;

import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.analytics.UiNode;
import com.google.android.wallet.instrumentmanager.analytics.util.AnalyticsUtil;
import com.google.android.wallet.instrumentmanager.common.util.PaypalPasswordEncryptor;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;
import com.google.android.wallet.instrumentmanager.ui.common.ClickSpan;
import com.google.android.wallet.instrumentmanager.ui.common.FormEditText;
import com.google.android.wallet.instrumentmanager.ui.common.FormFragment;
import com.google.android.wallet.instrumentmanager.ui.common.InfoMessageTextView;
import com.google.android.wallet.instrumentmanager.ui.common.WalletUiUtils;
import com.google.android.wallet.instrumentmanager.ui.common.WebViewDialogFragment;
import com.google.commerce.payments.orchestration.proto.ui.common.UiErrorOuterClass.FormFieldMessage;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.UsernamePassword.UsernamePasswordForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.UsernamePassword.UsernamePasswordFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiFieldValue;
import java.security.cert.CertificateException;
import java.util.Collections;
import java.util.List;

public class UsernamePasswordFragment extends FormFragment<UsernamePasswordForm> implements TextWatcher, OnClickListener, ClickSpan.OnClickListener {
    InfoMessageTextView mLegalMessageText;
    TextView mLoginHelpText;
    FormEditText mPasswordText;
    private final InstrumentManagerUiElement mUiElement;
    FormEditText mUsernameText;

    public UsernamePasswordFragment() {
        this.mUiElement = new InstrumentManagerUiElement(1680);
    }

    public static UsernamePasswordFragment newInstance(UsernamePasswordForm usernamePasswordForm, int themeResourceId) {
        UsernamePasswordFragment fragment = new UsernamePasswordFragment();
        fragment.setArguments(FormFragment.createArgsForFormFragment(themeResourceId, usernamePasswordForm, UsernamePasswordFragment.class));
        return fragment;
    }

    protected View onCreateThemedView(LayoutInflater themedInflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup content = (ViewGroup) themedInflater.inflate(R.layout.fragment_username_password, null, false);
        if (!TextUtils.isEmpty(((UsernamePasswordForm) this.mFormProto).title)) {
            TextView titleText = (TextView) content.findViewById(R.id.username_password_title);
            titleText.setText(((UsernamePasswordForm) this.mFormProto).title);
            titleText.setVisibility(0);
        }
        this.mUsernameText = (FormEditText) content.findViewById(R.id.username);
        WalletUiUtils.applyUiFieldSpecificationToFormEditText(((UsernamePasswordForm) this.mFormProto).usernameField, this.mUsernameText);
        this.mUsernameText.addTextChangedListener(this);
        this.mPasswordText = (FormEditText) content.findViewById(R.id.password);
        WalletUiUtils.applyUiFieldSpecificationToFormEditText(((UsernamePasswordForm) this.mFormProto).passwordField, this.mPasswordText);
        this.mPasswordText.addTextChangedListener(this);
        this.mLoginHelpText = (TextView) content.findViewById(R.id.login_help_text);
        if (TextUtils.isEmpty(((UsernamePasswordForm) this.mFormProto).loginHelpHtml)) {
            this.mLoginHelpText.setVisibility(8);
        } else {
            this.mLoginHelpText.setText(Html.fromHtml(((UsernamePasswordForm) this.mFormProto).loginHelpHtml));
            this.mLoginHelpText.setMovementMethod(LinkMovementMethod.getInstance());
            this.mLoginHelpText.setOnClickListener(this);
        }
        this.mLegalMessageText = (InfoMessageTextView) content.findViewById(R.id.legal_message_text);
        this.mLegalMessageText.setParentUiNode(this);
        this.mLegalMessageText.setUrlClickListener(this);
        if (((UsernamePasswordForm) this.mFormProto).legalMessage != null) {
            this.mLegalMessageText.setInfoMessage(((UsernamePasswordForm) this.mFormProto).legalMessage.messageText);
        }
        doEnableUi();
        notifyFormEvent(1, Bundle.EMPTY);
        return content;
    }

    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        notifyFormEvent(6, Bundle.EMPTY);
    }

    public boolean focusOnFirstInvalidFormField() {
        FormEditText[] arr$ = new FormEditText[]{this.mUsernameText, this.mPasswordText};
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            FormEditText fieldToValidate = arr$[i$];
            if (TextUtils.isEmpty(fieldToValidate.getError())) {
                i$++;
            } else {
                WalletUiUtils.requestFocusAndAnnounceError(fieldToValidate);
                return true;
            }
        }
        return false;
    }

    public boolean applyFormFieldMessage(FormFieldMessage formFieldMessage) {
        if (!formFieldMessage.formFieldReference.formId.equals(((UsernamePasswordForm) this.mFormProto).id)) {
            return false;
        }
        switch (formFieldMessage.formFieldReference.fieldId) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mUsernameText.setError(formFieldMessage.message);
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                this.mPasswordText.setError(formFieldMessage.message);
                break;
            default:
                throw new IllegalArgumentException("Unknown FormFieldMessage fieldId: " + formFieldMessage.formFieldReference.fieldId);
        }
        return true;
    }

    public void doEnableUi() {
        if (this.mUsernameText != null) {
            boolean uiEnabled = isUiEnabled();
            this.mUsernameText.setEnabled(uiEnabled);
            this.mPasswordText.setEnabled(uiEnabled);
        }
    }

    public boolean isReadyToSubmit() {
        return (this.mUsernameText == null || TextUtils.isEmpty(this.mUsernameText.getText()) || this.mPasswordText == null || TextUtils.isEmpty(this.mPasswordText.getText())) ? false : true;
    }

    public boolean validate() {
        return validate(true);
    }

    public boolean isValid() {
        return validate(false);
    }

    private boolean validate(boolean showErrorIfInvalid) {
        boolean valid = true;
        for (FormEditText fieldToValidate : new FormEditText[]{this.mUsernameText, this.mPasswordText}) {
            if (showErrorIfInvalid) {
                if (fieldToValidate.validate() && valid) {
                    valid = true;
                } else {
                    valid = false;
                }
            } else if (!fieldToValidate.isValid()) {
                return false;
            }
        }
        return valid;
    }

    public InstrumentManagerUiElement getUiElement() {
        return this.mUiElement;
    }

    public List<UiNode> getChildren() {
        if (((UsernamePasswordForm) this.mFormProto).legalMessage != null) {
            return Collections.singletonList(this.mLegalMessageText);
        }
        return null;
    }

    public boolean shouldShowButtonBarExpandButton() {
        return !this.mLegalMessageText.isExpanded();
    }

    public void onButtonBarExpandButtonClicked() {
        this.mLegalMessageText.expand(true);
    }

    public String getButtonBarExpandButtonText() {
        return this.mLegalMessageText.getExpandLabel();
    }

    public void onClick(View view, String url) {
        if (view == this.mLegalMessageText && getFragmentManager().findFragmentByTag("tagTosWebViewDialog") == null) {
            WebViewDialogFragment.newInstance(url, getThemeResourceId()).show(getFragmentManager(), "tagTosWebViewDialog");
        }
    }

    public void onClick(View view) {
        if (view == this.mLoginHelpText) {
            AnalyticsUtil.createAndSendClickEvent(this, 1681);
        }
    }

    public UsernamePasswordFormValue getUsernamePasswordFormValue() {
        UsernamePasswordFormValue formValue = new UsernamePasswordFormValue();
        formValue.username = new UiFieldValue();
        formValue.username.name = ((UsernamePasswordForm) this.mFormProto).usernameField.name;
        formValue.username.stringValue = this.mUsernameText.getText().toString();
        formValue.password = new UiFieldValue();
        formValue.password.name = ((UsernamePasswordForm) this.mFormProto).passwordField.name;
        switch (((UsernamePasswordForm) this.mFormProto).encryptionType) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                formValue.password.stringValue = this.mPasswordText.getText().toString();
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                PaypalPasswordEncryptor paypalPasswordEncryptor = new PaypalPasswordEncryptor(getActivity(), ((UsernamePasswordForm) this.mFormProto).vendorSpecificSalt);
                try {
                    formValue.password.stringValue = paypalPasswordEncryptor.encryptPassword(((UsernamePasswordForm) this.mFormProto).credentialsEncryptionKey, this.mPasswordText.getText().toString());
                    formValue.hashedDeviceId = paypalPasswordEncryptor.getHashedDeviceId();
                    break;
                } catch (CertificateException e) {
                    throw new IllegalStateException("Unable to encrypt user PayPal credentials", e);
                }
            default:
                throw new IllegalArgumentException("Unsupported encryption type: " + ((UsernamePasswordForm) this.mFormProto).encryptionType);
        }
        formValue.encryptionType = ((UsernamePasswordForm) this.mFormProto).encryptionType;
        if (((UsernamePasswordForm) this.mFormProto).legalMessage != null) {
            formValue.legalDocData = ((UsernamePasswordForm) this.mFormProto).legalMessage.opaqueData;
        }
        return formValue;
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public void afterTextChanged(Editable editable) {
        notifyFormEvent(1, Bundle.EMPTY);
    }
}
