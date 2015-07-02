package com.google.android.finsky.billing.genericinstrument;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.fragments.LoggingFragment;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.layout.PlayActionButton;

public class GenericInstrumentErrorFragment extends LoggingFragment implements OnClickListener {
    private PlayActionButton mButton;
    private TextView mErrorDescription;

    public static Fragment newInstance(String accountName, String errorHtml, boolean isTerminalError) {
        GenericInstrumentErrorFragment fragment = new GenericInstrumentErrorFragment();
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putString("GenericInstrumentErrorFragment.error_html", errorHtml);
        args.putBoolean("GenericInstrumentErrorFragment.is_terminal_error", isTerminalError);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mainView = (ViewGroup) inflater.inflate(R.layout.billing_generic_instrument_error, container, false);
        Bundle arguments = getArguments();
        String errorHtml = arguments.getString("GenericInstrumentErrorFragment.error_html");
        if (TextUtils.isEmpty(errorHtml)) {
            FinskyLog.wtf("Missing expected error message.", new Object[0]);
            errorHtml = getString(R.string.generic_instrument_unknown_error);
        }
        this.mErrorDescription = (TextView) mainView.findViewById(R.id.description);
        this.mErrorDescription.setText(Html.fromHtml(errorHtml));
        this.mErrorDescription.setMovementMethod(LinkMovementMethod.getInstance());
        int buttonTextResourceId = arguments.getBoolean("GenericInstrumentErrorFragment.is_terminal_error") ? R.string.ok : R.string.try_again;
        this.mButton = (PlayActionButton) mainView.findViewById(R.id.positive_button);
        this.mButton.configure(3, buttonTextResourceId, (OnClickListener) this);
        return mainView;
    }

    public void onResume() {
        super.onResume();
        UiUtils.sendAccessibilityEventWithText(getActivity(), this.mErrorDescription.getText().toString(), getView());
    }

    public void onClick(View v) {
        if (v == this.mButton) {
            logClickEvent(1502);
            GenericInstrumentHostFragment hostFragment = (GenericInstrumentHostFragment) getParentFragment();
            if (getArguments().getBoolean("GenericInstrumentErrorFragment.is_terminal_error")) {
                hostFragment.acknowledgeTerminalError();
            } else {
                hostFragment.retryAfterError();
            }
        }
    }

    protected int getPlayStoreUiElementType() {
        return 1501;
    }
}
