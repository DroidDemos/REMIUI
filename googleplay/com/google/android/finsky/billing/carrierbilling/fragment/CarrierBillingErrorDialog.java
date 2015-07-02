package com.google.android.finsky.billing.carrierbilling.fragment;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;

public class CarrierBillingErrorDialog extends DialogFragment implements OnClickListener {
    CarrierBillingErrorListener mListener;

    public interface CarrierBillingErrorListener {
        void onErrorDismiss(boolean z);
    }

    public static CarrierBillingErrorDialog newInstance(String message, boolean errorFatal) {
        CarrierBillingErrorDialog dialog = new CarrierBillingErrorDialog();
        dialog.setCancelable(false);
        Bundle args = new Bundle();
        args.putString("error_message", message);
        args.putBoolean("fatal_error", errorFatal);
        dialog.setArguments(args);
        return dialog;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder b = new Builder(getActivity());
        b.setTitle(FinskyApp.get().getString(R.string.error));
        b.setMessage(getArguments().getString("error_message"));
        b.setPositiveButton(17039370, this).setCancelable(false);
        return b.create();
    }

    public void setOnResultListener(CarrierBillingErrorListener listener) {
        this.mListener = listener;
    }

    public void onClick(DialogInterface dialog, int whichButton) {
        if (this.mListener != null) {
            this.mListener.onErrorDismiss(getArguments().getBoolean("fatal_error"));
        }
    }
}
