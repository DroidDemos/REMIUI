package com.google.android.wallet.instrumentmanager.pub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gsf.GservicesValue;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.analytics.UiNode;
import com.google.android.wallet.instrumentmanager.analytics.util.AnalyticsUtil;
import com.google.android.wallet.instrumentmanager.common.util.ErrorUtils;
import com.google.android.wallet.instrumentmanager.common.util.Objects;
import com.google.android.wallet.instrumentmanager.common.util.ParcelableProto;
import com.google.android.wallet.instrumentmanager.common.util.ProtoUtils;
import com.google.android.wallet.instrumentmanager.config.G;
import com.google.android.wallet.instrumentmanager.pub.analytics.CreditCardEntryAction;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;
import com.google.android.wallet.instrumentmanager.sidecar.InstrumentManagerSidecar;
import com.google.android.wallet.instrumentmanager.sidecar.SidecarFragment;
import com.google.android.wallet.instrumentmanager.sidecar.SidecarFragment.Listener;
import com.google.android.wallet.instrumentmanager.ui.common.BaseInstrumentManagerFragment;
import com.google.android.wallet.instrumentmanager.ui.common.ButtonBar;
import com.google.android.wallet.instrumentmanager.ui.common.ClickSpan;
import com.google.android.wallet.instrumentmanager.ui.common.FormEventListener;
import com.google.android.wallet.instrumentmanager.ui.common.FormFragment;
import com.google.android.wallet.instrumentmanager.ui.common.InfoMessageTextView;
import com.google.android.wallet.instrumentmanager.ui.common.TopBarView;
import com.google.android.wallet.instrumentmanager.ui.common.WalletUiUtils;
import com.google.android.wallet.instrumentmanager.ui.common.WebViewDialogFragment;
import com.google.android.wallet.instrumentmanager.ui.creditcard.AddCreditCardFragment;
import com.google.android.wallet.instrumentmanager.ui.creditcard.CreditCardExpirationDateFragment;
import com.google.android.wallet.instrumentmanager.ui.customer.CustomerFragment;
import com.google.android.wallet.instrumentmanager.ui.dcb.VerifyAssociationFragment;
import com.google.android.wallet.instrumentmanager.ui.usernamepassword.UsernamePasswordFragment;
import com.google.commerce.payments.orchestration.proto.api.integratorbuyflow.DataTokens.ActionToken;
import com.google.commerce.payments.orchestration.proto.api.integratorbuyflow.DataTokens.CommonToken;
import com.google.commerce.payments.orchestration.proto.ui.common.FormFieldReferenceOuterClass.FormFieldReference;
import com.google.commerce.payments.orchestration.proto.ui.common.ResponseContextOuterClass.ResponseContext;
import com.google.commerce.payments.orchestration.proto.ui.common.UiErrorOuterClass.FormFieldMessage;
import com.google.commerce.payments.orchestration.proto.ui.common.UiErrorOuterClass.UiError;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.InstrumentFormOuterClass.InstrumentFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.Dcb.DcbVerifyAssociationForm;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.InstrumentManagerParameters;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.Page;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.PageValue;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.RefreshPageRequest;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.RefreshPageResponse;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.SavePageRequest;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.SavePageResponse;
import com.google.protobuf.nano.MessageNano;
import java.util.ArrayList;
import java.util.List;

public final class InstrumentManagerFragment extends BaseInstrumentManagerFragment implements OnClickListener, OnFocusChangeListener, UiNode, Listener, ClickSpan.OnClickListener, FormEventListener {
    private boolean mAutoSubmitAllowed;
    ButtonBar mButtonBar;
    private CommonToken mCommonToken;
    TextView mErrorCode;
    Bundle mErrorMessageDetails;
    TextView mErrorText;
    private boolean mImpressionForPageTracked;
    private Handler mImpressionHandler;
    private boolean mInitialFocusRequired;
    private boolean mInitialFocusSet;
    private InstrumentManagerParameters mInstrumentManagerParameters;
    InstrumentManagerSidecar mInstrumentManagerSidecar;
    MessageNano mLastRequest;
    View mMainContent;
    private Page mPage;
    View mProgressBar;
    private ResponseContext mResponseContext;
    private ResultListener mResultListener;
    FormFragment mSubFormFragment;
    View mSubFormFragmentHolder;
    TopBarView mTopBarView;
    InfoMessageTextView mTopInfoText;

    public interface ResultListener {
        void onInstrumentManagerResult(int i, Bundle bundle);
    }

    public InstrumentManagerFragment() {
        this.mImpressionHandler = new Handler();
    }

    public static InstrumentManagerFragment newInstance(byte[] commonToken, byte[] actionToken, int themeResourceId) {
        if (actionToken.length <= 0) {
            throw new IllegalArgumentException("Action token is a required parameter");
        }
        InstrumentManagerFragment instance = new InstrumentManagerFragment();
        Bundle arguments = BaseInstrumentManagerFragment.createArgs(themeResourceId);
        arguments.putByteArray("commonToken", commonToken);
        arguments.putByteArray("actionToken", actionToken);
        instance.setArguments(arguments);
        return instance;
    }

    public void onDetach() {
        if (isRemoving()) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this.mInstrumentManagerSidecar).commit();
            this.mInstrumentManagerSidecar = null;
        }
        super.onDetach();
        this.mResultListener = null;
    }

    public void onCreate(Bundle savedInstanceState) {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof ResultListener) {
            this.mResultListener = (ResultListener) parentFragment;
        } else {
            this.mResultListener = (ResultListener) getActivity();
        }
        GservicesValue.init(getActivity().getApplicationContext());
        super.onCreate(savedInstanceState);
        this.mCommonToken = (CommonToken) ProtoUtils.parseFrom(getArguments().getByteArray("commonToken"), CommonToken.class);
        if (savedInstanceState == null) {
            ActionToken actionToken = (ActionToken) ProtoUtils.parseFrom(getArguments().getByteArray("actionToken"), ActionToken.class);
            ProtoUtils.log(actionToken, "actionToken=");
            this.mResponseContext = actionToken.initializeResponse.context;
            this.mPage = actionToken.initializeResponse.initialPage;
            this.mInstrumentManagerParameters = actionToken.parameters;
            return;
        }
        this.mResponseContext = (ResponseContext) ParcelableProto.getProtoFromBundle(savedInstanceState, "responseContext");
        this.mPage = (Page) ParcelableProto.getProtoFromBundle(savedInstanceState, "page");
        this.mInstrumentManagerParameters = (InstrumentManagerParameters) ParcelableProto.getProtoFromBundle(savedInstanceState, "instrumentManagerParameters");
        this.mImpressionForPageTracked = savedInstanceState.getBoolean("impressionForPageTracked");
        this.mErrorMessageDetails = savedInstanceState.getBundle("errorMessageDetails");
        this.mLastRequest = ParcelableProto.getProtoFromBundle(savedInstanceState, "lastRequest");
        this.mAutoSubmitAllowed = savedInstanceState.getBoolean("autoSubmitAllowed");
    }

    protected View onCreateThemedView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_instrument_manager, container, false);
        this.mTopBarView = (TopBarView) contentView.findViewById(R.id.top_bar);
        this.mProgressBar = contentView.findViewById(R.id.progress_bar);
        this.mMainContent = contentView.findViewById(R.id.main_content);
        this.mButtonBar = (ButtonBar) contentView.findViewById(R.id.button_bar);
        this.mButtonBar.setPositiveButtonOnClickListener(this);
        this.mButtonBar.setExpandButtonOnClickListener(this);
        this.mTopInfoText = (InfoMessageTextView) contentView.findViewById(R.id.top_info_text);
        this.mTopInfoText.setParentUiNode(this);
        this.mTopInfoText.setUrlClickListener(this);
        this.mSubFormFragmentHolder = contentView.findViewById(R.id.sub_form);
        this.mErrorText = (TextView) contentView.findViewById(R.id.error_message);
        this.mErrorCode = (TextView) contentView.findViewById(R.id.error_code);
        updateNonSubformPageUi();
        this.mSubFormFragment = (FormFragment) getChildFragmentManager().findFragmentById(R.id.sub_form);
        if (this.mSubFormFragment == null) {
            updateSubFormFragment();
        }
        if (!(savedInstanceState == null || this.mErrorMessageDetails == null)) {
            updateErrorMessageStateAndVisibility();
        }
        contentView.findViewById(R.id.focus_stealer).setOnFocusChangeListener(this);
        return contentView;
    }

    private void updateNonSubformPageUi() {
        String title;
        String positiveButtonText;
        String titleImageUri = null;
        String titleImageAltText = null;
        if (this.mErrorMessageDetails == null) {
            title = this.mPage.title;
            if (this.mPage.titleImage != null) {
                titleImageUri = this.mPage.titleImage.imageUri;
                titleImageAltText = this.mPage.titleImage.altText;
            }
            positiveButtonText = this.mPage.submitButtonText;
            this.mTopInfoText.setInfoMessage(this.mPage.topInfoMessage);
        } else {
            title = this.mErrorMessageDetails.getString("ErrorUtils.KEY_TITLE");
            positiveButtonText = this.mErrorMessageDetails.getString("ErrorUtils.KEY_ERROR_BUTTON_TEXT");
        }
        this.mTopBarView.setTitle(title, titleImageUri, titleImageAltText);
        getActivity().setTitle(title);
        this.mButtonBar.setPositiveButtonText(positiveButtonText);
        updateExpandButton();
    }

    public void onClick(View view, String url) {
        if (getFragmentManager().findFragmentByTag("InstrumentManagerFragment.webViewDialog") == null) {
            WebViewDialogFragment.newInstance(url, getThemeResourceId()).show(getFragmentManager(), "InstrumentManagerFragment.webViewDialog");
        }
    }

    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.focus_stealer && hasFocus) {
            WalletUiUtils.hideSoftKeyboard(getActivity(), v);
        }
    }

    private void updateSubFormFragment() {
        this.mTopInfoText.expand(false);
        this.mInitialFocusRequired = true;
        this.mInitialFocusSet = false;
        this.mImpressionForPageTracked = false;
        if (this.mPage.instrumentForm != null) {
            if (this.mPage.instrumentForm.creditCard != null) {
                this.mSubFormFragment = AddCreditCardFragment.newInstance(this.mPage.instrumentForm.creditCard, getThemeResourceId());
            } else if (this.mPage.instrumentForm.usernamePassword != null) {
                this.mSubFormFragment = UsernamePasswordFragment.newInstance(this.mPage.instrumentForm.usernamePassword, getThemeResourceId());
            } else if (this.mPage.instrumentForm.dcbVerifyAssociation != null) {
                this.mSubFormFragment = VerifyAssociationFragment.newInstance(this.mPage.instrumentForm.dcbVerifyAssociation, getThemeResourceId());
            } else {
                throw new IllegalStateException("No instrument form type specified");
            }
        } else if (this.mPage.creditCardExpirationDateForm != null) {
            this.mSubFormFragment = CreditCardExpirationDateFragment.newInstance(this.mPage.creditCardExpirationDateForm, getThemeResourceId());
        } else if (this.mPage.customerForm != null) {
            this.mSubFormFragment = CustomerFragment.newInstance(this.mPage.customerForm, getThemeResourceId());
        } else {
            throw new IllegalStateException("No top level form specified");
        }
        getChildFragmentManager().beginTransaction().replace(R.id.sub_form, this.mSubFormFragment).commit();
        this.mTopBarView.post(new Runnable() {
            public void run() {
                WalletUiUtils.announceForAccessibility(InstrumentManagerFragment.this.mTopBarView, InstrumentManagerFragment.this.mTopBarView.getTitle());
            }
        });
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mInstrumentManagerSidecar = (InstrumentManagerSidecar) getActivity().getSupportFragmentManager().findFragmentByTag("InstrumentManagerFragment.sidecar");
        if (this.mInstrumentManagerSidecar == null || savedInstanceState == null) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            if (this.mInstrumentManagerSidecar != null) {
                transaction.remove(this.mInstrumentManagerSidecar);
            }
            this.mInstrumentManagerSidecar = InstrumentManagerSidecar.newInstance(this.mCommonToken.androidEnvironmentConfig);
            transaction.add(this.mInstrumentManagerSidecar, "InstrumentManagerFragment.sidecar").commit();
        }
        if (savedInstanceState != null && this.mInstrumentManagerSidecar.getState() == 1) {
            updateProgressBarState(true, true, false);
        }
    }

    public void onResume() {
        super.onResume();
        this.mInstrumentManagerSidecar.setListener(this);
    }

    public void onPause() {
        super.onPause();
        this.mInstrumentManagerSidecar.setListener(null);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("responseContext", ParcelableProto.forProto(this.mResponseContext));
        outState.putParcelable("page", ParcelableProto.forProto(this.mPage));
        outState.putParcelable("instrumentManagerParameters", ParcelableProto.forProto(this.mInstrumentManagerParameters));
        outState.putBoolean("impressionForPageTracked", this.mImpressionForPageTracked);
        outState.putBundle("errorMessageDetails", this.mErrorMessageDetails);
        if (this.mLastRequest != null) {
            outState.putParcelable("lastRequest", ParcelableProto.forProto(this.mLastRequest));
        }
        outState.putBoolean("autoSubmitAllowed", this.mAutoSubmitAllowed);
    }

    public void onStateChange(SidecarFragment sidecar) {
        if (sidecar != this.mInstrumentManagerSidecar) {
            throw new IllegalArgumentException("Unexpected sidecar");
        }
        switch (this.mInstrumentManagerSidecar.getState()) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                updateProgressBarState(false, false, true);
                this.mAutoSubmitAllowed = true;
                autoSubmitIfReadyAndAllowed();
                return;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                updateProgressBarState(true, false, true);
                return;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                onSuccessfulResponse();
                updateProgressBarState(false, false, true);
                return;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                onErrorResponse();
                updateProgressBarState(false, false, true);
                return;
            default:
                return;
        }
    }

    private void onSuccessfulResponse() {
        SavePageResponse savePageResponse = this.mInstrumentManagerSidecar.getSavePageResponse();
        RefreshPageResponse refreshPageResponse = this.mInstrumentManagerSidecar.getRefreshPageResponse();
        if (savePageResponse != null) {
            if (savePageResponse.flowComplete) {
                Bundle data = new Bundle();
                if (!TextUtils.isEmpty(savePageResponse.instrumentId)) {
                    data.putString("com.google.android.wallet.instrumentmanager.INSTRUMENT_ID", savePageResponse.instrumentId);
                }
                this.mResultListener.onInstrumentManagerResult(50, data);
            } else if (savePageResponse.nextPage != null) {
                this.mAutoSubmitAllowed = true;
                this.mResponseContext = savePageResponse.context;
                this.mPage = savePageResponse.nextPage;
                updateNonSubformPageUi();
                updateSubFormFragment();
            } else {
                throw new IllegalStateException("SavePageResponse flowComplete flag was not set, but no error or next page was found.");
            }
        } else if (refreshPageResponse != null) {
            this.mAutoSubmitAllowed = true;
            this.mResponseContext = refreshPageResponse.context;
            this.mPage = refreshPageResponse.nextPage;
            updateNonSubformPageUi();
            updateSubFormFragment();
        } else {
            throw new IllegalStateException("Sidecar successful but no response was found");
        }
    }

    private void onErrorResponse() {
        SavePageResponse savePageResponse = this.mInstrumentManagerSidecar.getSavePageResponse();
        RefreshPageResponse refreshPageResponse = this.mInstrumentManagerSidecar.getRefreshPageResponse();
        if (savePageResponse != null) {
            this.mResponseContext = savePageResponse.context;
        } else if (refreshPageResponse != null) {
            this.mResponseContext = refreshPageResponse.context;
        }
        switch (this.mInstrumentManagerSidecar.getSubstate()) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                showErrorMessage(ErrorUtils.addErrorDetailsToBundle(new Bundle(), 2, getString(R.string.wallet_im_error_title), getString(R.string.wallet_im_unknown_error), null, getString(17039370)));
                return;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                showErrorMessage(ErrorUtils.addErrorDetailsToBundle(new Bundle(), 500, getString(R.string.wallet_im_network_error_title), getString(R.string.wallet_im_network_error_message), null, getString(R.string.wallet_im_retry)));
                return;
            case R.styleable.WalletImFormEditText_required /*4*/:
                FormFieldMessage[] arr$ = savePageResponse.error.formFieldMessage;
                int len$ = arr$.length;
                int i$ = 0;
                while (i$ < len$) {
                    FormFieldMessage message = arr$[i$];
                    if (this.mSubFormFragment.applyFormFieldMessage(message)) {
                        i$++;
                    } else {
                        throw new IllegalArgumentException("FormFieldMessage form not found: " + message.formFieldReference.formId);
                    }
                }
                AnalyticsUtil.createAndSendImpressionEvent(this.mSubFormFragment, 1623);
                this.mSubFormFragment.focusOnFirstInvalidFormField();
                return;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                UiError error;
                if (savePageResponse != null) {
                    error = savePageResponse.error;
                } else {
                    error = refreshPageResponse.error;
                }
                showErrorMessage(ErrorUtils.addErrorDetailsToBundle(new Bundle(), error.action, getString(R.string.wallet_im_error_title), error.message, error.errorCode, getString(17039370)));
                return;
            case 1000:
                showErrorMessage(ErrorUtils.addErrorDetailsToBundle(new Bundle(), 500, getString(R.string.wallet_im_error_title), getString(R.string.wallet_im_send_sms_for_dcb_error), null, getString(R.string.wallet_im_retry)));
                return;
            default:
                throw new IllegalArgumentException("Unknown sidecar substate: " + this.mInstrumentManagerSidecar.getSubstate());
        }
    }

    private void updateProgressBarState(boolean shown, boolean triggeredByConfigChange, boolean animate) {
        boolean z;
        boolean z2 = true;
        if (this.mProgressBar.getVisibility() == 0) {
            z = true;
        } else {
            z = false;
        }
        if (shown != z) {
            this.mSubFormFragment.enableUi(!shown);
            ButtonBar buttonBar = this.mButtonBar;
            if (shown || !this.mSubFormFragment.isReadyToSubmit()) {
                z = false;
            } else {
                z = true;
            }
            buttonBar.setPositiveButtonEnabled(z);
            InfoMessageTextView infoMessageTextView = this.mTopInfoText;
            if (shown) {
                z = false;
            } else {
                z = true;
            }
            infoMessageTextView.setEnabled(z);
            ButtonBar buttonBar2 = this.mButtonBar;
            if (shown) {
                z2 = false;
            }
            buttonBar2.setExpandButtonEnabled(z2);
            if (!triggeredByConfigChange && shown) {
                AnalyticsUtil.createAndSendImpressionEvent(this.mSubFormFragment, 1625);
            }
            if (animate) {
                if (shown) {
                    WalletUiUtils.hideSoftKeyboard(getActivity(), this.mMainContent);
                    WalletUiUtils.animateViewAppearing(this.mProgressBar, 0, 0);
                    WalletUiUtils.animateViewDisappearingToInvisible(this.mMainContent, 0, 0);
                    return;
                }
                WalletUiUtils.animateViewDisappearingToInvisible(this.mProgressBar, 0, 0);
                WalletUiUtils.animateViewAppearing(this.mMainContent, 0, 0);
            } else if (shown) {
                this.mProgressBar.setVisibility(0);
                this.mMainContent.setVisibility(4);
            } else {
                this.mProgressBar.setVisibility(4);
                this.mMainContent.setVisibility(0);
            }
        }
    }

    private void updateErrorMessageStateAndVisibility() {
        if (this.mErrorMessageDetails == null) {
            this.mTopInfoText.setVisibility(0);
            this.mSubFormFragmentHolder.setVisibility(0);
            this.mErrorText.setVisibility(8);
            this.mErrorCode.setVisibility(8);
            return;
        }
        this.mErrorText.setText(Html.fromHtml(this.mErrorMessageDetails.getString("ErrorUtils.KEY_ERROR_MESSAGE")));
        this.mErrorText.setMovementMethod(LinkMovementMethod.getInstance());
        String errorCode = this.mErrorMessageDetails.getString("ErrorUtils.KEY_ERROR_CODE");
        if (!TextUtils.isEmpty(errorCode)) {
            this.mErrorCode.setText(errorCode);
        }
        this.mTopInfoText.setVisibility(8);
        this.mSubFormFragmentHolder.setVisibility(8);
        this.mErrorText.setVisibility(0);
        this.mErrorCode.setVisibility(0);
    }

    public void notifyFormEvent(int eventType, Bundle eventDetails) {
        switch (eventType) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mButtonBar.setPositiveButtonEnabled(this.mSubFormFragment.isReadyToSubmit());
                autoSubmitIfReadyAndAllowed();
                return;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (this.mInstrumentManagerSidecar.getState() != 1) {
                    String triggeringFormId = eventDetails.getString("FormEventListener.EXTRA_FORM_ID");
                    int triggeringFieldId = eventDetails.getInt("FormEventListener.EXTRA_FIELD_ID");
                    for (FormFieldReference formFieldReference : this.mPage.refreshTriggerField) {
                        if (triggeringFieldId == formFieldReference.fieldId && Objects.equals(triggeringFormId, formFieldReference.formId)) {
                            refreshPage(constructRefreshPageRequest(formFieldReference));
                            return;
                        }
                    }
                    return;
                }
                return;
            case R.styleable.WalletImFormEditText_required /*4*/:
                trackImpressionForPageIfNecessary();
                if (this.mInitialFocusRequired && !this.mInitialFocusSet) {
                    this.mInitialFocusSet = WalletUiUtils.showSoftKeyboardOnFirstEditText(this.mMainContent);
                    return;
                }
                return;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                if (this.mInstrumentManagerSidecar.getState() != 1) {
                    showErrorMessage(eventDetails);
                    return;
                }
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                updateExpandButton();
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                int backgroundEventType = eventDetails.getInt("FormEventListener.EXTRA_BACKGROUND_EVENT_TYPE");
                switch (backgroundEventType) {
                    case 770:
                        CreditCardEntryAction creditCardEntryAction = (CreditCardEntryAction) eventDetails.getParcelable("FormEventListener.EXTRA_BACKGROUND_EVENT_DATA");
                        if (creditCardEntryAction == null) {
                            throw new IllegalArgumentException("CreditCardEntryAction background events must include a CreditCardEntryAction");
                        }
                        AnalyticsUtil.createAndSendCreditCardEntryActionBackgroundEvent(creditCardEntryAction, this.mResponseContext.logToken);
                        return;
                    default:
                        throw new IllegalArgumentException("Unknown analytics background event type: " + backgroundEventType);
                }
            default:
                throw new IllegalArgumentException("Unknown formEvent: " + eventType);
        }
    }

    private void updateExpandButton() {
        if (this.mErrorMessageDetails == null && this.mSubFormFragment != null && this.mSubFormFragment.shouldShowButtonBarExpandButton()) {
            this.mButtonBar.showExpandButton(true);
            this.mButtonBar.setExpandButtonText(this.mSubFormFragment.getButtonBarExpandButtonText());
            return;
        }
        this.mButtonBar.showExpandButton(false);
    }

    private void trackImpressionForPageIfNecessary() {
        if (!this.mImpressionForPageTracked) {
            this.mImpressionHandler.removeCallbacksAndMessages(null);
            this.mImpressionHandler.postDelayed(new Runnable() {
                public void run() {
                    if (!InstrumentManagerFragment.this.mImpressionForPageTracked) {
                        InstrumentManagerFragment.this.mImpressionForPageTracked = true;
                        AnalyticsUtil.createAndSendImpressionEvent(InstrumentManagerFragment.this);
                    }
                }
            }, (long) ((Integer) G.pageImpressionDelayBeforeTrackingMs.get()).intValue());
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment childFragment = getChildFragmentManager().findFragmentById(R.id.sub_form);
        if (childFragment != null) {
            childFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void showErrorMessage(Bundle errorMessageDetails) {
        this.mErrorMessageDetails = errorMessageDetails;
        updateNonSubformPageUi();
        updateErrorMessageStateAndVisibility();
        WalletUiUtils.hideSoftKeyboard(getActivity(), this.mMainContent);
        WalletUiUtils.announceForAccessibility(this.mErrorText, this.mErrorText.getText());
        AnalyticsUtil.createAndSendImpressionEvent(this.mSubFormFragment, 1626);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.positive_btn) {
            if (this.mErrorMessageDetails == null) {
                AnalyticsUtil.createAndSendClickEvent(this.mSubFormFragment, 1621);
                submitPage();
                return;
            }
            String formId = this.mErrorMessageDetails.getString("FormEventListener.EXTRA_FORM_ID");
            int errorType = this.mErrorMessageDetails.getInt("ErrorUtils.KEY_TYPE");
            this.mErrorMessageDetails = null;
            updateErrorMessageStateAndVisibility();
            updateNonSubformPageUi();
            if (formId == null) {
                switch (errorType) {
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        return;
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        this.mResultListener.onInstrumentManagerResult(52, Bundle.EMPTY);
                        return;
                    case 500:
                        retryLastRequest();
                        return;
                    default:
                        throw new IllegalArgumentException("Unknown errorType: " + errorType);
                }
            } else if (!this.mSubFormFragment.handleErrorMessageDismissed(formId, errorType)) {
                throw new IllegalArgumentException("Form to handle error message not found: " + formId);
            }
        } else if (view.getId() == R.id.expand_btn) {
            this.mButtonBar.showExpandButton(false);
            this.mSubFormFragment.onButtonBarExpandButtonClicked();
        }
    }

    private void submitPage() {
        if (this.mSubFormFragment.validate()) {
            savePage(constructSavePageRequest());
            return;
        }
        AnalyticsUtil.createAndSendImpressionEvent(this.mSubFormFragment, 1623);
        this.mSubFormFragment.focusOnFirstInvalidFormField();
    }

    private SavePageRequest constructSavePageRequest() {
        SavePageRequest request = new SavePageRequest();
        request.parameters = this.mInstrumentManagerParameters;
        request.pageValue = getPageValue();
        return request;
    }

    private PageValue getPageValue() {
        PageValue pageValue = new PageValue();
        if (this.mSubFormFragment instanceof AddCreditCardFragment) {
            pageValue.newInstrument = new InstrumentFormValue();
            pageValue.newInstrument.creditCard = ((AddCreditCardFragment) this.mSubFormFragment).getCreditCardFormValue();
        } else if (this.mSubFormFragment instanceof VerifyAssociationFragment) {
            pageValue.newInstrument = new InstrumentFormValue();
            pageValue.newInstrument.dcbVerifyAssociation = ((VerifyAssociationFragment) this.mSubFormFragment).getDcbVerifyAssociationFormValue();
        } else if (this.mSubFormFragment instanceof CreditCardExpirationDateFragment) {
            pageValue.newCreditCardExpirationDate = ((CreditCardExpirationDateFragment) this.mSubFormFragment).getCreditCardExpirationDateFormValue();
        } else if (this.mSubFormFragment instanceof CustomerFragment) {
            pageValue.newCustomer = ((CustomerFragment) this.mSubFormFragment).getCustomerFormValue();
        } else if (this.mSubFormFragment instanceof UsernamePasswordFragment) {
            pageValue.newInstrument = new InstrumentFormValue();
            pageValue.newInstrument.usernamePassword = ((UsernamePasswordFragment) this.mSubFormFragment).getUsernamePasswordFormValue();
        } else {
            throw new IllegalStateException("Unknown top level form");
        }
        return pageValue;
    }

    private RefreshPageRequest constructRefreshPageRequest(FormFieldReference refreshTriggerField) {
        RefreshPageRequest refreshPageRequest = new RefreshPageRequest();
        refreshPageRequest.pageValue = getPageValue();
        refreshPageRequest.refreshTriggerField = (FormFieldReference) ProtoUtils.copyFrom(refreshTriggerField);
        return refreshPageRequest;
    }

    public UiNode getParentUiNode() {
        return null;
    }

    public InstrumentManagerUiElement getUiElement() {
        return new InstrumentManagerUiElement(1620, this.mResponseContext.logToken);
    }

    public List<UiNode> getChildren() {
        ArrayList<UiNode> children = new ArrayList();
        if (this.mPage.topInfoMessage != null) {
            children.add(this.mTopInfoText);
        }
        children.add(this.mSubFormFragment);
        return children;
    }

    private void autoSubmitIfReadyAndAllowed() {
        if (this.mAutoSubmitAllowed && this.mSubFormFragment.shouldAutoSubmit() && this.mSubFormFragment.isReadyToSubmit()) {
            this.mAutoSubmitAllowed = false;
            updateProgressBarState(true, false, false);
            submitPage();
        }
    }

    private void retryLastRequest() {
        if (this.mLastRequest instanceof SavePageRequest) {
            SavePageRequest savePageRequest = this.mLastRequest;
            savePageRequest.pageValue = getPageValue();
            savePage(savePageRequest);
        } else if (this.mLastRequest instanceof RefreshPageRequest) {
            RefreshPageRequest refreshPageRequest = this.mLastRequest;
            refreshPageRequest.pageValue = getPageValue();
            refreshPage(refreshPageRequest);
        } else {
            throw new IllegalStateException("retryLastRequest() called with invalid last request. Unexpected request class: " + (this.mLastRequest != null ? this.mLastRequest.getClass().getName() : null));
        }
    }

    private void savePage(SavePageRequest savePageRequest) {
        this.mLastRequest = savePageRequest;
        if (this.mPage.instrumentForm == null || this.mPage.instrumentForm.dcbVerifyAssociation == null) {
            this.mInstrumentManagerSidecar.savePage(savePageRequest, this.mResponseContext);
            return;
        }
        DcbVerifyAssociationForm dcbVerifyAssociationForm = this.mPage.instrumentForm.dcbVerifyAssociation;
        this.mInstrumentManagerSidecar.sendSmsAndSavePage(dcbVerifyAssociationForm.smsPhoneNumber, dcbVerifyAssociationForm.smsMessage, savePageRequest, this.mResponseContext);
    }

    private void refreshPage(RefreshPageRequest refreshPageRequest) {
        this.mLastRequest = refreshPageRequest;
        this.mInstrumentManagerSidecar.refreshPage(refreshPageRequest, this.mResponseContext);
    }
}
