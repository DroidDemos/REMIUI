package com.google.android.finsky.billing.carrierbilling.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.vending.R;
import com.google.android.finsky.fragments.LoggingDialogFragment;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;

public class CarrierBillingPasswordDialogFragment extends LoggingDialogFragment implements ClickListener {
    private CarrierBillingPasswordResultListener mListener;
    private View mMainPasswordView;
    private EditText mPasswordEditText;
    private View mProgressIndicator;

    public interface CarrierBillingPasswordResultListener {

        public enum PasswordResult {
            SUCCESS,
            FAILURE,
            CANCELED
        }

        void onCarrierBillingPasswordResult(PasswordResult passwordResult, String str);
    }

    public static CarrierBillingPasswordDialogFragment newInstance(String accountName, String passwordPrompt, String passwordForgotUrl) {
        CarrierBillingPasswordDialogFragment fragment = new CarrierBillingPasswordDialogFragment();
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putString("password_prompt", passwordPrompt);
        args.putString("password_forgot_url", passwordForgotUrl);
        fragment.setArguments(args);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View mainView = View.inflate(getActivity(), R.layout.carrier_password, null);
        Bundle args = getArguments();
        String passwordPrompt = args.getString("password_prompt");
        String passwordForgotUrl = args.getString("password_forgot_url");
        this.mMainPasswordView = mainView.findViewById(R.id.carrier_password_main);
        this.mProgressIndicator = mainView.findViewById(R.id.loading_indicator);
        hideProgressIndicator();
        TextView passwordPromptTextView = (TextView) mainView.findViewById(R.id.password_prompt);
        TextView passwordForgotTextView = (TextView) mainView.findViewById(R.id.password_forgot);
        this.mPasswordEditText = (EditText) mainView.findViewById(R.id.password_edit);
        ButtonBar buttonBar = (ButtonBar) mainView.findViewById(R.id.button_bar);
        buttonBar.setClickListener(this);
        buttonBar.setPositiveButtonTitle((int) R.string.buy_no_price);
        buttonBar.setPadding(2, 4, 2, buttonBar.getPaddingBottom());
        passwordPromptTextView.setText(passwordPrompt);
        if (TextUtils.isEmpty(passwordForgotUrl)) {
            passwordForgotTextView.setVisibility(8);
        } else {
            passwordForgotTextView.setText(Html.fromHtml(createPasswordForgotHtml(passwordForgotUrl)));
            passwordForgotTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        AlertDialog dialog = new Builder(getActivity()).setTitle(R.string.enter_your_password_title).create();
        dialog.setView(mainView, 0, 5, 0, 0);
        return dialog;
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        this.mListener.onCarrierBillingPasswordResult(PasswordResult.CANCELED, null);
    }

    private String createPasswordForgotHtml(String str) {
        return "<a href=\"" + str + "\">" + getString(R.string.forgot_your_password) + "</a>";
    }

    public void clearPasswordField() {
        this.mPasswordEditText.setText("");
    }

    public void showProgressIndicator() {
        this.mMainPasswordView.setVisibility(4);
        this.mProgressIndicator.setVisibility(0);
    }

    public void hideProgressIndicator() {
        this.mMainPasswordView.setVisibility(0);
        this.mProgressIndicator.setVisibility(8);
    }

    protected int getPlayStoreUiElementType() {
        return 820;
    }

    public void onPositiveButtonClick() {
        logClickEvent(821);
        String enteredPassword = this.mPasswordEditText.getText().toString();
        if (TextUtils.isEmpty(enteredPassword)) {
            Toast.makeText(getActivity(), R.string.enter_a_password, 0).show();
        } else {
            this.mListener.onCarrierBillingPasswordResult(PasswordResult.SUCCESS, enteredPassword);
        }
    }

    public void onNegativeButtonClick() {
        logClickEvent(822);
        this.mListener.onCarrierBillingPasswordResult(PasswordResult.CANCELED, null);
    }

    public void setOnResultListener(CarrierBillingPasswordResultListener listener) {
        this.mListener = listener;
    }
}
