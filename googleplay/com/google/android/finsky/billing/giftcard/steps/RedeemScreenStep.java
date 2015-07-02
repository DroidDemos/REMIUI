package com.google.android.finsky.billing.giftcard.steps;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.android.vending.R;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.giftcard.RedeemCodeFragment;
import com.google.android.finsky.billing.lightpurchase.multistep.StepFragment;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.UiUtils;

public class RedeemScreenStep extends StepFragment<RedeemCodeFragment> {
    private int mBillingUiMode;
    private EditText mCodeEntry;
    private String mErrorMessageHtml;
    private TextView mErrorMessageView;
    private View mMainView;
    private PlayStoreUiElement mPlayStoreUiElement;

    public RedeemScreenStep() {
        this.mPlayStoreUiElement = FinskyEventLog.obtainPlayStoreUiElement(881);
    }

    public static RedeemScreenStep newInstance(String accountName, String prefillCode, String errorMessageHtml, int mode) {
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putString("RedeemCodeActivity.prefill_code", prefillCode);
        args.putString("RedeemCodeFragment.error_message_html", errorMessageHtml);
        args.putInt("ui_mode", mode);
        RedeemScreenStep result = new RedeemScreenStep();
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBillingUiMode = getArguments().getInt("ui_mode");
        if (savedInstanceState != null) {
            this.mErrorMessageHtml = savedInstanceState.getString("RedeemCodeFragment.error_message_html");
        } else {
            this.mErrorMessageHtml = getArguments().getString("RedeemCodeFragment.error_message_html");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("RedeemCodeFragment.error_message_html", this.mErrorMessageHtml);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.mBillingUiMode == 1) {
            getActivity().setTitle(getString(R.string.redeem_screen_title));
        }
        this.mMainView = inflater.inflate(this.mBillingUiMode == 0 ? R.layout.redeem_screen_step : R.layout.setup_wizard_redeem_screen_step, container, false);
        this.mCodeEntry = (EditText) this.mMainView.findViewById(R.id.pin_entry);
        String prefillCode = getArguments().getString("RedeemCodeActivity.prefill_code");
        if (savedInstanceState == null && !TextUtils.isEmpty(prefillCode)) {
            this.mCodeEntry.setText(prefillCode);
        }
        ((TextView) this.mMainView.findViewById(R.id.account)).setText(getArguments().getString("authAccount"));
        String tosString = BillingUtils.replaceLocale((String) G.redeemTermsAndConditionsUrl.get());
        TextView footerView = (TextView) this.mMainView.findViewById(R.id.footer);
        footerView.setText(Html.fromHtml(getString(R.string.redeem_screen_footer, tosString)));
        footerView.setMovementMethod(LinkMovementMethod.getInstance());
        footerView.setLinkTextColor(footerView.getTextColors());
        final ColorStateList codeEntryOriginalTextColors = this.mCodeEntry.getTextColors();
        this.mCodeEntry.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                RedeemScreenStep.this.mCodeEntry.setTextColor(codeEntryOriginalTextColors);
                RedeemScreenStep.this.syncContinueButtonState();
            }
        });
        this.mCodeEntry.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int actionCode, KeyEvent keyEvent) {
                if (actionCode == 6) {
                    RedeemScreenStep.this.logClickAndRedeemCode(true);
                }
                return false;
            }
        });
        this.mErrorMessageView = (TextView) this.mMainView.findViewById(R.id.error);
        syncContinueButtonState();
        syncErrorTextView();
        return this.mMainView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            UiUtils.showKeyboard(getActivity(), this.mCodeEntry);
            if (this.mErrorMessageHtml != null) {
                fireErrorEvents();
            }
        }
    }

    private void fireErrorEvents() {
        UiUtils.playShakeAnimationIfPossible(getActivity(), this.mCodeEntry);
        UiUtils.sendAccessibilityEventWithText(this.mMainView.getContext(), this.mErrorMessageView.getText().toString(), this.mMainView);
    }

    private void syncContinueButtonState() {
        ((RedeemCodeFragment) getMultiStepFragment()).setContinueButtonEnabled(!TextUtils.isEmpty(this.mCodeEntry.getText()));
    }

    public String getContinueButtonLabel(Resources resources) {
        return resources.getString(R.string.redeem_gift_card_button);
    }

    public void onContinueButtonClicked() {
        logClickAndRedeemCode(false);
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mPlayStoreUiElement;
    }

    public void showError(String errorMessageHtml) {
        this.mErrorMessageHtml = errorMessageHtml;
        UiUtils.showKeyboard(getActivity(), this.mCodeEntry);
        syncErrorTextView();
        fireErrorEvents();
    }

    private void syncErrorTextView() {
        if (this.mMainView == null) {
            FinskyLog.wtf("Null mMainView.", new Object[0]);
        } else if (this.mErrorMessageHtml != null) {
            this.mErrorMessageView.setText(Html.fromHtml(this.mErrorMessageHtml));
            this.mErrorMessageView.setVisibility(0);
            this.mErrorMessageView.setMovementMethod(LinkMovementMethod.getInstance());
            this.mErrorMessageView.setLinkTextColor(this.mErrorMessageView.getTextColors());
            this.mCodeEntry.setTextColor(getResources().getColor(R.color.credit_card_invalid_text_color));
        } else {
            this.mErrorMessageView.setVisibility(8);
        }
    }

    private void logClickAndRedeemCode(boolean isImeAction) {
        PlayStoreUiElementInfo clientLogsCookie = null;
        if (isImeAction) {
            clientLogsCookie = new PlayStoreUiElementInfo();
            clientLogsCookie.isImeAction = true;
            clientLogsCookie.hasIsImeAction = true;
        }
        logClick(882, clientLogsCookie);
        ((RedeemCodeFragment) getMultiStepFragment()).redeem(this.mCodeEntry.getText().toString());
    }
}
