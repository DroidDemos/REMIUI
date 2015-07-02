package com.google.android.finsky.billing.carrierbilling.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.android.vending.R;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.fragments.LoggingDialogFragment;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;
import com.google.android.finsky.utils.FinskyLog;

public class CarrierTosDialogFragment extends LoggingDialogFragment implements ClickListener {
    private ButtonBar mButtonBar;
    private CarrierTosResultListener mListener;
    private String mTosUrl;
    private CarrierTosWebViewClient mTosWebViewclient;

    public interface CarrierTosResultListener {

        public enum TosResult {
            SUCCESS,
            FAILURE,
            CANCELED
        }

        void onCarrierTosResult(TosResult tosResult);
    }

    private class CarrierTosWebViewClient extends WebViewClient {
        private final View mProgress;
        private boolean mReceivedError;
        private final View mTosDisplayView;

        public CarrierTosWebViewClient(View progress, View tosDisplayView) {
            this.mProgress = progress;
            this.mTosDisplayView = tosDisplayView;
            this.mReceivedError = false;
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            FinskyLog.w("Web error: (" + failingUrl + ") " + description, new Object[0]);
            this.mReceivedError = true;
            CarrierTosDialogFragment.this.mListener.onCarrierTosResult(TosResult.FAILURE);
        }

        public void onPageFinished(WebView view, String url) {
            view.setVisibility(0);
            this.mTosDisplayView.setVisibility(0);
            if (!this.mReceivedError) {
                CarrierTosDialogFragment.this.mButtonBar.setPositiveButtonEnabled(true);
            }
            this.mProgress.setVisibility(8);
        }
    }

    public static CarrierTosDialogFragment newInstance(String accountName, String tosUrl, String carrierName) {
        CarrierTosDialogFragment fragment = new CarrierTosDialogFragment();
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putString("carrier_tos_url", tosUrl);
        args.putString("carrier_name", carrierName);
        fragment.setArguments(args);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.carrier_tos, null);
        Bundle args = getArguments();
        setUpTos(args.getString("carrier_tos_url"));
        this.mButtonBar = (ButtonBar) v.findViewById(R.id.button_bar);
        this.mButtonBar.setClickListener(this);
        this.mButtonBar.setPositiveButtonTitle((int) R.string.accept);
        this.mButtonBar.setNegativeButtonTitle((int) R.string.decline);
        this.mButtonBar.setPositiveButtonEnabled(false);
        WebView webView = (WebView) v.findViewById(R.id.carrier_tos_text);
        this.mTosWebViewclient = getCarrierTosWebViewClient(v.findViewById(R.id.progress), v.findViewById(R.id.carrier_tos_and_address_main));
        webView.setWebViewClient(this.mTosWebViewclient);
        webView.loadUrl(this.mTosUrl);
        webView.getSettings().setSupportZoom(false);
        String carrierName = args.getString("carrier_name");
        AlertDialog dialog = new Builder(getActivity()).setTitle(getString(R.string.carrier_tos_and_address_title, carrierName)).create();
        dialog.setView(v, 0, 5, 0, 0);
        return dialog;
    }

    protected int getPlayStoreUiElementType() {
        return 823;
    }

    private void setUpTos(String tosUrl) {
        String localeReplacement = getString(R.string.tos_locale_replacement);
        if (!TextUtils.isEmpty(localeReplacement)) {
            tosUrl = tosUrl.replace("%locale%", localeReplacement);
        }
        this.mTosUrl = BillingUtils.replaceLocale(tosUrl);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        this.mListener.onCarrierTosResult(TosResult.CANCELED);
    }

    public void onPositiveButtonClick() {
        logClickEvent(824);
        this.mListener.onCarrierTosResult(TosResult.SUCCESS);
    }

    public void onNegativeButtonClick() {
        logClickEvent(825);
        this.mListener.onCarrierTosResult(TosResult.CANCELED);
    }

    public void setOnResultListener(CarrierTosResultListener listener) {
        this.mListener = listener;
    }

    CarrierTosWebViewClient getCarrierTosWebViewClient(View progress, View tosDisplayView) {
        return new CarrierTosWebViewClient(progress, tosDisplayView);
    }
}
