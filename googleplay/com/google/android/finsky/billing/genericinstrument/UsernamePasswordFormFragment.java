package com.google.android.finsky.billing.genericinstrument;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo;
import com.google.android.finsky.fragments.LoggingFragment;
import com.google.android.finsky.protos.BuyInstruments.CreateInstrumentRequest;
import com.google.android.finsky.protos.CreateInstrument.DeviceCreateInstrumentFlowState;
import com.google.android.finsky.protos.CreateInstrument.TosAcceptanceFormInput;
import com.google.android.finsky.protos.CreateInstrument.TosFormField;
import com.google.android.finsky.protos.CreateInstrument.UsernamePasswordForm;
import com.google.android.finsky.protos.CreateInstrument.UsernamePasswordFormInput;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.layout.PlayActionButton;
import java.security.cert.CertificateException;

public class UsernamePasswordFormFragment extends LoggingFragment implements OnClickListener {
    private final TextWatcher mEmptyTextWatcher;
    private DeviceCreateInstrumentFlowState mFlowState;
    private View mLearnMoreButton;
    private PlayActionButton mLoginButton;
    private ViewGroup mMainView;
    private EditText mPasswordEditText;
    private TextView mProblemWithLoginTextView;
    private TextView mTosTextView;
    private EditText mUsernameEditText;

    public UsernamePasswordFormFragment() {
        this.mEmptyTextWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                UsernamePasswordFormFragment.this.syncLoginButtonState();
            }
        };
    }

    public static Fragment newInstance(String accountName, DeviceCreateInstrumentFlowState flowState) {
        UsernamePasswordFormFragment fragment = new UsernamePasswordFormFragment();
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putParcelable("UsernamePasswordFlowFragment.flow_state", ParcelableProto.forProto(flowState));
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mFlowState = (DeviceCreateInstrumentFlowState) ParcelableProto.getProtoFromBundle(getArguments(), "UsernamePasswordFlowFragment.flow_state");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mMainView = (ViewGroup) inflater.inflate(R.layout.billing_generic_instrument_usernamepassword, container, false);
        UsernamePasswordForm form = this.mFlowState.usernamePasswordForm;
        FifeImageView imageView = (FifeImageView) this.mMainView.findViewById(R.id.image_icon);
        if (form.logoImage == null) {
            imageView.setVisibility(8);
        } else {
            imageView.setImage(form.logoImage.imageUrl, form.logoImage.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
        }
        ((TextView) this.mMainView.findViewById(R.id.title_text)).setText(form.actionTitleText);
        this.mUsernameEditText = (EditText) this.mMainView.findViewById(R.id.username_input);
        this.mUsernameEditText.addTextChangedListener(this.mEmptyTextWatcher);
        this.mPasswordEditText = (EditText) this.mMainView.findViewById(R.id.password_input);
        this.mPasswordEditText.addTextChangedListener(this.mEmptyTextWatcher);
        this.mPasswordEditText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView tv, int id, KeyEvent event) {
                if (id != 6) {
                    return false;
                }
                UsernamePasswordFormFragment.this.handleLoginClick(true);
                return true;
            }
        });
        this.mProblemWithLoginTextView = (TextView) this.mMainView.findViewById(R.id.problem_with_login);
        if (TextUtils.isEmpty(form.helpUrl)) {
            this.mProblemWithLoginTextView.setVisibility(8);
        } else {
            this.mProblemWithLoginTextView.setText(Html.fromHtml(getString(R.string.problem_with_login, form.helpUrl)));
            this.mProblemWithLoginTextView.setMovementMethod(LinkMovementMethod.getInstance());
            this.mProblemWithLoginTextView.setLinkTextColor(this.mProblemWithLoginTextView.getTextColors());
        }
        this.mTosTextView = (TextView) this.mMainView.findViewById(R.id.tos);
        this.mTosTextView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mTosTextView.setLinkTextColor(this.mTosTextView.getTextColors());
        this.mLearnMoreButton = this.mMainView.findViewById(R.id.learn_more_button);
        this.mLearnMoreButton.setOnClickListener(this);
        TosFormField tosField = form.tosField;
        if (TextUtils.isEmpty(tosField.tosHtml)) {
            this.mLearnMoreButton.setVisibility(8);
        }
        if (TextUtils.isEmpty(tosField.tosHtmlShort)) {
            this.mTosTextView.setVisibility(8);
        } else {
            this.mTosTextView.setText(fromHtml(tosField.tosHtmlShort));
        }
        this.mLoginButton = (PlayActionButton) this.mMainView.findViewById(R.id.positive_button);
        this.mLoginButton.configure(3, (int) R.string.login, (OnClickListener) this);
        GenericInstrumentUtils.updateEditText(this.mUsernameEditText, form.usernameField);
        GenericInstrumentUtils.updateEditText(this.mPasswordEditText, form.passwordField);
        if (this.mPasswordEditText.getInputType() != 129) {
            FinskyLog.wtf("Password field incorrect input type: %d", Integer.valueOf(this.mPasswordEditText.getInputType()));
            this.mPasswordEditText.setInputType(129);
        }
        this.mPasswordEditText.setFilters(new InputFilter[]{new LengthFilter(255)});
        return this.mMainView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            UiUtils.showKeyboard(getActivity(), this.mUsernameEditText);
        }
    }

    public void onResume() {
        super.onResume();
        syncLoginButtonState();
    }

    public void onClick(View v) {
        if (v == this.mLoginButton) {
            handleLoginClick(false);
        } else if (v == this.mLearnMoreButton) {
            logClickEvent(1531);
            this.mTosTextView.setText(fromHtml(this.mFlowState.usernamePasswordForm.tosField.tosHtml));
            this.mLearnMoreButton.setVisibility(8);
        }
    }

    private CharSequence fromHtml(String text) {
        return Utils.trim(Html.fromHtml(text));
    }

    private void syncLoginButtonState() {
        boolean isEmpty;
        boolean z = true;
        if (TextUtils.isEmpty(this.mUsernameEditText.getText()) || TextUtils.isEmpty(this.mPasswordEditText.getText())) {
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        PlayActionButton playActionButton = this.mLoginButton;
        if (isEmpty) {
            z = false;
        }
        playActionButton.setEnabled(z);
    }

    private void handleLoginClick(boolean isImeAction) {
        if (!TextUtils.isEmpty(this.mUsernameEditText.getText()) && !TextUtils.isEmpty(this.mPasswordEditText.getText())) {
            PlayStoreUiElementInfo clientLogsCookie = null;
            if (isImeAction) {
                clientLogsCookie = new PlayStoreUiElementInfo();
                clientLogsCookie.isImeAction = true;
                clientLogsCookie.hasIsImeAction = true;
            }
            logClickEvent(1532, clientLogsCookie);
            try {
                CreateInstrumentRequest request = buildCreateInstrumentRequest();
                UiUtils.hideKeyboard(getActivity(), this.mMainView);
                ((GenericInstrumentHostFragment) getParentFragment()).createInstrument(request);
            } catch (CertificateException e) {
                throw new IllegalStateException("Unable to construct CreateInstrumentRequest", e);
            }
        }
    }

    private CreateInstrumentRequest buildCreateInstrumentRequest() throws CertificateException {
        UsernamePasswordForm form = this.mFlowState.usernamePasswordForm;
        UsernamePasswordFormInput input = new UsernamePasswordFormInput();
        input.username = GenericInstrumentUtils.createTextFormInput(this.mUsernameEditText, form.usernameField);
        input.password = GenericInstrumentUtils.createTextFormInput(this.mPasswordEditText, form.passwordField);
        input.tos = new TosAcceptanceFormInput();
        input.tos.tosId = form.tosField.tosId;
        input.tos.hasTosId = true;
        CreateInstrumentRequest request = new CreateInstrumentRequest();
        request.flowHandle = this.mFlowState.handle;
        request.usernamePasswordFormInput = input;
        return request;
    }

    protected int getPlayStoreUiElementType() {
        return 1530;
    }
}
