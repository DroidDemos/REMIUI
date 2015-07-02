package com.google.android.finsky.billing.lightpurchase.ageverification;

import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.fragments.LoggingFragment;
import com.google.android.finsky.protos.ChallengeProto.SmsCodeChallenge;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.layout.PlayActionButton;

public class SmsCodeFragment extends LoggingFragment implements OnClickListener {
    private SmsCodeChallenge mChallenge;
    private EditText mCodeEntry;
    private View mMainView;
    private PlayActionButton mResendButton;
    private PlayActionButton mSubmitButton;

    public interface Listener {
        void onResendSmsCode(String str);

        void onVerifySmsCode(String str, String str2, String str3);
    }

    public static SmsCodeFragment newInstance(String accountName, int backend, SmsCodeChallenge challenge) {
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putInt("SmsCodeFragment.backend", backend);
        args.putParcelable("SmsCodeFragment.challenge", ParcelableProto.forProto(challenge));
        SmsCodeFragment result = new SmsCodeFragment();
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mChallenge = (SmsCodeChallenge) ParcelableProto.getProtoFromBundle(getArguments(), "SmsCodeFragment.challenge");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mMainView = inflater.inflate(R.layout.age_verification_sms_code_fragment, container, false);
        TextView title = (TextView) this.mMainView.findViewById(R.id.title);
        if (TextUtils.isEmpty(this.mChallenge.title)) {
            throw new IllegalStateException("No title returned");
        }
        title.setText(this.mChallenge.title);
        TextView description = (TextView) this.mMainView.findViewById(R.id.description);
        if (TextUtils.isEmpty(this.mChallenge.descriptionHtml)) {
            description.setVisibility(8);
        } else {
            description.setText(Html.fromHtml(this.mChallenge.descriptionHtml));
        }
        this.mCodeEntry = (EditText) this.mMainView.findViewById(R.id.code_entry);
        if (this.mChallenge.smsCode != null) {
            if (!TextUtils.isEmpty(this.mChallenge.smsCode.hint)) {
                this.mCodeEntry.setHint(this.mChallenge.smsCode.hint);
            }
            if (!TextUtils.isEmpty(this.mChallenge.smsCode.defaultValue)) {
                this.mCodeEntry.setText(this.mChallenge.smsCode.defaultValue);
            }
            this.mCodeEntry.addTextChangedListener(new TextWatcher() {
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void afterTextChanged(Editable s) {
                    SmsCodeFragment.this.syncSubmitButtonState();
                }
            });
            TextView errorMessageView = (TextView) this.mMainView.findViewById(R.id.error);
            if (TextUtils.isEmpty(this.mChallenge.smsCode.error)) {
                errorMessageView.setVisibility(8);
            } else {
                errorMessageView.setText(this.mChallenge.smsCode.error);
            }
            int backend = getArguments().getInt("SmsCodeFragment.backend");
            this.mSubmitButton = (PlayActionButton) this.mMainView.findViewById(R.id.positive_button);
            if (this.mChallenge.submitButton == null || TextUtils.isEmpty(this.mChallenge.submitButton.label)) {
                throw new IllegalStateException("No submit button returned.");
            }
            this.mSubmitButton.configure(backend, this.mChallenge.submitButton.label, (OnClickListener) this);
            this.mResendButton = (PlayActionButton) this.mMainView.findViewById(R.id.negative_button);
            if (this.mChallenge.resendCodeButton == null || TextUtils.isEmpty(this.mChallenge.resendCodeButton.label)) {
                this.mResendButton.setVisibility(8);
            } else {
                this.mResendButton.configure(backend, this.mChallenge.resendCodeButton.label, (OnClickListener) this);
            }
            syncSubmitButtonState();
            return this.mMainView;
        }
        throw new IllegalStateException("No SMS code field returned.");
    }

    public void onResume() {
        super.onResume();
        UiUtils.sendAccessibilityEventWithText(this.mMainView.getContext(), this.mChallenge.title, this.mMainView);
    }

    private void syncSubmitButtonState() {
        this.mSubmitButton.setEnabled(!Utils.isEmptyOrSpaces(this.mCodeEntry.getText()));
    }

    private Listener getListener() {
        if (getTargetFragment() instanceof Listener) {
            return (Listener) getTargetFragment();
        }
        if (getParentFragment() instanceof Listener) {
            return (Listener) getParentFragment();
        }
        if (getActivity() instanceof Listener) {
            return (Listener) getActivity();
        }
        throw new IllegalStateException("No listener registered.");
    }

    protected int getPlayStoreUiElementType() {
        return 1403;
    }

    public void onClick(View v) {
        if (v == this.mResendButton) {
            logClickEvent(1405);
            getListener().onResendSmsCode(this.mChallenge.resendCodeButton.actionUrl);
        } else if (v == this.mSubmitButton) {
            logClickEvent(1408);
            getListener().onVerifySmsCode(this.mChallenge.submitButton.actionUrl, this.mChallenge.smsCode.postParam, this.mCodeEntry.getText().toString());
        }
    }
}
