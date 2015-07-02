package com.google.android.finsky.billing;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.android.vending.R;

public class ProgressDialogFragment extends DialogFragment {
    public static ProgressDialogFragment newInstance(int messageId) {
        ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        Bundle args = new Bundle();
        args.putInt("message_id", messageId);
        progressDialogFragment.setArguments(args);
        return progressDialogFragment;
    }

    public static ProgressDialogFragment newInstance(String message) {
        ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        Bundle args = new Bundle();
        if (message != null) {
            args.putString("message", message);
        }
        progressDialogFragment.setArguments(args);
        return progressDialogFragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(false);
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setProgressStyle(0);
        int messageId = getArguments().getInt("message_id", R.string.loading);
        if (getArguments().containsKey("message")) {
            dialog.setMessage(getArguments().getString("message"));
        } else if (messageId != 0) {
            dialog.setMessage(getResources().getString(messageId));
        }
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        return dialog;
    }
}
