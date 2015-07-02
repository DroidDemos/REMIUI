package com.google.android.wallet.instrumentmanager.ui.dcb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.analytics.UiNode;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;
import com.google.android.wallet.instrumentmanager.ui.common.ClickSpan.OnClickListener;
import com.google.android.wallet.instrumentmanager.ui.common.FormFragment;
import com.google.android.wallet.instrumentmanager.ui.common.InfoMessageTextView;
import com.google.android.wallet.instrumentmanager.ui.common.WebViewDialogFragment;
import com.google.commerce.payments.orchestration.proto.ui.common.UiErrorOuterClass.FormFieldMessage;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.Dcb.DcbVerifyAssociationForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.Dcb.DcbVerifyAssociationFormValue;
import java.util.Collections;
import java.util.List;

public class VerifyAssociationFragment extends FormFragment<DcbVerifyAssociationForm> implements OnClickListener {
    InfoMessageTextView mRequiredMessage;
    private final InstrumentManagerUiElement mUiElement;

    public VerifyAssociationFragment() {
        this.mUiElement = new InstrumentManagerUiElement(1700);
    }

    public static VerifyAssociationFragment newInstance(DcbVerifyAssociationForm verifyAssociationForm, int themeResourceId) {
        VerifyAssociationFragment fragment = new VerifyAssociationFragment();
        fragment.setArguments(FormFragment.createArgsForFormFragment(themeResourceId, verifyAssociationForm, VerifyAssociationFragment.class));
        return fragment;
    }

    protected View onCreateThemedView(LayoutInflater themedInflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup content = (ViewGroup) themedInflater.inflate(R.layout.fragment_dcb_verify_association, null);
        this.mRequiredMessage = (InfoMessageTextView) content.findViewById(R.id.required_message_text);
        if (((DcbVerifyAssociationForm) this.mFormProto).requiredMessage != null) {
            this.mRequiredMessage.setVisibility(0);
            this.mRequiredMessage.setParentUiNode(this);
            this.mRequiredMessage.setUrlClickListener(this);
            this.mRequiredMessage.setInfoMessage(((DcbVerifyAssociationForm) this.mFormProto).requiredMessage.messageText);
        }
        doEnableUi();
        notifyFormEvent(1, Bundle.EMPTY);
        return content;
    }

    public boolean focusOnFirstInvalidFormField() {
        return false;
    }

    public boolean applyFormFieldMessage(FormFieldMessage formFieldMessage) {
        return false;
    }

    public void doEnableUi() {
        if (this.mRequiredMessage != null) {
            this.mRequiredMessage.setEnabled(isUiEnabled());
        }
    }

    public boolean isReadyToSubmit() {
        return true;
    }

    public boolean validate() {
        return true;
    }

    public boolean isValid() {
        return true;
    }

    public boolean shouldAutoSubmit() {
        return this.mRequiredMessage.getVisibility() != 0;
    }

    public InstrumentManagerUiElement getUiElement() {
        return this.mUiElement;
    }

    public List<UiNode> getChildren() {
        if (this.mRequiredMessage.getVisibility() == 0) {
            return Collections.singletonList(this.mRequiredMessage);
        }
        return null;
    }

    public void onClick(View view, String url) {
        if (view == this.mRequiredMessage && getFragmentManager().findFragmentByTag("tagTosWebViewDialog") == null) {
            WebViewDialogFragment.newInstance(url, getThemeResourceId()).show(getFragmentManager(), "tagTosWebViewDialog");
        }
    }

    public boolean shouldShowButtonBarExpandButton() {
        return !this.mRequiredMessage.isExpanded();
    }

    public void onButtonBarExpandButtonClicked() {
        this.mRequiredMessage.expand(true);
    }

    public String getButtonBarExpandButtonText() {
        return this.mRequiredMessage.getExpandLabel();
    }

    public DcbVerifyAssociationFormValue getDcbVerifyAssociationFormValue() {
        DcbVerifyAssociationFormValue formValue = new DcbVerifyAssociationFormValue();
        if (((DcbVerifyAssociationForm) this.mFormProto).requiredMessage != null) {
            formValue.legalDocData = ((DcbVerifyAssociationForm) this.mFormProto).requiredMessage.opaqueData;
        }
        return formValue;
    }
}
