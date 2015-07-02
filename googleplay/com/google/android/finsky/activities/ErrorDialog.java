package com.google.android.finsky.activities;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.utils.FinskyLog;

public class ErrorDialog extends DialogFragment {
    private boolean mIsRemoved;

    public ErrorDialog() {
        this.mIsRemoved = false;
        setCancelable(true);
    }

    public static ErrorDialog show(FragmentManager fragmentManager, String title, String htmlMessage, boolean goBack) {
        fragmentManager.executePendingTransactions();
        ErrorDialog previousErrorDialog = (ErrorDialog) fragmentManager.findFragmentByTag("error_dialog");
        if (previousErrorDialog != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            try {
                previousErrorDialog.mIsRemoved = true;
                ft.remove(previousErrorDialog).addToBackStack(null).commit();
            } catch (IllegalStateException e) {
                FinskyLog.w("Double remove of error dialog fragment: " + previousErrorDialog, new Object[0]);
            }
        }
        ErrorDialog dialog = newInstance(title, htmlMessage, goBack);
        dialog.show(fragmentManager, "error_dialog");
        return dialog;
    }

    private static ErrorDialog newInstance(String title, String htmlMessage, boolean goBack) {
        Bundle args = new Bundle();
        if (title == null) {
            title = FinskyApp.get().getString(R.string.error);
        }
        args.putString("title", title);
        args.putString("html_message", htmlMessage);
        args.putBoolean("go_back", goBack);
        ErrorDialog errorDialog = new ErrorDialog();
        errorDialog.setArguments(args);
        return errorDialog;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog alertDialog = new Builder(getActivity()).setTitle(getArguments().getString("title")).setPositiveButton(17039370, null).setCancelable(true).create();
        View messageView = alertDialog.getLayoutInflater().inflate(R.layout.error_dialog_message, null);
        TextView messageTextView = (TextView) messageView.findViewById(R.id.error_dialog_message);
        messageTextView.setText(Html.fromHtml(getArguments().getString("html_message")));
        messageTextView.setMovementMethod(LinkMovementMethod.getInstance());
        alertDialog.setView(messageView);
        return alertDialog;
    }

    public void onDismiss(DialogInterface dialog) {
        if (!(this.mIsRemoved || getActivity() == null || !getArguments().getBoolean("go_back"))) {
            if (getActivity() instanceof PageFragmentHost) {
                ((PageFragmentHost) getActivity()).goBack();
            } else {
                FinskyLog.wtf("Dialog not hosted by PageFragmentHost. Cannot navigate back.", new Object[0]);
            }
        }
        super.onDismiss(dialog);
    }
}
