package com.google.android.finsky.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.utils.UiUtils;

public class PinEntryDialog extends FragmentActivity implements ClickListener {
    private ButtonBar mButtonBar;
    private FinskyEventLog mEventLogger;
    private String mMatchPin;
    private GenericUiElementNode mNode;
    private EditText mPinEntryView;
    private TextWatcher mPinWatcher;

    public PinEntryDialog() {
        this.mNode = new GenericUiElementNode(311, null, null, null);
        this.mPinWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                PinEntryDialog.this.syncOkButtonState();
            }

            public void afterTextChanged(Editable arg0) {
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        };
    }

    public static Intent getIntent(Context context, int titleId, int promptId, String matchPin, Bundle extraParams) {
        Intent intent = new Intent(context, PinEntryDialog.class);
        intent.putExtra("PinEntryDialog-title-string-id", titleId);
        intent.putExtra("PinEntryDialog-prompt-string-id", promptId);
        intent.putExtra("PinEntryDialog-pin-to-match", matchPin);
        intent.putExtra("PinEntryDialog-extra-params", extraParams);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin_entry_dialog);
        Intent intent = getIntent();
        int titleId = intent.getIntExtra("PinEntryDialog-title-string-id", -1);
        int promptId = intent.getIntExtra("PinEntryDialog-prompt-string-id", -1);
        this.mMatchPin = intent.getStringExtra("PinEntryDialog-pin-to-match");
        TextView promptView = (TextView) findViewById(R.id.prompt);
        this.mPinEntryView = (EditText) findViewById(R.id.passcode);
        this.mButtonBar = (ButtonBar) findViewById(R.id.button_bar);
        setTitle(titleId);
        promptView.setText(promptId);
        this.mButtonBar.setPositiveButtonTitle((int) R.string.content_filter_ok);
        this.mButtonBar.setNegativeButtonTitle((int) R.string.content_filter_cancel);
        this.mButtonBar.setClickListener(this);
        this.mPinEntryView.addTextChangedListener(this.mPinWatcher);
        this.mEventLogger = FinskyApp.get().getEventLogger();
        if (savedInstanceState == null) {
            this.mEventLogger.logPathImpression(0, this.mNode);
        }
    }

    public void onPositiveButtonClick() {
        this.mEventLogger.logClickEvent(258, null, this.mNode);
        String userPin = getUserPin();
        if (this.mMatchPin == null || this.mMatchPin.equals(userPin)) {
            this.mEventLogger.logOperationSuccessBackgroundEvent(501, true);
            setPinResult(userPin);
            finish();
            return;
        }
        this.mEventLogger.logOperationSuccessBackgroundEvent(501, false);
        this.mPinEntryView.setText("");
        UiUtils.setErrorOnTextView(this.mPinEntryView, getString(R.string.pin_label), getString(R.string.pin_entry_hint_wrong_passcode));
    }

    public void onNegativeButtonClick() {
        this.mEventLogger.logClickEvent(259, null, this.mNode);
        setResult(0);
        finish();
    }

    public void onResume() {
        super.onResume();
        syncOkButtonState();
    }

    private void setPinResult(String resultPin) {
        Intent result = new Intent();
        result.putExtra("PinEntryDialog-result-pin", resultPin);
        result.putExtra("PinEntryDialog-extra-params", getIntent().getBundleExtra("PinEntryDialog-extra-params"));
        setResult(-1, result);
    }

    private String getUserPin() {
        return this.mPinEntryView.getText().toString().trim();
    }

    private void syncOkButtonState() {
        this.mButtonBar.setPositiveButtonEnabled(getUserPin().length() >= 4);
    }
}
