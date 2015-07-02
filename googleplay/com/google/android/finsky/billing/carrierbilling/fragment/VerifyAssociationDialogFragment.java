package com.google.android.finsky.billing.carrierbilling.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import com.android.vending.R;
import com.google.android.finsky.fragments.LoggingDialogFragment;

public class VerifyAssociationDialogFragment extends LoggingDialogFragment implements OnClickListener {
    private VerifyAssociationListener mListener;

    public interface VerifyAssociationListener {
        void onVerifyCancel();
    }

    public static VerifyAssociationDialogFragment newInstance(String accountName) {
        VerifyAssociationDialogFragment dialog = new VerifyAssociationDialogFragment();
        dialog.setCancelable(false);
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        dialog.setArguments(args);
        return dialog;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setTitle(getResources().getString(R.string.verify_pin_title));
        dialog.setMessage(getResources().getString(R.string.associating_device_message));
        dialog.setProgressStyle(0);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setButton(-1, getResources().getString(R.string.device_association_back_button), this);
        return dialog;
    }

    public void setOnResultListener(VerifyAssociationListener listener) {
        this.mListener = listener;
    }

    public void onClick(DialogInterface dialog, int which) {
        if (this.mListener != null) {
            this.mListener.onVerifyCancel();
        }
    }

    protected int getPlayStoreUiElementType() {
        return 842;
    }
}
